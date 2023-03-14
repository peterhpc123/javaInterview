package com.example.assemblerframework.extension;


import com.example.assemblerframework.store.StoreDetails;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

/*
* This is an extension class with jupiter models, we can use this class do some extension before running method like: @ExtendWith(AssemblerExtension.class)
* It will execute the override method following lifecycle callbacks
* */
public class AssemblerExtension implements BeforeAllCallback, BeforeEachCallback, ParameterResolver {
    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(AssemblerExtension.class);


    /*
    * description:
    * */
    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        if (!AnnotationSupport.isAnnotated(extensionContext.getRequiredTestClass(), Story.class)) {
            throw new Exception("Use @Story annotation with this test class...");
        }
        Class<?> clazz = extensionContext.getRequiredTestClass();// test class
        Story story = clazz.getAnnotation(Story.class);
        //TODO: get app's type
        StoreDetails storeDetails = new StoreDetails()
                .setName(story.name())
                .setDescription(story.description())
                .setClassName(clazz.getName());

        extensionContext.getStore(NAMESPACE).put(clazz.getName(), storeDetails);
    }

    @Override
    public void beforeEach(ExtensionContext extensionContext) throws Exception {
        if (!AnnotationSupport.isAnnotated(extensionContext.getRequiredTestMethod(), Scenario.class)) {
            throw new Exception("Use @Scenario annotation to use the StoryExtension Service. Mathod:" + extensionContext.getRequiredTestMethod());
        }
        Scene scene = new Scene().setMethodName(extensionContext.getRequiredTestMethod().getName());
        getStoreDetails(extensionContext).getStore().put(scene.getMethodName(), scene);
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return false;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return null;
    }

    private static StoreDetails getStoreDetails(ExtensionContext context) {
        Class clazz = context.getRequiredTestClass();
        return context.getStore(NAMESPACE).get(clazz.getName(), StoreDetails.class);
    }
}
