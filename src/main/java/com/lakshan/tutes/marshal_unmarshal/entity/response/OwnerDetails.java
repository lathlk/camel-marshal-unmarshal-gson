package com.lakshan.tutes.marshal_unmarshal.entity.response;


public class OwnerDetails{
	
	private String Id;
	private String Name;
	private String Address;
	private String dogName;
	private String dogCategory;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getDogName() {
		return dogName;
	}
	public void setDogName(String dogName) {
		this.dogName = dogName;
	}
	public String getDogCategory() {
		return dogCategory;
	}
	public void setDogCategory(String dogCategory) {
		this.dogCategory = dogCategory;
	}	
}
