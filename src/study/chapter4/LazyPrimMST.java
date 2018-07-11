package study.chapter4;

import study.chapter1.QuickUnionPathCompressionUF;
import depend.In;
import study.chapter1.Queue;
import study.chapter2.MinPQ;

// space O(E)
// time O(ElogE),worst case
// 一般情况下优先队列中的边要远小于E,所以一般时间复杂度远小于ElogE
public class LazyPrimMST {
	private static final double FLOATING_POINT_EPSILON = 1E-12;

	private double weight;
	private Queue<Edge> mst;
	private boolean[] marked;
	private MinPQ<Edge> pq;

	public LazyPrimMST(EdgeWeightedGraph G) {
		mst = new Queue<Edge>();
		pq = new MinPQ<Edge>();
		marked = new boolean[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v])
				prim(G, v);
		}
		assert check(G);
	}

	private void prim(EdgeWeightedGraph G, int s) {
		visit(G, s);
		while (!pq.isEmpty()) {
			Edge e = pq.delMin();
			int v = e.either(), w = e.other(v);
			assert marked[v] || marked[w];
			if (marked[v] && marked[w])
				continue;
			mst.enqueue(e);
			weight += e.weight();
			if (!marked[v])
				visit(G, v);
			if (!marked[w])
				visit(G, w);
		}
	}

	private void visit(EdgeWeightedGraph G, int v) {
		assert !marked[v];
		marked[v] = true;
		for (Edge e : G.adj(v)) {
			if (!marked[e.other(v)])
				pq.add(e);
		}
	}
	
	public Iterable<Edge> edges() {
        return mst;
    }
	
	public double weight() {
        return weight;
    }
	
	private boolean check(EdgeWeightedGraph G) {

        // check weight
        double totalWeight = 0.0;
        for (Edge e : edges()) {
            totalWeight += e.weight();
        }
        if (Math.abs(totalWeight - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", totalWeight, weight());
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

        // check that it is a minimal spanning forest (cut optimality conditions)
        for (Edge e : edges()) {

            // all edges in MST except e
            uf = new QuickUnionPathCompressionUF(G.V());
            for (Edge f : mst) {
                int x = f.either(), y = f.other(x);
                if (f != e) uf.union(x, y);
            }

            // check that e is min weight edge in crossing cut
            for (Edge f : G.edges()) {
                int x = f.either(), y = f.other(x);
                if (!uf.connected(x, y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
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
        LazyPrimMST mst = new LazyPrimMST(G);
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.printf("%.5f\n", mst.weight());

	}

}
