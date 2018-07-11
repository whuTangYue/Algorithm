package exercise.chapter2;

import java.util.ArrayList;
import java.util.Comparator;

import study.chapter2.MinPQ;

public class EX2520FreeTime {
	static class task {
		double startTime;
		double endTime;

		public task(double st, double et) {
			startTime = st;
			endTime = et;
		}
	}

	static class StartOrder implements Comparator<task> {

		@Override
		public int compare(task o1, task o2) {
			if (o1.startTime < o2.startTime)
				return -1;
			if (o1.startTime > o2.startTime)
				return +1;
			return 0;
		}

	}

	static class EndOrder implements Comparator<task> {

		@Override
		public int compare(task o1, task o2) {
			if (o1.endTime < o2.endTime)
				return -1;
			if (o1.endTime > o2.endTime)
				return +1;
			return 0;
		}

	}

	public static void solve(ArrayList<task> taskList, int N) {
		taskList.sort(new StartOrder());
		MinPQ<task> p = new MinPQ<task>(new EndOrder());
		double longestFreeTime = 0;
		double longestBusyTime = 0;
		double startFree = 0;
		double startBusy = 0;
		for (int i = 0; i < taskList.size(); i++) {
			task t = taskList.get(i);
			while (true) {
				if (p.isEmpty()) {
					longestFreeTime = Math.max(longestFreeTime, t.startTime-startFree);
					startBusy = t.startTime;
					p.add(t);
					break;
				} else if (t.startTime >= p.min().endTime) {
					if (p.size() == 1 && t.startTime > p.min().endTime) {
						startFree = p.min().endTime;
						longestBusyTime = Math.max(longestBusyTime,
								p.min().endTime - startBusy);
					}
					p.delMin();
				} else if (p.size() < N) {
					p.add(t);
					break;
				} else {
					t.endTime = t.endTime - t.startTime + p.min().endTime;
					t.startTime = p.min().endTime;
				}
			}
		}
		while(!p.isEmpty()) {
			longestBusyTime = Math.max(longestBusyTime,
					p.delMin().endTime - startBusy);
		}
		System.out.println(longestBusyTime);
		System.out.println(longestFreeTime);
	}

	public static void main(String[] args) {
		ArrayList<task> taskList = new ArrayList<task>();
		taskList.add(new task(1, 2));
		taskList.add(new task(1.5, 3));
		taskList.add(new task(2.5, 5));
		taskList.add(new task(7,13));
		solve(taskList,10);

	}

}
