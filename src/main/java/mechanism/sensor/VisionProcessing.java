package mechanism.sensor;

import org.opencv.core.Mat;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class VisionProcessing {
	private int IMG_WIDTH = 320;
	private Thread processingThread;
	private double centerX;
	private final Object centerLock = new Object();
    private double processingRunning = 0.0;
    private NetworkTableEntry centerXEntry;
    private NetworkTableEntry processingRunningEntry;


    
    public VisionProcessing(CameraStream stream, Joystick j) {    	
        this.IMG_WIDTH = stream.getWidth();
        centerXEntry = Shuffleboard.getTab("Vision").add("Center X", 0).getEntry();	
        processingRunningEntry = Shuffleboard.getTab("Vision").add("Processing Running", 0).getEntry();	
	    processingThread = new Thread(() -> {
			final GripPipeline pipeline = new GripPipeline();
			Mat mat;
	    	while(!Thread.interrupted()) {
                mat = stream.getMat();
	    		if(mat.height() <= 0) {
	    			continue;
	    		}
                pipeline.process(mat);
                synchronized(centerLock){
                    centerX = pipeline.findMinAndMaxMaxLoc().x;
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
