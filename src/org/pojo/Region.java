package org.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Region")
public class Region {
	
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private String id;
	
	@Column(name = "Name")
	private String name;
	
	
	public Region(){
		super();
	}
	
	public Region(String id, String name){
		super();
		this.id = id;
		this.name = name;
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


	
}
