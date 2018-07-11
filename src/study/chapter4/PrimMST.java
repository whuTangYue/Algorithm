package study.chapter4;

import study.chapter1.QuickUnionPathCompressionUF;
import depend.In;
import study.chapter1.Queue;
import study.chapter2.IndexMinPQ;

// space O(V)
// time O(ElogV),worst case
// 对于实际应用中的稀疏图, Prim 与 LazyPrim 时间上限区别不大,空间上限减小了一个常数因子
public class PrimMST {
	private static final double FLOATING_POINT_EPSILON = 1E-12;

	private Edge[] edgeTo;
	private double[] distTo;
	private boolean[] marked;
	private IndexMinPQ<Double> pq;

	public PrimMST(EdgeWeightedGraph G) {
		edgeTo = new Edge[G.V()];
		distTo = new double[G.V()];
		pq = new IndexMinPQ<Double>(G.V());
		marked = new boolean[G.V()];
		for (int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v])
				prim(G, v);
		}
		assert check(G);
	}

	private void prim(EdgeWeightedGraph G, int s) {
		distTo[s] = 0.0;
		pq.insert(s, distTo[s]);
		while (!pq.isEmpty()) {
			int v = pq.delMin();
			visit(G, v);
		}
	}

	private void visit(EdgeWeightedGraph G, int v) {
		assert !marked[v];
		marked[v] = true;
		for (Edge e : G.adj(v)) {
			int w = e.other(v);
			if (marked[w])
				continue;
			if (e.weight() < distTo[w]) {
				distTo[w] = e.weight();
				edgeTo[w] = e;
				if (pq.contains(w))
					pq.change(w, distTo[w]);
				else
					pq.insert(w, distTo[w]);
			}
		}
	}

	public Iterable<Edge> edges() {
		Queue<Edge> mst = new Queue<Edge>();
		for (int v = 0; v < edgeTo.length; v++) {
			Edge e = edgeTo[v];
			if (e != null) {
				mst.enqueue(e);
			}
		}
		return mst;
	}

	public double weight() {
		double weight = 0.0;
		for (Edge e : edges()) {
			weight += e.weight();
		}
		return weight;
	}

	private boolean check(EdgeWeightedGraph G) {

		// check weight
		double totalWeight = 0.0;
		for (Edge e : edges()) {
			totalWeight += e.weight();
		}
		if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
			System.err.printf(
					"Weight of edges does not equal weight(): %f vs. %f\n",
					totalWeight, weight());
			return false;
		}

		// check that it is acyclic
		QuickUnionPathCompressionUF uf = new QuickUnionPathCompressionUF(G.V());
		for (Edge e : edges()) {
			int v = e.either(), w = e.other(v);
			if (uf.connected(v, w)) {
				System.err.println("Not a forest");
				return false;
			}
			uf.union(v, w);
		}

		// check that it is a spanning forest
		for (Edge e : G.edges()) {
			int v = e.either(), w = e.other(v);
			if (!uf.connected(v, w)) {
				System.err.println("Not a spanning forest");
				return false;
			}
		}

		// check that it is a minimal spanning forest (cut optimality
		// conditions)
		for (Edge e : edges()) {

			// all edges in MST except e
			uf = new QuickUnionPathCompressionUF(G.V());
			for (Edge f : edges()) {
				int x = f.either(), y = f.other(x);
				if (f != e)
					uf.union(x, y);
			}

			// check that e is min weight edge in crossing cut
			for (Edge f : G.edges()) {
				int x = f.either(), y = f.other(x);
				if (!uf.connected(x, y)) {
					if (f.weight() < e.weight()) {
						System.err.println("Edge " + f
								+ " violates cut optimality conditions");
						return false;
					}
				}
			}

		}

		return true;
	}

	public static void main(String[] args) {
		In in = new In("src/data/tinyEWG.txt");
		EdgeWeightedGraph G = new EdgeWeightedGraph(in);
		PrimMST mst = new PrimMST(G);
		for (Edge e : mst.edges()) {
			System.out.println(e);
		}
		System.out.printf("%.5f\n", mst.weight());

	}
}
