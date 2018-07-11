package study.chapter4;

import depend.StdRandom;
import study.chapter1.Stack;

public class Bipartite {
	private boolean isBipartite;
	private boolean[] color;
	private boolean[] marked;
	private int[] edgeTo;
	private Stack<Integer> cycle;

	public Bipartite(Graph G) {
		isBipartite = true;
		color = new boolean[G.V()];
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];

		for (int v = 0; v < G.V(); v++) {
			if (!marked[v]) {
				dfs(G, v);
			}
		}
		assert check(G);
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (cycle != null)
				return;

			if (!marked[w]) {
				edgeTo[w] = v;
				color[w] = !color[v];
				dfs(G, w);
			} else if (color[w] == color[v]) {
				isBipartite = false;
				cycle = new Stack<Integer>();
				cycle.push(w);
				for (int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
			}
		}
	}

	public boolean isBipartite() {
		return isBipartite;
	}

	public boolean color(int v) {
		validateVertex(v);
		if (!isBipartite)
			throw new UnsupportedOperationException("graph is not bipartite");
		return color[v];
	}

	public Iterable<Integer> oddCycle() {
		return cycle;
	}

	private boolean check(Graph G) {
		if (isBipartite) {
			for (int v = 0; v < G.V(); v++) {
				for (int w : G.adj(v)) {
					if (color[v] == color[w]) {
						System.err.printf(
								"edge %d-%d with %d and %d in same side of bipartition\n",
								v, w, v, w);
						return false;
					}
				}
			}
		} else {
			int first = -1, last = -1;
			for (int v : oddCycle()) {
				if (first == -1)
					first = v;
				last = v;
			}
			if (first != last) {
				System.err.printf("cycle begins with %d and ends with %d\n",
						first, last);
				return false;
			}
		}
		return true;
	}

	private void validateVertex(int v) {
		int V = marked.length;
		if (v < 0 || v >= V)
			throw new IllegalArgumentException(
					"vertex " + v + " is not between 0 and " + (v - 1));
	}

	public static void main(String[] args) {
		int V1 = 6;
		int V2 = 6;
		int E = 6;
		int F = 6;

		// create random bipartite graph with V1 vertices on left side,
		// V2 vertices on right side, and E edges; then add F random edges
		Graph G = GraphGenerator.bipartite(V1, V2, E);
		for (int i = 0; i < F; i++) {
			int v = StdRandom.uniform(V1 + V2);
			int w = StdRandom.uniform(V1 + V2);
			G.addEdge(v, w);
		}

		System.out.println(G);

		Bipartite b = new Bipartite(G);
		if (b.isBipartite()) {
			System.out.println("Graph is bipartite");
			for (int v = 0; v < G.V(); v++) {
				System.out.println(v + ": " + b.color(v));
			}
		} else {
			System.out.print("Graph has an odd-length cycle: ");
			for (int x : b.oddCycle()) {
				System.out.print(x + " ");
			}
			System.out.println();
		}

	}

}
