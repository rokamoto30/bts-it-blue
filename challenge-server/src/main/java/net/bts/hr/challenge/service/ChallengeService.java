package net.bts.hr.challenge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

	public List<Speed> getSpeedList() {
		List<Speed> speedList = new ArrayList<>();
		speedRepo.findAll().forEach(speedList::add);
		return speedList;
	}
	
	public WalkedSteps saveStep(WalkedSteps walkStep) {
		walkStep.setId(null);
		WalkedSteps created = stepRepo.save(walkStep);
		return created;
	}
	
	public List<WalkedSteps> getSteps() {
		return stepRepo.getSteps();
	}

}
