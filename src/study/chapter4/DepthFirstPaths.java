package study.chapter4;

import depend.In;
import study.chapter1.Stack;

public class DepthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private final int s;

	public DepthFirstPaths(Graph G, int s) {

		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		validateVertex(s);
		this.s = s;
		dfs(G, s);
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}
		}
	}

	public boolean hasPathTo(int v) {
		validateVertex(s);
		return marked[v];
	}

	private void validateVertex(int v) {
		int V = marked.length;
		if (v < 0 || v >= V)
			throw new IllegalArgumentException(
					"vertex " + v + " is not between 0 and " + (V - 1));
	}

	public Iterable<Integer> pathTo(int v) {
		validateVertex(s);
		if (!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<Integer>();
		for (int x = v; x != s; x = edgeTo[x])
			path.push(x);
		path.push(s);
		return path;
	}

	public static void main(String[] args) {
		In in = new In("src/data/mediumDG.txt");
		Digraph G = new Digraph(in);
		int s = 0;
		DepthFirstPaths dfs = new DepthFirstPaths(G, s);

		for (int v = 0; v < G.V(); v++) {
			if (dfs.hasPathTo(v)) {
				System.out.printf("%d to %d:  ", s, v);
				for (int x : dfs.pathTo(v)) {
					if (x == s)
						System.out.print(x);
					else
						System.out.print("-" + x);
				}
				System.out.println();
			}

			else {
				System.out.printf("%d to %d:  not connected\n", s, v);
			}

		}
	}
}
