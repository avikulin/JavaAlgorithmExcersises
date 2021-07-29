package FinalB.Tests;

import FinalB.Utils.TreeBuilder;

import static FinalB.Solution.remove;

public class TestFunctionEnvelope {
    public static String process(TestParamsBundle params) {
        return new TreeBuilder(
                remove(
                        params.getRootNode(),
                        params.getKeyToDelete()
                ),
                params.getTreeHeight()
        ).getTreeSignature();
    }
}
