/*

Joshua Lambert
10/28/2017

Graph Class with
Breadth first Search
Depth First Search
and supporting FIFO Que


 */


public class Graph {
    private final int WHITE = 1;
    private final int GRAY = 2;
    private final int BLACK = 3;
    private final int NIL = -1;


    int[][] adj;    // adjacency matrix
    int[] d;        // distance array, distance from source to vertex u  BFS
                    // time in the case of DFS
    int[] f;        // Color attribute
    int[] pi;       // Previous Vertex
    private int time;       // time attribute for DFS visit

    private Que Q;          // FIFO Que object for BFS


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


    // construct an graph with the adjacency matrix public Graph(int[][] adj)
    // breadth-first search from s. results in d[], pi[]


    public Graph(int[][] a) {
        adj = a;
        Q = new Que(a.length);
        f = new int[a.length];
        d = new int[a.length];
        pi = new int[a.length];
    }


    public void bfs(int s) {
        // Initialize
        for (int i = 0; i < adj.length; i++) {
            f[i] = WHITE;
            d[i] = Integer.MAX_VALUE;
            pi[i] = NIL;
        }

        f[s] = GRAY;
        d[s] = 0;
        pi[s] = NIL;

        Q.enqueue(s);
        while (!Q.isEmpty()) {
            int vertex = Q.dequeue();
            for (int edge = 0; edge < adj.length; edge++) {
                // scan down the adjacency matrix and add connected vertexes to the que.
                // for each vertex search its edges and change its color to GRAY if its color is WHITE

                if (adj[vertex][edge] == 1)
                    if (f[edge] == WHITE) {
                        f[edge] = GRAY;
                        d[edge] = d[vertex] + 1;
                        pi[edge] = vertex;
                        Q.enqueue(edge);

                    }
                f[vertex] = BLACK;

            }

        }

    }


    // depth-first search. results in d[], f[], pi[]
    public void dfs() {
        for (int vertex = 0; vertex < adj.length; vertex++) {
            f[vertex] = WHITE;
            pi[vertex] = NIL;
        }
        time = 0;
        for (int vertex = 0; vertex < adj.length; vertex++) {
            if (f[vertex] == WHITE)
                dfsVisit(vertex);
        }
    }

    private void dfsVisit(int vertex) {
        time++;
        d[vertex] = time;
        f[vertex] = GRAY;

        for (int edge = 0; edge < adj.length; edge++) {
            if (adj[vertex][edge] == 1) {
                if (f[edge] == WHITE) {
                    pi[edge] = vertex;
                    dfsVisit(edge);
                }
            }
        }

        f[vertex] = BLACK;
        time++;
        f[vertex] = time;

    }

    // FIFO Que
    class Que {
        int head = 0;
        int tail = 0;
        int[] que;

        Que(int size) {
            que = new int[size];
        }

        void enqueue(int s) {
            que[tail] = s;

            if (tail == que.length - 1) {
                tail = 0;
            } else {
                tail++;
            }

        }

        int dequeue() {
            int x = que[head];
            if (head == que.length - 1) {
                head = 0;
            } else {
                head++;
            }

            return x;
        }

        boolean isEmpty() {
            return head == tail;

        }
    }


}
