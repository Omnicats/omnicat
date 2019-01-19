package mechanism.sensor;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Threads;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class VisionProcessing {
	private static final int IMG_WIDTH = 320;
	private static final int IMG_HEIGHT = 240;
	private static final int FPS = 30;
	private Thread processingThread;
	private double centerX;
    private Mat mat = new Mat();
    private Rect r = new Rect();
	private final Object centerLock = new Object();
	private final Object imgLock = new Object();
	private double processingFinding = 0.0;
    private double processingRunning = 0.0;
    private CameraStream stream;
    private Joystick j;
    private NetworkTableEntry centerXEntry;
    private NetworkTableEntry processingRunningEntry;
    private NetworkTableEntry processingFindingEntry;


    
    public VisionProcessing(CameraStream stream, Joystick j) {    	
        /*this.stream = stream;
    	this.j = j;
	    processingThread = new Thread(() -> {
			final GripPipeline pipeline = new GripPipeline();
			Mat mat;
	    	while(!Thread.interrupted()) {
	    		mat = stream.getMat();
	    		if(mat.height() <= 0) {
	    			continue;
	    		}
		    	pipeline.process(mat);
		        if (pipeline.filterContoursOutput().size() > 0) {
		        	r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
                    processingFinding++;
                    processingFindingEntry.setNumber(processingFinding);
		            synchronized (centerLock) {
		            	centerX = (r.x + (r.width / 2));
		            }
		        }else{
		        	centerX = 0;
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
	    processingThread.start();*/
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
