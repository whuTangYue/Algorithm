package study.chapter2;

import depend.StdRandom;

//Simulation of queueing networks.
// M/M/1 queue for double parallel queues, etc
// Difficult to analyze complex queueing networks mathematically.
// Use simulation to plot distribution of waiting times, etc.
// Priority queue to determine which event to process next.

public class QueueNetworkSim {

	public static void main(String[] args) {
		int numArr = 10;
		double lambda = 0.20; // arrival rate1
		double mu = 2; // service rate

		MinPQ<Double> q = new MinPQ<Double>(); // arrival times of
												// queue
		double[] nextArrival = new double[numArr];
		double lastArrival = 0;
		while (lastArrival < 10000) {
			for (int i = 0; i < nextArrival.length; i++) {
				nextArrival[i] += StdRandom.exp(lambda);
				lastArrival = Math.max(lastArrival, nextArrival[i]);
				q.add(nextArrival[i]);
			}
		}
		double nextDeparture = q.min() + StdRandom.exp(mu); // time of next
															// departure
		double totalWait = 0;
		long customersServiced = 0;
		while (nextDeparture<2000) {
			if (nextDeparture < q.min()) {
				nextDeparture = q.min() + StdRandom.exp(mu);
			} else {
				double wait = nextDeparture - q.delMin();
				totalWait += wait;
				customersServiced++;
				nextDeparture += StdRandom.exp(mu);
			}
		}
		System.out.println("serviced:"+customersServiced);
		System.out.println("total time:"+ nextDeparture);
		System.out.println("average wait time:"+totalWait/customersServiced);

	}

}
