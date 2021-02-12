package YTester;

@FunctionalInterface
public interface Comparer<S>{
    boolean compare(S t, S r);
}