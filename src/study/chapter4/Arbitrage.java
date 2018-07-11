package study.chapter4;

import depend.In;

/**
 * The {@code Arbitrage} class provides a client that finds an arbitrage
 * opportunity in a currency exchange table by constructing a complete-digraph
 * representation of the exchange table and then finding a negative cycle in the
 * digraph.
 * <p>
 * This implementation uses the Bellman-Ford algorithm to find a negative cycle
 * in the complete digraph. The running time is proportional to
 * <em>V</em><sup>3</sup> in the worst case, where <em>V</em> is the number of
 * currencies.
 * <p>
 * For additional documentation, see
 * <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Arbitrage {

	// this class cannot be instantiated
	private Arbitrage() {
	}

	/**
	 * Reads the currency exchange table from standard input and prints an
	 * arbitrage opportunity to standard output (if one exists).
	 *
	 * @param args
	 *            the command-line arguments
	 */
	public static void main(String[] args) {
		In in = new In("src/data/rates.txt");

		// V currencies
		int V = in.readInt();
		String[] name = new String[V];

		// create complete network
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(V);
		for (int v = 0; v < V; v++) {
			name[v] = in.readString();
			for (int w = 0; w < V; w++) {
				double rate = in.readDouble();
				DirectedEdge e = new DirectedEdge(v, w, -Math.log(rate));
				G.addEdge(e);
			}
		}

		// find negative cycle
		BellmanFordSP spt = new BellmanFordSP(G, 0);
		if (spt.hasNegativeCycle()) {
			double stake = 1000.0;
			for (DirectedEdge e : spt.negativeCycle()) {
				System.out.printf("%10.5f %s ", stake, name[e.from()]);
				stake *= Math.exp(-e.weight());
				System.out.printf("= %10.5f %s\n", stake, name[e.to()]);
			}
		} else {
			System.out.println("No arbitrage opportunity");
		}
	}

}

/******************************************************************************
 * Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
 *
 * This file is part of algs4.jar, which accompanies the textbook
 *
 * Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne, Addison-Wesley
 * Professional, 2011, ISBN 0-321-57351-X. http://algs4.cs.princeton.edu
 *
 *
 * algs4.jar is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * algs4.jar is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * algs4.jar. If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
