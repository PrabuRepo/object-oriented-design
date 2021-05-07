package com.practice.ood.problems;

import java.util.ArrayList;
import java.util.List;

public class MeetingScheduler {
	int MAX_MEETING_ROOMS;
	List<MeetingRoom> meetingRooms;
	int idCounter;
	List<Meeting> meetingsHistory;
	EmailService emailService;

	public MeetingScheduler(int n, EmailService emailService) {
		MAX_MEETING_ROOMS = n;
		meetingRooms = new ArrayList<>();
		meetingsHistory = new ArrayList<>();
		idCounter = 0;
		initMeetingRooms();
		this.emailService = emailService;
	}

	private void initMeetingRooms() {
		for (int i = 0; i < MAX_MEETING_ROOMS; i++) {
			MeetingRoom room = new MeetingRoom(i);
			meetingRooms.add(room);
		}
	}

	public synchronized Meeting bookMeeting(List<String> emailList, int startTime, int endTime) throws Exception {
		for (MeetingRoom room : meetingRooms) {
			if (room.isAvailable(startTime, endTime)) {
				Meeting meeting = room.bookMeeting(idCounter++, emailList, startTime, endTime);
				emailService.sendMultipleEmail(emailList, meeting);
				meetingsHistory.add(meeting);
				return meeting;
			}
		}
		throw new Exception("Meeting Room Unavailable");
	}

	//TODO: Think about it 
	//public synchronized Meeting cancelMeeting()
	//public synchronized Meeting postponeMeeting()
	//List<Interval> getAvailableTimes() -> for given meeting room

	public List<Meeting> getHistoryOfMeetings(int size) {
		int historySize = meetingsHistory.size();
		int finalSize = size < historySize ? historySize : size;
		return new ArrayList<>(meetingsHistory.subList(historySize - finalSize, historySize));
	}
}

class MeetingRoom {
	int id;
	List<Meeting> meetings;

	public MeetingRoom(int id) {
		this.id = id;
		meetings = new ArrayList<>();
	}

	public boolean isAvailable(int startTime, int endTime) {
		for (Meeting meeting : meetings) {
			//if(meeting.endTime > startTime || meeting.startTime <endTime) return false; 

			//or 
			if (startTime >= meeting.startTime && startTime <= meeting.endTime
					|| endTime >= meeting.startTime && endTime <= meeting.endTime)
				return false;
		}
		return true;
	}

	public Meeting bookMeeting(int id, List<String> emailList, int startTime, int endTime) {
		Meeting meeting = new Meeting(emailList, id, startTime, endTime);
		meetings.add(meeting);
		return meeting;
	}

}

class Meeting {
	int id;
	int startTime;
	int endTime;
	List<String> emailList;

	public Meeting(List<String> emailList, int id, int startTime, int endTime) {
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.emailList = emailList;
	}
}

interface EmailService {
	public void sendEmail(String email, Meeting meeting);

	public void sendMultipleEmail(List<String> emailList, Meeting meeting);
}

class SendGridEmailService implements EmailService {

	public SendGridEmailService() {
	}

	public void sendEmail(String email, Meeting meeting) {
		//TODO: Implement this
	}

	public void sendMultipleEmail(List<String> emailList, Meeting meeting) {
		for (String email : emailList) {
			sendEmail(email, meeting);
		}
	}
}
