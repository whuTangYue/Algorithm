package study.chapter4;

public class DegreesOfSeparation {

	public static void main(String[] args) {
		String filename = "src/data/movies.txt";
		String delimiter = "/";
		String source = "Bacon, Kevin";
		
		SymbolGraph sg = new SymbolGraph(filename, delimiter);
		Graph G = sg.graph();
		if(!sg.contains(source)) {
			System.out.println(source+ "not in database");
			return;
		}
		
		int s = sg.indexOf(source);
		BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);
		
		String sink = "Grant, Cary";
		if(sg.contains(sink)) {
			int t = sg.indexOf(sink);
			if(bfs.hasPathTo(t)) {
				for(int v:bfs.pathTo(t)) {
					System.out.println(" " +sg.nameOf(v));
				}
			}
			else {
				System.out.println("not connected");
			}
		}
		else {
			System.out.println(" Not in database");
		}

	}

}
