package FinalB.Tests;

import FinalB.Utils.TreeBuilder;

// import test utilities
import YTester.YTestCase;
import YTester.YTestRunner;

public class RunTests {

    public static void main(String[] args) {


        YTestRunner<String, TestParamsBundle> tr = new YTestRunner<>(
                FinalB.Tests.TestFunctionEnvelope::process,
                String::equals,
                i -> i,
                200);


        //  Берем шаблон 0. Удаляем корень
        tr.append(new YTestCase<>(
                        "0-1",
                        new TestParamsBundle(TestDataFabric.getTree(0), 1, 50),
                        "[-1]"
                )
        );

        //  Берем шаблон 1. Удаляем корень.
        tr.append(new YTestCase<>(
                        "1-1",
                        new TestParamsBundle(TestDataFabric.getTree(1), 2, 50),
                        "[-1, 35]"
                )
        );

        //  Берем шаблон 1. Удаляем лист.
        tr.append(new YTestCase<>(
                        "1-2",
                        new TestParamsBundle(TestDataFabric.getTree(1), 2, 35),
                        "[-1, 50]"
                )
        );

        //  Берем шаблон 2. Удаляем корень.
        tr.append(new YTestCase<>(
                        "2-1",
                        new TestParamsBundle(TestDataFabric.getTree(2), 2, 50),
                        "[-1, 75]"
                )
        );

        //  Берем шаблон 2. Удаляем лист.
        tr.append(new YTestCase<>(
                        "2-2",
                        new TestParamsBundle(TestDataFabric.getTree(2), 2, 75),
                        "[-1, 50]"
                )
        );

        //  Берем шаблон 3. Удаляем корень.
        tr.append(new YTestCase<>(
                        "3-1",
                        new TestParamsBundle(TestDataFabric.getTree(3), 2, 50),
                        "[-1, 75, 35]"
                )
        );

        //  Берем шаблон 3. Удаляем левый лист.
        tr.append(new YTestCase<>(
                        "3-2",
                        new TestParamsBundle(TestDataFabric.getTree(3), 2, 35),
                        "[-1, 50, -1, 75]"
                )
        );
        //  Берем шаблон 3. Удаляем правый лист.
        tr.append(new YTestCase<>(
                        "3-3",
                        new TestParamsBundle(TestDataFabric.getTree(3), 2, 75),
                        "[-1, 50, 35]"
                )
        );

        //  Берем шаблон 4. Удаляем корень.
        tr.append(new YTestCase<>(
                        "4-1",
                        new TestParamsBundle(TestDataFabric.getTree(4), 3, 50),
                        "[-1, 35, 15]"
                )
        );
        //  Берем шаблон 4. Удаляем середину.
        tr.append(new YTestCase<>(
                        "4-2",
                        new TestParamsBundle(TestDataFabric.getTree(4), 3, 35),
                        "[-1, 50, 15]"
                )
        );
        //  Берем шаблон 4. Удаляем лист.
        tr.append(new YTestCase<>(
                        "4-3",
                        new TestParamsBundle(TestDataFabric.getTree(4), 3, 15),
                        "[-1, 50, 35]"
                )
        );
        //  Берем шаблон 5. Удаляем корень.
        tr.append(new YTestCase<>(
                        "5-1",
                        new TestParamsBundle(TestDataFabric.getTree(5), 3, 50),
                        "[-1, 75, -1, 95]"
                )
        );
        //  Берем шаблон 5. Удаляем середину.
        tr.append(new YTestCase<>(
                        "5-2",
                        new TestParamsBundle(TestDataFabric.getTree(5), 3, 75),
                        "[-1, 50, -1, 95]"
                )
        );
        //  Берем шаблон 5. Удаляем лист.
        tr.append(new YTestCase<>(
                        "5-3",
                        new TestParamsBundle(TestDataFabric.getTree(5), 3, 95),
                        "[-1, 50, -1, 75]"
                )
        );
        //  Берем шаблон 6. Удаляем середину.
        tr.append(new YTestCase<>(
                        "6-1",
                        new TestParamsBundle(TestDataFabric.getTree(6), 3, 35),
                        "[-1, 50, 45]"
                )
        );
        //  Берем шаблон 7. Удаляем середину.
        tr.append(new YTestCase<>(
                        "7-1",
                        new TestParamsBundle(TestDataFabric.getTree(7), 3, 75),
                        "[-1, 50, -1, 65]"
                )
        );

        //  Берем шаблон 8. Удаляем корень.
        tr.append(new YTestCase<>(
                        "8-1",
                        new TestParamsBundle(TestDataFabric.getTree(8), 3, 50),
                        "[-1, 75, 35, 95, 15]"
                )
        );
        //  Берем шаблон 8. Удаляем середину слева.
        tr.append(new YTestCase<>(
                        "8-2",
                        new TestParamsBundle(TestDataFabric.getTree(8), 3, 35),
                        "[-1, 50, 15, 75, -1, -1, -1, 95]"
                )
        );
        //  Берем шаблон 8. Удаляем середину справа.
        tr.append(new YTestCase<>(
                        "8-3",
                        new TestParamsBundle(TestDataFabric.getTree(8), 3, 75),
                        "[-1, 50, 35, 95, 15]"
                )
        );

        //  Берем шаблон 9. Удаляем корень.
        tr.append(new YTestCase<>(
                        "9-1",
                        new TestParamsBundle(TestDataFabric.getTree(9), 3, 50),
                        "[-1, 75, 35, 95]"
                )
        );
        //  Берем шаблон 9. Удаляем лист слева.
        tr.append(new YTestCase<>(
                        "9-2",
                        new TestParamsBundle(TestDataFabric.getTree(9), 3, 35),
                        "[-1, 50, -1, 75, -1, -1, -1, 95]"
                )
        );

        //  Берем шаблон 9. Удаляем середину справа.
        tr.append(new YTestCase<>(
                        "9-3",
                        new TestParamsBundle(TestDataFabric.getTree(9), 3, 75),
                        "[-1, 50, 35, 95]"
                )
        );

        //  Берем шаблон 10. Удаляем корень.
        tr.append(new YTestCase<>(
                        "10-1",
                        new TestParamsBundle(TestDataFabric.getTree(10), 3, 50),
                        "[-1, 75, 35, -1, 15]"
                )
        );
        //  Берем шаблон 10. Удаляем середину слева.
        tr.append(new YTestCase<>(
                        "10-2",
                        new TestParamsBundle(TestDataFabric.getTree(10), 3, 35),
                        "[-1, 50, 15, 75]"
                )
        );
        //  Берем шаблон 10. Удаляем лист справа.
        tr.append(new YTestCase<>(
                        "10-3",
                        new TestParamsBundle(TestDataFabric.getTree(10), 3, 75),
                        "[-1, 50, 35, -1, 15]"
                )
        );
        //  Берем шаблон 11. Удаляем корень.
        tr.append(new YTestCase<>(
                        "11-1",
                        new TestParamsBundle(TestDataFabric.getTree(11), 3, 50),
                        "[-1, 75, 65, 95]"
                )
        );
        //  Берем шаблон 11. Удаляем середину.
        tr.append(new YTestCase<>(
                        "11-2",
                        new TestParamsBundle(TestDataFabric.getTree(11), 3, 75),
                        "[-1, 50, -1, 95, -1, -1, 65]"
                )
        );
        //  Берем шаблон 12. Удаляем корень.
        tr.append(new YTestCase<>(
                        "12-1",
                        new TestParamsBundle(TestDataFabric.getTree(12), 3, 50),
                        "[-1, 35, 15, 45]"
                )
        );
        //  Берем шаблон 12. Удаляем середину.
        tr.append(new YTestCase<>(
                        "12-2",
                        new TestParamsBundle(TestDataFabric.getTree(12), 3, 35),
                        "[-1, 50, 45, -1, 15]"
                )
        );
        //  Берем шаблон 13. Удаляем корень.
        tr.append(new YTestCase<>(
                        "13-1",
                        new TestParamsBundle(TestDataFabric.getTree(13), 3, 50),
                        "[-1, 75, 35, -1, -1, 45]"
                )
        );

        //  Берем шаблон 13. Удаляем середину слева.
        tr.append(new YTestCase<>(
                        "13-2",
                        new TestParamsBundle(TestDataFabric.getTree(13), 3, 35),
                        "[-1, 50, 45, 75]"
                )
        );

        //  Берем шаблон 14. Удаляем корень.
        tr.append(new YTestCase<>(
                        "14-1",
                        new TestParamsBundle(TestDataFabric.getTree(14), 3, 50),
                        "[-1, 65, 35, 75]"
                )
        );

        //  Берем шаблон 14. Удаляем середину справа.
        tr.append(new YTestCase<>(
                        "14-2",
                        new TestParamsBundle(TestDataFabric.getTree(14), 3, 75),
                        "[-1, 50, 35, 65]"
                )
        );

        //  Берем шаблон 15. Удаляем корень.
        tr.append(new YTestCase<>(
                        "15-1",
                        new TestParamsBundle(TestDataFabric.getTree(15), 3, 50),
                        "[-1, 75, 35, 95, -1, 45]"
                )
        );

        //  Берем шаблон 15. Удаляем середину слева.
        tr.append(new YTestCase<>(
                        "15-2",
                        new TestParamsBundle(TestDataFabric.getTree(15), 3, 35),
                        "[-1, 50, 45, 75, -1, -1, -1, 95]"
                )
        );

        //  Берем шаблон 15. Удаляем середину справа.
        tr.append(new YTestCase<>(
                        "15-3",
                        new TestParamsBundle(TestDataFabric.getTree(15), 3, 75),
                        "[-1, 50, 35, 95, -1, 45]"
                )
        );

        //  Берем шаблон 16. Удаляем корень.
        tr.append(new YTestCase<>(
                        "16-1",
                        new TestParamsBundle(TestDataFabric.getTree(16), 3, 50),
                        "[-1, 65, 35, 75, 15]"
                )
        );

        //  Берем шаблон 16. Удаляем середину слева.
        tr.append(new YTestCase<>(
                        "16-2",
                        new TestParamsBundle(TestDataFabric.getTree(16), 3, 35),
                        "[-1, 50, 15, 75, -1, -1, 65]"
                )
        );

        //  Берем шаблон 16. Удаляем середину справа.
        tr.append(new YTestCase<>(
                        "16-3",
                        new TestParamsBundle(TestDataFabric.getTree(16), 3, 75),
                        "[-1, 50, 35, 65, 15]"
                )
        );

        //  Берем шаблон 17. Удаляем корень.
        tr.append(new YTestCase<>(
                        "17-1",
                        new TestParamsBundle(TestDataFabric.getTree(17), 3, 50),
                        "[-1, 65, 35, 75, -1, 45]"
                )
        );

        //  Берем шаблон 17. Удаляем середину слева.
        tr.append(new YTestCase<>(
                        "17-2",
                        new TestParamsBundle(TestDataFabric.getTree(17), 3, 35),
                        "[-1, 50, 45, 75, -1, -1, 65]"
                )
        );

        //  Берем шаблон 17. Удаляем середину справа.
        tr.append(new YTestCase<>(
                        "17-3",
                        new TestParamsBundle(TestDataFabric.getTree(17), 3, 75),
                        "[-1, 50, 35, 65, -1, 45]"
                )
        );

        //  Берем шаблон 17. Удаляем лист слева.
        tr.append(new YTestCase<>(
                        "17-3",
                        new TestParamsBundle(TestDataFabric.getTree(17), 3, 45),
                        "[-1, 50, 35, 75, -1, -1, 65]"
                )
        );
        //  Берем шаблон 17. Удаляем лист справа.
        tr.append(new YTestCase<>(
                        "17-4",
                        new TestParamsBundle(TestDataFabric.getTree(17), 3, 65),
                        "[-1, 50, 35, 75, -1, 45]"
                )
        );
        //  Берем шаблон 18. Удаляем корень.
        tr.append(new YTestCase<>(
                        "18-1",
                        new TestParamsBundle(TestDataFabric.getTree(18), 3, 50),
                        "[-1, 75, 35, -1, 15, 45]"
                )
        );
        //  Берем шаблон 18. Удаляем середину слева.
        tr.append(new YTestCase<>(
                        "18-2",
                        new TestParamsBundle(TestDataFabric.getTree(18), 3, 35),
                        "[-1, 50, 45, 75, 15]"
                )
        );
        //  Берем шаблон 18. Удаляем верхний лист справа.
        tr.append(new YTestCase<>(
                        "18-3",
                        new TestParamsBundle(TestDataFabric.getTree(18), 3, 75),
                        "[-1, 50, 35, -1, 15, 45]"
                )
        );
        //  Берем шаблон 18. Удаляем нижний лист слева.
        tr.append(new YTestCase<>(
                        "18-4",
                        new TestParamsBundle(TestDataFabric.getTree(18), 3, 15),
                        "[-1, 50, 35, 75, -1, 45]"
                )
        );
        //  Берем шаблон 18. Удаляем нижний лист справа.
        tr.append(new YTestCase<>(
                        "18-5",
                        new TestParamsBundle(TestDataFabric.getTree(18), 3, 45),
                        "[-1, 50, 35, 75, 15]"
                )
        );
        //  Берем шаблон 19. Удаляем корень.
        tr.append(new YTestCase<>(
                        "19-1",
                        new TestParamsBundle(TestDataFabric.getTree(19), 3, 50),
                        "[-1, 65, 35, 75, -1, -1, -1, 95]"
                )
        );
        //  Берем шаблон 19. Удаляем верхний лист слева.
        tr.append(new YTestCase<>(
                        "19-2",
                        new TestParamsBundle(TestDataFabric.getTree(19), 3, 35),
                        "[-1, 50, -1, 75, -1, -1, 65, 95]"
                )
        );
        //  Берем шаблон 19. Удаляем середину справа.
        tr.append(new YTestCase<>(
                        "19-3",
                        new TestParamsBundle(TestDataFabric.getTree(19), 3, 75),
                        "[-1, 50, 35, 95, -1, -1, 65]"
                )
        );
        //  Берем шаблон 19. Удаляем нижний лист слева.
        tr.append(new YTestCase<>(
                        "19-3",
                        new TestParamsBundle(TestDataFabric.getTree(19), 3, 65),
                        "[-1, 50, 35, 75, -1, -1, -1, 95]"
                )
        );
        //  Берем шаблон 19. Удаляем нижний лист справа.
        tr.append(new YTestCase<>(
                        "19-4",
                        new TestParamsBundle(TestDataFabric.getTree(19), 3, 95),
                        "[-1, 50, 35, 75, -1, -1, 65]"
                )
        );
        //  Берем шаблон 20. Удаляем корень.
        tr.append(new YTestCase<>(
                        "20-1",
                        new TestParamsBundle(TestDataFabric.getTree(20), 3, 50),
                        "[-1, 75, 35, 95, 15, 45]"
                )
        );
        //  Берем шаблон 20. Удаляем середину слева.
        tr.append(new YTestCase<>(
                        "20-2",
                        new TestParamsBundle(TestDataFabric.getTree(20), 3, 35),
                        "[-1, 50, 45, 75, 15, -1, -1, 95]"
                )
        );
        //  Берем шаблон 20. Удаляем середину справа.
        tr.append(new YTestCase<>(
                        "20-3",
                        new TestParamsBundle(TestDataFabric.getTree(20), 3, 75),
                        "[-1, 50, 35, 95, 15, 45]"
                )
        );

        //  Берем шаблон 21. Удаляем корень.
        tr.append(new YTestCase<>(
                        "21-1",
                        new TestParamsBundle(TestDataFabric.getTree(21), 3, 50),
                        "[-1, 65, 35, 75, 15, -1, -1, 95]"
                )
        );
        //  Берем шаблон 21. Удаляем середину слева.
        tr.append(new YTestCase<>(
                        "21-2",
                        new TestParamsBundle(TestDataFabric.getTree(21), 3, 35),
                        "[-1, 50, 15, 75, -1, -1, 65, 95]"
                )
        );

        //  Берем шаблон 21. Удаляем середину справа.
        tr.append(new YTestCase<>(
                        "21-3",
                        new TestParamsBundle(TestDataFabric.getTree(21), 3, 75),
                        "[-1, 50, 35, 95, 15, -1, 65]"
                )
        );

        //  Берем шаблон 22. Удаляем корень.
        tr.append(new YTestCase<>(
                        "22-1",
                        new TestParamsBundle(TestDataFabric.getTree(22), 3, 50),
                        "[-1, 65, 35, 75, 15, 45, -1, 95]"
                )
        );
        //  Берем шаблон 22. Удаляем середину слева.
        tr.append(new YTestCase<>(
                        "22-2",
                        new TestParamsBundle(TestDataFabric.getTree(22), 3, 35),
                        "[-1, 50, 45, 75, 15, -1, 65, 95]"
                )
        );
        //  Берем шаблон 22. Удаляем середину справа.
        tr.append(new YTestCase<>(
                        "22-3",
                        new TestParamsBundle(TestDataFabric.getTree(22), 3, 75),
                        "[-1, 50, 35, 95, 15, 45, 65]"
                )
        );
        //  Берем шаблон 23. Удаляем корень.
        tr.append(new YTestCase<>(
                        "23-1",
                        new TestParamsBundle(TestDataFabric.getTree(23), 4, 50),
                        "[-1, 35, 15, -1, 10]"
                )
        );
        //  Берем шаблон 23. Удаляем середину сверху.
        tr.append(new YTestCase<>(
                        "23-2",
                        new TestParamsBundle(TestDataFabric.getTree(23), 4, 35),
                        "[-1, 50, 15, -1, 10]"
                )
        );
        //  Берем шаблон 23. Удаляем середину снизу.
        tr.append(new YTestCase<>(
                        "23-3",
                        new TestParamsBundle(TestDataFabric.getTree(23), 4, 15),
                        "[-1, 50, 35, -1, 10]"
                )
        );
        //  Берем шаблон 24. Удаляем корень.
        tr.append(new YTestCase<>(
                        "24-1",
                        new TestParamsBundle(TestDataFabric.getTree(24), 4, 50),
                        "[-1, 75, -1, 95, -1, -1, -1, 100]"
                )
        );
        //  Берем шаблон 24. Удаляем середину сверху.
        tr.append(new YTestCase<>(
                        "24-2",
                        new TestParamsBundle(TestDataFabric.getTree(24), 4, 75),
                        "[-1, 50, -1, 95, -1, -1, -1, 100]"
                )
        );
        //  Берем шаблон 24. Удаляем середину снизу.
        tr.append(new YTestCase<>(
                        "24-3",
                        new TestParamsBundle(TestDataFabric.getTree(24), 4, 95),
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
        tr.append(new YTestCase<>(
                        "29-1",
                        new TestParamsBundle(TestDataFabric.getTree(29), 4, 50),
                        "[-1, 75, 65, -1, 60, 70]"
                )
        );
        //  Берем шаблон 29. Удаляем середину сверху.
        tr.append(new YTestCase<>(
                        "29-2",
                        new TestParamsBundle(TestDataFabric.getTree(29), 4, 75),
                        "[-1, 50, -1, 65, -1, -1, 60, 70]"
                )
        );
        //  Берем шаблон 29. Удаляем середину снизу.
        tr.append(new YTestCase<>(
                        "29-3",
                        new TestParamsBundle(TestDataFabric.getTree(29), 4, 65),
                        "[-1, 50, -1, 75, -1, -1, 70, -1, -1, -1, -1, -1, 60]"
                )
        );

        //  Берем шаблон 30. Удаляем корень.
        tr.append(new YTestCase<>(
                        "30-1",
                        new TestParamsBundle(TestDataFabric.getTree(30), 4, 50),
                        "[-1, 35, -1, 45, -1, -1, 40, 49]"
                )
        );
        //  Берем шаблон 30. Удаляем середину сверху.
        tr.append(new YTestCase<>(
                        "30-2",
                        new TestParamsBundle(TestDataFabric.getTree(30), 4, 35),
                        "[-1, 50, 45, -1, 40, 49]"
                )
        );
        //  Берем шаблон 30. Удаляем середину снизу.
        tr.append(new YTestCase<>(
                        "30-3",
                        new TestParamsBundle(TestDataFabric.getTree(30), 4, 45),
                        "[-1, 50, 35, -1, -1, 49, -1, -1, -1, -1, 40]"
                )
        );

        //  Берем шаблон 31. Удаляем корень.
        tr.append(new YTestCase<>(
                        "31-1",
                        new TestParamsBundle(TestDataFabric.getTree(31), 4, 50),
                        "[-1, 35, -1, 45, -1, -1, 40]"
                )
        );
        //  Берем шаблон 31. Удаляем середину сверху.
        tr.append(new YTestCase<>(
                        "31-2",
                        new TestParamsBundle(TestDataFabric.getTree(31), 4, 35),
                        "[-1, 50, 45, -1, 40]"
                )
        );
        //  Берем шаблон 31. Удаляем середину снизу.
        tr.append(new YTestCase<>(
                        "31-3",
                        new TestParamsBundle(TestDataFabric.getTree(31), 4, 45),
                        "[-1, 50, 35, -1, -1, 40]"
                )
        );
        //  Берем шаблон 32. Удаляем корень.
        tr.append(new YTestCase<>(
                        "32-1",
                        new TestParamsBundle(TestDataFabric.getTree(32), 4, 50),
                        "[-1, 75, 65, -1, -1, 70]"
                )
        );
        //  Берем шаблон 32. Удаляем середину сверху.
        tr.append(new YTestCase<>(
                        "32-2",
                        new TestParamsBundle(TestDataFabric.getTree(32), 4, 75),
                        "[-1, 50, -1, 65, -1, -1, -1, 70]"
                )
        );
        //  Берем шаблон 32. Удаляем середину снизу.
        tr.append(new YTestCase<>(
                        "32-3",
                        new TestParamsBundle(TestDataFabric.getTree(32), 4, 65),
                        "[-1, 50, -1, 75, -1, -1, 70]"
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
         

        tr.append(new YTestCase<>(
                        "EXAMPLE-FROM-BOOK",
                        new TestParamsBundle(new TreeBuilder(new int[]{-1,
                                50,
                                25, -1,
                                15, 35, -1, -1,
                                5, 20, 30, 40, -1, -1, -1, -1}
                        ).projectToTree(), 4, 25),
                        "[-1, 50, 30, -1, 15, 35, -1, -1, 5, 20, -1, 40]"
                )
        );

        tr.append(new YTestCase<>(
                        "NEGATIVE-TEST",
                        new TestParamsBundle(new TreeBuilder(new int[]{-1,
                                50,
                                25, -1,
                                15, 35, -1, -1,
                                5, 20, 30, 40, -1, -1, -1, -1}
                        ).projectToTree(), 4, 225),
                        "[-1, 50, 25, -1, 15, 35, -1, -1, 5, 20, 30, 40]"
                )
        );
        tr.append(new YTestCase<>(
                        "NULL-TEST",
                        new TestParamsBundle(null, 1, 100),
                        "[-1]"
                )
        );
        // run all tests & print results to console
        tr.run();
        //tr.run( new String[]{"1-1"});
    }
}
