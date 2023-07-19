package net.bts.hr.challenge.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.bts.hr.challenge.model.Speed;
import net.bts.hr.challenge.model.WalkedSteps;
import net.bts.hr.challenge.service.ChallengeService;

@RestController()
public class ChallengeController {

	@Autowired
	ChallengeService service;

	@GetMapping("/speed-list")
	public List<Speed> getSpeedList() {
		return service.getSpeedList();
	}
	
	@PostMapping("/step/create")
	public WalkedSteps saveStep(@RequestBody WalkedSteps walkedStep) {
		return service.saveStep(walkedStep);
	}
	
	@GetMapping("/step/get")
	public List<WalkedSteps> getSteps() {
		return service.getSteps();
	}
}
