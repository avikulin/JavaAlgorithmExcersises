package ProblemE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Graph implements Iterable<Vertex> {
    private final TreeMap<Vertex, Set<Vertex>> graphStorage;
    private final Vertex[] vertexStorage;
    private int numberOfComponents;
    private final boolean isOriented;
    private int colorOfCollectedVertexes;

    private Vertex startVertex;

    public Graph(int vertexCount, int edgesCount, boolean oriented) {
        graphStorage = new TreeMap<>();
        vertexStorage = new Vertex[vertexCount + 1];
        isOriented = oriented;
        startVertex = null;
        numberOfComponents = 0;
        colorOfCollectedVertexes = 0;
    }

    public String getComponentsSignatureString(){
        StringJoiner res = new StringJoiner("\n");

        for(int idx=1; idx < vertexStorage.length; idx++) {
            StringJoiner subRes = new StringJoiner(" ");
            // ищем первую (непокрашенную) вершину следующего компонента связности.
            if (vertexStorage[idx]==null) {
                numberOfComponents++;
                subRes.add(String.valueOf(idx));
                res.add(subRes.toString());
                continue;
            }

            if (!vertexStorage[idx].hasColor()) {
                numberOfComponents++;
                setStartVertex(vertexStorage[idx]);
                //обходим все вершины компонента связности, начиная с заданной
                for (Vertex vertex : this) {
                    vertex.setVertexColor(numberOfComponents);
                    subRes.add(String.valueOf(vertex.getId()));
                }
                // добавляем вершины найденного компонента связности в коллекцию
                if (subRes.length()!=0)
                    res.add(subRes.toString());
            }
        }
        String numComponents = String.valueOf(numberOfComponents);
        return numComponents + "\n" + res.toString();
    }

    private Vertex getVertex(int idx){
        if (vertexStorage[idx]==null) {
            Vertex v = new Vertex(idx);
            vertexStorage[idx] = v;
            return v;
        }
        return  vertexStorage[idx];
    }

    public void AddEdge(int idA, int idB){
        Vertex v1 = getVertex(idA);
        Vertex v2 = getVertex(idB);
        graphStorage.putIfAbsent(v1,new TreeSet<>());
        graphStorage.get(v1).add(v2);

        if (!isOriented) {
            graphStorage.putIfAbsent(v2,new TreeSet<>());
            graphStorage.get(v2).add(v1);
        }
    }

    public void setStartVertex(Vertex vertex) {startVertex = vertex;}

    @Override
    public Iterator<Vertex> iterator() {
        return new DepthFirstIterator(this.graphStorage, startVertex, colorOfCollectedVertexes);
    }
}

class Vertex implements Comparable<Vertex>{
    private final int Id;
    private int vertexColor;
    private boolean colored;
    Vertex(int ID){
        Id = ID;
        vertexColor = 0;
        colored = false;
    }
    public int getId() {return Id;}
    public void setVertexColor(int value) {colored = true; vertexColor = value;}
    public boolean hasColor(){return colored;}

    @Override
    public int compareTo(Vertex o) {
        if (Id == o.Id) return 0;
        return Id > o.Id ? 1: -1;
    }
}

class DepthFirstIterator implements Iterator<Vertex> {
    private final TreeMap<Vertex, Set<Vertex>> graphVertexStorage;
    private final Stack<Vertex> vertexQueue;
    private Vertex currentVertex;
    int colorForVisitedVertex;

    public DepthFirstIterator(TreeMap<Vertex, Set<Vertex>> vertexStorage, Vertex startVertx, int colorOfCollectedVertexes) {
        graphVertexStorage = vertexStorage;
        vertexQueue = new Stack<>();
        vertexQueue.add(startVertx);
        currentVertex = null;
        colorForVisitedVertex = colorOfCollectedVertexes;
//        System.out.println(String.format("New iteration: from %d", startVertx.getId()));
    }

    @Override
    public boolean hasNext() {
        return !vertexQueue.isEmpty();
    }

    @Override
    public Vertex next() {
        currentVertex = vertexQueue.pop();
//        System.out.println(String.format("\tvertex %d", currentVertex.getId()));
        currentVertex.setVertexColor(colorForVisitedVertex);

        Set<Vertex> adjVertexes = graphVertexStorage.get(currentVertex);
        Iterator<Vertex> reverseIterator = ((TreeSet<Vertex>)adjVertexes).descendingIterator();

        while (reverseIterator.hasNext()){
            Vertex vertex = reverseIterator.next();
            if (!vertex.hasColor()) {
                vertexQueue.add(vertex);
            }
        }

        // очистка очереди от посещенных узлов
        while ((!vertexQueue.empty()) && vertexQueue.peek().hasColor()){
            Vertex v = vertexQueue.pop();
//            System.out.println(String.format("\t\tcleared %d", v.getId()));
        }

        return currentVertex;
    }
}

public class SolutionE {

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

        return graph.getComponentsSignatureString();
    }

    private static void profile(){
        while (true){
            process(new String[]{
                    "6 3",
                    "1 2",
                    "6 5",
                    "2 3"
            });
        }
    }
    public static void main(String[] args) throws IOException {
//        profile();

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
