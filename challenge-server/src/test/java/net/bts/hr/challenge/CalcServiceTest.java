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
	
	void compareOutput(String expected) {
		//mock repo
		Mockito.when(speedRepo.getSpeeds()).thenReturn(speeds);
		Mockito.when(stepRepo.getSteps()).thenReturn(steps);
		
		// compare
		String actual = calcService.printTreasureLocation();
		assertEquals(expected, actual);		
	}

	@Test
	void testNorth() {	
		//setup
		speeds.add(new Speed(1, "WLK", "Walk", 3.0));
		steps.add(new WalkedSteps(1, "WLK", "N", 1, 0));
		
		compareOutput("Treasure At: 3.0 Miles North");	
	}
	
	@Test
	void testSouth() {	
		//setup
		speeds.add(new Speed(1, "WLK", "Walk", 3.0));
		steps.add(new WalkedSteps(1, "WLK", "S", 1, 0));
		
		compareOutput("Treasure At: 3.0 Miles South");	
	}
	
	@Test
	void testEast() {	
		//setup
		speeds.add(new Speed(1, "WLK", "Walk", 3.0));
		steps.add(new WalkedSteps(1, "WLK", "E", 1, 0));
		
		compareOutput("Treasure At: 3.0 Miles East");	
	}
	
	@Test
	void testWest() {	
		//setup
		speeds.add(new Speed(1, "WLK", "Walk", 3.0));
		steps.add(new WalkedSteps(1, "WLK", "W", 1, 0));
		
		compareOutput("Treasure At: 3.0 Miles West");	
	}
	
	@Test
	void testBackTrack() {	
		//setup
		speeds.add(new Speed(1, "WLK", "Walk", 3.0));
		steps.add(new WalkedSteps(1, "WLK", "N", 1, 0));
		steps.add(new WalkedSteps(1, "WLK", "S", 1, 0));
		
		compareOutput("Treasure At: current location");	
	}
	
	@Test
	void testNE() {	
		//setup
		speeds.add(new Speed(1, "WLK", "Walk", 3.0));
		steps.add(new WalkedSteps(1, "WLK", "NE", 1, 0));
		
		compareOutput("Treasure At: 2.12 Miles North, 2.12 Miles East");	
	}
	
	@Test
	void testSW() {	
		//setup
		speeds.add(new Speed(1, "WLK", "Walk", 3.0));
		steps.add(new WalkedSteps(1, "WLK", "SW", 1, 0));
		
		compareOutput("Treasure At: 2.12 Miles South, 2.12 Miles West");	
	}
	
	@Test
	void testNW() {	
		//setup
		speeds.add(new Speed(1, "WLK", "Walk", 3.0));
		steps.add(new WalkedSteps(1, "WLK", "NW", 1, 0));
		
		compareOutput("Treasure At: 2.12 Miles North, 2.12 Miles West");	
	}
	
	@Test
	void testCombo() {	
		//setup
		speeds.add(new Speed(1, "WLK", "Walk", 3.0));
		steps.add(new WalkedSteps(1, "WLK", "NW", 3, 0));
		steps.add(new WalkedSteps(1, "WLK", "N", 5, 0));
		
		compareOutput("Treasure At: 21.36 Miles North, 6.36 Miles West");	
	}
	
	@Test
	void testComplex() {	
		//setup
		speeds.add(new Speed(1, "WLK", "Walk", 1.0)); // made walking speed 1 to simplify calculations
		steps.add(new WalkedSteps(1, "WLK", "N", 10, 0));
		steps.add(new WalkedSteps(1, "WLK", "SE", 28, 0));
		steps.add(new WalkedSteps(1, "WLK", "SW", 92, 0));
		steps.add(new WalkedSteps(1, "WLK", "NE", 54, 0));
		steps.add(new WalkedSteps(1, "WLK", "NW", 8, 0));
		steps.add(new WalkedSteps(1, "WLK", "W", 15, 0));
		steps.add(new WalkedSteps(1, "WLK", "E", 70, 0));
		
		compareOutput("Treasure At: 31.01 Miles South, 42.27 Miles East");	
	}

}
