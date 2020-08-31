package com.practice.ood.problems;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/*
 * Google Interview Question
 */
public class Logger {
	/*
	 * {3} started at {7} and ended at {19}
	 * {2} started at {8} and ended at {12}
	 * {1} started at {12} and ended at {15}
	 */
	public static void main(String[] args) {
		final LogClient logger = new LogImplementation();
		logger.start("1", 12);
		logger.poll();
		logger.start("3", 7);
		logger.poll();
		logger.start("2", 8);
		logger.end("2", 12);
		logger.end("3", 19);
		logger.end("1", 15);
		logger.poll();
		logger.poll();
		logger.poll();
		logger.poll();
	}
}

interface LogClient {
	/**
	 * When a process starts, it calls 'start' with processId.
	 */
	void start(String processId, long time);

	/**
	 * When the same process ends, it calls 'end' with processId.
	 */
	void end(String processId, long time);

	/* 
	 * Polls the first log entry of a completed process sorted by the start time of processes in the
	 * below format {processId} started at {startTime} and ended at {endTime}
	 * 
	 * process id = 1 --> 12, 15 
	 * process id = 2 --> 8, 12  
	 * process id = 3 --> 7, 19  
	 * 
	 * Output:
	 * 	{3} started at {7} and ended at {19}  
	 * 	{2} started at {8} and ended at {12}
	 *  {1} started at {12} and ended at {15}
	 */
	String poll();
}

class LogImplementation implements LogClient {

	public Map<String, Process> map = new HashMap<>();

	//TreeMap or Priority Queue is used to order the Process based on time
	public TreeMap<Long, Process> queue = new TreeMap<>();

	@Override
	public void start(String processId, long time) {
		Process process = new Process(processId, time);
		map.put(processId, process);
		queue.put(time, process);
	}

	@Override
	public void end(String processId, long time) {
		if (!map.containsKey(processId)) return;

		map.get(processId).endTime = time;
	}

	@Override
	public String poll() {
		if (queue.isEmpty()) {
			System.out.println("No record found!");
			return null;
		}

		Process top = queue.firstEntry().getValue();
		if (top.endTime != -1) {
			System.out.println(top.processId + " started at " + top.startTime + " ended at " + top.endTime);
			map.remove(top.processId);
			queue.pollFirstEntry();
		} else {
			System.out.println("No completed tasks in queue. Curr size of queue is " + queue.size());
		}
		return null;
	}

}

class Process {
	public String processId;

	public long startTime;

	public long endTime;

	public Process(String id, long startTime) {
		this.processId = id;
		this.startTime = startTime;
		this.endTime = -1;
	}
}