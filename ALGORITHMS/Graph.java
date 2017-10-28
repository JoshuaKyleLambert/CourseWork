public class Graph {


    int[][] adj;    // adjacency matrix
    int[] d;        // distance array, distance from source to vertex u
    int[] f;        // Color attribute
    int[] pi;       // Previous Vertex

    public Graph(int[][] a) {
        adj = a;
    }

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

    public void bfs(int s) {
    }

    // depth-first search. results in d[], f[], pi[]
    public void dfs() {
    }

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
            if ( head == que.length - 1 ) {
                head = 0;
            } else {
                head++;
            }

            return x;
        }

    }


}
