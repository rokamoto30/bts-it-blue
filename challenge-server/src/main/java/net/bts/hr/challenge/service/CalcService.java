package net.bts.hr.challenge.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bts.hr.challenge.model.Speed;
import net.bts.hr.challenge.model.WalkedSteps;
import net.bts.hr.challenge.pojos.Cord;
import net.bts.hr.challenge.repo.SpeedRepo;
import net.bts.hr.challenge.repo.WalkStepRepo;

@Service
public class CalcService {	
	static final int HOUR_TO_MIN = 60; // static variables: use more memory but maybe faster than defining local variables every time?
	static final double PRECISION = .000001; // calculation precision
	static final int PRINT_ROUND = 100; // round the printed answer
	static final Map<String, Integer> DEGREES = new HashMap<>(); // map direction to degrees
	static Map<String, Double> speedMap = new HashMap<>(); // map speed type to speed
	
	@Autowired
	SpeedRepo speedRepo;
	@Autowired
	WalkStepRepo stepRepo;
	
	@PostConstruct
	private void init() {
		DEGREES.put("E", 0);
		DEGREES.put("NE", 45);
		DEGREES.put("N", 90);
		DEGREES.put("NW", 135);
		DEGREES.put("W", 180);
		DEGREES.put("SW", 225);
		DEGREES.put("S", 270);
		DEGREES.put("SE", 315);
		
		updateSpeedMap();
	}

	public List<Cord> getVectors() {
		updateSpeedMap();
		List<WalkedSteps> steps = stepRepo.getSteps();
		List<Cord> cords = new ArrayList<Cord>();
		for (WalkedSteps step : steps) {
			String direciton = step.getSpeedDirection();
			Double speed = speedMap.get(step.getSpeedType());
			Integer hours = step.getDurationHours();
			Integer mins = step.getDurationMinutes();
			cords.add(speedToDistance(direciton, speed, hours,mins)); 
		}
		return cords;
	}
	
	public void updateSpeedMap() {
		List<Speed> speeds = speedRepo.getSpeeds();
		for (Speed speed : speeds) {
			speedMap.put(speed.getSpeedType(), speed.getSpeedValue());
		}
	}
	
	public Double round(Double num, int places) {
		// use absolute value of num since +/- are floored differently
		// negatives are rounded "up"
		Double rounded = Math.floor(Math.abs(num) * places) / places;
		if (num < 0) { // add the sign back
			rounded = rounded * -1;
		}
		
		return rounded;
	}
	
	public Double roundDown(Double num, double percision) {
		if (Math.abs(num) < percision) { // if the magnitude of num is less then precision, return 0
			return 0.0;
		} return num;
	}
	
	public Cord speedToDistance(String direction, Double speed, Integer hours, Integer mins) {
		int totalMins = HOUR_TO_MIN * hours + mins; // total minutes
		
		Double speedMins = speed / HOUR_TO_MIN; // speed in miles per minute
		
		Double miles = totalMins * speedMins; // total distance
		
		Cord cord = new Cord();
		Double radians = Math.toRadians(DEGREES.get(direction)); // convert degrees to radians
		Double xPortion = roundDown(Math.cos(radians), PRECISION); // eliminate floating point arithmetic error by rounding numbers close to 0
		Double yPortion = roundDown(Math.sin(radians), PRECISION);
		
		cord.setX(miles * xPortion); // set cords
		cord.setY(miles * yPortion);
		
		return cord;
	}
	
	public  Cord calculateDistance(List<WalkedSteps> steps) {
		Cord finalDirection = new Cord();
		for (WalkedSteps step : steps) { // for each direction
			String direciton = step.getSpeedDirection();
			Double speed = speedMap.get(step.getSpeedType());
			Integer hours = step.getDurationHours();
			Integer mins = step.getDurationMinutes();
			finalDirection.add(speedToDistance(direciton, speed, hours,mins)); // add distance
		}
		return finalDirection;
	}
	
	
	public String printTreasureLocation() {
		updateSpeedMap();

		Cord cord = calculateDistance(stepRepo.getSteps());
		
		Double xDist = round(cord.getX(), PRINT_ROUND);
		Double yDist = round(cord.getY(), PRINT_ROUND);
		cord.setX( round(cord.getX(), PRINT_ROUND) ); // round output string
		cord.setY( round(cord.getY(), PRINT_ROUND) );
		
		StringBuilder treasureLocation = new StringBuilder();
		treasureLocation.append("Treasure At: ");
		
		if (xDist == 0 && yDist == 0) {
			treasureLocation.append("current location"); // both x and y cords are 0, treasure is at current location
			return treasureLocation.toString();
		}
		
		if(yDist != 0) {
			treasureLocation.append( Math.abs( yDist ) + " Miles ");
			if(yDist < 0) {
				treasureLocation.append("South");
			} else {
				treasureLocation.append("North");
			}
		}
		
		if (xDist != 0 && yDist != 0) {
			treasureLocation.append(", " );
		} 
		
		if(xDist != 0) {
			treasureLocation.append( Math.abs( xDist ) + " Miles ");
			if(xDist < 0) {
				treasureLocation.append("West");
			} else {
				treasureLocation.append("East");
			}
		}
		
		return treasureLocation.toString();
	}
	
	
}
