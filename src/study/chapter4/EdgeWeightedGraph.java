package study.chapter4;

import depend.In;
import study.chapter1.Bag;
import study.chapter1.Stack;

public class EdgeWeightedGraph {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private int E;
	private Bag<Edge>[] adj;

	@SuppressWarnings("unchecked")
	public EdgeWeightedGraph(int V) {
		if (V < 0)
			throw new IllegalArgumentException(
					"Number of vertices must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (Bag<Edge>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<Edge>();
		}
	}

	public EdgeWeightedGraph(In in) {
		this(in.readInt());
		int E = in.readInt();
		if (E < 0)
			throw new IllegalArgumentException(
					"Number of edges must be nonnegative");
		for (int i = 0; i < E; i++) {
			int v = in.readInt();
			int w = in.readInt();
			validateVertex(v);
			validateVertex(w);
			double weight = in.readDouble();
			Edge e = new Edge(v, w, weight);
			addEdge(e);
		}
	}

	public EdgeWeightedGraph(EdgeWeightedGraph G) {
		this(G.V());
		this.E = G.E();
		for (int v = 0; v < G.V(); v++) {
			// reverse so that adjacency list is in same order as original
			Stack<Edge> reverse = new Stack<Edge>();
			for (Edge e : G.adj[v]) {
				reverse.push(e);
			}
			for (Edge e : reverse) {
				adj[v].add(e);
			}
		}
	}

	public void addEdge(Edge e) {
		int v = e.either();
		int w = e.other(v);
		validateVertex(v);
		validateVertex(w);
		adj[v].add(e);
		adj[w].add(e);
		E++;
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	private void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException(
					"vertex " + v + " is not between 0 and " + (V - 1));
	}

	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	public Iterable<Edge> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	public Iterable<Edge> edges() {
		Bag<Edge> list = new Bag<Edge>();
		for (int v = 0; v < V; v++) {
			int selfLoops = 0;
			for (Edge e : adj(v)) {
				if (e.other(v) > v) {
					list.add(e);
				}
				// add only one copy of each self loop (self loops will be
				// consecutive)
				else if (e.other(v) == v) {
					if (selfLoops % 2 == 0)
						list.add(e);
					selfLoops++;
				}
			}
		}
		return list;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (Edge e : adj[v]) {
				s.append(e + "  ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

	public static void main(String[] args) {
		In in = new In("src/data/tinyEWG.txt");
		EdgeWeightedGraph G = new EdgeWeightedGraph(in);
		System.out.println(G);
	}
}
