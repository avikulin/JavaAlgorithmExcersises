//--- номер посылки в Яндекс.Контест = 51003681 ---//

package FinalB; // эту строку нужно закоментировать перед отправкой в Яндекс.Контест.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/*
        ---ОПИСАНИЕ РЕШЕНИЯ---

        Решение состоит из слудующих основных частей:
    1)  Класс хэш-таблицы (HashTable), реализующий функции управления адресацией элементов по цепочкам (buckets),
        а также инкапсулирующий функции хранения (получить, записать, удалить), реализованные в классе цепочек.
    2)  Класс цепочек хранимых значений (HashBucket), имеющих одинаковое значение хэш-функции.
        Вложен в класс хэш-таблицы.
    3)  Класс хранимого значения (HashPair).
        Вложен в класс хэш-таблицы.
    4)  Класс интерпретатора команд управления хэш-таблицей (Commander).

        Объектная архитектура хэш-таблицы:

                  ┍─ фиксированный массив HashBucket[]
                  ▼                     ┍─ значение хэш-функции <HashTable::computeBucketIdx>
     HashTable--▶([ . . . . . . . . . . ▓▓ . . . . . . . . . . . . . . . . . ] ◀-- <hashBucketsStorage>)
                                         ▲
                     List<HashPair>      ║
                                  ╰--▶ ╭╼╼╮ ◀-- ссылка на <HashBucket>
                                         ▉
                                         ▉
                                         ▉
                                         ▉   ◀-- ссылка на <HashPair>
                                         .
                                         .
                                         .
                                       ╰╼╼╯
      ---РАСЧЕТ ВРЕМЕННОЙ СЛОЖНОСТИ---
      1) Создание хэш-таблицы и интерпретатора - O(1)
      2) Добавление элемента в хэш таблицу:
            а) вычисление номер цепочки - O(1)
                ...и...
            2) создание цепочки (если необходимо) - O(1)
                ...и...
            3) поиск существующего в цепочке ключа - O(k), где k-количество коллизий для заданного значения ключа (**).
                ...и...
            4) добавление элемента в цепочку - O(1)
                ...или...
            5) обновление элемента в целочке - O(1)

            ИТОГО: O(1) + O(1) + O(1) + O(1) + O(1) = 5 * O(1) = O(1)

      3) Получение элемента по ключу:
            а) вычисление номер цепочки - O(1)
                ...и...
            2) проверка существования цепочки - O(1)
                ...и...
            3) поиск ключа в цепочке - O(k), где k-количество коллизий для заданного значения ключа.

            ИТОГО: O(1) + O(1) + O(1) = 3 * O(1) = O(1)

      3) Удаление элемента по ключу:
            а) вычисление номер цепочки - O(1)
                ...и...
            2) проверка существования цепочки - O(1)
                ...и...
            3) поиск ключа в цепочке - O(k), где k-количество коллизий для заданного значения ключа.

            ИТОГОВАЯ ВРЕМЕННАЯ СЛОЖНОСТЬ: O(1) + O(1) + O(1) = 3 * O(1) = O(1)

       4) Интерпретация команд - O(1)


       ИТОГО ВРЕМЕННАЯ СЛОЖНОСТЬ = <время интерпретации> + <время выполнения команды> = O(1) + O(1) = 2 * O(1) = O(1)

        (**) - При равномерном распределении значения ключей k стремится к 1.
               Поэтому все операции выполняются за О(1) в среднем.

       ---РАСЧЕТ ПРОСТРАНСТВЕННОЙ СЛОЖНОСТИ---

        1) Класс <HashPair>. Объектная обвязка вокруг хранимого значения, хранящая константное количество
           служебных данных.
           Пространственная сложность = O(1)
        2) Класс <HashBucket>. Последовательность элементов (ссылка + экземпляр) <HashPair> длины k.
           Пространственная сложность = k * O(1) = O(k)
        3) Класс <HashTable>. Хранит фиксированную последовательность элементов  <HashBucket> (ссылки + существующие
           экземпляры). Количество существующих последовательностей n задается результатами хэш-функции. Причем,
           N = n*k,  где N - количество хранимых элементов в хэш-таблице. Фиксированный размер последовательности s
           ссылок на <HashBucket>, задается в конструкторе.

           Пространственная сложность = s * O(1) + n * O(k) =O(s) + O(n*k) = O(s+N).

           При больших N, размером фиксированной последовательности ссылок на цепочки элементов можно пренебречь и
           принять равной O(1). Таким образом

           ИТОГОВАЯ ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ = O(1+N) = O(N).



 */

/**
 * Основной класс хэш таблицы с реализованным механизмом решения коллизий методом цепочек.
 * Включает в себя вложенные классы хранимого элемента (HashPair), и цепочки элементов с
 * одинаковым значением хэш функции (HashBucket).
 * Поскольку ключи представлены целыми числами, то хэш функция не требуется (идентичное отображение Q->Q).
 * Индекс цепочки вычисляется на основании числового значения ключа методом остатка от деления.
 * Количество целочек задается в конструкторе главного класса.
 */
class HashTable {
    private final int NOT_FOUND = -1; // значение индекса ненайденного элемента
    private final String NONE_STR = "None"; // текстовое представление значения ненайденного элемента

    private HashBucket[] hashBucketsStorage;

    /**
     * Внутренний класс, реализующий хранимый элемент (ключ, значение)
     */
    class HashPair {
        private int key;
        private int value;

        /**
         * Конструктор хранимого элемента
         * @param hashKey   Ключ.
         * @param hashValue Значение.
         */
        HashPair(int hashKey, int hashValue) {
            key = hashKey;
            value = hashValue;
        }

        /**
         * Реализая интерфейса сравнения на равенство (унаследовано от Object).
         * Требуется для поиска нужного элемента ссылке.
         * @param otherObj  Ссылка на сравниваемый элемент
         * @return  true - если элементы равны, false - в противном случае.
         */
        @Override
        public boolean equals(Object otherObj) {
            if (this == otherObj) return true;
            if (otherObj == null || getClass() != otherObj.getClass()) return false;
            HashPair hashPair = (HashPair) otherObj;
            return key == hashPair.key &&
                    value == hashPair.value;
        }
    }

    /**
     * Внутренний класс, реализующий цепочку элементов, имеющих одинаковый хэш (bucket)
     */
    class HashBucket {
        private final List<HashPair> hashPairsStorage;

        HashBucket() {
            hashPairsStorage = new LinkedList<>(); //некрасиво. но DI-фреймворк не поддерживается Контестом.
        }

        /**
         * Поиск элемента в цепочке по заданному ключу.
         * @param key   Значение искомого ключа элемента.
         * @return  Ссылка на хранимый в цепочке элемент (или null, если элемент не найден).
         */
        private HashPair findReference(int key) {
            Iterator<HashPair> pairsIterator = hashPairsStorage.iterator();
            while (pairsIterator.hasNext()) {
                HashPair kv = pairsIterator.next();
                if (key == kv.key) return kv;
            }
            return null;
        }

        /**
         * Получение значения элемента по ключу.
         * @param key   Значение искомого ключа элемента.
         * @return  Значение элемента с заданным ключом (или -1, если элемент не найден).
         */
        public int getValue(int key) {
            HashPair res = findReference(key);
            return (res == null) ? NOT_FOUND : res.value;
        }

        /**
         * Добавление элемента в цепочку.
         * @param element Ссылка на добавляемый элемент.
         */
        public void putValue(HashPair element) {
            HashPair kv = findReference(element.key);

            if (kv == null)
                hashPairsStorage.add(element);
            else
                kv.value = element.value;
        }

        /**
         * Удаление элемента из цепочки по значению ключа.
         * @param key Ключ удаляемого элемента.
         * @return  Значение удаляемого элемента на момент удаления (или -1, если элемент не найден).
         */
        public int deleteValue(int key) {
            HashPair element = findReference(key);
            if (element == null) return NOT_FOUND;

            int previousValue = element.value;
            hashPairsStorage.remove(element);
            return previousValue;
        }
    }

    /**
     * Конструктор основного класса хэш-таблицы
     * @param size  Начальное (фиксированное) количество цепочек элементов (buckets).
     */
    HashTable(int size) {
        hashBucketsStorage = new HashBucket[size];
    }

    /**
     * Вычисление номера цепочки элементов (bucket) по значению ключа.
     * @param key   Значение ключа элемента.
     * @return  Номер цепочки элементов.
     */
    private int computeBucketIdx(int key) {
        return key % hashBucketsStorage.length;
    }

    /**
     * Получение значения элемента по ключу.
     * @param key   Ключ искомого элемента.
     * @return  Значение элемента в текстовой форме (или "None", если элемент не найден)
     */
    public String getElement(int key) {
        int idx = computeBucketIdx(key);
        if (hashBucketsStorage[idx] == null) return NONE_STR;
        int value = hashBucketsStorage[idx].getValue(key);
        return (value == NOT_FOUND) ? NONE_STR : String.valueOf(value);
    }

    /**
     * Добавление элемента в хэш-таблицу.
     * @param key   Ключ добавляемого элемента.
     * @param value Значение добавляемого элемента.
     */
    public void putElement(int key, int value) {
        HashPair kv = new HashPair(key, value);
        int idx = computeBucketIdx(key);
        if (hashBucketsStorage[idx] == null)
            hashBucketsStorage[idx] = new HashBucket(); //некрасиво. но DI-фреймворк не поддерживается Контестом.

        hashBucketsStorage[idx].putValue(kv);
    }

    /**
     * Удаление элемента по ключу.
     * @param key   Ключ удаляемого элемента.
     * @return  Значение элемента в текстовом виде на момент удаления (или "None", если элемент не найден)
     */
    public String deleteElement(int key) {
        int idx = computeBucketIdx(key);
        if (hashBucketsStorage[idx] == null) return NONE_STR;
        int value = hashBucketsStorage[idx].deleteValue(key);
        return (value == NOT_FOUND) ? NONE_STR : String.valueOf(value);
    }
}


/**
 * Класс текстового интерпретатора команд управления хэш-таблицей ("get", "put" и "delete")
 */
class Commander {
    private final String GET_CMD = "get";
    private final String PUT_CMD = "put";
    private final String DEL_CMD = "delete";

    private HashTable cmdObject;

    /**
     * Конструктор класса интерпретатора.
     * @param objRef    Ссылка на управляемый экземпляр хэш-таблицы.
     */
    Commander(HashTable objRef) {
        cmdObject = objRef;
    }

    /**
     * Выполнение команды по ее текстовому представлению (вкл. извлечение параметров).
     * @param parameters    Текстовое представление команды с набором параметров.
     * @return Текстовое представление результата выполнения команды.
     */
    public String interpret(String parameters) {
        StringTokenizer tokenizer = new StringTokenizer(parameters);
        String cmdName = tokenizer.nextToken();

        switch (cmdName) {
            case GET_CMD:
                return cmdObject.getElement(Integer.parseInt(tokenizer.nextToken()));
            case PUT_CMD:
                cmdObject.putElement(Integer.parseInt(tokenizer.nextToken()),
                                     Integer.parseInt(tokenizer.nextToken()));
                return ""; //текстовый аналог void
            case DEL_CMD:
                return cmdObject.deleteElement(Integer.parseInt(tokenizer.nextToken()));
            default:
                return ""; //текстовый аналог void
        }
    }
}

/**
 * Основной класс решения. Содержит точку входа.
 */
public class FinalSolutionB {
    private static final int NUMBER_OF_BUCKETS = 33199; // количество цепочек в хэш-таблице.

    /**
     * Функция обработки массива входных параметров.
     * @param input Массив входных команд с параметрами.
     * @return  Текстовое представление совокупного результата выполнения набора переданных команд.
     */
    public static String processRequests(String[] input) {
        HashTable hashTable = new HashTable(NUMBER_OF_BUCKETS);
        Commander commander = new Commander(hashTable);

        int cmdCount = Integer.parseInt(input[0]);
        StringJoiner stringJoiner = new StringJoiner("\n");

        for (int i = 1; i < cmdCount + 1; i++) {
            String cmdOutput = commander.interpret(input[i]);
            if (!cmdOutput.isEmpty())
                stringJoiner.add(cmdOutput);
        }

        return stringJoiner.toString();
    }

    /**
     * Точка входа в программу.
     * @param args  Аргументы командной строки (для целей совместимости).
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int numOfRequests = Integer.parseInt(reader.readLine());
        String[] buffer = new String[numOfRequests + 1];

        buffer[0] = String.valueOf(numOfRequests);
        for (int i = 1; i < numOfRequests + 1; i++)
            buffer[i] = reader.readLine();

        System.out.println(processRequests(buffer));
    }
}
