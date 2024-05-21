package sa.osama_alharbi.prj.testers.assistance.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import sa.osama_alharbi.prj.testers.assistance.entity.Page;
import sa.osama_alharbi.prj.testers.assistance.entity.TestType;
import sa.osama_alharbi.prj.testers.assistance.enums.TestTypeEnum;
import sa.osama_alharbi.prj.testers.assistance.model.ProjectFxModel;
import sa.osama_alharbi.prj.testers.assistance.service.OnFxService;
import sa.osama_alharbi.prj.testers.assistance.utils.Fx;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Slf4j
public class Pages_AddPageFxController {
    public static boolean HASE_BEAN = false;
    public static URL FXML_PATH = Fx.getViewXmlFilePath(Pages_AddPageFxController.class);
    @Setter
    private Stage stage;
    @Setter
    private PagesFxController pagesFxController;
    @Setter
    private OnFxService onFxService;
    private ProjectFxModel projectFxModel;

    @FXML
    public VBox root;
    @FXML
    public TextField titleTxt,urlTxt;
    @FXML
    public ComboBox<String> testTypeCmbx;

    @FXML
    public void onClickAdd(){
        if(checkIsNotEmptyAndBlank(titleTxt) && checkIsNotEmptyAndBlank(urlTxt)){
            onFxService.onPagesCRUD.addNewPage(
                    Page
                            .builder()
                            .title(titleTxt.getText())
                            .url(urlTxt.getText())
                            .isCompleted(false)
                            .projectId(projectFxModel.getId())
                            .testTypeId(TestTypeEnum.UI.id)
                            .build()
            );
            stage.close();
        }
    }
    @FXML
    public void onClickCancel(){
        stage.close();
    }
    @FXML
    public void onClickUrlBtn(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("URL");
        alert.setHeaderText("Click Ok after go to URL");
        pagesFxController.onFxService.onSlmCRUD.startStop(pagesFxController.model.project.get.getSelectedProject().getBaseUrl());
        alert.showAndWait();
        urlTxt.setText(pagesFxController.onFxService.onSlmCRUD.getUrl());
    }

    private boolean checkIsNotEmptyAndBlank(TextField textField){
        return !textField.getText().isEmpty() && !textField.getText().isBlank();
    }

    public void setProjectFxModel(ProjectFxModel projectFxModel) {
        this.projectFxModel = projectFxModel;
        this.urlTxt.setText(this.projectFxModel.getBaseUrl());
    }
}
