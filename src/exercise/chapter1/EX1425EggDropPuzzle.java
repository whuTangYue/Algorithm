package exercise.chapter1;

import java.util.ArrayList;

public class EX1425EggDropPuzzle {

	private int[][] minDropsInWorstCase;
	private int[][] firstDropFloor;

	EX1425EggDropPuzzle(int eggs, int floors) {
		minDropsInWorstCase = new int[eggs + 1][floors + 1];
		firstDropFloor = new int[eggs + 1][floors + 1];
		evalMinDropsInWorstCase(eggs, floors);
	}

	public int getMinDropsInWorstCase(int eggs, int floors) {
		return minDropsInWorstCase[eggs][floors];
	}

	public ArrayList<Integer> traceDrops(int eggs, int floors, int exactFloor) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (exactFloor <= floors) {
			int lo = 0;
			int hi = floors;
			int tryfloor = firstDropFloor[eggs][floors];
			int eggnum = eggs;
			while (exactFloor != tryfloor) {
				list.add(tryfloor);
				if (exactFloor < tryfloor) {
					hi = tryfloor;
					tryfloor = firstDropFloor[eggnum--][tryfloor - lo] + lo;
				}
				else if(exactFloor > tryfloor){
					lo = tryfloor;
					tryfloor = firstDropFloor[eggnum][hi - tryfloor] + tryfloor;
				}
			}
			list.add(tryfloor);
		}
		return list;
	}

	public void evalMinDropsInWorstCase(int eggs, int floors) {

		// boundary condition
		// f(i,1)=1,f(i,0)=0
		// f(1,k)=k
		for (int i = 0; i < eggs + 1; i++) {
			minDropsInWorstCase[i][1] = 1;
			minDropsInWorstCase[i][0] = 0;
			firstDropFloor[i][0] = 0;
			firstDropFloor[i][1] = 1;
		}
		for (int j = 0; j < floors + 1; j++) {
			minDropsInWorstCase[0][j] = 0;
			minDropsInWorstCase[1][j] = j;
			firstDropFloor[1][j] = 1;
			firstDropFloor[0][j] = 0;
		}

		// f(eggs,floors) =1+Max(f(eggs-1,floors-1), f(eggs,floors-x))
		for (int i = 2; i <= eggs; i++) {
			for (int j = 2; j <= floors; j++) {

				minDropsInWorstCase[i][j] = Integer.MAX_VALUE;

				for (int floorTriedFirst = 1; floorTriedFirst <= j; floorTriedFirst++) {
					int res = 1 + Math.max(minDropsInWorstCase[i - 1][floorTriedFirst - 1],
							minDropsInWorstCase[i][j - floorTriedFirst]);
					if (res < minDropsInWorstCase[i][j]) {
						minDropsInWorstCase[i][j] = res;
						firstDropFloor[i][j] = floorTriedFirst;
					}
				}
			}

		}

	}

	public static void main(String[] args) {
		int eggs = 2;
		int floors = 100;
		int exactFloor = 100;

		EX1425EggDropPuzzle eggDrop = new EX1425EggDropPuzzle(eggs, floors);
		System.out.println(eggDrop.getMinDropsInWorstCase(eggs, floors));
		System.out.println(eggDrop.traceDrops(eggs, floors, exactFloor));

	}

}
