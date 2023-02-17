package gumtree.spoon;

import gumtree.spoon.diff.Diff;
import gumtree.spoon.diff.operations.InsertOperation;
import gumtree.spoon.diff.operations.Operation;
import gumtree.spoon.diff.support.SpoonSupport;
import org.junit.Test;
import spoon.reflect.declaration.CtElement;

import java.io.File;

public class FixBenchTest {
    @Test
    public void test1() throws Exception {
        String srcFile = "/Users/yumeng/Workspace/BUGs/FixBench/WithinSingleMethod/Genesis-NP/Genesis#178/old/FixedPointLongCodec.java";
        String dstFile = "/Users/yumeng/Workspace/BUGs/FixBench/WithinSingleMethod/Genesis-NP/Genesis#178/new/FixedPointLongCodec.java";

        File fl = new File(srcFile);
        File fr = new File(dstFile);

        AstComparator diff = new AstComparator();
        // DiffConfiguration diffConfig = new DiffConfiguration();
        // diffConfig.setMatcher(new CompositeMatchers.ClassicGumtreeTheta());
        Diff editScript = diff.compare(fl, fr);

        SpoonSupport support = new SpoonSupport();
        Operation op = editScript.getRootOperations().get(0);
        if (op instanceof InsertOperation) {
            CtElement invokeSrc = ((InsertOperation) op).getParent();
            CtElement invokeTgt = support.getMappedElement(editScript, invokeSrc, true);
            System.out.println(invokeSrc.toString());
            System.out.println(invokeTgt.toString());
        }

        System.out.println("debug");
    }
}
