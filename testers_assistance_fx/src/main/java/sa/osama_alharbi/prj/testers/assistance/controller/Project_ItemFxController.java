package sa.osama_alharbi.prj.testers.assistance.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.Getter;
import sa.osama_alharbi.prj.testers.assistance.model.ProjectFxModel;
import sa.osama_alharbi.prj.testers.assistance.utils.Fx;
import sa.osama_alharbi.prj.testers.assistance.view.ViewGUIIndex;

import java.net.URL;

//@RequiredArgsConstructor
//@Component
public class Project_ItemFxController {
    public static boolean HASE_BEAN = false;
    public static URL FXML_PATH = Fx.getViewXmlFilePath(Project_ItemFxController.class);
    @Getter
    private ProjectFxModel projectFxModel;
    @Getter
    private ProjectFxController project;

    @FXML
    public VBox root;
    @FXML
    public Label titleTxt;
    @FXML
    public void onClickEnter(){
        project.goToProject(this.projectFxModel);
    }

    public void init(ProjectFxController project, ProjectFxModel projectFxModel){
        this.project = project;
        this.projectFxModel = projectFxModel;
        this.titleTxt.textProperty().bind(this.projectFxModel.titleProperty());
    }

}
