package study.chapter4;

import depend.In;
import study.chapter1.Bag;

public class KosarajuSCC {
	private boolean[] marked;
	private int[] id;
	private int count;

	public KosarajuSCC(Digraph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		DepthFirstOrder order = new DepthFirstOrder(G.reverse());
		for (int s : order.reversePost())
			if (!marked[s]) {
				dfs(G, s);
				count++;
			}
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		for (int w : G.adj(v))
			if (!marked[w])
				dfs(G, w);
	}

	public boolean stronglyConnected(int v, int w) {
		return id[v] == id[w];
	}

	public int id(int v) {
		return id[v];
	}

	public int count() {
		return count;
	}

	@SuppressWarnings("unchecked")
	public static void show(Digraph G) {
		KosarajuSCC scc = new KosarajuSCC(G);

		int M = scc.count();
		System.out.println(M + " strong components");

		Bag<Integer>[] components;
		components = (Bag<Integer>[]) new Bag[M];
		for (int i = 0; i < M; i++)
			components[i] = new Bag<Integer>();
		for (int v = 0; v < G.V(); v++)
			components[scc.id(v)].add(v);
		for (int i = 0; i < M; i++) {
			for (int v : components[i])
				System.out.print(v + " ");
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Digraph G = new Digraph(new In("src/data/tinyDG.txt"));
		KosarajuSCC.show(G);

	}
}
