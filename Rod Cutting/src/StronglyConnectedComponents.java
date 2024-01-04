import java.util.*;

public class StronglyConnectedComponents {
    // Number of antennas
    static int V;

    // Array of antennas
    static ArrayList<Integer>[] antenna;

    // Array to store visited status of a node
    static boolean[] visited;

    // Function to perform DFS traversal
    static void dfs(int node) {
        // Mark the current node as visited
        visited[node] = true;

        // Recur for all the vertices adjacent to this vertex
        antenna[node].stream().filter((i) -> (!visited[i])).forEachOrdered((i) -> {
            dfs(i);
        });
    }

    // Function to check if the given graph is strongly connected
    static boolean isStronglyConnected() {
        // Mark all the vertices as not visited
        visited = new boolean[V];
        Arrays.fill(visited, false);

        // Do a DFS traversal starting from any random node
        dfs(0);

        // If DFS traversal doesn't visit all nodes, then return false
        for (int i = 0; i < V; i++)
            if (!visited[i])
                return false;

        // Create a reversed graph
        ArrayList<Integer>[] reversedAntenna = new ArrayList[V];
        for (int i = 0; i < V; i++)
            reversedAntenna[i] = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            for (int j : antenna[i]) {
                reversedAntenna[j].add(i);
            }
        }

        // Mark all the vertices as not visited
        visited = new boolean[V];
        Arrays.fill(visited, false);

        // Do a DFS traversal of reversed graph from same node
        dfs(0);

        // If all vertices are not visited in second DFS traversal, then return false
        for (int i = 0; i < V; i++)
            if (!visited[i])
                return false;

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Number of antennas
        V = scanner.nextInt();

        // Number of cables
        int E = scanner.nextInt();

        // Adjacency list
        antenna = new ArrayList[V];
        for (int i = 0; i < V; i++)
            antenna[i] = new ArrayList<>();

        // Adding edges to the graph
        for (int i = 0; i < E; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            antenna[src].add(dest);
        }

        // Check if the graph is strongly connected or not
        if (isStronglyConnected())
            System.out.println("yes");
        else
            System.out.println("no");
    }
}