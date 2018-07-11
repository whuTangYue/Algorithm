package study.chapter4;

import depend.In;
import study.chapter1.Queue;
import study.chapter1.QuickUnionPathCompressionUF;
import study.chapter2.MinPQ;

/*Kruskal's algorithm computes the MST of any connected edge-weighted
 *  graph with E edges and V vertices using extra space proportional to
 *   E and time proportional to E log E (in the worst case).
*/
// space O(E)
// time O(ElogE),worst case
// 实际时间成本应该与E+E0logE 成正比,E0是权重小于最小生成树中权重最大边的所有边的总数
// 但是 Kruskal 一般还是比 Prim 要慢,因为处理每条边时还有一次 connect() 操作
public class KruskalMST {
	private static final double FLOATING_POINT_EPSILON = 1E-12;

	private double weight; // weight of MST
	private Queue<Edge> mst = new Queue<Edge>(); // edges in MST

	public KruskalMST(EdgeWeightedGraph G) {
		MinPQ<Edge> pq = new MinPQ<Edge>();
		for (Edge e : G.edges()) {
			pq.add(e);
		}
		QuickUnionPathCompressionUF uf = new QuickUnionPathCompressionUF(G.V());
		while (!pq.isEmpty() && mst.size() < G.V() - 1) {
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if (!uf.connected(v, w)) {
				uf.union(v, w);
				mst.enqueue(e);
				weight += e.weight();
			}
		}
		assert check(G);
	}

	public Iterable<Edge> edges() {
		return mst;
	}

	public double weight() {
		return weight;
	}

	private boolean check(EdgeWeightedGraph G) {

		// check total weight
		double total = 0.0;
		for (Edge e : edges()) {
			total += e.weight();
		}
		if (Math.abs(total - weight()) > FLOATING_POINT_EPSILON) {
			System.err.printf(
					"Weight of edges does not equal weight(): %f vs. %f\n",
					total, weight());
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
			for (Edge f : mst) {
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
		KruskalMST mst = new KruskalMST(G);
		for (Edge e : mst.edges()) {
			System.out.println(e);
		}
		System.out.printf("%.5f\n", mst.weight());

	}
}
