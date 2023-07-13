package net.bts.hr.challenge.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import net.bts.hr.challenge.model.Speed;
import net.bts.hr.challenge.service.ChallengeService;

@RestController()
public class ChallengeController {

	@Autowired
	ChallengeService speedService;

	@GetMapping("/speed-list")
	public List<Speed> getSpeedList() {
		return speedService.getSpeedList();
	}
}
