import java.util.*;

public class HealthNetGraph {

    private ArrayList<ArrayList<Integer>> graph;
    private int vertices;

    HealthNetGraph(int v) {
        vertices = v;
        graph = new ArrayList<>();

        for (int i = 0; i < v; i++) {
            graph.add(new ArrayList<>());
        }
    }

    void addRoute(int source, int destination) {
        graph.get(source).add(destination);
        graph.get(destination).add(source);
    }

    void BFS(int start) {

        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {

            int current = queue.poll();
            System.out.print(current + " ");

            for (int next : graph.get(current)) {

                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
    }

    void DFS(int current, boolean[] visited) {

        visited[current] = true;
        System.out.print(current + " ");

        for (int next : graph.get(current)) {

            if (!visited[next]) {
                DFS(next, visited);
            }
        }
    }

    public static void main(String[] args) {

        HealthNetGraph hospital = new HealthNetGraph(6);

        hospital.addRoute(0, 1);
        hospital.addRoute(0, 2);
        hospital.addRoute(1, 3);
        hospital.addRoute(2, 4);
        hospital.addRoute(4, 5);

        System.out.println("BFS Traversal:");
        hospital.BFS(0);

        System.out.println();

        System.out.println("\nDFS Traversal:");

        boolean[] visited = new boolean[6];
        hospital.DFS(0, visited);
    }
}