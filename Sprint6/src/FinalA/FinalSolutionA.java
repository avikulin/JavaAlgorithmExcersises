// Код отправки в Яндекс.Контест - 52659386
package FinalA; //--- эту строку нужно закомментировать перед отправкой в Яндекс.Контест.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    ОПИСАНИЕ РЕШЕНИЯ

    По сути задание сводится к построению максимального остовного дерева, при котором мы на каждой итерации выбираем
    ребро, инцидентное текущей вершины, но с максимальным весом. Для решения используем алгоритм Прима на базе
    очереди с приоритетами, которая реализуется классом TreeSet.
    Для выбора максимального ребра, а также для различимости экземпляров ребер внутри контейнера TreeSet,
    переопределим метод equals() и compareTo() в классе ребра.

    Решение структурно состоит из следующих классов:
    1) класс графа <Graph>, реализующего операции добавления ребер, обхода вершин и построения MST
    2) класс ребра <Edge>, реализующего функционал хранения весов, а также операции сравнения <equals(...)>
       и упорядочивания <compareTo(...)>, а также маркировка концов ребра, как входящих/не входящих в MST.
    3) управляющего класса <FinalSolutionA>, реализующего функцию входа в программу <main(...)>
       и функцию-драйвер <pprocess(...)>

    АНАЛИЗ ПРОСТРАНСТВЕННОЙ И ВРЕМЕННОЙ СЛОЖНОСТИ АЛГОРИТМА

    Алгоритм построения MST (M - максимальное) состоит из n итарций следующих основных этапов:

    А) Формирование перечня ребер, инцидентных текущей вершине, и наполнение очереди с приоритетами.
       Считаем, что операции сравнения ребер выполняются за O(1) и требуют O(1) дополнительной памяти.

        ВРЕМЕННАЯ СЛОЖНОСТЬ: k*O(log m),
        ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ (в худшем случае): O(m)
        где:
            k - количество ребер, инцидентных текущей вершине. Намного меньше m, поэтому O(k) ~ O(1)
            m - количество ребер

    Б) Добавление ребра в MST:
        - извлечение максимального ребра из очереди с приоритетами
        - проверка ребра на невхождение в MST
        - маркировка ребра, как входящего в MST
        - добавление веса ребра к общему весу MST

        ВРЕМЕННАЯ СЛОЖНОСТЬ: O(log m) + O(1) + O(1) + O(1) = O(log m)
        ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ (в худшем случае): O(1)

    *****
    ИТОГОВАЯ ВРЕМЕННАЯ СЛОЖНОСТЬ АЛГОРИТМА  (в худшем случае) = n * ({k} * O(log m) + O(log m)) =
                                                                n * (O(1) * O(log m) + O(log m)) =
                                                                n * O(2*log m) = O(n*log m)

    ИТОГОВАЯ ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ АЛГОРИТМА  (в худшем случае) = n * (O(m) + O(1)) = n * O(m) = O(n*m)

    где:
        n - количество вершин,
        m - количество ребер
 */

/**
 * Класс графа
 */
class Graph {
    private final List<Edge>[] graphStorage;
    private final boolean[] mstAddedVertexes;
    private final TreeSet<Edge> mstCandidateEdges; // явно указываем тип, чтобы не тратить время на приведение
    private final int numberOfVertexes;
    private int mstWeight;
    private final boolean isOriented;
    private int startVertexId;
    private int numberOfMstVertices;

    /**
     * Конструктор экземпляра графа
     * @param vertexCount   Количество вершин
     * @param oriented  Признак ориентированности. true означает создание ориентированного графа.
     */
    public Graph(int vertexCount, boolean oriented) {
        graphStorage = new List[vertexCount + 1];
        mstAddedVertexes = new boolean[vertexCount + 1];
        mstCandidateEdges = new TreeSet<>();
        numberOfVertexes = vertexCount;
        isOriented = oriented;
        startVertexId = -1;
        numberOfMstVertices = 0;
        mstWeight = 0;
    }

    /**
     * Добавление ребра в граф
     * @param e Ссылка на экземпляр ребра
     */
    public void addEdge(Edge e) {
        int v1 = e.getVertexA();
        int v2 = e.getVertexB();

        if (v1 == v2) return; //не добавляем петли в граф. они не влияют на MST.

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

    /**
     * Добавление вершины в MST
     * @param vertex    Код добавляемой вершины
     */
    private void addVertexToMST(int vertex) {
        this.mstAddedVertexes[vertex] = true;
        this.numberOfMstVertices++;
        if (this.graphStorage[vertex] == null) return;
        List<Edge> adjVertexes = this.graphStorage[vertex];

        // с удивлением обнаружил, что цикл с итератором на 20% менее эффективен.
        // и вообще итераторы оказались чудовищно неэффективны, как по времени, так и по памяти.
        // все бэст-практисы профайлеру под хвост.... когда бьешься за миллисекунды - уже не до красоты.

        /*for (Edge e : adjVertexes) {
            if (!this.mstAddedVertexes[e.getOppositeVertex(vertex)]) {
                e.setMSTIncludedVertex(vertex);
                this.mstCandidateEdges.add(e);
            }
        }*/

        for (int i = 0; i < adjVertexes.size(); i++) {
            Edge e = adjVertexes.get(i);
            if (!this.mstAddedVertexes[e.getOppositeVertex(vertex)]) {
                e.setMSTIncludedVertex(vertex);
                this.mstCandidateEdges.add(e);
            }
        }
    }

    /**
     * Задание стартовой вершины, начиная с которой производится построение MST
     * @param vertex    Код стартовой вершины
     */
    public void setStartVertex(int vertex) {
        this.startVertexId = vertex;
    }

    /**
     * Выполнение обхода графа вглубину и построение MST
     * @return  Признак полноты MST. true означает, что MST содержит все вершины графа.
     */
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

    /**
     * Получение значения итогового веса MST
     * @return  Значение веса MST
     */
    public int getMstWeight() {
        return this.mstWeight;
    }
}

/**
 * Класс ребра графа
 */
class Edge implements Comparable<Edge> {
    private final int vertexA;
    private final int vertexB;
    private boolean vertexAinMST;
    private boolean vertexBinMST;
    private final int weight;

    /**
     * Конструктор экземпляра ребра
     * @param vertexA   Код вершины А
     * @param vertexB   Код вершины B
     * @param weight    Вес ребра
     */
    public Edge(int vertexA, int vertexB, int weight) {
        this.vertexA = vertexA;
        this.vertexB = vertexB;
        this.weight = weight;
        this.vertexAinMST = false;
        this.vertexBinMST = false;
    }

    /**
     * Получение кода вершины-начала ребра
     * @return  Код начальной вершины
     */
    public int getVertexA() {
        return this.vertexA;
    }

    /**
     * Получение кода вершины-конца ребра
     * @return  Код конечной вершины
     */

    public int getVertexB() {
        return this.vertexB;
    }

    /**
     * Маркировка заданной вершины ребра, как входящей в MST
     * @param vertex    Код вершины
     */
    public void setMSTIncludedVertex(int vertex) {
        if (vertex == vertexA)
            this.vertexAinMST = true;
        else
            this.vertexBinMST = true;
    }

    /**
     * Получение кода вершины, противоположной заданной
     * @param vertex    Код заданной вершины
     * @return  Код противоположной вершины
     */
    public int getOppositeVertex(int vertex) {
        return (vertex == vertexA) ? vertexB : vertexA;
    }

    /**
     * Получение кода вершины одного из концов ребра, не включенной в MST
     * @return  Код вершины ребра, не включенной в MST
     */
    public int getNotMSTVertex() {
        if ((!this.vertexAinMST) && (!this.vertexBinMST)) return -1;
        return (this.vertexAinMST) ? this.vertexB : this.vertexA;
    }

    /**
     * Получение веса ребра
     * @return  Значение веса
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Проверка равенства экзепляров класса ребра графа
     * @param o Ссылка на экземпляр ребра, сравниваемый с данным
     * @return  Результат проверки. true означает эквивалентность экземпляров.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Edge edge = (Edge) o;

        return vertexA == edge.vertexA &&
                vertexB == edge.vertexB &&
                weight == edge.weight;
    }

    /**
     * Получение хэщ-кода экземпляра ребра (необходим для корректной работы контейнера TreeSet).
     * @return Значение хэш-кода.
     */
    @Override
    public int hashCode() {
        return Objects.hash(vertexA, vertexB, weight);
    }

    /**
     * Выполнение сравнения текущего экземпляра ребра с другим.
     * @param o Ссылка на сравниваемый экземпляр ребра
     * @return Результат сравнения:
     *                              0 - экземпляры равны,
     *                              1 - текущий экземпляр больше,
     *                              (-1) - сравниваемый экземпляр больше
     */
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

/**
 * Основной класс выполнения расчетов
 */
public class FinalSolutionA {

    /**
     * Функция-драйвер
     * @param input Массив входных строк (согласно условию задачи)
     * @return  Выходная строка (согласно условию задачи)
     * @throws RuntimeException
     */
    public static String process(String[] input) throws RuntimeException {
        String mainParams = input[0];
        StringTokenizer mainParamsTokens = new StringTokenizer(mainParams);

        int vertexCount = Integer.parseInt(mainParamsTokens.nextToken());
        int edgeCount = Integer.parseInt(mainParamsTokens.nextToken());
        mainParamsTokens = null; //обжогшись на строках, дую на null

        Graph graph = new Graph(vertexCount, false);
        for (int i = 1; i < edgeCount + 1; i++) {
            // когда возвращаются не только токены данных, но и разделители,
            // StringTokenizer работает на 20% эффективнее по времени.
            StringTokenizer edgeParamsTokens = new StringTokenizer(input[i], " ", true);
            int vertexA = Integer.parseInt(edgeParamsTokens.nextToken());
            String spaceA = edgeParamsTokens.nextToken();
            int vertexB = Integer.parseInt(edgeParamsTokens.nextToken());
            String spaceB = edgeParamsTokens.nextToken();
            int weight = Integer.parseInt(edgeParamsTokens.nextToken());

            //принудительно подчищаем память между итерациями
            edgeParamsTokens = null;
            spaceA = null;
            spaceB = null;

            graph.addEdge(new Edge(vertexA, vertexB, weight));

        }
        graph.setStartVertex(1);
        boolean res = graph.traverseMst();
        return (res) ? Integer.toString(graph.getMstWeight()) : "Oops! I did it again";
    }

    /**
     * Функция входа в программу
     * @param args  Аргументы командной строки (для целей совместимости)
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
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
