package study.chapter4;

import java.util.NoSuchElementException;
import java.util.Stack;

import depend.In;
import study.chapter1.Bag;

public class Graph {
	protected static final String NEWLINE = System
			.getProperty("line.separator");

	protected final int V;
	protected int E;
	protected Bag<Integer>[] adj; // vertices adjacent to v


	@SuppressWarnings("unchecked")
	public Graph(int V) {
		if (V < 0)
			throw new IllegalArgumentException(
					"Num of vertices must be non-neg");
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Integer>();
		}
	}

	@SuppressWarnings("unchecked")
	public Graph(In in) {
		try {
			this.V = in.readInt();
			if (V < 0)
				throw new IllegalArgumentException(
						"Num of vertices must be non-neg");
			adj = (Bag<Integer>[]) new Bag[V];
			for (int v = 0; v < V; v++) {
				adj[v] = new Bag<Integer>();
			}
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

	public Graph(Graph G) {
		this(G.V());
		this.E = G.E();
		for (int v = 0; v < G.V(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<Integer> reverse = new Stack<Integer>();
			for (int w : G.adj[v]) {
				reverse.push(w);
			}
			for (int w : reverse) {
				adj[v].add(w);
			}
		}
	}

	/**
	 * Returns the number of vertices in this graph.
	 *
	 * @return the number of vertices in this graph
	 */
	public int V() {
		return V;
	}

	/**
	 * Returns the number of edges in this graph.
	 *
	 * @return the number of edges in this graph
	 */
	public int E() {
		return E;
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	protected void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException(
					"vertex " + v + " is not between 0 and " + (V - 1));
	}

	/**
	 * Adds the undirected edge v-w to this graph.
	 *
	 * @param v
	 *            one vertex in the edge
	 * @param w
	 *            the other vertex in the edge
	 * @throws IllegalArgumentException
	 *             unless both {@code 0 <= v < V} and {@code 0 <= w < V}
	 */
	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		E++;
		adj[v].add(w);
		adj[w].add(v);
	}

	/**
	 * Returns the vertices adjacent to vertex {@code v}.
	 *
	 * @param v
	 *            the vertex
	 * @return the vertices adjacent to vertex {@code v}, as an iterable
	 * @throws IllegalArgumentException
	 *             unless {@code 0 <= v < V}
	 */
	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	/**
	 * Returns the degree of vertex {@code v}.
	 *
	 * @param v
	 *            the vertex
	 * @return the degree of vertex {@code v}
	 * @throws IllegalArgumentException
	 *             unless {@code 0 <= v < V}
	 */
	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	/**
	 * Returns a string representation of this graph.
	 *
	 * @return the number of vertices <em>V</em>, followed by the number of
	 *         edges <em>E</em>, followed by the <em>V</em> adjacency lists
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				s.append(w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

	/**
	 * Unit tests the {@code Graph} data type.
	 *
	 * @param args
	 *            the command-line arguments
	 */
	public static void main(String[] args) {
		In in = new In("src/data/tinyG.txt");
		Graph G = new Graph(in);
		System.out.println(G);
	}

}
