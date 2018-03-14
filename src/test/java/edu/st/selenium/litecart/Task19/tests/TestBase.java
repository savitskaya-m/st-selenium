package edu.st.selenium.litecart.Task19.tests;

import edu.st.selenium.litecart.Task19.app.Application;
import org.junit.Before;

public class TestBase {

    private static ThreadLocal<Application> tlApp = new ThreadLocal<>();
    public Application app;

    @Before
    public void start() {
        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    app.quit();
                    app = null;
                }));
    }
}
