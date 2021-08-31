package ProblemG;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Graph {
    private final List<Path>[] graphStorage;
    private final int[] pathStorage;
    private final int[] originStorage;
    private int numberOfEdges;
    private int numberOfVertexes;
    private final boolean isOriented;
    private int startVertexId;
    private int diameterValue;

    public Graph(int vertexCount, int edgesCount, boolean oriented) {
        graphStorage    = new List[vertexCount + 1];
        pathStorage     = new  int[vertexCount + 1];
        originStorage   = new  int[vertexCount + 1];
        numberOfEdges = edgesCount;
        numberOfVertexes = vertexCount;
        isOriented = oriented;
        startVertexId = -1;
        diameterValue = 0;
    }

    public void AddEdge(int v1, int v2){
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

    public void TraverseRoutes(){
        Queue<Integer> vertexQueue = new LinkedList<>();
        boolean[] visitedVertexes = new boolean[numberOfVertexes + 1];
        vertexQueue.add(this.startVertexId);
        Arrays.fill(this.pathStorage, Integer.MAX_VALUE);
        Arrays.fill(this.originStorage, -1);
        pathStorage[0] = -1;
        pathStorage[this.startVertexId] = 0;

        while (!vertexQueue.isEmpty()) {
            int currentVertex = vertexQueue.poll();

            int currentPath = pathStorage[currentVertex];
            visitedVertexes[currentVertex] = true;

            List<Path> adjVertexes = graphStorage[currentVertex];
            //exit if there are no edges from the current vertex.
            if (adjVertexes == null) return;

            for (Path path : adjVertexes) {
                int vertexId = path.getDestination();
                int possiblePathTo = currentPath + path.distance;
                int actualPathTo = pathStorage[vertexId];
                if (possiblePathTo < actualPathTo) {
                    pathStorage[vertexId] = possiblePathTo;
                    originStorage[vertexId] = currentVertex;
                    if (this.diameterValue < possiblePathTo)
                        this.diameterValue = possiblePathTo;
                }
                if (visitedVertexes[vertexId] == false) {
                    vertexQueue.add(vertexId);
                }
            }
        }
    }

    public int getMinDistanceTo(int destinationId){
        int distance = this.pathStorage[destinationId];
        return (distance==Integer.MAX_VALUE)?-1:distance;
    }

    public int getDiameterValue(){return diameterValue;}
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

public class SolutionG {

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
            graph.AddEdge(vertexA, vertexB);
        }

        String startAndDestinationIDs =input[input.length - 1];
        StringTokenizer startAndDestinationTokens = new StringTokenizer(startAndDestinationIDs);
        int pathStartId = Integer.parseInt(startAndDestinationTokens.nextToken());
        graph.setStartVertex(pathStartId);
        graph.TraverseRoutes();
        return String.valueOf(graph.getDiameterValue());
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
