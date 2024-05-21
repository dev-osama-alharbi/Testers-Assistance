package sa.osama_alharbi.prj.testers.assistance;


import javafx.application.Application;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sa.osama_alharbi.prj.testers.assistance.config.JavaFxLauncher;

@SpringBootApplication
@RequiredArgsConstructor
public class Main {
    // selenium
    // API
    // API-from postman, swagger
    // API-Spring


    // java
    // javaFx
    // Spring Framework
    // Database mariadb ==> MySQL
    // selenium
    // browser-extension
    public static void main(String[] args) {
        JavaFxLauncher.E = Main.class;
        Application.launch(JavaFxLauncher.class,args);
    }
}