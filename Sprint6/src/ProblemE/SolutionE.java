package ProblemE;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Graph {
    private final List<Integer>[] graphStorage;
    private final int[] vertexComponentMarker;
    private final boolean isOriented;


    public Graph(int vertexCount, int edgesCount, boolean oriented) {
        graphStorage = new List[vertexCount + 1];
        vertexComponentMarker = new int[vertexCount + 1];
        isOriented = oriented;
    }

    private void exploreVerticesDFS(int startVertex, int markerCode){
        Stack<Integer> vertexQueue = new Stack<>();
        Set<Integer> visitedVertices = new HashSet<>();
        vertexQueue.push(startVertex);
        while (!vertexQueue.isEmpty()) {
            int currentVertexId = vertexQueue.pop();
            visitedVertices.add(currentVertexId);
            vertexComponentMarker[currentVertexId] = markerCode;
            if (graphStorage[currentVertexId] != null) {
                List<Integer> adjVertices = graphStorage[currentVertexId];
                Iterator<Integer> it = adjVertices.iterator();
                while (it.hasNext()) {
                    int vertexId=it.next();
                    if (!visitedVertices.contains(vertexId)) {
                        vertexQueue.add(vertexId);
                    }
                }
                it = null;
            }
        }

        vertexQueue = null;
        visitedVertices = null;
    }

    public String getComponentsSignatureString() {
        int numberOfComponents = 0;
        for (int idx = 1; idx < vertexComponentMarker.length; idx++) {
            // search for next unmarked vertex
            if (vertexComponentMarker[idx] == 0) {
                numberOfComponents++;
                // depth-first iterating through linked vertexes
                exploreVerticesDFS(idx, numberOfComponents);
            }
        }

        StringJoiner[] subRes = new StringJoiner[numberOfComponents+1];
        subRes[0] = null;
        for (int idx = 1; idx < vertexComponentMarker.length; idx++) {
            int componentIdx = vertexComponentMarker[idx];
            if (subRes[componentIdx]==null)
                subRes[componentIdx] = new StringJoiner(" ");
                subRes[componentIdx].add(String.valueOf(idx));
        }
        StringJoiner res = new StringJoiner("\n");
        res.add(String.valueOf(numberOfComponents));
        for (int i=1; i<numberOfComponents + 1;i++){
            res.add(subRes[i].toString());
            subRes[i] = null;

        }
        return res.toString();
    }

    public void AddEdge(int idA, int idB) {
        if (graphStorage[idA] == null)
            graphStorage[idA] = new ArrayList<>();

        graphStorage[idA].add(idB);
        if (!isOriented) {
            if (graphStorage[idB] == null)
                graphStorage[idB] = new ArrayList<>();

            graphStorage[idB].add(idA);
        }
    }
}

public class SolutionE {

    public static String process(String[] input) throws RuntimeException {
        String mainParams = input[0];
        StringTokenizer mainParamsTokens = new StringTokenizer(mainParams);

        int vertexCount = Integer.parseInt(mainParamsTokens.nextToken());
        int edgeCount = Integer.parseInt(mainParamsTokens.nextToken());
        mainParamsTokens = null;

        Graph graph = new Graph(vertexCount, edgeCount, false);
        for (int i = 1; i < edgeCount + 1; i++) {
            StringTokenizer edgeParamsTokens = new StringTokenizer(input[i]);
            int vertexA = Integer.parseInt(edgeParamsTokens.nextToken());
            int vertexB = Integer.parseInt(edgeParamsTokens.nextToken());
            edgeParamsTokens = null;
            graph.AddEdge(vertexA, vertexB);
        }
        return graph.getComponentsSignatureString();
    }

    private static void profile(String[] p) {
        while (true) {
            process(p);
        }
    }

    public static void main(String[] args) throws IOException {
        /*String[] p = new String[]{  "80929 86",
                                    "43022 60397" ,
                                    "37953 36846" ,
                                    "44772 36751" ,
                                    "28376 77977" ,
                                    "17404 32156" ,
                                    "77202 37034" ,
                                    "47257 25410" ,
                                    "17761 61364" ,
                                    "51297 77323" ,
                                    "15278 48992" ,
                                    "73180 57545" ,
                                    "61334 27119" ,
                                    "24974 21611" ,
                                    "11155 17497" ,
                                    "71561 13406" ,
                                    "10407 22203" ,
                                    "9370 73109" ,
                                    "24738 74874" ,
                                    "45338 28136" ,
                                    "15177 40995" ,
                                    "5002 69405" ,
                                    "2041 26838" ,
                                    "25065 12" ,
                                    "51572 9547" ,
                                    "2282 49566" ,
                                    "17010 52117" ,
                                    "74109 958" ,
                                    "19716 53771" ,
                                    "43496 62596" ,
                                    "57146 46667" ,
                                    "10661 17482" ,
                                    "51269 50703" ,
                                    "74222 3429" ,
                                    "7414 59914" ,
                                    "43084 73585" ,
                                    "38138 11174" ,
                                    "70901 48050" ,
                                    "71826 10851" ,
                                    "17695 59340" ,
                                    "42426 32451" ,
                                    "72033 77584" ,
                                    "58263 76727" ,
                                    "70716 18563" ,
                                    "77979 77903" ,
                                    "71202 69116" ,
                                    "12460 71032" ,
                                    "17909 5878" ,
                                    "55100 75709" ,
                                    "53218 32965" ,
                                    "38928 13202" ,
                                    "53521 24544" ,
                                    "11242 38956" ,
                                    "626 25446" ,
                                    "18064 30238" ,
                                    "40269 49608" ,
                                    "11004 16139" ,
                                    "2763 26878" ,
                                    "32647 33744" ,
                                    "6692 44226" ,
                                    "30583 76168" ,
                                    "62560 16379" ,
                                    "38535 56028" ,
                                    "6107 47588" ,
                                    "49447 69564" ,
                                    "62570 57878" ,
                                    "18410 56018" ,
                                    "29765 48779" ,
                                    "49704 10024" ,
                                    "8304 13266" ,
                                    "11437 13825" ,
                                    "27526 35557" ,
                                    "56906 45737" ,
                                    "46577 36807" ,
                                    "21942 76536" ,
                                    "77516 42437" ,
                                    "39877 32550" ,
                                    "10663 30656" ,
                                    "32221 7373" ,
                                    "25791 47907" ,
                                    "71493 28624" ,
                                    "11896 37043" ,
                                    "49631 29462" ,
                                    "63975 55943" ,
                                    "10021 63234" ,
                                    "62141 66554" ,
                                    "46929 25443"};
        profile(p);*/

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
