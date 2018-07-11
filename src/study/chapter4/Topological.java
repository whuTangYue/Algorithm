package study.chapter4;

// 拓扑顺序
public class Topological {
	private Iterable<Integer> order;
	private int[] rank;

	public Topological(Digraph G) {
		DirectedCycle cyclefinder = new DirectedCycle(G);
		if (!cyclefinder.hasCycle()) {
			DepthFirstOrder dfs = new DepthFirstOrder(G);
			order = dfs.reversePost();
			rank = new int[G.V()];
			int i = 0;
			for (int v : order)
				rank[v] = i++;
		}
	}

	/**
	 * Returns a topological order if the digraph has a topologial order, and
	 * {@code null} otherwise.
	 * 
	 * @return a topological order of the vertices (as an interable) if the
	 *         digraph has a topological order (or equivalently, if the digraph
	 *         is a DAG), and {@code null} otherwise
	 */
	public Iterable<Integer> order() {
		return order;
	}

	/**
	 * Does the digraph have a topological order?
	 * 
	 * @return {@code true} if the digraph has a topological order (or
	 *         equivalently, if the digraph is a DAG), and {@code false}
	 *         otherwise
	 */
	public boolean hasOrder() {
		return order != null;
	}

	/**
	 * Does the digraph have a topological order?
	 * 
	 * @return {@code true} if the digraph has a topological order (or
	 *         equivalently, if the digraph is a DAG), and {@code false}
	 *         otherwise
	 * @deprecated Replaced by {@link #hasOrder()}.
	 */
	@Deprecated
	public boolean isDAG() {
		return hasOrder();
	}

	/**
	 * The the rank of vertex {@code v} in the topological order; -1 if the
	 * digraph is not a DAG
	 *
	 * @param v
	 *            the vertex
	 * @return the position of vertex {@code v} in a topological order of the
	 *         digraph; -1 if the digraph is not a DAG
	 * @throws IllegalArgumentException
	 *             unless {@code 0 <= v < V}
	 */
	public int rank(int v) {
		validateVertex(v);
		if (hasOrder())
			return rank[v];
		else
			return -1;
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int v) {
		int V = rank.length;
		if (v < 0 || v >= V)
			throw new IllegalArgumentException(
					"vertex " + v + " is not between 0 and " + (V - 1));
	}

	/**
	 * Unit tests the {@code Topological} data type.
	 *
	 * @param args
	 *            the command-line arguments
	 */
	public static void main(String[] args) {
		String filename = "src/data/jobs.txt";
		String delimiter = "/";
		SymbolDigraph sg = new SymbolDigraph(filename, delimiter);
		Topological topological = new Topological(sg.digraph());
		for (int v : topological.order()) {
			System.out.println(sg.nameOf(v));
		}
	}

}
