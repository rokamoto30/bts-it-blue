package net.bts.hr.challenge.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.bts.hr.challenge.exception.InvalidException;
import net.bts.hr.challenge.model.Speed;
import net.bts.hr.challenge.model.WalkedSteps;
import net.bts.hr.challenge.service.CalcService;
import net.bts.hr.challenge.service.ChallengeService;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin()
@RestController()
public class ChallengeController {

	@Autowired
	ChallengeService service;
	
	@Autowired
	CalcService calcService;

	@GetMapping("/speed-list")
	public List<Speed> getSpeedList() {
		return service.getSpeedList();
	}
	
	@GetMapping("/direction-list")
	public List<String> getDirections() {
		return service.getDirections();
	}
	
	@PostMapping("/step/create")
	public WalkedSteps saveStep(@Valid @RequestBody WalkedSteps walkedStep) throws InvalidException {
		return service.saveStep(walkedStep);
	}
	
	@PostMapping("/step/saveUpdate")
	public WalkedSteps saveUpdateStep(@Valid @RequestBody WalkedSteps walkedStep) throws InvalidException {
		return service.saveUpdateStep(walkedStep);
	}
	
	@GetMapping("/step/get")
	public List<WalkedSteps> getSteps() {
		return service.getSteps();
	}
	
	@PutMapping("step/update")
	public WalkedSteps updateStep(@Valid @RequestBody WalkedSteps walkedStep) throws InvalidException {
		return service.updateStep(walkedStep);
	}
	
	@DeleteMapping("step/delete")
	public void deleteStep(@Valid @RequestBody WalkedSteps walkedStep) throws InvalidException {
		service.deleteStep(walkedStep);
	}
	
	@GetMapping("total")
	public String getTotalDistance() {
		return calcService.printTreasureLocation();
	}
	
}
