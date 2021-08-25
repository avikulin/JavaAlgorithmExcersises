package ProblemD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Graph implements Iterable<Integer> {
    private final TreeMap<Integer, Set<Integer>> graphStorage;
    private final int maxEdges;
    private int numberOfEdges;
    private final boolean isOriented;
    private int startVertex;

    public Graph(int vertexCount, int edgesCount, boolean oriented) {
        graphStorage = new TreeMap<>();
        maxEdges = edgesCount;
        numberOfEdges = 0;
        isOriented = oriented;
        startVertex = -1;
        for (int i = 1; i <= vertexCount; i++)
            graphStorage.put(i, new TreeSet<>());
    }

    public void AddEdge(Edge e) throws RuntimeException {
        if (numberOfEdges == maxEdges) throw new RuntimeException("Max number of edges exceeded");
        graphStorage.get(e.getVertexA()).add(e.getVertexB());
        if (!isOriented)
            graphStorage.get(e.getVertexB()).add(e.getVertexA());
        numberOfEdges++;
    }

    public void setStartVertex(int vertex) {
        startVertex = vertex;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new DepthFirstIterator(this.graphStorage, startVertex);
    }
}

class Edge {
    int vertexA;
    int vertexB;

    public Edge(int a, int b) {
        vertexA = a;
        vertexB = b;
    }

    public int getVertexA() {
        return vertexA;
    }

    public int getVertexB() {
        return vertexB;
    }

    @Override
    public String toString() {
        return String.format("%d %d", vertexA, vertexB);
    }
}

class DepthFirstIterator implements Iterator<Integer> {
    private final TreeMap<Integer, Set<Integer>> graphVertexStorage;
    private final Set<Integer> visitedVertexes;
    private final Queue<Integer> vertexQueue;
    private int currentVertex;

    public DepthFirstIterator(TreeMap<Integer, Set<Integer>> vertexStorage, int startVertx) {
        graphVertexStorage = vertexStorage;
        visitedVertexes = new HashSet<>();
        vertexQueue = new LinkedList<>();
        vertexQueue.add(startVertx);
        currentVertex = -1;
    }

    @Override
    public boolean hasNext() {
        return !vertexQueue.isEmpty();
    }

    @Override
    public Integer next() {

        currentVertex = vertexQueue.poll();
        visitedVertexes.add(currentVertex);

        Set<Integer> adjVertexes = graphVertexStorage.get(currentVertex);
        for (Integer vertex : adjVertexes) {
            if (!visitedVertexes.contains(vertex)) {
                vertexQueue.add(vertex);
            }
        }

        // очистка очереди от посещенных узлов
        while (!vertexQueue.isEmpty() && visitedVertexes.contains(vertexQueue.peek())) {
            vertexQueue.poll();
        }
        return currentVertex;
    }
}

public class SolutionD {

    public static String process(String[] input) throws RuntimeException {
        String mainParams = input[0];
        StringTokenizer mainParamsTokens = new StringTokenizer(mainParams);

        int vertexCount = Integer.parseInt(mainParamsTokens.nextToken());
        int edgeCount = Integer.parseInt(mainParamsTokens.nextToken());

        Graph graph = new Graph(vertexCount, edgeCount, false);
        for (int i = 1; i < edgeCount + 1; i++) {
            StringTokenizer edgeParamsTokens = new StringTokenizer(input[i]);
            int vertexA = Integer.parseInt(edgeParamsTokens.nextToken());
            int vertexB = Integer.parseInt(edgeParamsTokens.nextToken());
            graph.AddEdge(new Edge(vertexA, vertexB));
        }

        int startTraverseFrom = Integer.parseInt(input[input.length - 1]);
        graph.setStartVertex(startTraverseFrom);

        StringJoiner resJoiner = new StringJoiner(" ");
        for (Integer vertex : graph)
            resJoiner.add(String.valueOf(vertex));
        return resJoiner.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String params = reader.readLine();
        StringTokenizer mainParamsTokens = new StringTokenizer(params);
        Integer.parseInt(mainParamsTokens.nextToken()); //сдвинули итератор на 1 токен вправо
        int numberOfEdges = Integer.parseInt(mainParamsTokens.nextToken());

        String[] input = new String[numberOfEdges + 2];
        input[0] = params;
        for (int i = 1; i <= numberOfEdges; i++)
            input[i] = reader.readLine();
        input[input.length - 1] = reader.readLine();

        System.out.println(process(input));
    }
}
