package ru.otus.utils;

public class TestHelper {
    private static final String TEST_SUCCESS_TEMPLATE = "Тест %s успешно пройден.\n";
    private static final String TEST_FAILED_TEMPLATE = "Тест %s не пройден.\n";
    private static final String TEST_RESULT_DELIMITER = "-".repeat(12);
    private static final String TESTS_RESULT_TEMPLATE = """
            Запущено тестов: %d.
            Успешно пройдено тестов: %d.
            Не пройдено тестов: %d.
            """;

    private final int testsCount;
    private int successTestsCount;
    private int failedTestsCount;

    public TestHelper(int testsCount) {
        this.testsCount = testsCount;
        this.successTestsCount = 0;
        this.failedTestsCount = 0;
    }

    public void printSuccessTest(String methodName) {
        increaseSuccessTestsCount();
        System.out.printf(TEST_SUCCESS_TEMPLATE, methodName);
    }

    public void printFailedTest(String methodName) {
        increaseFailedTestsCount();
        System.out.printf(TEST_FAILED_TEMPLATE, methodName);
    }

    public void printDelimiter() {
        System.out.println(TEST_RESULT_DELIMITER);
    }

    public void printResults() {
        System.out.printf(TESTS_RESULT_TEMPLATE, testsCount, successTestsCount, failedTestsCount);
    }

    private void increaseSuccessTestsCount() {
        successTestsCount += 1;
    }

    private void increaseFailedTestsCount() {
        failedTestsCount += 1;
    }
}
