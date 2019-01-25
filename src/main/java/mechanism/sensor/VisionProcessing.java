package mechanism.sensor;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;

public class VisionProcessing {
	private int IMG_WIDTH = 320;
	private Thread processingThread;
	private double centerX;
	private final Object centerLock = new Object();
    private double processingRunning = 0.0;
    private NetworkTableEntry centerXEntry;
    private NetworkTableEntry processingRunningEntry;
	private boolean processingCargo = true;
	private Rect r1;
	private Rect r2;


    
    public VisionProcessing(CameraStream stream, Joystick j) {    	
        this.IMG_WIDTH = stream.getWidth();
        centerXEntry = Shuffleboard.getTab("Vision").add("Center X", 0).getEntry();	
        processingRunningEntry = Shuffleboard.getTab("Vision").add("Processing Running", 0).getEntry();	
	    processingThread = new Thread(() -> {
			final CargoPipeline cPipeline = new CargoPipeline();
			final RetroreflectorPipeline rPipeline = new RetroreflectorPipeline();
			Mat mat;
	    	while(!Thread.interrupted()) {
				if(j.getRawButton(1))
					processingCargo = false;
				if(j.getRawButton((2)))
					processingCargo = true;
				mat = stream.getMat();
	    		if(mat.height() <= 0) {
	    			continue;
				}
				if(processingCargo){
					cPipeline.process(mat);
					synchronized(centerLock){
						centerX = cPipeline.findMinAndMaxMaxLoc().x;
					}	
				}
				else{
					rPipeline.process(mat);
					r1 = Imgproc.boundingRect(rPipeline.filterContoursOutput().get(0));
					r2 = Imgproc.boundingRect(rPipeline.filterContoursOutput().get(1));
					synchronized(centerLock){
						centerX = ((r1.x + r1.width/2) + (r2.x + r2.width/2))/2;
					}	
				}
                centerXEntry.setNumber(centerX);
                processingRunning++;
                processingRunningEntry.setNumber(processingRunning);
	            try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    	}
	    });
    	processingThread.setDaemon(true);
	    processingThread.start();
    }
    
    public double getCenterX() {
    	double centerX;
    	synchronized(centerLock) {
    		centerX = this.centerX;
    	}
    	return centerX;
    }
    
    public double getError() {
    	return (getCenterX() * 2 - IMG_WIDTH)/IMG_WIDTH;
    }

}
