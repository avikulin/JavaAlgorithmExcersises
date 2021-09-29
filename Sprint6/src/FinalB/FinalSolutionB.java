// Код посылки в Яндекс.Контест - 53640916
package FinalB; //- эту строку нужно закомментировать перед отправкой в Яндекс. Контест.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/*
    Гениальный подход взял отсуюда - https://yandex-students.slack.com/archives/C024VK4DYDC/p1630589363071200
    Основу алгоритма составляет принцип, что достижимость вершины по двум путям разного типа эквивалентно наличию цикла.

    Для ориентированного графа из первого примера

                                                    1:[{2, R}, {3, B}]
                                                    2:[{3, R}]

   имеем следующие эквивалентные отображения:

      ╔════[1]────────┐                               ┌─────────[1]◀────────┐
      ║R              │B           <==>               │                     │
      ║               │                               │                     │
      ▼      R       ▼                               ▼                     │
     [2]════════════▶[3]                             [2]──────────────────▶[3]

    Таким образом, для решения задачи мы должны:
    1) построить производный граф G', гомоморфный исходному графу: (G, R, B) ~ (G', ->, <-),
        где:
            операция "->" (прямое ребро) эквивалентно ребру типа R в исходном графе
            операция "<-" (обратное ребро) эквивалентно ребру типа B в исходном графе


    ОЦЕНКА ВРЕМЕННОЙ СЛОЖНОСТИ: O(m+n), где m - количество вершин, а n - количество ребер
    ОЦЕНКА ПРОСТРАНСТВЕННОЙ СЛОЖНОСТИ: O(m+n), где m - количество вершин, а n - количество ребер

    2) опредилить наличие циклов в производном графе G'. При обнаружении цикла сеть дорог, описанная исходным графом G,
       считается неоптимальной (существует более 1 пути, соединяющих заданную пару точек). Поиск циклов производится
       классическим алгоритмом обхода в глубину: при попытке войти в ранее посещенную, но не обработанную вершину,
       фиксируется наличие цикла.

    ОЦЕНКА ВРЕМЕННОЙ СЛОЖНОСТИ: O(m+n), где m - количество вершин, а n - количество ребер
    ОЦЕНКА ПРОСТРАНСТВЕННОЙ СЛОЖНОСТИ: O(2m) + O(1) = O(m), где m - количество вершин
                                       ▲        ▲
                                       │         │
                                       │         └────────очередь смежных вершин в пути прохода DFS.
                                       │                  Размер очереди много меньше общего количества вершин,
                                       │                  поэтому принимается за константу.
                                       │
                                       └────────два массива для отметок посещенных и полностью обработанных вершин.

   3) обработать все возможные компоненты связности. Для этого, обход в глубину производится начиная с первой
      необработанной вершины вплоть до момента, когда необработанных вершин в графе не останется.

    ОЦЕНКА ВРЕМЕННОЙ СЛОЖНОСТИ: O(m), где m - количество вершин
    ОЦЕНКА ПРОСТРАНСТВЕННОЙ СЛОЖНОСТИ: O(1)

    ****

    ИТОГОВАЯ ОЦЕНКА ВРЕМЕННОЙ СЛОЖНОСТИ: O(m+n) + O(m+n) + O(m) = O(m+n)
    ИТОГОВАЯ ОЦЕНКА ПРОСТРАНСТВЕННОЙ СЛОЖНОСТИ: O(m+n) + O(m) + O(1) = O(m+n)

    где:
        m - количество вершин,
        n - количество ребер
*/

/**
 * Класс графа и его операций
 */
class Graph {
    private final List<Integer>[] graphStorage;
    private final boolean[] visitedVertexes;
    private final boolean[] processedVertexes;
    private int startVertexId;
    private boolean isAcyclic;

    /**
     * Контруктор экземпляра графа
     * @param vertexCount   Количество вершин графа
     */
    public Graph(int vertexCount) {
        graphStorage = new List[vertexCount + 1];
        visitedVertexes = new boolean[vertexCount + 1];
        processedVertexes = new boolean[vertexCount + 1];
        startVertexId = -1;
        isAcyclic = true;
    }

    /**
     * Метод добавления ребра в граф
     * @param v1    Код вершины А
     * @param v2    Код вершины B
     * @param isForward Ориентация ребра. Если true, mо A->B
     */
    public void addEdge(int v1, int v2, boolean isForward) {
        int startIdx = isForward ? v1 : v2;
        int endIdx = isForward ? v2 : v1;

        if (graphStorage[startIdx] == null) {
            graphStorage[startIdx] = new ArrayList<>();
        }
        graphStorage[startIdx].add(endIdx);
    }

    /**
     * Установка начальной вершины, с которой начнется обход вглубину
     * @param vertex    Код вершины графа
     */
    public void setStartVertex(int vertex) {
        startVertexId = vertex;
    }

    /**
     * Выполнение обхода графа в вглубину, начиная с заранее заданной начальной вершины.
     */
    public void traverseDFS() {
        Deque<Integer> vertexQueue = new ArrayDeque<>();
        vertexQueue.push(this.startVertexId);
        while (!vertexQueue.isEmpty()) {
            int currentVertex = vertexQueue.peek();
            if (!visitedVertexes[currentVertex]) {
                visitedVertexes[currentVertex] = true;
            } else {
                if (!processedVertexes[currentVertex]) {
                    processedVertexes[currentVertex] = true;
                }
                vertexQueue.pop();
                continue;
            }

            List<Integer> adjVertexes = graphStorage[currentVertex];
            if (adjVertexes != null) {
                for (int i = 0; i < adjVertexes.size(); i++) {
                    int vertexId = adjVertexes.get(i);
                    if (!visitedVertexes[vertexId]) {
                        vertexQueue.push(vertexId);
                    } else {
                        if (!processedVertexes[vertexId]) {
                            isAcyclic = false;
                        }
                    }
                }
            }
        }
    }

    /**
     * Поиск первой, ранее не обработнной алгоритмом DFS, вершны графа
     * @return  Код первой необработанный вершины
     */
    public int findNextUnprocessedVertex() {
        for (int i = 1; i < processedVertexes.length; i++) {
            if (processedVertexes[i] == false) {
                startVertexId = i;
                return i;
            }
        }
        return -1;
    }

    /**
     * Получение признака ацикличности графа
     * @return  Если true, значит в графе не найдено ни одного цикла.
     */
    public boolean getAcyclicityStatus() {
        return isAcyclic;
    }
}

/**
 * Основной класс запуска вычислений
 */
public class FinalSolutionB {

    /**
     * Функция-драйвер расчетов
     * @param input Массив входных строк (с учетом условия задачи)
     * @return  Выходная строка (с учетом условия задачи)
     */
    public static String process(String[] input) {
        int numberOfVertexes = Integer.parseInt(input[0]);
        Graph graph = new Graph(numberOfVertexes);
        for (int rowIdx = 1; rowIdx < input.length; rowIdx++) {
            String edgeCodesStr = input[rowIdx];
            for (int colIdx = 0; colIdx < edgeCodesStr.length(); colIdx++) {
                char directionCode = input[rowIdx].charAt(colIdx);
                graph.addEdge(rowIdx, rowIdx + colIdx + 1, (directionCode == 'R'));
            }
        }

        while (graph.findNextUnprocessedVertex() != -1) { // на случай, если у нас несколько компонент связности.
            graph.traverseDFS();
        }

        return graph.getAcyclicityStatus() ? "YES" : "NO";
    }

    /**
     * Точка входа в программу
     * @param args  Аргументы командной строки (для целей совместимости)
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String numberOfStrings = reader.readLine();
        int vertexCount = Integer.parseInt(numberOfStrings);
        String[] input = new String[vertexCount];
        input[0] = numberOfStrings;

        for (int i = 1; i < vertexCount; i++) {
            input[i] = reader.readLine();
        }
        System.out.println(process(input));
    }
}
