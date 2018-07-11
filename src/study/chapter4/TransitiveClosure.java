package study.chapter4;

import depend.In;

// 传递闭包
//有向图G的传递闭包定义为 G' = (V, E'), 
//其中 E' = {(u, v) | 图 G 中存在一条从顶点 u 到顶点 v 的路径}。
public class TransitiveClosure {
	private DepthFirstSearch[] tc;
	TransitiveClosure(Digraph G){
		tc = new DepthFirstSearch[G.V()];
		for(int v=0;v<G.V();v++) {
			tc[v]=new DepthFirstSearch(G,v);
		}
	}
	
	public boolean reachable(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return tc[v].marked(w);
    }
	
	private void validateVertex(int v) {
        int V = tc.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

	public static void main(String[] args) {
		In in = new In("src/data/tinyDG.txt");
        Digraph G = new Digraph(in);

        TransitiveClosure tc = new TransitiveClosure(G);

        // print header
        System.out.print("     ");
        for (int v = 0; v < G.V(); v++)
            System.out.printf("%3d", v);
        System.out.println();
        System.out.println("--------------------------------------------");

        // print transitive closure
        for (int v = 0; v < G.V(); v++) {
            System.out.printf("%3d: ", v);
            for (int w = 0; w < G.V(); w++) {
                if (tc.reachable(v, w)) System.out.printf("  T");
                else                    System.out.printf("   ");
            }
            System.out.println();
        }

	}

}
