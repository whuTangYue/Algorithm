package study.chapter4;

import depend.In;
import study.chapter1.Queue;

public class BreadthFirstSearch {
	private boolean[] marked;
	private int count;

	public BreadthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];
		validateVertex(s);
		bfs(G, s);
	}

	private void bfs(Graph G, int s) {
		marked[s] = true;
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(s);
		while(!queue.isEmpty()) {
			int v = queue.dequeue();
			for(int w:G.adj(v))
				if(!marked[w]) {
					marked[w] = true;
					queue.enqueue(w);
				}
		}
	}

	public boolean marked(int v) {
		validateVertex(v);
		return marked[v];
	}

	public int count() {
		return count;
	}

	private void validateVertex(int v) {
		int V = marked.length;
		if (v < 0 || v >= V)
			throw new IllegalArgumentException(
					"vertex " + v + " is not between 0 and " + (V - 1));
	}

	public static void main(String[] args) {
		In in = new In("src/data/tinyG.txt");
		Graph G = new Graph(in);
		int s = 0;
		DepthFirstSearch search = new DepthFirstSearch(G, s);
		for (int v = 0; v < G.V(); v++) {
			if (search.marked(v))
				System.out.print(v + " ");
		}

		System.out.println();
		if (search.count() != G.V())
			System.out.println("NOT connected");
		else
			System.out.println("connected");
	}
}
