package sa.osama_alharbi.prj.testers.assistance.utils;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sa.osama_alharbi.prj.testers.assistance.controller.Page_ByItemFxController;
import sa.osama_alharbi.prj.testers.assistance.view.ViewGUIIndex;

import java.net.URL;

public class Fx {
    public static URL getViewXmlFilePath(Class<?> c){
        return ViewGUIIndex.class.getResource( c.getSimpleName().replace("FxController","FxView")+".fxml");
    }


    public static void showImageFullPage(Stage rootStage,String base64Image){
        StackPane background = new StackPane();
        background.getChildren().add(new ImageView(new Image(base64Image)));
        background.setOnKeyTyped(event -> {
            System.out.println(event.getCode().getName());
            System.out.println(event.getCharacter());
        });

        Stage stage = new Stage();
        Scene imgeScene = new Scene(background);
        stage.initOwner(rootStage);
        stage.setScene(imgeScene);
        stage.setFullScreen(true);
        stage.show();
        imgeScene.setOnKeyPressed(event -> {
            if(event.getCode() == KeyCode.ESCAPE){
                stage.close();
            }
        });
    }
}
