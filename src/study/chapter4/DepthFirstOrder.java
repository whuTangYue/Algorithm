package study.chapter4;

import depend.In;
import study.chapter1.Queue;
import study.chapter1.Stack;

public class DepthFirstOrder {
	private boolean[] marked;
	private int[] pre;
	private int[] post;
	private Queue<Integer> preorder;
	private Queue<Integer> postorder;
	private int preCounter;
	private int postCounter;

	public DepthFirstOrder(Digraph G) {
		pre = new int[G.V()];
		post = new int[G.V()];
		postorder = new Queue<Integer>();
		preorder = new Queue<Integer>();
		marked = new boolean[G.V()];
		for (int v = 0; v < G.V(); v++)
			if (!marked[v])
				dfs(G, v);
		assert check();
	}

	private void dfs(Digraph G, int v) {
		marked[v] = true;
		pre[v] = preCounter++;
		preorder.enqueue(v);
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
		postorder.enqueue(v);
		post[v] = postCounter++;
	}

	public int pre(int v) {
		validateVertex(v);
		return pre[v];
	}

	public int post(int v) {
		validateVertex(v);
		return post[v];
	}

	public Iterable<Integer> post() {
		return postorder;
	}

	public Iterable<Integer> pre() {
		return preorder;
	}

	public Iterable<Integer> reversePost() {
		Stack<Integer> reverse = new Stack<Integer>();
		for (int v : postorder)
			reverse.push(v);
		return reverse;
	}

	private boolean check() {

		// check that post(v) is consistent with post()
		int r = 0;
		for (int v : post()) {
			if (post(v) != r) {
				System.out.println("post(v) and post() inconsistent");
				return false;
			}
			r++;
		}

		// check that pre(v) is consistent with pre()
		r = 0;
		for (int v : pre()) {
			if (pre(v) != r) {
				System.out.println("pre(v) and pre() inconsistent");
				return false;
			}
			r++;
		}

		return true;
	}
	
	private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
	
	public static void main(String[] args) {
        In in = new In("src/data/tinyDG.txt");
        Digraph G = new Digraph(in);

        DepthFirstOrder dfs = new DepthFirstOrder(G);
        System.out.println("   v  pre post");
        System.out.println("--------------");
        for (int v = 0; v < G.V(); v++) {
            System.out.printf("%4d %4d %4d\n", v, dfs.pre(v), dfs.post(v));
        }

        System.out.print("Preorder:  ");
        for (int v : dfs.pre()) {
            System.out.print(v + " ");
        }
        System.out.println();

        System.out.print("Postorder: ");
        for (int v : dfs.post()) {
            System.out.print(v + " ");
        }
        System.out.println();

        System.out.print("Reverse postorder: ");
        for (int v : dfs.reversePost()) {
            System.out.print(v + " ");
        }
        System.out.println();


    }

}
