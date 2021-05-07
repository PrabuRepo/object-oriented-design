package com.practice.lld.parkinglot;

public abstract class Vehicle {
	private String licenseNumber;
	private final VehicleType type;
	private ParkingTicket ticket;

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public ParkingTicket getTicket() {
		return ticket;
	}

	public void setTicket(ParkingTicket ticket) {
		this.ticket = ticket;
	}

	public VehicleType getType() {
		return type;
	}

	public Vehicle(VehicleType type) {
		this.type = type;
	}

	public void assignTicket(ParkingTicket ticket) {
		this.ticket = ticket;
	}
}

class Car extends Vehicle {
	public Car() {
		super(VehicleType.CAR);
	}
}

class Van extends Vehicle {
	public Van() {
		super(VehicleType.VAN);
	}
}

class Truck extends Vehicle {
	public Truck() {
		super(VehicleType.TRUCK);
	}
}

class Motorcycle extends Vehicle {
	public Motorcycle() {
		super(VehicleType.MOTORBIKE);
	}
}

class ElectricVehicle extends Vehicle {
	public ElectricVehicle() {
		super(VehicleType.ELECTRIC);
	}
}