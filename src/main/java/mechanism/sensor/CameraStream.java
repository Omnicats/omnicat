package mechanism.sensor;

import org.opencv.core.Mat;
import org.usfirst.frc.team1452.robot.OI;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class CameraStream {
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	private static final int FPS = 30;
	private Thread visionThread;
	private Mat mat = new Mat();
    private final Object imgLock = new Object();
    private NetworkTableEntry cameraRunningEntry;

    
    public CameraStream() {    
        cameraRunningEntry = Shuffleboard.getTab("Vision").add("Camera Running", 0).getEntry();	
    	visionThread = new Thread(() -> {
			UsbCamera mainCamera = CameraServer.getInstance().startAutomaticCapture(0);
			mainCamera.setResolution(IMG_WIDTH, IMG_HEIGHT);
        	mainCamera.setFPS(FPS);
			UsbCamera cargoCamera = CameraServer.getInstance().startAutomaticCapture(1);
        	cargoCamera.setResolution(IMG_WIDTH, IMG_HEIGHT);
        	cargoCamera.setFPS(FPS);
        	
        	CvSink sink = new CvSink("cam0");
        	sink.setSource(mainCamera);
        	sink.setEnabled(true);
        	        	
        	VideoSink server; //is this necessary?
        	server = CameraServer.getInstance().getServer();
        	server.setSource(mainCamera);
			int cameraRunning = 0;
			
			while (!Thread.interrupted()) {
				if(OI.acquiringCargo && !sink.getSource().equals(cargoCamera)){
					sink.setSource(cargoCamera);
				}
				else if(!OI.acquiringCargo && !sink.getSource().equals(mainCamera)){
					sink.setSource(mainCamera);
				}

				synchronized(imgLock) {
					if (sink.grabFrame(mat) == 0) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						continue;
					}
				}
                cameraRunning++;
                cameraRunningEntry.setNumber(cameraRunning);
            }
            sink.close();
		});
    	visionThread.setDaemon(true);
        visionThread.start();
    }

    public Mat getMat() {
    	Mat mat;
    	synchronized(imgLock) {
    		mat = this.mat;
    	}
    	return mat;
    }

    public int getWidth(){
        return IMG_WIDTH;
    }

    public Object getImgLock(){
        return imgLock;
    }
}
