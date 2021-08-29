package ProblemF;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Graph implements Iterable<Integer> {
    private final List<Path>[] graphStorage;
    private final int[] pathStorage;
    private final int[] originStorage;
    private int numberOfEdges;
    private int numberOfVertexes;
    private final boolean isOriented;
    private int startVertexId;

    public Graph(int vertexCount, int edgesCount, boolean oriented) {
        graphStorage  = new List[vertexCount + 1];
        pathStorage   = new  int[vertexCount + 1];
        originStorage = new  int[vertexCount + 1];
        numberOfEdges = edgesCount;
        numberOfVertexes = vertexCount;
        isOriented = oriented;
        startVertexId = -1;
    }

    public void AddEdge(Edge e) throws RuntimeException {
        int v1 = e.getVertexA();
        int v2 = e.getVertexB();
        if (graphStorage[v1]==null){
            graphStorage[v1] = new LinkedList<>();
        }
        graphStorage[v1].add(new Path(v2,1));
        if (!isOriented){
            if (graphStorage[v2]==null){
                graphStorage[v2] = new LinkedList<>();
            }
            graphStorage[v2].add(new Path(v1,1));
        }
    }

    public void setStartVertex(int vertex) {
        startVertexId = vertex;
    }

    public int getMinDistanceTo(int destinationId){
        /*int res = 0;
        int currentVertex = destinationId;
        int currentOrigin = originStorage[currentVertex];
        while (currentOrigin!=startVertexId){
            if (originStorage[currentVertex]==-1) return -1;
            res += pathStorage[currentVertex];
            currentOrigin = originStorage[currentVertex];
            currentVertex = currentOrigin;
        }*/

        int distance = this.pathStorage[destinationId];
        return (distance==Integer.MAX_VALUE)?-1:distance;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new DijkstraIterator(this.graphStorage, this.pathStorage, this.originStorage, startVertexId);
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

class Path{
    int to;
    int distance;
    public Path(int vertexId, int value){
        to = vertexId;
        distance = value;
    }
    public int getDestination(){return to;}
    public int getDistance(){return distance;}
}

class DijkstraIterator implements Iterator<Integer> {
    private final List<Path>[] vertexStorage;
    private final int[] pathStorage;
    private final int[] originStorage;
    private final boolean[] visitedVertexes;
    private final Queue<Integer> vertexQueue;
    private int startVertexId;
    private int currentVertex;


    public DijkstraIterator( List<Path>[] vertexStorage, int[] pathStorage, int[] originStorage, int startVertexId) {
        this.vertexStorage = vertexStorage;
        this.pathStorage = pathStorage;
        this.originStorage = originStorage;
        visitedVertexes = new boolean[vertexStorage.length];
        vertexQueue = new LinkedList<>();
        this.startVertexId = startVertexId;
        vertexQueue.add(this.startVertexId);
        Arrays.fill(this.pathStorage, Integer.MAX_VALUE);
        Arrays.fill(this.originStorage, -1);
        pathStorage[0] = -1;
        pathStorage[this.startVertexId] = 0;
        currentVertex = -1;
    }

    @Override
    public boolean hasNext() {
        return !vertexQueue.isEmpty();
    }

    @Override
    public Integer next() {
        currentVertex = vertexQueue.poll();
        int currentPath = pathStorage[currentVertex];
        visitedVertexes[currentVertex] = true;

        int currentMinPathTo = Integer.MAX_VALUE;
        int possibleDestTo = -1;

        List<Path> adjVertexes = vertexStorage[currentVertex];
        //exit if there are no edges from the current vertex.
        if (adjVertexes==null) return currentVertex;

        for (Path path : adjVertexes) {
            int vertexId = path.getDestination();
            int distanceTo = path.getDistance();
            int possiblePathTo = currentPath + path.distance;
            int actualPathTo = pathStorage[vertexId];
            if (possiblePathTo < actualPathTo){
                pathStorage[vertexId] = possiblePathTo;
                originStorage[vertexId] = currentVertex;
            }
            if (visitedVertexes[vertexId]==false) {
                if(distanceTo < currentMinPathTo){
                    currentMinPathTo = distanceTo;
                    possibleDestTo = vertexId;
                }
            }
        }
        if (possibleDestTo != -1) {
            vertexQueue.add(possibleDestTo);
        }
        return currentVertex;
    }
}

public class SolutionF {

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

        String startAndDestinationIDs =input[input.length - 1];
        StringTokenizer startAndDestinationTokens = new StringTokenizer(startAndDestinationIDs);
        int pathStartId = Integer.parseInt(startAndDestinationTokens.nextToken());
        int pathDestId =  Integer.parseInt(startAndDestinationTokens.nextToken());
        graph.setStartVertex(pathStartId);
        for (Integer vertex : graph){}
        return String.valueOf(graph.getMinDistanceTo(pathDestId));
    }

    private static void profile(String[] input){
        while (true){
            process(input);
        }
    }
    public static void main(String[] args) throws IOException {
        /*String[] testData =  new String[]{
                                            "5 5" ,
                                            "2 4" ,
                                            "3 5" ,
                                            "2 1" ,
                                            "2 3" ,
                                            "4 5" ,
                                            "1 5"
                                        };
        profile(testData);*/

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
