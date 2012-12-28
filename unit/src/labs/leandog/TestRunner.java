package labs.leandog;

import java.util.ArrayList;
import java.util.List;

import org.junit.runners.model.InitializationError;
import org.mockito.MockitoAnnotations;

import com.xtremelabs.robolectric.Robolectric;
import com.xtremelabs.robolectric.RobolectricConfig;
import com.xtremelabs.robolectric.RobolectricTestRunner;

public abstract class TestRunner extends RobolectricTestRunner {
    
    public TestRunner(Class<?> testClass, RobolectricConfig robolectricConfig) throws InitializationError {
        super(testClass, robolectricConfig);
    }

    @Override
    protected void bindShadowClasses() {
        super.bindShadowClasses();
        Robolectric.bindShadowClasses(getShadows());
    }

    @Override
    public Object createTest() throws Exception {
        Object test = super.createTest();
        MockitoAnnotations.initMocks(test);
        return test;
    }

    public List<Class<?>> getShadows() {
        ArrayList<Class<?>> shadows = new ArrayList<Class<?>>();
        return shadows;
    }
}
