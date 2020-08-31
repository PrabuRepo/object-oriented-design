package com.practice.ood.problems;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/*
 * We need to design an online Movie ticket booking system where a user can search a movie in a given city and book it.
 * How to implement seat booking process?
 * 
 * Solution :
 * 	The Main Classes to be used for the user persons: 
 * 		User, Movie, Theater, Booking, Address, Facilities
 */
public class BookMyShow {

}

/*
 * In the below code, as you can see enums are self-explanatory. 
 * We have users class in which users details are kept.
 * Theater class in which name of the theater, it’s address and list of movies currently running are kept.
 * Booking class lets you book the seat in a particular theater. It keeps a reference in Movie, Payment class.
 */

//Java skeleton code to design an online movie booking system. 
enum SeatStatus {
	SEAT_BOOKED, SEAT_NOT_BOOKED;
}

enum MovieStatus {
	Movie_Available, Movie_NotAvailable;
}

enum MovieType {
	ENGLISH, HINDI;
}

enum SeatType {
	NORMAL, EXECUTIVE, PREMIUM, VIP;
}

enum PaymentStatus {
	PAID, UNPAID;
}

class User4 {
	int userId;
	String name;
	Date dateOfBirth;
	String mobNo;
	String emailId;
	String sex;
}

class Movie {
	int movieId;
	int theaterId;
	MovieType movieType;
	MovieStatus movieStatus;
}

class Theater {
	int theaterId;
	String theaterName;
	Address adress;
	List<Movie> movies;
	float rating;
}

class Booking {
	int bookingId;
	int userId;
	int movieId;
	List<Movie> bookedSeats;
	int amount;
	PaymentStatus status_of_payment;
	Date booked_date;
	Timestamp movie_timing;
}

class Address {
	String city;
	String pinCode;
	String state;
	String streetNo;
	String landmark;
}