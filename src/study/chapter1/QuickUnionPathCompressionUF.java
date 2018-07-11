package study.chapter1;

import depend.In;
import study.chapter4.Graph;

public class QuickUnionPathCompressionUF {
	private int[] id;
	private int count;
	
	public QuickUnionPathCompressionUF(int n) {
		count = n;
		id = new int[n];
		for(int i = 0;i<n;i++) {
			id[i]=i;
		}
	}
	
	public int count() {
		return count;
	}
	
	public int find(int p) {
		int root = p;
		while(root!=id[root])
			root = id[root];
		while(p!=root) {
			int newp = id[p];
			id[p] = root;
			p = newp;
		}
		return root;
	}
	
	public boolean connected(int p,int q) {
		return find(p)==find(q);
	}
	
	public void union(int p,int q) {
		int rootP = find(p);
		int rootQ = find(q);
		if(rootP == rootQ) return ;
		id[rootP] =rootQ;
		count--;
	}

	public static void main(String[] args) {
		Graph G = new Graph(new In("src/data/tinyG.txt"));
		QuickUnionPathCompressionUF uf = new QuickUnionPathCompressionUF(G.V());
		for(int v =0;v<G.V();v++) {
			for(int w:G.adj(v)) {
				if(uf.connected(v, w)) continue;
				uf.union(v, w);
				System.out.println(v+" "+w);
			}
		}
		System.out.println(uf.count + " components");

	}

}
