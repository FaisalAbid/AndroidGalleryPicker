package labs.leandog;

import java.io.File;

import org.junit.runners.model.InitializationError;

import com.xtremelabs.robolectric.RobolectricConfig;

public class LibraryTestRunner extends TestRunner {

    public LibraryTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass, new RobolectricConfig(new File("../lib/")));
    }
}
