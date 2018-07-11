package exercise.chapter2;

import java.util.Stack;

import study.chapter1.Queue;
import study.chapter2.MinPQ;

// solve 8Puzzle using A*
public class EX2532_8Puzzle {
	public static class Board {
		private final int[][] blocks;
		private final int N;

		public Board(int[][] blocks) {
			N = blocks.length;
			this.blocks = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					this.blocks[i][j] = blocks[i][j];
				}
			}
		}
		public Board(int[] blocks) {
			N=(int) Math.sqrt(blocks.length);
			this.blocks = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					this.blocks[i][j] = blocks[i*N+j];
				}
			}
		}

		public int dimension() {
			return N;
		}

		public int hamming() {
			int hammingDis = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (isSpace(blocks[i][j]))
						continue;
					if (blocks[i][j] != i * N + j + 1)
						hammingDis++;
				}
			}
			return hammingDis;
		}

		public int manhattan() {
			int manhattanDis = 0;
			int[] location = new int[2];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (isSpace(blocks[i][j]))
						continue;
					if (blocks[i][j] != i * N + j + 1) {
						location = findGoalLocation(blocks[i][j]);
						manhattanDis += Math.abs(i - location[0])
								+ Math.abs(j - location[1]);
					}
				}
			}
			return manhattanDis;
		}

		private int[] findGoalLocation(int data) {
			int[] ij = new int[2];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (data == N * i + j + 1) {
						ij[0] = i;
						ij[1] = j;
					}
				}
			}
			return ij;
		}

		public boolean isGoal() {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (blocks[i][j] == 0)
						continue;
					if (blocks[i][j] != i * N + j + 1)
						return false;
				}
			}
			return true;
		}

		public int inverseNum() {
			int[] aux = new int[N * N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					aux[i * N + j] = blocks[i][j];
				}
			}
			int cntInverseNum = EX2219InverseNumber.cntInverseNumber(aux);
			return cntInverseNum;
		}

		public boolean equals(Object y) {

			if (y == null)
				return false;
			if (y.getClass() != this.getClass())
				return false;
			if (y == this)
				return true;

			Board that = (Board) y;
			if (this.dimension() != that.dimension())
				return false;

			for (int i = 0; i < this.dimension(); i++) {
				for (int j = 0; j < this.dimension(); j++) {
					if (this.blocks[i][j] != that.blocks[i][j])
						return false;
				}
			}

			return true;
		}

		public Iterable<Board> neighbors() {
			Queue<Board> neighbors = new Queue<Board>();
			int[] location = spaceLocation();
			int spaceRow = location[0];
			int spaceCol = location[1];
			if (spaceRow > 0)
				neighbors.enqueue(new Board(
						swap(spaceRow, spaceCol, spaceRow - 1, spaceCol)));
			if (spaceRow < dimension() - 1)
				neighbors.enqueue(new Board(
						swap(spaceRow, spaceCol, spaceRow + 1, spaceCol)));
			if (spaceCol > 0)
				neighbors.enqueue(new Board(
						swap(spaceRow, spaceCol, spaceRow, spaceCol - 1)));
			if (spaceCol < dimension() - 1)
				neighbors.enqueue(new Board(
						swap(spaceRow, spaceCol, spaceRow, spaceCol + 1)));
			return neighbors;
		}

		private int[][] swap(int row1, int col1, int row2, int col2) {
			int[][] copy = new int[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++) {
					copy[i][j] = blocks[i][j];
				}
			int tmp = copy[row1][col1];
			copy[row1][col1] = copy[row2][col2];
			copy[row2][col2] = tmp;
			return copy;
		}

		private int[] spaceLocation() {
			for (int row = 0; row < N; row++) {
				for (int col = 0; col < N; col++) {
					if (isSpace(blocks[row][col])) {
						int[] location = new int[2];
						location[0] = row;
						location[1] = col;
						return location;
					}
				}
			}
			throw new RuntimeException();
		}

		private boolean isSpace(int block) {
			return block == 0;
		}

		public String toString() {
			StringBuilder s = new StringBuilder();
			s.append(N + "\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					s.append(String.format("%2d ", blocks[i][j]));
				}
				s.append("\n");
			}
			return s.toString();
		}
	}

	public static class Solver {
		private MinPQ<SearchNode> pq;
		private Stack<Board> solutions;
		private int Moves = -1;
		private class SearchNode implements Comparable<SearchNode> {
			private final Board board;
			private final int move;
			private final int priority;
			private final SearchNode parent;

			public SearchNode(Board board, int move, SearchNode parent) {
				this.board = board;
				this.move = move;
				this.priority = board.manhattan() + move;
				this.parent = parent;
			}

			public int compareTo(SearchNode that) {
				if (this.board.equals(that.board))
					return 0;
				if (this.priority < that.priority)
					return -1;
				else
					return 1;
			}
		}

		public Solver(Board initial) {
			if (initial == null)
				throw new java.lang.NullPointerException();
			if (!isSolvable(initial)) {
				return;
			}
			pq = new MinPQ<SearchNode>();
			solutions = new Stack<Board>();
			SearchNode initialNode = new SearchNode(initial, 0, null);
			pq.add(initialNode);
			while (true) {
				SearchNode searchNode = pq.delMin();
				if (searchNode.board.isGoal()) {
					Moves = searchNode.move;
					this.solutions.push(searchNode.board);
					while (searchNode.parent != null) {
						searchNode = searchNode.parent;
						this.solutions.push(searchNode.board);
					}
					break;
				} else {
					for (Board neiborBoard : searchNode.board.neighbors()) {
						SearchNode neiborNode = new SearchNode(neiborBoard,
								searchNode.move + 1, searchNode);
						if(searchNode.parent==null) {
							pq.add(neiborNode);
						}else if(!searchNode.parent.board.equals(neiborNode.board)) {
							pq.add(neiborNode);
						}
					}
				}
			}
		}

		public boolean isSolvable(Board initial) {
			if ((initial.inverseNum() & 1) != 0) {
				return false;
			} else
				return true;
		}
		
	}

	public static void main(String[] args) {
		int[] a = {0,1,4,2,7,6,3,8,5};
		Board initial = new Board(a);
	    // solve the puzzle
	    Solver solver = new Solver(initial);
	    // print solution to standard output
	    if (!solver.isSolvable(initial))
	        System.out.println("No solution possible");
	    else {
	    	System.out.println("Minimum number of moves = " + solver.Moves);
	        for (Board board : solver.solutions)
	        	System.out.println(board);
	    }

	}

}
