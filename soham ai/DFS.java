
import java.util.*;

public class DFS {
    private static ArrayList<Integer> bfs;
    static void dfsTraversal(int v, boolean visited[], LinkedList<Integer> adj[])
    {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        // Recur for all the vertices adjacent to this
        // vertex

        for(Integer num:adj[v]){
            if(!visited[num])
                dfsTraversal(num, visited, adj);
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the size of the graph: ");
        int size = scan.nextInt();
        Graph g = new Graph(size);
        int edgeSize=0, s=0,e=0;
        System.out.println("Enter the number of edges of the graph");
        edgeSize = scan.nextInt();
        for (int i = 0; i < edgeSize; i++) {
            System.out.println("Enter the starting vertex and ending vertex of the edge");
            s=scan.nextInt();
            e=scan.nextInt();
            g.addEdge(s,e);
        }

        LinkedList<Integer> adj[] = g.getAdj();
        boolean[] visited = new boolean[size];

        dfsTraversal(0, visited, adj);
    }
}
