package net.bts.hr.challenge.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.bts.hr.challenge.exception.InvalidException;
import net.bts.hr.challenge.model.Speed;
import net.bts.hr.challenge.model.WalkedSteps;
import net.bts.hr.challenge.repo.SpeedRepo;
import net.bts.hr.challenge.repo.WalkStepRepo;

@Component
public class ChallengeService {

	@Autowired
	SpeedRepo speedRepo;

	@Autowired
	WalkStepRepo stepRepo;
	
	static final private List<String> directions = Arrays.asList("N", "NE", "E", "SE", "S", "SW", "W", "NW"); //validate direction

	public List<Speed> getSpeedList() {
		List<Speed> speedList = new ArrayList<>();
		speedRepo.findAll().forEach(speedList::add);
		return speedList;
	}
	
	public WalkedSteps saveStep(WalkedSteps walkStep) throws InvalidException {
		walkStep.setId(null); //reset id for auto increment
				
		if (!directions.contains(walkStep.getSpeedDirection())) {
			throw new InvalidException("not a valid direction");
		}
		
		List<Speed> speeds = speedRepo.getSpeeds(); //validate speed
		List<String> types = new ArrayList<>();
		speeds.stream().map(speed -> types.add(speed.getSpeedType()));
		if (!types.contains(walkStep.getSpeedType())) {
			throw new InvalidException("not a valid speed type");
		}
		
		WalkedSteps created = stepRepo.save(walkStep); 
		return created;
	}
	
	public List<WalkedSteps> getSteps() {
		return stepRepo.getSteps();
	}

}
