/**
 * COPYRIGHT (C) 2015 archit.imsec10@gmail.com. All Rights Reserved.
 * ******************************************************************
 * @author archit.kapoor
 *
 */

package com.tournament;

import java.util.ArrayList;
import java.util.List;

public class Venue {

	private String venueName;
	private boolean occupied;

	private List<TimeSlot> timeSlots = new ArrayList<>();

	// Add time slots to venue
	public void addTimeSlot(TimeSlot timeSlot) {
		this.timeSlots.add(timeSlot);
	}

	public List<TimeSlot> getTimeSlots() {
		return timeSlots;
	}
	
	public Venue(String name) {
		this.venueName = name;
		this.occupied = false;
	}

	public String getVenueName() {
		return venueName;
	}

	public void setVenueName(String name) {
		this.venueName = name;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public void removeTimeSlot(TimeSlot timeSlot)
	{
		this.timeSlots.remove(timeSlot);
	}
}