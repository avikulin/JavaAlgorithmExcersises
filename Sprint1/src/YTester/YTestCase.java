package YTester;

public class YTestCase<T, R> {
    R input;
    T expectedResult;

    public YTestCase(R input_data, T expectedResult) {
        this.input = input_data;
        this.expectedResult = expectedResult;
    }
}

