package sa.osama_alharbi.prj.testers.assistance.config;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class JavaFxLauncher extends Application {

    public static ConfigurableApplicationContext springContext;
    public static Class<?> E;


    @Override
    public void start(Stage stage) throws Exception {
        springContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(E).run();
    }

    @Override
    public void stop() {
        springContext.close();
        Platform.exit();
    }
}
