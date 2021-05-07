package com.practice.lld.parkinglot;

public class ParkingSpot {
	private String number;
	private boolean free;
	private Vehicle vehicle;
	private final ParkingSpotType type;

	public boolean isFree() {
		return true;
	}

	public ParkingSpot(ParkingSpotType type) {
		this.type = type;
	}

	public boolean assignVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
		this.free = false;
		return true;
	}

	public boolean removeVehicle() {
		this.vehicle = null;
		this.free = true;
		return true;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public ParkingSpotType getType() {
		return type;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

}

class HandicappedSpot extends ParkingSpot {
	public HandicappedSpot() {
		super(ParkingSpotType.HANDICAPPED);
	}
}

class CompactSpot extends ParkingSpot {
	public CompactSpot() {
		super(ParkingSpotType.COMPACT);
	}
}

class LargeSpot extends ParkingSpot {
	public LargeSpot() {
		super(ParkingSpotType.LARGE);
	}
}

class MotorbikeSpot extends ParkingSpot {
	public MotorbikeSpot() {
		super(ParkingSpotType.MOTORBIKE);
	}
}

class ElectricSpot extends ParkingSpot {
	public ElectricSpot() {
		super(ParkingSpotType.ELECTRIC);
	}
}
