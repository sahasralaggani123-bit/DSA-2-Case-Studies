import java.util.*;

public class QuickBite {

    private int vertices;
    private LinkedList<Integer>[] graph;

    // Constructor
    QuickBite(int v) {
        vertices = v;
        graph = new LinkedList[v];

        for (int i = 0; i < v; i++) {
            graph[i] = new LinkedList<>();
        }
    }

    // Add route between locations
    void addRoute(int source, int destination) {
        graph[source].add(destination);
        graph[destination].add(source);
    }

    // BFS Traversal
    void BFS(int start) {

        boolean visited[] = new boolean[vertices];

        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {

            int location = queue.poll();

            System.out.print(location + " ");

            for (int next : graph[location]) {

                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }

    // DFS Traversal
    void DFS(int location, boolean visited[]) {

        visited[location] = true;

        System.out.print(location + " ");

        for (int next : graph[location]) {

            if (!visited[next]) {
                DFS(next, visited);
            }
        }
    }

    public static void main(String[] args) {

        QuickBite deliveryNetwork = new QuickBite(6);

        deliveryNetwork.addRoute(0, 1);
        deliveryNetwork.addRoute(0, 2);
        deliveryNetwork.addRoute(1, 3);
        deliveryNetwork.addRoute(2, 4);
        deliveryNetwork.addRoute(4, 5);

        System.out.println("BFS Traversal:");
        deliveryNetwork.BFS(0);

        System.out.println("\n");

        System.out.println("DFS Traversal:");
        boolean visited[] = new boolean[6];
        deliveryNetwork.DFS(0, visited);
    }
}