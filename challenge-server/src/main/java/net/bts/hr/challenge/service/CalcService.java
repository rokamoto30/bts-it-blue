package net.bts.hr.challenge.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.hibernate.internal.build.AllowSysOut;
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
	static final Map<String, Integer> degrees = new HashMap<>();
	static Map<String, Double> speedMap = new HashMap<>();
	
	@Autowired
	SpeedRepo speedRepo;
	@Autowired
	WalkStepRepo stepRepo;
	
	@PostConstruct
	private void init() {
		degrees.put("E", 0);
		degrees.put("NE", 45);
		degrees.put("N", 90);
		degrees.put("NW", 135);
		degrees.put("W", 180);
		degrees.put("SW", 225);
		degrees.put("S", 270);
		degrees.put("SE", 315);
		
		updateSpeedMap();
	}
	
	public void updateSpeedMap() {
		List<Speed> speeds = speedRepo.getSpeeds();
		for (Speed speed : speeds) {
			speedMap.put(speed.getSpeedType(), speed.getSpeedValue());
		}
	}
	
	public  Cord speedToDistance(String direction, Double speed, Integer hours, Integer mins) {
		int totalMins = HOUR_TO_MIN * hours + mins;
		
		Double speedMins = speed / HOUR_TO_MIN;
		
		Double miles = totalMins * speedMins;
		
		Cord cord = new Cord();
		cord.setX(miles * Math.cos(degrees.get(direction)));
		cord.setY(miles * Math.sin(degrees.get(direction)));
		
		return cord;
	}
	
	public  Cord calculateDistance(List<WalkedSteps> steps) {
		Cord finalDirection = new Cord();
		for (WalkedSteps step : steps) {
			String direciton = step.getSpeedDirection();
			Double speed = speedMap.get(step.getSpeedType());
			Integer hours = step.getDurationHours();
			Integer mins = step.getDurationMinutes();
			finalDirection.add(speedToDistance(direciton, speed, hours,mins));
			System.out.println(finalDirection);
		}
		return finalDirection;
	}
	
	public String printTreasureLocation() {
		updateSpeedMap();

		Cord cord = calculateDistance(stepRepo.getSteps());
		
		StringBuilder treasureLocation = new StringBuilder();
		treasureLocation.append("Treasure At: ");
		
		if(cord.getY() != 0) {
			treasureLocation.append( Math.abs( cord.getY() ) + "Miles ");
			if(cord.getY() < 0) {
				treasureLocation.append("South");
			} else {
				treasureLocation.append("North");
			}
		}
		
		if (cord.getX() != 0 && cord.getY() != 0) {
			treasureLocation.append(", " );
		}
		
		
		if(cord.getX() != 0) {
			treasureLocation.append( Math.abs( cord.getX() ) + "Miles ");
			if(cord.getX() < 0) {
				treasureLocation.append("West");
			} else {
				treasureLocation.append("East");
			}
		}
		
		return treasureLocation.toString();
	}
}
