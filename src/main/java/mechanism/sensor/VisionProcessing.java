package mechanism.sensor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1452.robot.OI;

public class VisionProcessing {
	private int IMG_WIDTH = 320;
	private int IMG_HEIGHT = 240;
	private double INCHES_PER_PIXEL = 5.39717889014	/ IMG_HEIGHT;
	private Thread processingThread;
	private double centerX;
	private double centerHeight;
	private final Object centerLock = new Object();
    private double processingRunning = 0.0;
	private NetworkTableEntry centerXEntry;
	private NetworkTableEntry centerHeightEntry;
    private NetworkTableEntry processingRunningEntry;

    
    public VisionProcessing(CameraStream stream) {    	
        this.IMG_WIDTH = stream.getWidth();
		centerXEntry = Shuffleboard.getTab("Vision").add("Center X", 0).getEntry();	
		centerHeightEntry = Shuffleboard.getTab("Vision").add("Center Height", 0).getEntry();	
        processingRunningEntry = Shuffleboard.getTab("Vision").add("Processing Running", 0).getEntry();	
	    processingThread = new Thread(() -> {
			final CargoPipeline cPipeline = new CargoPipeline();
			final RetroreflectorPipeline rPipeline = new RetroreflectorPipeline();
			Mat mat;
	    	while(!Thread.interrupted()) {
				mat = stream.getMat();
	    		if(mat.height() <= 0) {
	    			continue;
				}
				if(!OI.hatchSwitch.get()){
					cPipeline.process(mat);
					synchronized(centerLock){
						centerX = cPipeline.findMinAndMaxMaxLoc().x;
					}	
				}
				else{
					rPipeline.process(mat);
					//TODO test, potentially add mask threshold thingy 
					//https://stackoverflow.com/questions/34237253/detect-centre-and-angle-of-rectangles-in-an-image-using-opencv
					PriorityQueue<RotatedRect> leftTargets = new PriorityQueue<>(5, new RotatedRectComparator());
					PriorityQueue<RotatedRect> rightTargets = new PriorityQueue<>(5, new RotatedRectComparator());
					
					for(int i = 0; i < rPipeline.filterContoursOutput().size(); i++){
						MatOfPoint2f contour = new MatOfPoint2f(rPipeline.filterContoursOutput().get(i));
						RotatedRect rotatedRect = Imgproc.minAreaRect(contour);
						if((rotatedRect.angle + 360)%90 < 45){
							leftTargets.add(rotatedRect);
						}else{
							rightTargets.add(rotatedRect);
						}
					}

					/*r1 = Imgproc.boundingRect(rPipeline.filterContoursOutput().get(0));
					r2 = Imgproc.boundingRect(rPipeline.filterContoursOutput().get(1));
					synchronized(centerLock){
						centerX = ((r1.x + r1.width/2) + (r2.x + r2.width/2))/2;
					}*/

					Queue<Double> centers = new LinkedList<>();
					Queue<Double> heights = new LinkedList<>();

					while(leftTargets.size() > 0 && rightTargets.size() > 0){
						if(rightTargets.peek().center.x < leftTargets.peek().center.x){
							rightTargets.remove();
							continue;
						}
						centers.add((leftTargets.peek().center.x + rightTargets.peek().center.x)/2);
						heights.add((Math.max(leftTargets.peek().size.height, leftTargets.poll().size.width) + Math.max(rightTargets.peek().size.height, rightTargets.poll().size.width))/2);
					}
					
					if(centers.size()==0){
						synchronized(centerLock){
							centerX = IMG_WIDTH/2;
							centerHeight = 0;
						}
					}else{
						double distanceFromCenter = centers.poll() - (IMG_WIDTH/2);
						double heightFromCenter = heights.poll();
						while(centers.size() > 0){
							if(Math.abs(centers.poll() - (IMG_WIDTH/2)) < Math.abs(distanceFromCenter)){
								distanceFromCenter = centers.poll() - (IMG_WIDTH/2);
								heightFromCenter = heights.poll();
							}
							else{
								heights.poll();
							}
						}
						synchronized(centerLock){
							centerX = IMG_WIDTH/2 + distanceFromCenter;
							centerHeight = heightFromCenter;
						}
					}

				}
				centerXEntry.setNumber(centerX);
				centerHeightEntry.setNumber(centerHeight);
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
	
	public double getCenterHeight(){
		double centerHeight;
		synchronized(centerLock) {
    		centerHeight = this.centerHeight;
		}
		return centerHeight;
	}
    
    public double getTurnError() {
    	return (getCenterX() * 2 - IMG_WIDTH)/IMG_WIDTH;
	}
	
	public double getDistanceErrorInches(){
		return getCenterHeight() * INCHES_PER_PIXEL;
	}

}

class RotatedRectComparator implements Comparator<RotatedRect>{
	public int compare(RotatedRect r1, RotatedRect r2){
		if(r1.center.x > r2.center.x){
			return 1;
		}else if(r2.center.x < r1.center.x){
			return -1;
		}
		return 0;
	}
} 
