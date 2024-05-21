package sa.osama_alharbi.prj.testers.assistance.service;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.controller.*;
import sa.osama_alharbi.prj.testers.assistance.config.JavaFxLauncher;
import sa.osama_alharbi.prj.testers.assistance.view.ViewGUIIndex;

import java.io.IOException;
import java.net.URL;

@RequiredArgsConstructor
@Service
public class GUIFxService {
    public Stage stage;
    public Scene scene;

    private final ControllerFxService controller;
    private final ViewFxService view;

    public void start(Stage stage) {
        this.stage = stage;
        generateGUI();
    }

    private void generateGUI() {

        FXMLLoader loaderPagePath = getLoader(Page_PathFxController.FXML_PATH, Page_PathFxController.HASE_BEAN);
        view.gui_PagePath = (AnchorPane) getPain(loaderPagePath);
        controller.pagePath = loaderPagePath.<Page_PathFxController>getController();

        FXMLLoader loaderPageInfo = getLoader(Page_InfoFxController.FXML_PATH, Page_InfoFxController.HASE_BEAN);
        view.gui_PageInfo = (AnchorPane) getPain(loaderPageInfo);
        controller.pageInfo = loaderPageInfo.<Page_InfoFxController>getController();

        FXMLLoader loaderPage = getLoader(PageFxController.FXML_PATH, PageFxController.HASE_BEAN);
        view.gui_Page = (VBox) getPain(loaderPage);
        controller.page = loaderPage.<PageFxController>getController();

        FXMLLoader loaderPage_infoView = getLoader(Pages_InfoFxController.FXML_PATH, Pages_InfoFxController.HASE_BEAN);
        view.gui_Pages_info = (AnchorPane) getPain(loaderPage_infoView);
        controller.pages_info = loaderPage_infoView.<Pages_InfoFxController>getController();

        FXMLLoader loaderPage_cookiesView = getLoader(Pages_CookiesFxController.FXML_PATH, Pages_CookiesFxController.HASE_BEAN);
        view.gui_Pages_cookies = (AnchorPane) getPain(loaderPage_cookiesView);
        controller.pages_cookies = loaderPage_cookiesView.<Pages_CookiesFxController>getController();

        FXMLLoader loaderPage_codeView = getLoader(Pages_CodeFxController.FXML_PATH, Pages_CodeFxController.HASE_BEAN);
        view.gui_Pages_code = (AnchorPane) getPain(loaderPage_codeView);
        controller.pages_code = loaderPage_codeView.<Pages_CodeFxController>getController();

        FXMLLoader loaderPages = getLoader(PagesFxController.FXML_PATH, PagesFxController.HASE_BEAN);
        view.gui_Pages = (VBox) getPain(loaderPages);
        controller.pages = loaderPages.<PagesFxController>getController();

        FXMLLoader loaderProject = getLoader(ProjectFxController.FXML_PATH, ProjectFxController.HASE_BEAN);
        view.gui_Project = (VBox) getPain(loaderProject);
        controller.project = loaderProject.<ProjectFxController>getController();

        FXMLLoader loaderRoot_Header = getLoader(Root_HeaderFxController.FXML_PATH, Root_HeaderFxController.HASE_BEAN);
        view.gui_Root_Header = (VBox) getPain(loaderRoot_Header);
        controller.root_HeaderFxController = loaderRoot_Header.<Root_HeaderFxController>getController();

        FXMLLoader loaderRoot = getLoader(RootFxController.FXML_PATH, RootFxController.HASE_BEAN);
        view.gui_Root = (BorderPane) getPain(loaderRoot);
        controller.root = loaderRoot.<RootFxController>getController();

        scene = new Scene(view.gui_Root);
        scene.getStylesheets().add(ViewGUIIndex.class.getResource("css.css").toExternalForm());
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

        Platform.setImplicitExit(true);
    }

    public FXMLLoader getLoader(String path, boolean hasBean) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(ViewGUIIndex.class.getResource(path + ".fxml"));
        if(hasBean){
            loader.setControllerFactory(JavaFxLauncher.springContext::getBean);
        }
        return loader;
    }

    public FXMLLoader getLoader(URL url, boolean hasBean) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);
        if(hasBean){
            loader.setControllerFactory(JavaFxLauncher.springContext::getBean);
        }
        return loader;
    }

    public Pane getPain(FXMLLoader loader) {
        try {
            return loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
