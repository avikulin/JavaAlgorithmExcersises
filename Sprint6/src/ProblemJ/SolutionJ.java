package ProblemJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Graph {
    private final List<Integer>[] graphStorage;
    private final boolean[] visitedVertexes;
    private final boolean[] processedVertexes;
    private final Stack<Integer> topologyStore;
    private int numberOfEdges;
    private int numberOfVertexes;
    private final boolean isOriented;
    private int startVertexId;

    public Graph(int vertexCount, int edgesCount, boolean oriented) {
        graphStorage     = new List[vertexCount + 1];
        visitedVertexes  = new  boolean[vertexCount + 1];
        processedVertexes = new  boolean[vertexCount + 1];
        topologyStore = new Stack<>();
        numberOfEdges = edgesCount;
        numberOfVertexes = vertexCount;
        isOriented = oriented;
        startVertexId = -1;
    }

    public void AddEdge(int v1, int v2){
        if (graphStorage[v1]==null){
            graphStorage[v1] = new ArrayList<>();
        }
        graphStorage[v1].add(v2);

        if (!isOriented){
            if (graphStorage[v2]==null){
                graphStorage[v2] = new ArrayList<>();
            }
            graphStorage[v2].add(v1);
        }
    }

    public void setStartVertex(int vertex) {
        startVertexId = vertex;
    }

    public void traverseDFS(){
        Stack<Integer> vertexQueue = new Stack<>();
        vertexQueue.add(this.startVertexId);
        int time=-1;
        while (!vertexQueue.isEmpty()) {
            int currentVertex = vertexQueue.peek();
            if (!visitedVertexes[currentVertex]) {
                time++;
                visitedVertexes[currentVertex] = true;
            } else {
                if(!processedVertexes[currentVertex]){
                    time++;
                    processedVertexes[currentVertex] = true;
                    topologyStore.push(currentVertex);
                }
                vertexQueue.pop();
                continue;
            }

            List<Integer> adjVertexes = graphStorage[currentVertex];
            if (adjVertexes != null) {
                for (int i=0; i< adjVertexes.size(); i++) {
                    int vertexId = adjVertexes.get(i);
                    if (!visitedVertexes[vertexId]) {
                        vertexQueue.push(vertexId);
                    }
                }
            }
        }
    }

    public String topologicalOrderStr(){
        for (int i=1; i<this.numberOfVertexes+1; i++){
            if (!this.processedVertexes[i]){
                this.setStartVertex(i);
                this.traverseDFS();
            }
        }
        StringJoiner res = new StringJoiner(" ");
        while (!this.topologyStore.isEmpty()){
            res.add(Integer.toString(topologyStore.pop()));
        }
        return res.toString();
    }
}

public class SolutionJ {

    public static String process(String[] input) throws RuntimeException {
        String mainParams = input[0];
        StringTokenizer mainParamsTokens = new StringTokenizer(mainParams);

        int vertexCount = Integer.parseInt(mainParamsTokens.nextToken());
        int edgeCount = Integer.parseInt(mainParamsTokens.nextToken());
        mainParamsTokens = null;

        Graph graph = new Graph(vertexCount, edgeCount, true);
        for (int i = 1; i < edgeCount + 1; i++) {
            StringTokenizer edgeParamsTokens = new StringTokenizer(input[i]);
            int vertexA = Integer.parseInt(edgeParamsTokens.nextToken());
            int vertexB = Integer.parseInt(edgeParamsTokens.nextToken());
            graph.AddEdge(vertexA, vertexB);
            edgeParamsTokens = null;
        }
        /*graph.setStartVertex(1);
        graph.traverseDFS();*/
        return graph.topologicalOrderStr();
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

        System.out.println(process(input));
    }
}
