package sa.osama_alharbi.prj.testers.assistance.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import sa.osama_alharbi.prj.testers.assistance.service.ProjectFxService;
import sa.osama_alharbi.prj.testers.assistance.utils.Fx;

import java.net.URL;
import java.util.ResourceBundle;

@Slf4j
public class Project_AddProjectFxController implements Initializable {
    public static boolean HASE_BEAN = false;
    public static URL FXML_PATH = Fx.getViewXmlFilePath(Project_AddProjectFxController.class);
    @Setter
    private ProjectFxService projectFxService;
    @Setter
    private Stage stage;

    @FXML
    public VBox root;
    @FXML
    public TextField titleTxt,baseUrlTxt;
    @FXML
    public ComboBox<String> typeCmbx;

    @FXML
    public void onClickAdd(){
        if(!titleTxt.getText().isEmpty() && !titleTxt.getText().isBlank()
            && !baseUrlTxt.getText().isEmpty() && !baseUrlTxt.getText().isBlank()){
            projectFxService.addNewProject(titleTxt.getText(),baseUrlTxt.getText());
            stage.close();
        }
    }
    @FXML
    public void onClickCancel(){
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeCmbx.getItems().addAll("UI Selenium",
                "[SOON] API Normal",
                "[SOON] API [postman, swagger]",
                "[SOON] API Spring Framework");
        typeCmbx.getSelectionModel().selectFirst();
        typeCmbx.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == null || !newValue.equals("UI Selenium")){
                typeCmbx.getSelectionModel().selectFirst();
            }
        });

    }
}
