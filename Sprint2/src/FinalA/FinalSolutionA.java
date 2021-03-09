/*
    ИД отправки в Яндекс.Контест - 49075532
 */

package FinalA; // эту строку нужно закомментировать перед отправкой в Яндекс.Контест

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
    ---ОПИСАНИЕ РЕШЕНИЯ---

    Решение состоит из 3 основных частей:
    1) класс дэка (double-end queue, deque), реализующий последовательную запись и чтение элементов как с конца,
        так и из начала дэка с сохранением их порядка.
    2) интерпретатор команд Commander, обеспечивающий анализ текстовых представлений команд/параметров и
        вызов соответствующих функций и методов дэка (double-end queue, deque)
    3) управляющих класс Solution A, обеспечивающий взаимодействие с потоками ввода/вывода

    ---ПРИНЦИП РЕАЛИЗАЦИИ ВНУТРЕННЕЙ БИЗНЕС-ЛОГИКИ ДЭКА---

    Для обеспечения возможность добавления и чтения (с изъятием) элементов очереди за О(1) используется кольцевой буфер
    на основе статического массива целых чисел. Сохранение порядка записи/чтения элементов производится посредством
    последовательного передвижения двух независимых указателей текущей позиции чтения/записи:
    - "headPointer" управляет чтением/записью элементов в начале очереди;
    - "tailPointer" управляет чтением/записью элементов в конце очереди.

                pushFront           pushBack
                popFront            popBack
                |                     |
        headPointer->[0..........N]<-tailPointer

    При условии непревышения записанных элементов общего размера очереди, а также при условии контроля чтения пустой
    очереди указатеди не перекрываются (т.е. не указывают на одни и тот же элемент очереди.), за исключением "нулевого"
    состояния, когда очередь не содержит элементов:
    - очередь была только что создана ("headPointer" и "tailPointer" изначально указывают на одни и тот же элемент);
    - очередь была полностью вычитана ("headPointer" и "tailPointer" принудительно приравниваются друг к другу);

    ---ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ ВНУТРЕННЕЙ БИЗНЕС-ЛОГИКИ ДЭКА---

    Порядок записи элементов, как вначало, так и в конец очереди, сохраняется посредством строго последовательного
    передвижения указателей текущей позиции:
    - при записи указали взаимно удаляются друг от друга,
    - при чтении указатели взаимно приближаются.

    За счет гарантий неперекрытия указателей, а также при условии их взаимного сближения при чтении (вне зависимости от
    того, с какой стороны дэка идет чтение), данная структура данных гарантирует чтение элементов ровно в том порядке,
    в котором они были добавлены в очередь.

    ---ПРИНЦИП РЕАЛИЗАЦИИ ВНУТРЕННЕЙ ЛОГИКИ ИНТЕРПРЕТАТОРА---

    Для обеспечения возможности вызова функций дэка, используется анализатор текста команд который выполняет:
    1) лексический анализ: сопоставление имени команды со словарем
    2) маршрутизацию вызова: вызов функции дэка по результату сопоставления именни команды со словарем
    3) прием результата вызова.

    ---ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ ВНУТРЕННЕЙ БИЗНЕС-ЛОГИКИ ИНТЕРПРЕТАТОРА---
    Интерпретатор представляет собой автомат конечных состояний с настроенной структурой ассоциативных переходов между
    состояниями. При отсутствии исключительных ситуаций (ошибок, прерывающих ваполнение программы), состояние автомата
    определено в каждый момент времени.

    ---ПРИНЦИП РЕАЛИЗАЦИИ УПРАВЛЯЮЩЕГО КЛАССА РЕШЕНИЯ---

    Класс реализует две основные задачи:
    1) буферизованное зачитывание всех текстовых команд с параметрами в массив строк
        и передача данного массива на обработку.
    2) последовательная обработка команд из входного массива:
        - токенизация: отделение имени команды от параметра (единственен и опционален)
        - семантический анализ: определение наличия параметра и его парсинг (при наличии) в число
        - интерпретация пары (команда + параметр): посредством вызова функций класса Commander
        - буферизация полученных результатов в текст (каждый результат на отдельной строке)
    3) буферизованный вывод полученных результатов в консоль

    ---ДОКАЗАТЕЛЬТСТВО КОРРЕКТНОСТИ ВНУТРЕННЕЙ БИЗНЕС-ЛОГИКИ УПРАВЛЯЮЩЕГО КЛАССА ---
    Буферизация команд в массив с сохранением порядка обеспечивает сохранение последовательности выполнения команд.
    Так как выполнение каждой команды ведет к переходу системы в новое логически определенное состояние,
    конечное состояние очереди после выполнение последовательности команд также определено (см. доказательтсво
    корректности бизнес-логики интерпретатора).

    Буферизация выходного результата выполнения команд в текст путем добавления новых строк обеспечивает сохранение
    порядка записанных результатов относительно порядка переданных комманд.

    В соответсствии с заданным нулевым исходным состоянием системы, значение выходного буфера полностью определяются
    значением входного буфера, и только им. Согласно доказательству корректности бизнес-логики интерпретатора и
    доказательству корректности бизнес-логики дэка, система не может перейти в различные конечные состояния при одном и
    том же наборе входных параметров (при условии одинакового исходного "нулевого" состояния).

    Соответственно выходной результат работы системы является функцией от переданных входных параметров.

    ---ВРЕМЕННАЯ СЛОЖНОСТЬ ДЭКА---
    Поскольку в качестве внутреннего хранилища очереди используется массив целых значений, а чтение и запись в него
    производится посредством индексного оператора [], то все операции чтения/записи имеют асимтотику О(1), так как
    отличаются только эквивалентным константным количеством операций вычисления текущей позиции чтения/записи.

    ---ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ ДЭКА---
    Все элементы очереди из k элементов хранятся в массиве целых чисел, размерностью k. Кроме этого дэк хранит
    состояние с константным перечнем свойств:
    - данные двух указателей "headPointer" и "tailPointer";
    - размер очереди;
    - количество заполненных значений.
    Таким образом, для очереди из k элементов потребуется дополнительной памяти в размере O(k) + O(1) = O(k).

    ---ВРЕМЕННАЯ СЛОЖНОСТЬ ИНТЕРПРЕТАТОРА---
    1) лексический анализ: O(1), считаем длину текста "команда+параметр" константной.
    2) маршрутизацию вызова: O(1)
    3) прием результата вызова: O(1)

    Итого: O(1) + O(1) + O(1) = O(1)

    ---ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ ИНТЕРПРЕТАТОРА---
    Состояние интерпретатора включается в себя только ссылку на объект дэка,
    поэтому объем дополнительной памяти составляет О(1)

    ---ВРЕМЕННАЯ СЛОЖНОСТЬ ФУНКЦИЙ УПРАВЛЯЮЩЕГО КЛАССА---
    При поступлении на вход m команд, имеем следующие показатели:
    1) буферизованное зачитывание всех текстовых команд с параметрами: O(m).
    2) последовательная обработка команд из входного массива: O(1), считаем длину текста "команда+параметр" константной.
    3) буферизованный вывод полученных результатов в консоль: O(1)

    Итого: O(m) + O(1) + O(1) = O(m)

    ---ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ ФУНКЦИЙ УПРАВЛЯЮЩЕГО КЛАССА---
    Состояние функций управляющего класса состои из:
    1) Ссылка на экземпляр дэка: O(1)
    2) Ссылка на экземпляр интерпретатора: O(1)
    3) Массив в текстом m комманд условно константной длины: O(m)
    4) Текстовая строка с результатами выполнения m команд:O(m)

    Итого: O(1)+O(1)+O(m)+O(m)=O(m)

    ---ИТОГОВЫЙ РАСЧЕТ ПО ПРОГРАММЕ В ЦЕЛОМ---
    Временная сложность = O(1) + O(1) + O(m) = O(m), где:
        m - количество команд, поданных на вход.
    Пространственная сложность = O(k) + O(1) + O(m) = max(O(m), O(k)), где:
        k- максимальное количество элементов в очереди,
        m - количество команд, поданных на вход.

P.S.\> Изначально была реализация с полиморфизмом функторов (деораторов над указателеми на функции дэка),
а маршрутизация осеществлялась через ключи в HashMap. От данного кода пришлось отказаться по причине недостаточного
быстродействия, хотя такая реализация была более расширяема и менее связана с конкретной реализацией дэка.
Варианты данного подхода можно найти в отправках: 49043144, 49051452, 49054100.

 */

/**
 * Класс глобальных констант, используемых для совместимости с контекстом тестирующей системы
 */
class GlobalSettings {
    public static final String CMD_PUSH_FRONT = "push_front";
    public static final String CMD_POP_FRONT = "pop_front";
    public static final String CMD_PUSH_BACK = "push_back";
    public static final String CMD_POP_BACK = "pop_back";
    public static final String ERR_MESSAGE = "error";
}

/**
 * Класс интерпретатора команд.
 * Используется для трансляции тестовых команд в вызовы функций дэка (double-end queue, deque).
 */

class Commander {
    private Deque commandTarget;

    /**
     * Конструктор интерпретатора с инъекцией управляемого объекта
     *
     * @param target Ссылка на управляемый экземпляр дэка (double-end queue, deque)
     */
    public Commander(Deque target) {
        commandTarget = target;
    }

    /**
     * Функция интерпретации текстовых команд и связанных с ними параметров
     *
     * @param commandName    Текстовое представление команды
     * @param parameterValue Параметр команды (для push-команд передается произвольное значение)
     * @return Строковое представление результата (число или "error")
     */
    public String Interpret(String commandName, int parameterValue) {
        if (commandName.equals(GlobalSettings.CMD_PUSH_FRONT)) return commandTarget.pushFront(parameterValue);
        if (commandName.equals(GlobalSettings.CMD_PUSH_BACK)) return commandTarget.pushBack(parameterValue);
        if (commandName.equals(GlobalSettings.CMD_POP_FRONT)) return commandTarget.popFront();
        if (commandName.equals(GlobalSettings.CMD_POP_BACK)) return commandTarget.popBack();
        return new String();
    }
}

/**
 * Класс бизнес-логики дэка (double-end queue, deque)
 */
class Deque {
    /*

     */
    private int storageSize;
    private int fillCounter;
    private final int[] storage;
    private int headPointer;
    private int tailPointer;

    /**
     * Конструктор дэка (double-end queue, deque)
     *
     * @param size Максимальный конечный размер очереди (кол-во элементов).
     */
    public Deque(int size) {
        storageSize = size;
        storage = new int[size];
        headPointer = 0;
        tailPointer = 0;
        fillCounter = 0;
    }

    /**
     * Функция кольцевого позиционирования указателей чтения/записи.
     * Обеспечивает нормализацию (перепозиционирование) указателя элементов
     * последовательности при выходе за ее границы по следующей логике:
     * 1) "последний + 1"->"первый"
     * 2) "первый - 1"->"последний"
     *
     * @param position Текущая позиция указателя чтения/записи.
     * @return Нормализованная позиция указателя чтения/записи.
     */
    private int normalizePosition(int position) {
        if (position < 0) return storageSize - 1;
        if (position > storageSize - 1) return 0;
        return position;
    }

    /**
     * Функция проверки налиция элементов в очереди
     *
     * @return Возравщает true, если очередь не пуста.
     */
    public boolean isEmpty() {
        return fillCounter == 0;
    }

    /**
     * Функция помещения элемента в конец очереди
     *
     * @param value Значение помещаемого элемента
     * @return Текстовое представление результата функции:
     * 1) Пустая строка в случае успешного помещения элемента в очередь
     * 2) "error" в случае, если очередь полностью заполнена
     */
    public String pushBack(int value) {
        int writePosition = tailPointer;
        if (fillCounter > 0)
            writePosition = normalizePosition(++writePosition);
        if (fillCounter == storageSize)
            return GlobalSettings.ERR_MESSAGE;
        storage[writePosition] = value;
        tailPointer = writePosition;
        fillCounter++;
        return new String();
    }

    /**
     * Функция помещения элемента в начало очереди
     *
     * @param value Значение помещаемого элемента
     * @return Текстовое представление результата функции:
     * 1) Пустая строка в случае успешного помещения элемента в очередь
     * 2) "error" в случае, если очередь полностью заполнена
     */
    public String pushFront(int value) {
        int writePosition = headPointer;
        if (fillCounter > 0)
            writePosition = normalizePosition(--writePosition);
        if (fillCounter == storageSize)
            return GlobalSettings.ERR_MESSAGE;
        storage[writePosition] = value;
        headPointer = writePosition;
        fillCounter++;
        return new String();
    }

    /**
     * Функция чтения значения элемента из конца очереди
     *
     * @return Текстовое представление результата функции:
     * * 1) Строковое представление прочитанного элемента
     * * 2) "error" в случае, если очередь пуста
     */

    public String popBack() {
        int readPosition = tailPointer;
        if (isEmpty()) return GlobalSettings.ERR_MESSAGE;
        int value = storage[readPosition];
        tailPointer = normalizePosition(--readPosition);
        --fillCounter;
        if (fillCounter == 0) headPointer = tailPointer;
        return String.valueOf(value);
    }

    /**
     * Функция чтения значения элемента из начала очереди
     *
     * @return Текстовое представление результата функции:
     * * 1) Строковое представление прочитанного элемента
     * * 2) "error" в случае, если очередь пуста
     */
    public String popFront() {
        int readPosition = headPointer;
        if (isEmpty()) return GlobalSettings.ERR_MESSAGE;
        int value = storage[readPosition];
        headPointer = normalizePosition(++readPosition);
        --fillCounter;
        if (fillCounter == 0) tailPointer = headPointer;
        return String.valueOf(value);
    }
}


/**
 * Управляющий класс, реализующий запуск и выполнение бизнес-логики задания
 */
public class FinalSolutionA {
    /**
     * Функция, реализующая оркестрацию вызовов бизнес-логики задания,
     * инкапсулированной в классах интепретатора команд (Commander) и очереди (Deque)
     *
     * @param commandsToProcess Массив строк с текстовым представлением команд и связанных с ними параметров.
     * @return Строковое представление всех результатов последовательной обработки полученных команд.
     */
    public static String ProcessCommands(String[] commandsToProcess) {
        int dequeueSize = Integer.parseInt(commandsToProcess[1]);
        Deque deque = new Deque(dequeueSize);

        Commander cmd = new Commander(deque);
        StringBuilder outLog = new StringBuilder();

        for (int i = 2; i < commandsToProcess.length; i++) {
            String[] commandTokens = commandsToProcess[i].split(" ");
            String commandName = commandTokens[0];
            int commandValue = commandTokens.length > 1 ? Integer.parseInt(commandTokens[1]) : 0;

            String cmdResult = cmd.Interpret(commandName, commandValue);
            if (!cmdResult.isEmpty()) {
                outLog.append(cmdResult);
                outLog.append("\n");
            }

        }
        return outLog.toString();
    }

    /**
     * Основная функция взаимодействия с потоками ввода/вывода.
     * Точка входа в программу.
     *
     * @param args Параметры командной строки (для целей совместимости)
     * @throws IOException Возможен выброс исключений ввода/вывода
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String firstString = reader.readLine();
        int numberOfCommands = Integer.parseInt(firstString);
        String[] inputDataSet = new String[numberOfCommands + 2];
        inputDataSet[0] = firstString;

        for (int i = 1; i < numberOfCommands + 2; i++) {
            inputDataSet[i] = reader.readLine();
        }

        System.out.println(ProcessCommands(inputDataSet));
    }
}