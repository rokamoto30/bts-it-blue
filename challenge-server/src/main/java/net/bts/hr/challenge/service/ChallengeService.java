package net.bts.hr.challenge.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
	
	public List<String> getDirections() {
		return directions;
	}
	
	public WalkedSteps saveStep(WalkedSteps walkStep) throws InvalidException {
		walkStep.setId(null); //reset id for auto increment
				
		if (!directions.contains(walkStep.getSpeedDirection())) {
			throw new InvalidException("not a valid direction");
		}
		
		List<Speed> speeds = speedRepo.getSpeeds(); //validate speed
		List<String> types = new ArrayList<>();
		types = speeds.stream().map(speed -> speed.getSpeedType()).collect(Collectors.toList());
		
		System.out.println(types);
		System.out.println(walkStep.getSpeedType());
		
		if (!types.contains(walkStep.getSpeedType())) {
			throw new InvalidException("not a valid speed type");
		}
		
		WalkedSteps created = stepRepo.save(walkStep); 
		return created;
	}
	
	public List<WalkedSteps> getSteps() {
		return stepRepo.getSteps();
	}
	
	public WalkedSteps updateStep(WalkedSteps step) throws InvalidException {
		if (stepRepo.findById(step.getId()).isEmpty()) {
			throw new InvalidException("step not found");
		}
		return stepRepo.save(step);
	}
	
	public WalkedSteps saveUpdateStep(WalkedSteps step) throws InvalidException { // if step exists update, otherwise save (can always use id 0 to save)
		if (stepRepo.findById(step.getId()).isEmpty()) {
			return saveStep(step);
		} else {
			return updateStep(step);
		}
	}
	
	public void deleteStep(WalkedSteps step) throws InvalidException {
		if (stepRepo.findById(step.getId()).isEmpty()) {
			throw new InvalidException("step not found");
		}
		stepRepo.deleteById(step.getId());
		
	}

}
