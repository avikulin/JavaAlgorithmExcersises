package YTester;

public interface ThrowableFunction<R, T> {
    T tryApply(R r) throws Exception;
}