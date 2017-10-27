public class Graph {


    int[][] adj; // adjacency matrix
    int[] d;
    int[] f;
    int[] pi;

    public Graph(int[][] a) {
    }


    // construct an graph with the adjacency matrix public Graph(int[][] adj)
    // breadth-first search from s. results in d[], pi[]


    // a test program
    public static void main(String[] args) {
        int[][] a = {{0, 0, 1, 1, 0}, {0, 0, 1, 0, 1}, {1, 1, 0, 1, 0}, {1, 0, 1, 0, 0}, {0, 1, 0, 0, 0}};
        Graph g = new Graph(a);
        g.bfs(3);
        for (int i = 0; i < g.d.length; i++) {
            System.out.print(g.d[i] + " ");
        }
        System.out.println();
        g.dfs();
        for (int i = 0; i < g.f.length; i++) {
            System.out.print(g.d[i] + "/" + g.f[i] + " ");
        }
        System.out.println();
    }

    public void bfs(int s) {
    }

    // depth-first search. results in d[], f[], pi[]
    public void dfs() {
    }


}
