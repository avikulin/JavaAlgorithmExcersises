package ProblemH;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Graph {
    private final List<Integer>[] graphStorage;
    private boolean normalizedState[];
    private final boolean[] visitedVertexes;
    private final boolean[] processedVertexes;
    private final int[] vertexInputTime;
    private final int[] vertexOutputTime;
    private int numberOfEdges;
    private int numberOfVertexes;
    private final boolean isOriented;
    private int startVertexId;

    public Graph(int vertexCount, int edgesCount, boolean oriented) {
        graphStorage     = new List[vertexCount + 1];
        normalizedState  = new boolean[vertexCount + 1];
        visitedVertexes  = new  boolean[vertexCount + 1];
        processedVertexes = new  boolean[vertexCount + 1];
        vertexInputTime  = new int[vertexCount + 1];
        vertexOutputTime = new int[vertexCount + 1];
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

    public void TraverseDFS(){
        Stack<Integer> vertexQueue = new Stack<>();
        vertexQueue.add(this.startVertexId);
        int time=-1;
        while (!vertexQueue.isEmpty()) {
            int currentVertex = vertexQueue.peek();
            if (visitedVertexes[currentVertex]==false) {
                time++;
                visitedVertexes[currentVertex] = true;
                vertexInputTime[currentVertex] = time;
            } else {
                if(processedVertexes[currentVertex]==false){
                    time++;
                    vertexOutputTime[currentVertex] = time;
                    processedVertexes[currentVertex] = true;
                }
                vertexQueue.pop();
                continue;
            }

            List<Integer> adjVertexes = graphStorage[currentVertex];
            if (adjVertexes != null) {
                if (!normalizedState[currentVertex]){
                    Collections.sort(adjVertexes, Collections.reverseOrder());
                    normalizedState[currentVertex] = true;
                }
                for (int i=0; i< adjVertexes.size(); i++) {
                    int vertexId = adjVertexes.get(i);
                    if (visitedVertexes[vertexId] == false) {
                        vertexQueue.push(vertexId);
                    }
                }
            }
        }
    }

    public String timesMatrixStr(){
        StringBuilder res = new StringBuilder();
        for (int i=1; i <= this.numberOfVertexes;i++){
            String v1 = Integer.toString(this.vertexInputTime[i]);
            String v2 = Integer.toString(this.vertexOutputTime[i]);
            res.append(v1);
            res.append(" ");
            res.append(v2);
            res.append("\n");
            v1=null;
            v2=null;
        }
        return res.toString();
    }
}

public class SolutionH {

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
        graph.setStartVertex(1);
        graph.TraverseDFS();
        return graph.timesMatrixStr();
    }

    static void profile(){
        while (true){
            process(new String[]{
                    "169242 85",
                    "38045 152748",
                    "44576 150178",
                    "61412 117580",
                    "3584 114932",
                    "43743 136920",
                    "409 2376",
                    "75527 143405",
                    "26713 49658",
                    "122403 125220",
                    "761 7123",
                    "53675 166811",
                    "84098 68532",
                    "88269 118057",
                    "57096 119862",
                    "2983 120407",
                    "1098 70231",
                    "8018 9346",
                    "17821 147191",
                    "71896 120687",
                    "40753 42714",
                    "18993 141729",
                    "49835 65365",
                    "8515 41816",
                    "49379 50087",
                    "9828 13169",
                    "17222 22210",
                    "104275 117112",
                    "1119 2018",
                    "342 28519",
                    "27867 113214",
                    "83325 86103",
                    "47636 98667",
                    "166244 161983",
                    "50204 104445",
                    "39702 39829",
                    "91740 140006",
                    "32224 126254",
                    "13829 122286",
                    "23904 39448",
                    "8788 47377",
                    "11002 45377",
                    "140 269",
                    "162 2673",
                    "56063 115041",
                    "31562 83400",
                    "36987 59249",
                    "69405 165088",
                    "1172 18779",
                    "25313 44919",
                    "77662 114887",
                    "18968 151078",
                    "4141 127166",
                    "23520 157188",
                    "20865 116765",
                    "29331 35512",
                    "23102 71540",
                    "137985 142537",
                    "659 9433",
                    "18001 64174",
                    "69384 87720",
                    "32808 37318",
                    "79956 709",
                    "60097 96967",
                    "71620 168664",
                    "4645 29728",
                    "30874 102892",
                    "9850 30745",
                    "384 141628",
                    "1265 3217",
                    "71677 72448",
                    "23947 53984",
                    "793 17816",
                    "846 1506",
                    "18320 44522",
                    "43952 111808",
                    "62633 78966",
                    "3042 3710",
                    "126567 166874",
                    "7088 58510",
                    "65812 92944",
                    "19692 33346",
                    "76838 108446",
                    "3209 37642",
                    "21151 144053",
                    "23097 68"
            });
        }
    }

    public static void main(String[] args) throws IOException {
        /*profile();*/

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
