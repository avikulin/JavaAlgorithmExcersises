package ProblemA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Graph implements Iterable<Edge> {
    private TreeMap<Integer, Set<Integer>> graphStorage;
    private int maxEdges;
    private int numberOfEdges;
    private boolean isOriented;

    public Graph(int vertexCount, int edgesCount, boolean oriented) {
        graphStorage = new TreeMap<>();
        maxEdges = edgesCount;
        numberOfEdges = 0;
        isOriented = oriented;

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

    public Iterator<Map.Entry<Integer, Set<Integer>>> getMainVertexIterator() {
        return graphStorage.entrySet().iterator();
    }

    @Override
    public Iterator<Edge> iterator() {
        return new EdgeIterator(this.graphStorage);
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

class EdgeIterator implements Iterator<Edge> {
    private TreeMap<Integer, Set<Integer>> graphVertexStorage;
    private Iterator<Map.Entry<Integer, Set<Integer>>> vertexIterator;
    private Iterator<Integer> edgeIterator;
    private Map.Entry<Integer, Set<Integer>> currentVertexEdges;
    private int vertexA;
    private int vertexB;
    private int linearPosition;

    public EdgeIterator(TreeMap<Integer, Set<Integer>> vertexStorage) {
        graphVertexStorage = vertexStorage;
        vertexIterator = graphVertexStorage.entrySet().iterator();
        edgeIterator = null;
        vertexA = -1;
        vertexB = -1;
        linearPosition = 0;
    }

    public int getCurrentMainSequenceLength() {
        return graphVertexStorage.size();
    }

    public int getCurrentSubSequenceLength() {
        return currentVertexEdges.getValue().size();
    }

    public int getAbsolutePosition() {
        return linearPosition;
    }

    @Override
    public boolean hasNext() {
        if ((edgeIterator == null) || (!edgeIterator.hasNext())) {
            return vertexIterator.hasNext();
        }
        return edgeIterator.hasNext();
    }

    @Override
    public Edge next() {
        if ((edgeIterator == null) || (!edgeIterator.hasNext())) {
            currentVertexEdges = vertexIterator.next();
            vertexA = currentVertexEdges.getKey();
            edgeIterator = currentVertexEdges.getValue().iterator();
        }
        vertexB = edgeIterator.next();
        linearPosition++;
        return new Edge(vertexA, vertexB);
    }
}

public class SolutionA {

    public static String process(String[] input) throws RuntimeException {
        String mainParams = input[0];
        StringTokenizer mainParamsTokens = new StringTokenizer(mainParams);

        int vertexCount = Integer.parseInt(mainParamsTokens.nextToken());
        int edgeCount = Integer.parseInt(mainParamsTokens.nextToken());

        Graph graph = new Graph(vertexCount, edgeCount, true);
        for (int i = 1; i < input.length; i++) {
            StringTokenizer edgeParamsTokens = new StringTokenizer(input[i]);
            int vertexA = Integer.parseInt(edgeParamsTokens.nextToken());
            int vertexB = Integer.parseInt(edgeParamsTokens.nextToken());
            graph.AddEdge(new Edge(vertexA, vertexB));
        }

        StringJoiner res = new StringJoiner("\n");
        Iterator<Map.Entry<Integer, Set<Integer>>> adjListMainIterator = graph.getMainVertexIterator();
        while (adjListMainIterator.hasNext()) {
            Map.Entry<Integer, Set<Integer>> graphAdjointList = adjListMainIterator.next();
            int listSize = graphAdjointList.getValue().size();
            if (listSize > 0) {
                StringJoiner joiner = new StringJoiner(" ");
                Iterator<Integer> endVertexIterator = graphAdjointList.getValue().iterator();
                joiner.add(String.valueOf(listSize));
                while (endVertexIterator.hasNext()) {
                    joiner.add(String.valueOf(endVertexIterator.next()));
                }
                res.add(joiner.toString());
            } else {
                res.add(String.valueOf(listSize));
            }
        }
        return res.toString();
    }

    public static void main(String[] args) throws IOException, RuntimeException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String params = reader.readLine();
        StringTokenizer mainParamsTokens = new StringTokenizer(params);
        int numberOfVertises = Integer.parseInt(mainParamsTokens.nextToken());
        int numberOfEdges = Integer.parseInt(mainParamsTokens.nextToken());

        String[] input = new String[numberOfEdges + 1];
        input[0] = params;
        for (int i = 1; i <= numberOfEdges; i++)
            input[i] = reader.readLine();
        System.out.println(process(input));
    }
}
