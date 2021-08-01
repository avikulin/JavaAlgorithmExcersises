package YTester;

import javax.management.InvalidApplicationException;
import javax.management.OperationsException;

public interface ThrowableFunction<R, T> {
    T tryApply(R r) throws Exception;
}
