package mechanism.sensor;

import org.opencv.core.Mat;

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
        	UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(0);
        	camera.setResolution(IMG_WIDTH, IMG_HEIGHT);
        	camera.setFPS(FPS);
        	
        	CvSink sink = new CvSink("cam0");
        	sink.setSource(camera);
        	sink.setEnabled(true);
        	        	
        	VideoSink server;
        	server = CameraServer.getInstance().getServer();
        	server.setSource(camera);
        	int cameraRunning = 0;
			
			while (!Thread.interrupted()) {
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
