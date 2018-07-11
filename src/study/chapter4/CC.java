package study.chapter4;

import depend.In;
import study.chapter1.Bag;

public class CC {
	private boolean[] marked;
	private int[] id;
	private int count;

	public CC(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for (int s = 0; s < G.V(); s++) {
			if (!marked[s]) {
				dfs(G, s);
				count++;
			}
		}
	}

	private void dfs(Graph G, int v) {
		marked[v] = true;
		id[v] = count;
		for (int w : G.adj(v))
			if (!marked[w])
				dfs(G, w);
	}

	public boolean connected(int v, int w) {
		return id[v] == id[w];
	}

	public int id(int v) {
		return id[v];
	}

	public int count() {
		return count;
	}

	@SuppressWarnings("unchecked")
	public static void show(Graph G) {
		CC cc = new CC(G);

		int M = cc.count();
		System.out.println(M + " components");

		Bag<Integer>[] components;
		components = (Bag<Integer>[]) new Bag[M];
		for (int i = 0; i < M; i++)
			components[i] = new Bag<Integer>();
		for (int v = 0; v < G.V(); v++)
			components[cc.id(v)].add(v);
		for (int i = 0; i < M; i++) {
			for (int v : components[i])
				System.out.print(v + " ");
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Graph G = new Graph(new In("src/data/tinyG.txt"));
		CC.show(G);

	}

}
