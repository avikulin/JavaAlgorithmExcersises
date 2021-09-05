/*package FinalA;*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Graph {
    private final List<Edge>[] graphStorage;
    private final boolean[] mstAddedVertexes;
    private final TreeSet<Edge> mstCandidateEdges; // явно указываем тип, чтобы не тратить время на приведение
    private final int numberOfVertexes;
    private int mstWeight;
    private final boolean isOriented;
    private int startVertexId;
    private int numberOfMstVertices;

    public Graph(int vertexCount, int edgesCount, boolean oriented) {
        graphStorage = new List[vertexCount + 1];
        mstAddedVertexes = new boolean[vertexCount + 1];
        mstCandidateEdges = new TreeSet<>();
        numberOfVertexes = vertexCount;
        isOriented = oriented;
        startVertexId = -1;
        numberOfMstVertices = 0;
        mstWeight = 0;
    }

    public void AddEdge(Edge e) {
        int v1 = e.getVertexA();
        int v2 = e.getVertexB();

        if (v1==v2) return; //не добавляем петли в граф. они не влияют на MST.

        if (graphStorage[v1] == null) {
            graphStorage[v1] = new ArrayList<>();
        }
        graphStorage[v1].add(e);

        if (!isOriented) {
            if (graphStorage[v2] == null) {
                graphStorage[v2] = new ArrayList<>();
            }
            graphStorage[v2].add(e);
        }
    }

    private void addVertexToMST(int vertex) {
        this.mstAddedVertexes[vertex] = true;
        this.numberOfMstVertices++;
        if (this.graphStorage[vertex] == null) return;
        List<Edge> adjVertexes = this.graphStorage[vertex];
        /*for (Edge e : adjVertexes) {
            if (!this.mstAddedVertexes[e.getOppositeVertex(vertex)]) {
                e.setMSTIncludedVertex(vertex);
                this.mstCandidateEdges.add(e);
            }
        }*/
        for (int i=0; i<adjVertexes.size();i++) {
            Edge e = adjVertexes.get(i);
            if (!this.mstAddedVertexes[e.getOppositeVertex(vertex)]) {
                e.setMSTIncludedVertex(vertex);
                this.mstCandidateEdges.add(e);
            }
        }
    }

    public void setStartVertex(int vertex) {
        this.startVertexId = vertex;
    }

    public boolean traverseMst() {
        addVertexToMST(startVertexId);
        while ((this.numberOfMstVertices < this.numberOfVertexes) && (!this.mstCandidateEdges.isEmpty())) {
            Edge maxWeightedEdge = this.mstCandidateEdges.pollLast();
            int end = maxWeightedEdge.getNotMSTVertex();
            if (!this.mstAddedVertexes[end]) {
                addVertexToMST(end);
                this.mstWeight += maxWeightedEdge.getWeight();
            }
        }

        return this.numberOfVertexes == this.numberOfMstVertices;
    }

    public int getMstWeight() {
        return this.mstWeight;
    }
}

class Edge implements Comparable<Edge> {
    private int vertexA;
    private int vertexB;
    private boolean vertexAinMST;
    private boolean vertexBinMST;
    private int weight;

    public Edge(int vertexA, int vertexB, int weight) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.weight = weight;
        this.vertexAinMST = false;
        this.vertexBinMST = false;
    }

    public int getVertexA() {
        return this.vertexA;
    }

    public int getVertexB() {
        return this.vertexB;
    }

    public void setMSTIncludedVertex(int vertex) {
        if (vertex == vertexA)
            this.vertexAinMST = true;
        else
            this.vertexBinMST = true;
    }

    public int getOppositeVertex(int vertex) {
        return (vertex == vertexA) ? vertexB : vertexA;
    }

    public int getNotMSTVertex() {
        if ((!this.vertexAinMST) && (!this.vertexBinMST)) return -1;
        return (this.vertexAinMST) ? this.vertexB : this.vertexA;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Edge edge = (Edge) o;
        /*boolean vertexesAreTheSame = ((this.vertexA == edge.vertexB)&&(this.vertexB == edge.vertexA));
        boolean vertexesAreEqual = ((this.vertexA == edge.vertexA)&&(this.vertexB == edge.vertexB));
        boolean weightsAreEqual = (this.weight == edge.weight);
        return (vertexesAreEqual||vertexesAreTheSame)&&weightsAreEqual;*/
        return vertexA == edge.vertexA &&
                vertexB == edge.vertexB &&
                weight == edge.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexA, vertexB, weight);
    }

    @Override
    public int compareTo(Edge o) {
        if (this.equals(o)) return 0;
        if (this.weight == o.weight) {
            return (this.vertexA > o.vertexB) ? 1 : -1; // для различия ребер с одинаковым весом в контейнере Set<Edge>
        } else {
            return (this.weight > o.weight) ? 1 : -1;
        }
    }
}

public class FinalSolutionA {

    public static String process(String[] input) throws RuntimeException {
        String mainParams = input[0];
        StringTokenizer mainParamsTokens = new StringTokenizer(mainParams);

        int vertexCount = Integer.parseInt(mainParamsTokens.nextToken());
        int edgeCount = Integer.parseInt(mainParamsTokens.nextToken());
        mainParamsTokens = null;

        Graph graph = new Graph(vertexCount, edgeCount, false);
        for (int i = 1; i < edgeCount + 1; i++) {
            StringTokenizer edgeParamsTokens = new StringTokenizer(input[i], " ", true);
            int vertexA = Integer.parseInt(edgeParamsTokens.nextToken());
            String spaceA = edgeParamsTokens.nextToken();
            int vertexB = Integer.parseInt(edgeParamsTokens.nextToken());
            String spaceB = edgeParamsTokens.nextToken();
            int weight = Integer.parseInt(edgeParamsTokens.nextToken());
            edgeParamsTokens = null;
            spaceA = null;
            spaceB = null;
            /*String[] tokens = input[i].split("\\s");
            int vertexA = Integer.parseInt(tokens[0]);
            int vertexB = Integer.parseInt(tokens[1]);
            int weight = Integer.parseInt(tokens[2]);
            tokens = null;*/
            graph.AddEdge(new Edge(vertexA, vertexB, weight));

        }
        graph.setStartVertex(1);
        boolean res = graph.traverseMst();
        return (res) ? Integer.toString(graph.getMstWeight()) : "Oops! I did it again";
    }

    /*public static void profile(String[] input) {
        while (true) {
            process(input);
        }
    }*/

    public static void main(String[] args) throws IOException {
        /*profile(new String[]{
                "1000 80",
                "143 293 8219",
                "532 82 4567",
                "911 734 6423",
                "80 568 6910",
                "765 447 9126",
                "169 952 6911",
                "541 696 948",
                "856 180 3606",
                "815 774 8218",
                "758 847 6739",
                "698 859 741",
                "989 920 2694",
                "981 571 1371",
                "644 615 2295",
                "342 537 3261",
                "597 775 7800",
                "828 975 4900",
                "844 535 2118",
                "763 275 2055",
                "336 836 4126",
                "855 399 7546",
                "329 964 4355",
                "608 809 160",
                "996 564 9943",
                "275 197 4096",
                "8 260 9317",
                "751 113 9817",
                "806 905 9372",
                "809 346 3215",
                "142 662 5664",
                "588 903 4252",
                "388 852 9277",
                "331 9 4828",
                "404 812 2096",
                "930 564 8416",
                "911 731 1727",
                "867 40 5105",
                "586 950 3977",
                "715 763 3848",
                "516 302 400",
                "132 95 4552",
                "371 490 1223",
                "33 843 7032",
                "678 955 9683",
                "172 433 8854",
                "213 292 8802",
                "132 918 5563",
                "227 347 9233",
                "871 34 8345",
                "702 477 8609",
                "6 108 5507",
                "320 924 1087",
                "361 442 1056",
                "117 656 8911",
                "583 637 7921",
                "350 812 1387",
                "324 969 114",
                "533 424 8847",
                "43 774 1046",
                "83 286 3369",
                "777 536 1443",
                "980 993 9123",
                "332 807 8748",
                "485 639 2023",
                "305 480 3525",
                "950 512 4132",
                "58 832 3765",
                "597 552 8485",
                "420 970 6739",
                "164 402 6107",
                "472 825 3306",
                "815 369 4911",
                "375 202 334",
                "936 235 7701",
                "51 429 3761",
                "503 65 9565",
                "602 83 7378",
                "712 358 568",
                "281 678 5069",
                "944 524 71"
        });*/

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String params = reader.readLine();
        StringTokenizer mainParamsTokens = new StringTokenizer(params);
        mainParamsTokens.nextToken(); //сдвинули итератор на 1 токен вправо
        int numberOfEdges = Integer.parseInt(mainParamsTokens.nextToken());

        String[] input = new String[numberOfEdges + 1];
        input[0] = params;
        for (int i = 1; i <= numberOfEdges; i++)
            input[i] = reader.readLine();

        System.out.println(process(input));
    }
}
