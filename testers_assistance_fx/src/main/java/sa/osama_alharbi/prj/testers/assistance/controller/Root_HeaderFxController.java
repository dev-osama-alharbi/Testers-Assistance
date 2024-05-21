package sa.osama_alharbi.prj.testers.assistance.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.service.RootFxService;
import sa.osama_alharbi.prj.testers.assistance.utils.Fx;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class Root_HeaderFxController implements Initializable {
    public static boolean HASE_BEAN = true;
    public static URL FXML_PATH = Fx.getViewXmlFilePath(Root_HeaderFxController.class);
    private final Model model;
    private final RootFxService rootFxService;

    @FXML
    public VBox root;
    @FXML
    public HBox homeBox,projectBox, pageBox;
    @FXML
    public Button homeBtn,projectBtn, pageBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        projectBox.visibleProperty().bind(model.project.selectedProject.isNotNull());
        model.project.selectedProject.addListener((observable, oldValue, newValue) -> {
            if(projectBtn.textProperty().isBound()){
                projectBtn.textProperty().unbind();
            }
            if(newValue == null){
                projectBtn.textProperty().set("");
            }else{
                projectBtn.textProperty().bind(newValue.titleProperty());
            }
        });

        pageBox.visibleProperty().bind(model.page.selectedPage.isNotNull());
        model.page.selectedPage.addListener((observable, oldValue, newValue) -> {
            if(pageBtn.textProperty().isBound()){
                pageBtn.textProperty().unbind();
            }
            if(newValue == null){
                pageBtn.textProperty().set("");
            }else{
                pageBtn.textProperty().bind(newValue.titleProperty());
            }
        });
    }

    @FXML
    public void onClickHomeBtn() {
        rootFxService.showHome();
    }

    @FXML
    public void onClickProjectBtn() {
        rootFxService.showPages();
    }

    @FXML
    public void onClickPageBtn() {

    }
}
