package FinalB.Tests;

import FinalB.Solution;
import FinalB.Utils.TreeBuilder;
import FinalB.Tests.TestParamsBundle;

// import test utilities
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {

    public static void main(String[] args) {
        /* Библиотека тестовых деревьев

        0) *        1) /       2)  \         3) /\

        4) /        5) \       6)  /         7) \
          /             \          \            /

        8)  /\     9) /\       10)   /\      11)  \      12)  /
           /  \         \           /             /\         /\

        13) /\     14)  /\     15)   /\      16)   /\    17)  /\
            \            /           \ \          / /         \/

        18)  /\    19)  /\     20)   /\      21)   /\    22)   /\
            /\           /\         /\ \          / /\        /\/\

        23)   /    24) \       25) /         26)  \      27)  /      28)  \     29)  \      30) /
             /          \          \              /          /             \         /          \
            /            \          \            /           \             /        /\          /\
                                                                                                   __
        31)   /    32) \       33)  /        34)  \      35)  /      36)   \    37)   /\    38)   /  \
              \        /           /\             /\         /\            /\        /\/\        /\  /\
              /        \            /             \         /\              /\      /\  /\      /\/\/\/\

         */

        TreeBuilder[] trees = new TreeBuilder[39];

        trees[0] = new TreeBuilder(new int[]{-1,50});

        trees[1] = new TreeBuilder(new int[]{-1,50, 35, -1});
        trees[2] = new TreeBuilder(new int[]{-1,50, -1, 75});
        trees[3] = new TreeBuilder(new int[]{-1,50, 35, 75});

        trees[4] = new TreeBuilder(new int[]{-1,50, 35, -1, 15, -1, -1, -1});
        trees[5] = new TreeBuilder(new int[]{-1,50, -1, 75, -1, -1, -1, 95});
        trees[6] = new TreeBuilder(new int[]{-1,50, 35, -1, -1, 45, -1, -1});
        trees[7] = new TreeBuilder(new int[]{-1,50, -1, 75, -1, -1, 65, -1});

        trees[8] = new TreeBuilder(new int[]{-1,50, 35, 75, 15, -1, -1, 95});
        trees[9] = new TreeBuilder(new int[]{-1,50, 35, 75, -1, -1, -1, 95});
        trees[10] = new TreeBuilder(new int[]{-1,50, 35, 75, 15, -1, -1, -1});
        trees[11] = new TreeBuilder(new int[]{-1,50, -1, 75, -1, -1, 65, 95});
        trees[12] = new TreeBuilder(new int[]{-1,50, 35, -1, 15, 45, -1, -1});

        trees[13] = new TreeBuilder(new int[]{-1,50, 35, 75, -1, 45, -1, -1});
        trees[14] = new TreeBuilder(new int[]{-1,50, 35, 75, -1, -1, 65, -1});
        trees[15] = new TreeBuilder(new int[]{-1,50, 35, 75, -1, 45, -1, 95});
        trees[16] = new TreeBuilder(new int[]{-1,50, 35, 75, 15, -1, 65, -1});
        trees[17] = new TreeBuilder(new int[]{-1,50, 35, 75, -1, 45, 65, -1});

        trees[18] = new TreeBuilder(new int[]{-1,50, 35, 75, 15, 45, -1, -1});
        trees[19] = new TreeBuilder(new int[]{-1,50, 35, 75, -1, -1, 65, 95});
        trees[20] = new TreeBuilder(new int[]{-1,50, 35, 75, 15, 45, -1, 95});
        trees[21] = new TreeBuilder(new int[]{-1,50, 35, 75, 15, -1, 65, 95});
        trees[22] = new TreeBuilder(new int[]{-1,50, 35, 75, 15, 45, 65, 95});

        trees[23] = new TreeBuilder(new int[]{-1,50, 35, -1, 15, -1, -1, -1, 10, -1, -1, -1, -1, -1, -1, -1});
        trees[24] = new TreeBuilder(new int[]{-1,50, -1, 75, -1, -1, -1, 95, -1, -1, -1, -1, -1, -1, -1, 100});
        trees[25] = new TreeBuilder(new int[]{-1,50, 35, -1, -1, 45, -1, -1, -1, -1, -1, 49, -1, -1, -1, -1});
        trees[26] = new TreeBuilder(new int[]{-1,50, -1, 75, -1, -1, 65, -1, -1, -1, -1, -1, 60, -1, -1, -1});
        trees[27] = new TreeBuilder(new int[]{-1,50, 35, -1, 15, -1, -1, -1, -1, 20, -1, -1, -1, -1, -1, -1});
        trees[28] = new TreeBuilder(new int[]{-1,50, -1, 75, -1, -1, -1, 95, -1, -1, -1, -1, -1, -1, 90, -1});
        trees[29] = new TreeBuilder(new int[]{-1,50, -1, 75, -1, -1, 65, -1, -1, -1, -1, -1, 60, 70, -1, -1});
        trees[30] = new TreeBuilder(new int[]{-1,50, 35, -1, -1, 45, -1, -1, -1, -1, 40, 49, -1, -1, -1, -1});
        trees[31] = new TreeBuilder(new int[]{-1,50, 35, -1, -1, 45, -1, -1, -1, -1, 40, -1, -1, -1, -1, -1});
        trees[32] = new TreeBuilder(new int[]{-1,50, -1, 75, -1, -1, 65, -1, -1, -1, -1, -1, -1, 70, -1, -1});
        trees[33] = new TreeBuilder(new int[]{-1,50, 35, -1, 15, 45, -1, -1, -1, -1, 40, -1, -1, -1, -1, -1});
        trees[34] = new TreeBuilder(new int[]{-1,50, -1, 75, -1, -1, 65, 95, -1, -1, -1, -1, -1, 70, -1, -1});
        trees[35] = new TreeBuilder(new int[]{-1,50, 35, -1, 15, 45, -1, -1, 10, 20, -1, -1, -1, -1, -1, -1});
        trees[36] = new TreeBuilder(new int[]{-1,50, -1, 75, -1, -1, 65, 95, -1, -1, -1, -1, -1, -1, 90, 100});
        trees[37] = new TreeBuilder(new int[]{-1,50, 35, 75, 15, 45, 65, 95, 10, 20, -1, -1, -1, -1, 90, 100});
        trees[38] = new TreeBuilder(new int[]{-1,50, 35, 75, 15, 45, 65, 95, 10, 20, 40, 50, 60, 70, 90, 100});


        YTestRunner<String, TestParamsBundle> tr = new YTestRunner<String, TestParamsBundle>(
                FinalB.Solution::process,
                String::equals,
                i -> i,
                200);

        // add test case definitions
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "",
                        new TestParamsBundle(trees[0].projectToTree(), 1, 50),
                        "[-1, 50, 30, 15, 35, -1, -1, 5, 20, 40, -1, -1, -1, -1]"
                )
        );
        
        //  Берем шаблон 0. Удаляем корень
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "0-1",
                        new TestParamsBundle(trees[0].projectToTree(), 1, 50),
                        "[-1]"
                )
        );

        //  Берем шаблон 1. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "1-1",
                        new TestParamsBundle(trees[1].projectToTree(), 1, 50),
                        "[-1, 35, -1, -1]"
                )
        );

        //  Берем шаблон 1. Удаляем лист.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "1-2",
                        new TestParamsBundle(trees[1].projectToTree(), 1, 35),
                        "[-1, 50, -1, -1]"
                )
        );

        //  Берем шаблон 2. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "2-1",
                        new TestParamsBundle(trees[2].projectToTree(), 1, 50),
                        "[-1, 75, -1, -1]"
                )
        );

        //  Берем шаблон 2. Удаляем лист.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "2-2",
                        new TestParamsBundle(trees[2].projectToTree(), 1, 75),
                        "[-1, 50, -1, -1]"
                )
        );

        //  Берем шаблон 3. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "3-1",
                        new TestParamsBundle(trees[3].projectToTree(), 1, 50),
                        "[-1, 75, 35, -1]"
                )
        );

        //  Берем шаблон 3. Удаляем левый лист.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "3-2",
                        new TestParamsBundle(trees[3].projectToTree(), 1, 35),
                        "[-1, 50, -1, 75]"
                )
        );
        //  Берем шаблон 3. Удаляем правый лист.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "3-3",
                        new TestParamsBundle(trees[3].projectToTree(), 1, 75),
                        "[-1, 50, 35, -1]"
                )
        );

        //  Берем шаблон 4. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "4-1",
                        new TestParamsBundle(trees[4].projectToTree(), 2, 50),
                        "[-1, 25, 15, -1]"
                )
        );
        //  Берем шаблон 4. Удаляем середину.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "4-2",
                        new TestParamsBundle(trees[4].projectToTree(), 2, 35),
                        "[-1, 50, 15, -1]"
                )
        );
        //  Берем шаблон 4. Удаляем лист.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "4-3",
                        new TestParamsBundle(trees[4].projectToTree(), 2, 15),
                        "[-1, 50, 25, -1]"
                )
        );
        //  Берем шаблон 5. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "5-1",
                        new TestParamsBundle(trees[5].projectToTree(), 2, 50),
                        "[-1, 75, -1, 95]"
                )
        );
        //  Берем шаблон 5. Удаляем середину.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "5-2",
                        new TestParamsBundle(trees[5].projectToTree(), 2, 75),
                        "[-1, 50, -1, 95]"
                )
        );
        //  Берем шаблон 5. Удаляем лист.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "5-3",
                        new TestParamsBundle(trees[5].projectToTree(), 2, 95),
                        "[-1, 50, -1, 75]"
                )
        );
        //  Берем шаблон 6. Удаляем середину.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "6-1",
                        new TestParamsBundle(trees[6].projectToTree(), 2, 35),
                        "[-1, 50, 45, -1]"
                )
        );
        //  Берем шаблон 7. Удаляем середину.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "7-1",
                        new TestParamsBundle(trees[7].projectToTree(), 2, 75),
                        "[-1, 50, -1, 65]"
                )
        );

        //  Берем шаблон 8. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "8-1",
                        new TestParamsBundle(trees[8].projectToTree(), 2, 50),
                        "[-1, 75, 35, 95, 15, -1, -1, -1]"
                )
        );
        //  Берем шаблон 8. Удаляем середину слева.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "8-2",
                        new TestParamsBundle(trees[8].projectToTree(), 2, 35),
                        "[-1, 50, 15, 75, -1, -1, -1, 95]"
                )
        );
        //  Берем шаблон 8. Удаляем середину справа.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "8-3",
                        new TestParamsBundle(trees[8].projectToTree(), 2, 75),
                        "[-1, 50, 35, 95, 15, -1, -1, -1]"
                )
        );

        //  Берем шаблон 9. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "9-1",
                        new TestParamsBundle(trees[9].projectToTree(), 2, 50),
                        "[-1, 75, 35, 95]"
                )
        );
        //  Берем шаблон 9. Удаляем лист слева.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "9-2",
                        new TestParamsBundle(trees[9].projectToTree(), 2, 35),
                        "[-1, 50, -1, 75, -1, -1, -1, 95]"
                )
        );

        //  Берем шаблон 9. Удаляем середину справа.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "9-3",
                        new TestParamsBundle(trees[9].projectToTree(), 2, 35),
                        "[-1, 50, 35, 95]"
                )
        );

        //  Берем шаблон 10. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "10-1",
                        new TestParamsBundle(trees[10].projectToTree(), 2, 50),
                        "[-1, 75, 35, -1, 15, -1, -1, -1]"
                )
        );
        //  Берем шаблон 10. Удаляем середину слева.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "10-2",
                        new TestParamsBundle(trees[10].projectToTree(), 2, 30),
                        "[-1, 50, 15, 75]"
                )
        );
        //  Берем шаблон 10. Удаляем лист справа.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "10-3",
                        new TestParamsBundle(trees[10].projectToTree(), 2, 75),
                        "[-1, 50, 35, -1, 15, -1, -1, -1]"
                )
        );
        //  Берем шаблон 11. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "11-1",
                        new TestParamsBundle(trees[11].projectToTree(), 2, 50),
                        "[-1, 75, 65, 95]"
                )
        );
        //  Берем шаблон 11. Удаляем середину.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "11-2",
                        new TestParamsBundle(trees[11].projectToTree(), 2, 75),
                        "[-1, 50, -1, 65, -1, -1, -1, 95]"
                )
        );
        //  Берем шаблон 12. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "12-1",
                        new TestParamsBundle(trees[12].projectToTree(), 2, 50),
                        "[-1, 35, 15, 45]"
                )
        );
        //  Берем шаблон 12. Удаляем середину.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "12-2",
                        new TestParamsBundle(trees[12].projectToTree(), 2, 35),
                        "[-1, 50, 45, -1, 15, -1, -1, -1]"
                )
        );
        //  Берем шаблон 13. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "13-1",
                        new TestParamsBundle(trees[13].projectToTree(), 2, 50),
                        "[-1, 75, 35, -1, -1, 45, -1, -1]"
                )
        );

        //  Берем шаблон 13. Удаляем середину слева.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "13-2",
                        new TestParamsBundle(trees[13].projectToTree(), 2, 35),
                        "[-1, 50, 45, 75]"
                )
        );

        //  Берем шаблон 14. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "14-1",
                        new TestParamsBundle(trees[14].projectToTree(), 2, 50),
                        "[-1, 65, 35, 75]"
                )
        );

        //  Берем шаблон 14. Удаляем середину справа.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "14-2",
                        new TestParamsBundle(trees[14].projectToTree(), 2, 75),
                        "[-1, 50, 35, 65]"
                )
        );

        //  Берем шаблон 15. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "15-1",
                        new TestParamsBundle(trees[15].projectToTree(), 2, 50),
                        "[-1, 75, 35, 95, -1, 45, -1, -1]"
                )
        );

        //  Берем шаблон 15. Удаляем середину слева.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "15-2",
                        new TestParamsBundle(trees[15].projectToTree(), 2, 35),
                        "[-1, 50, 45, 75, -1, -1, -1, 95]"
                )
        );

        //  Берем шаблон 15. Удаляем середину справа.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "15-3",
                        new TestParamsBundle(trees[15].projectToTree(), 2, 75),
                        "[-1, 50, 35, 95, -1, 45, -1, -1]"
                )
        );

        //  Берем шаблон 16. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "16-1",
                        new TestParamsBundle(trees[16].projectToTree(), 2, 50),
                        "[-1, 65, 35, 75, 15, -1, -1, -1]"
                )
        );

        //  Берем шаблон 16. Удаляем середину слева.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "16-2",
                        new TestParamsBundle(trees[16].projectToTree(), 2, 35),
                        "[-1, 50, 15, 75, -1, -1, 65, -1]"
                )
        );

        //  Берем шаблон 16. Удаляем середину справа.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "16-3",
                        new TestParamsBundle(trees[16].projectToTree(), 2, 75),
                        "[-1, 50, 35, 65, 15, -1, -1, -1]"
                )
        );

        //  Берем шаблон 17. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "17-1",
                        new TestParamsBundle(trees[17].projectToTree(), 2, 50),
                        "[-1, 65, 35, 75, -1, 45, -1, -1]"
                )
        );

        //  Берем шаблон 17. Удаляем середину слева.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "17-2",
                        new TestParamsBundle(trees[17].projectToTree(), 2, 35),
                        "[-1, 50, 45, 75, -1, -1, 65, -1]"
                )
        );

        //  Берем шаблон 17. Удаляем середину справа.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "17-3",
                        new TestParamsBundle(trees[17].projectToTree(), 2, 75),
                        "[-1, 50, 35, 65, -1, 45, -1, -1]"
                )
        );

        //  Берем шаблон 17. Удаляем лист слева.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "17-3",
                        new TestParamsBundle(trees[17].projectToTree(), 2, 45),
                        "[-1, 50, 35, 75, -1, -1, 65, -1]"
                )
        );
        //  Берем шаблон 17. Удаляем лист справа.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "17-4",
                        new TestParamsBundle(trees[17].projectToTree(), 2, 65),
                        "[-1, 50, 35, 75, -1, 45, -1, -1]"
                )
        );
        //  Берем шаблон 18. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "18-1",
                        new TestParamsBundle(trees[18].projectToTree(), 2, 50),
                        "[-1, 75, 35, -1, 15, 45, -1, -1]"
                )
        );
        //  Берем шаблон 18. Удаляем середину слева.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "18-2",
                        new TestParamsBundle(trees[18].projectToTree(), 2, 35),
                        "[-1, 50, 45, 75, 15, -1, -1, -1]"
                )
        );
        //  Берем шаблон 18. Удаляем верхний лист справа.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "18-3",
                        new TestParamsBundle(trees[18].projectToTree(), 2, 75),
                        "[-1, 50, 35, -1, 15, 45, -1, -1]"
                )
        );
        //  Берем шаблон 18. Удаляем нижний лист слева.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "18-4",
                        new TestParamsBundle(trees[18].projectToTree(), 2, 15),
                        "[-1, 50, 35, 75, -1, 45, -1, -1]"
                )
        );
        //  Берем шаблон 18. Удаляем нижний лист справа.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "18-5",
                        new TestParamsBundle(trees[18].projectToTree(), 2, 45),
                        "[-1, 50, 35, 75, 15, -1, -1, -1]"
                )
        );
        //  Берем шаблон 19. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "19-1",
                        new TestParamsBundle(trees[19].projectToTree(), 2, 50),
                        "[-1, 65, 35, 75, -1, -1, -1, 95]"
                )
        );
        //  Берем шаблон 19. Удаляем верхний лист слева.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "19-2",
                        new TestParamsBundle(trees[19].projectToTree(), 2, 35),
                        "[-1, 50, -1, 75, -1, -1, 65, 95]"
                )
        );
        //  Берем шаблон 19. Удаляем середину справа.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "19-3",
                        new TestParamsBundle(trees[19].projectToTree(), 2, 75),
                        "[-1, 50, 35, 95, -1, -1, 65, -1]"
                )
        );
        //  Берем шаблон 19. Удаляем нижний лист слева.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "19-3",
                        new TestParamsBundle(trees[19].projectToTree(), 2, 65),
                        "[-1, 50, 35, 75, -1, -1, -1, 95]"
                )
        );
        //  Берем шаблон 19. Удаляем нижний лист справа.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "19-4",
                        new TestParamsBundle(trees[19].projectToTree(), 2, 95),
                        "[-1, 50, 35, 75, -1, -1, 65, -1]"
                )
        );
        //  Берем шаблон 20. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "20-1",
                        new TestParamsBundle(trees[20].projectToTree(), 2, 50),
                        "[-1, 75, 35, 95, 15, 45, -1, -1]"
                )
        );
        //  Берем шаблон 20. Удаляем середину слева.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "20-2",
                        new TestParamsBundle(trees[20].projectToTree(), 2, 35),
                        "[-1, 50, 45, 75, 15, -1, -1, 95]"
                )
        );
        //  Берем шаблон 20. Удаляем середину справа.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "20-3",
                        new TestParamsBundle(trees[20].projectToTree(), 2, 75),
                        "[-1, 50, 35, 95, 15, 45, -1, -1]"
                )
        );

        //  Берем шаблон 21. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "21-1",
                        new TestParamsBundle(trees[21].projectToTree(), 2, 50),
                        "[-1, 65, 35, 75, 15, -1, -1, 95]"
                )
        );
        //  Берем шаблон 21. Удаляем середину слева.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "21-2",
                        new TestParamsBundle(trees[21].projectToTree(), 2, 35),
                        "[-1, 50, 15, 75, -1, -1, 65, 95]"
                )
        );

        //  Берем шаблон 21. Удаляем середину справа.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "21-3",
                        new TestParamsBundle(trees[21].projectToTree(), 2, 75),
                        "[-1, 50, 35, 95, 15, -1, 65, -1]"
                )
        );

        //  Берем шаблон 22. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "22-1",
                        new TestParamsBundle(trees[22].projectToTree(), 2, 50),
                        "[-1, 65, 35, 75, 15, 45, -1, 95]"
                )
        );
        //  Берем шаблон 22. Удаляем середину слева.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "22-2",
                        new TestParamsBundle(trees[22].projectToTree(), 2, 35),
                        "[-1, 50, 45, 75, 15, -1, 65, 95]"
                )
        );
        //  Берем шаблон 22. Удаляем середину справа.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "22-3",
                        new TestParamsBundle(trees[22].projectToTree(), 2, 75),
                        "[-1, 50, 35, 95, 15, 45, 65, -1]"
                )
        );
        //  Берем шаблон 23. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "23-1",
                        new TestParamsBundle(trees[23].projectToTree(), 3, 50),
                        "[-1, 35, 15, -1, 10, -1, -1, -1]"
                )
        );
        //  Берем шаблон 23. Удаляем середину сверху.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "23-2",
                        new TestParamsBundle(trees[23].projectToTree(), 3, 35),
                        "[-1, 50, 15, -1, 10, -1, -1, -1]"
                )
        );
        //  Берем шаблон 23. Удаляем середину снизу.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "23-3",
                        new TestParamsBundle(trees[23].projectToTree(), 3, 15),
                        "[-1, 50, 35, -1, 10, -1, -1, -1]"
                )
        );
        //  Берем шаблон 24. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "24-1",
                        new TestParamsBundle(trees[24].projectToTree(), 3, 50),
                        "[-1, 75, -1, 95, -1, -1, -1, 100]"
                )
        );
        //  Берем шаблон 24. Удаляем середину сверху.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "24-2",
                        new TestParamsBundle(trees[24].projectToTree(), 3, 75),
                        "[-1, 50, -1, 95, -1, -1, -1, 100]"
                )
        );
        //  Берем шаблон 24. Удаляем середину снизу.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "24-3",
                        new TestParamsBundle(trees[24].projectToTree(), 3, 95),
                        "[-1, 50, -1, 75, -1, -1, -1, 100]"
                )
        );
        //  Берем шаблон 25. Удаляем корень.
        //  Берем шаблон 25. Удаляем середину сверху.
        //  Берем шаблон 25. Удаляем середину снизу.
        //  Берем шаблон 26. Удаляем корень.
        //  Берем шаблон 26. Удаляем середину сверху.
        //  Берем шаблон 26. Удаляем середину снизу.
        //  Берем шаблон 27. Удаляем корень.
        //  Берем шаблон 27. Удаляем середину сверху.
        //  Берем шаблон 27. Удаляем середину снизу.
        //  Берем шаблон 28. Удаляем корень.
        //  Берем шаблон 28. Удаляем середину сверху.
        //  Берем шаблон 28. Удаляем середину снизу.
        //  Берем шаблон 29. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "29-1",
                        new TestParamsBundle(trees[29].projectToTree(), 3, 50),
                        "[-1, 60, -1, 75, -1, -1, -1, 65, -1, -1, -1, -1, -1, -1, 70, -1, -1]"
                )
        );
        //  Берем шаблон 29. Удаляем середину сверху.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "29-2",
                        new TestParamsBundle(trees[29].projectToTree(), 3, 75),
                        "[-1, 50, -1, 65, -1, -1, 60, 70]"
                )
        );
        //  Берем шаблон 29. Удаляем середину снизу.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "29-3",
                        new TestParamsBundle(trees[29].projectToTree(), 3, 65),
                        "[-1, 50, -1, 75, -1, -1, 70, -1, -1, -1, -1, -1, 60, -1, -1, -1]"
                )
        );

        //  Берем шаблон 30. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "30-1",
                        new TestParamsBundle(trees[30].projectToTree(), 3, 50),
                        "[-1, 35, -1, 45, -1, -1, 40, 49]"
                )
        );
        //  Берем шаблон 30. Удаляем середину сверху.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "30-2",
                        new TestParamsBundle(trees[30].projectToTree(), 3, 35),
                        "[-1, 50, 40, -1, -1, 45, -1, -1, -1, -1, -1, 49, -1, -1, -1, -1]"
                )
        );
        //  Берем шаблон 30. Удаляем середину снизу.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "30-3",
                        new TestParamsBundle(trees[30].projectToTree(), 3, 45),
                        "[-1, 50, 35, -1, -1, 45, -1, -1, -1, -1, 40, 49, -1, -1, -1, -1]"
                )
        );

        //  Берем шаблон 31. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "31-1",
                        new TestParamsBundle(trees[31].projectToTree(), 3, 50),
                        "[-1, 35, -1, 45, -1, -1, 40, -1]"
                )
        );
        //  Берем шаблон 31. Удаляем середину сверху.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "31-2",
                        new TestParamsBundle(trees[31].projectToTree(), 3, 35),
                        "[-1, 50, 40, -1, -1, 45, -1, -1]"
                )
        );
        //  Берем шаблон 31. Удаляем середину снизу.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "31-3",
                        new TestParamsBundle(trees[31].projectToTree(), 3, 45),
                        "[-1, 50, 35, -1, 40, -1, -1]"
                )
        );
        //  Берем шаблон 32. Удаляем корень.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "32-1",
                        new TestParamsBundle(trees[32].projectToTree(), 3, 50),
                        "[-1, 60, -1, 75, -1, -1, 70, -1]"
                )
        );
        //  Берем шаблон 32. Удаляем середину сверху.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "32-2",
                        new TestParamsBundle(trees[32].projectToTree(), 3, 75),
                        "[-1, 50, -1, 65, -1, -1, -1, 70]"
                )
        );
        //  Берем шаблон 32. Удаляем середину снизу.
        tr.append(new YTestCase<String, TestParamsBundle>(
                        "32-3",
                        new TestParamsBundle(trees[32].projectToTree(), 3, 65),
                        "[-1, 50, -1, 75, -1, -1, 70, -1]"
                )
        );
        //  Берем шаблон 33. Удаляем корень.
        //  Берем шаблон 33. Удаляем середину сверху.
        //  Берем шаблон 33. Удаляем середину снизу.
        //  Берем шаблон 33. Удаляем верхний лист слева.
        //  Берем шаблон 33. Удаляем нижний лист справа.
        //  Берем шаблон 34. Удаляем корень.
        //  Берем шаблон 34. Удаляем середину сверху.
        //  Берем шаблон 34. Удаляем середину снизу.
        //  Берем шаблон 34. Удаляем нижний лист слева.
        //  Берем шаблон 34. Удаляем верхний лист справа.
        //  Берем шаблон 35. Удаляем корень.
        //  Берем шаблон 35. Удаляем середину сверху.
        //  Берем шаблон 35. Удаляем середину снизу.
        //  Берем шаблон 36. Удаляем корень.
        //  Берем шаблон 36. Удаляем середину сверху.
        //  Берем шаблон 36. Удаляем середину снизу.
        //  Берем шаблон 37. Удаляем корень.
        //  Берем шаблон 37. Удаляем середину сверху слева.
        //  Берем шаблон 37. Удаляем середину снизу слева.
        //  Берем шаблон 37. Удаляем середину сверху справа.
        //  Берем шаблон 37. Удаляем середину снизу справа.
        //  Берем шаблон 38. Удаляем корень.
        //  Берем шаблон 37. Удаляем середину сверху слева снаружи.
        //  Берем шаблон 37. Удаляем середину снизу слева снаружи.
        //  Берем шаблон 37. Удаляем середину снизу слева внутри.
        //  Берем шаблон 37. Удаляем середину сверху справа снаружи.
        //  Берем шаблон 37. Удаляем середину снизу справа внутри.
         

        tr.append(new YTestCase<String, TestParamsBundle>(
                        "",
                        new TestParamsBundle(new TreeBuilder(new int[]{-1, 50,
                                25, -1,
                                15, 35, -1, -1,
                                5, 20, 30, 40, -1, -1, -1, -1}
                        ).projectToTree(), 3, 25),
                        "[-1, 50, 30, 15, 35, -1, -1, 5, 20, 40, -1, -1, -1, -1]"
                )
        );
        // run all tests & print results to console
        tr.run();
    }
}
