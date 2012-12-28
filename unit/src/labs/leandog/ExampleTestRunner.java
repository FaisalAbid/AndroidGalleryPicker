package labs.leandog;

import java.io.File;

import org.junit.runners.model.InitializationError;

import com.xtremelabs.robolectric.RobolectricConfig;

public class ExampleTestRunner extends TestRunner {

    public ExampleTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass, new RobolectricConfig(new File("../example/")));
    }
}
