package org.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "City")
public class City {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private String id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "CountryID")
	private Integer countryId;
	
	public City(){
		super();
	}
	
	public City(String id, String name, int countryId){
		super();
		this.id = id;
		this.name = name;
		this.countryId = countryId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	
}
