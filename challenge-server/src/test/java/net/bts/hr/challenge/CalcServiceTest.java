package net.bts.hr.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import net.bts.hr.challenge.model.Speed;
import net.bts.hr.challenge.model.WalkedSteps;
import net.bts.hr.challenge.repo.SpeedRepo;
import net.bts.hr.challenge.repo.WalkStepRepo;
import net.bts.hr.challenge.service.CalcService;

@SpringBootTest
class CalcServiceTest {
	
	@Autowired
	private CalcService calcService;
	
	@MockBean
	private SpeedRepo speedRepo;
	
	@MockBean
	private WalkStepRepo stepRepo;
	
	
	private List<WalkedSteps> steps = new ArrayList<>();
	private List<Speed> speeds = new ArrayList<>();
	
	{
		speeds.add(new Speed(1, "WLK", "Walk", 3.0));
		steps.add(new WalkedSteps(1, "WLK", "N", 1, 0));
	}


	@Test
	void test() {		
		Mockito.when(speedRepo.getSpeeds()).thenReturn(speeds);
		Mockito.when(stepRepo.getSteps()).thenReturn(steps);
		String expected = "Treasure At 3 Miles North";
		String actual = calcService.printTreasureLocation();
		assertEquals(expected, actual);		
	}

}
