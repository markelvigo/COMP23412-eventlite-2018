package uk.ac.man.cs.eventlite.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

@Entity
@Table(name="Venues")
public class Venue {
	
	@Id
	private long id;
	private String name;
	private String address;
	private int capacity;
	private double latitude;	
	private double longitude;
	
	@OneToMany
	private List<Event> eventList;
	
	public Venue() {
	}

	public long getId() {
		return id;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return this.longitude;
	}

	public double getLatitude() {
		return this.latitude;
	}	
	
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public void setAddress(String address){
		this.address=address;
		setLocation(address);
	}
	
	public String getAddress(){
		return this.address;
	}
	
	private void setLocation(String address){
		GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyCJCIxY1ePC5dpmONRjy64v4dMqmnA35AE").build();
		GeocodingResult[] results = null;
		try{
			results =  GeocodingApi.geocode(context,address).await();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		this.latitude=results[0].geometry.location.lat;
		this.longitude=results[0].geometry.location.lng;
	}
	
}
