package net.bts.hr.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;  

@Entity
@Table(name = "walked_steps")
public class WalkedSteps {
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "speed_type")
	private String speedType;
	
	@Column(name = "speed_direction")
	private String speedDirection;
	
	@Column(name = "duration_hours")
	private Integer durationHours;

	@Column(name = "duration_minutes")
	private Integer durationMinutes;
	
	

	public WalkedSteps(Integer id, String speedType, String speedDirection, Integer durationHours, Integer durationMinutes) {
		super();
		this.id = id;
		this.speedType = speedType;
		this.speedDirection = speedDirection;
		this.durationHours = durationHours;
		this.durationMinutes = durationMinutes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSpeedType() {
		return speedType;
	}

	public void setSpeedType(String speedType) {
		this.speedType = speedType;
	}

	public String getSpeedDirection() {
		return speedDirection;
	}

	public void setSpeedDirection(String speedDirection) {
		this.speedDirection = speedDirection;
	}

	public Integer getDurationHours() {
		return durationHours;
	}

	public void setDurationHours(Integer durationHours) {
		this.durationHours = durationHours;
	}

	public Integer getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(Integer durationMinutes) {
		this.durationMinutes = durationMinutes;
	}

	@Override
	public String toString() {
		return "WalkedSteps [id=" + id + ", speedType=" + speedType + ", speedDirection=" + speedDirection
				+ ", durationHours=" + durationHours + ", durationMinutes=" + durationMinutes + "]";
	}
	
	
}
