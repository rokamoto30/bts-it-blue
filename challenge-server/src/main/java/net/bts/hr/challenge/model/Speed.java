package net.bts.hr.challenge.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;  
import javax.persistence.Id;  

@Entity
@Table(name = "speed")
public class Speed {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "speed_type")
	private String speedType;
	
	@Column(name = "speed_descpription")
	private String speedDescpription;
	
	@Column(name = "speed_value")
	private Double speedValue;
	
	public Speed() {}

	public Speed(Integer id, String speedType, String speedDescpription, Double speedValue) {
		this.id = id;
		this.speedType = speedType;
		this.speedDescpription = speedDescpription;
		this.speedValue = speedValue;
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

	public String getSpeedDescpription() {
		return speedDescpription;
	}

	public void setSpeedDescpription(String speedDescpription) {
		this.speedDescpription = speedDescpription;
	}

	public Double getSpeedValue() {
		return speedValue;
	}

	public void setSpeedValue(Double speedValue) {
		this.speedValue = speedValue;
	}

	@Override
	public String toString() {
		return "Speed [id=" + id + ", speedType=" + speedType + ", speedDescpription=" + speedDescpription
				+ ", speedValue=" + speedValue + "]";
	}
	
	
	
}
