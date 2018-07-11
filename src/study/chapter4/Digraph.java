package study.chapter4;

import java.util.NoSuchElementException;

import depend.In;
import study.chapter1.Stack;

public class Digraph extends Graph {
	private int[] indegree;

	public Digraph(int V) {
		super(V);
		indegree = new int[V];
	}

	public Digraph(In in) {
		this(in.readInt());
		try {
			int E = in.readInt();
			if (E < 0)
				throw new IllegalArgumentException(
						"Num of edges must be non-neg");
			for (int i = 0; i < E; i++) {
				int v = in.readInt();
				int w = in.readInt();
				validateVertex(v);
				validateVertex(w);
				addEdge(v, w);
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException(
					"invalid input format in Graph constructor", e);
		}

	}

	public Digraph(Digraph G) {
		this(G.V());
		this.E = G.E();
		for (int v = 0; v < V; v++) {
			this.indegree[v] = G.indegree(v);
		}
		for (int v = 0; v < G.V(); v++) {
			Stack<Integer> reverse = new Stack<Integer>();
			for (int w : G.adj[v]) {
				reverse.push(w);
			}
			for (int w : reverse) {
				adj[v].add(w);
			}
		}
	}
	
	public Digraph(EdgeWeightedDigraph G) {
		this(G.V());
		this.E = G.E();
		for (int v = 0; v < V; v++) {
			this.indegree[v] = G.indegree(v);
		}
		for (int v = 0; v < G.V(); v++) {
			Stack<DirectedEdge> reverse = new Stack<DirectedEdge>();
			for (DirectedEdge w : G.adj(v)) {
				reverse.push(w);
			}
			for (DirectedEdge e : reverse) {
				adj[v].add(e.to());
			}
		}
	}

	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		adj[v].add(w);
		indegree[w]++;
		E++;
	}

	public int outdegree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	public int indegree(int v) {
		validateVertex(v);
		return indegree[v];
	}

	public Digraph reverse() {
		Digraph reverse = new Digraph(V);
		for (int v = 0; v < V; v++) {
			for (int w : adj[v]) {
				reverse.addEdge(w, v);
			}
		}
		return reverse;
	}

	public static void main(String[] args) {
		In in = new In("src/data/tinyDG.txt");
		Digraph G = new Digraph(in);
		System.out.println(G);

	}

}
