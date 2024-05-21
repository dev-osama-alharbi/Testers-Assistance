package sa.osama_alharbi.prj.testers.assistance.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.ProjectFxModel;
import sa.osama_alharbi.prj.testers.assistance.service.GUIFxService;
import sa.osama_alharbi.prj.testers.assistance.service.ProjectFxService;
import sa.osama_alharbi.prj.testers.assistance.utils.Fx;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class ProjectFxController implements Initializable {
    public static boolean HASE_BEAN = true;
    public static URL FXML_PATH = Fx.getViewXmlFilePath(ProjectFxController.class);
    private final ProjectFxService projectFxService;
    private final GUIFxService guiFxService;
    private final Model model;

    @FXML
    public VBox root;
    @FXML
    public FlowPane flowPane;
    private ObservableMap<Long, Node> flowNodes = null;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        flowNodes = FXCollections.observableHashMap();
        model.project.obsList.addListener((ListChangeListener<? super ProjectFxModel>) observable -> {
            while (observable.next()){
                if(observable.wasAdded())
                    for(ProjectFxModel add: observable.getAddedSubList())
                        onAddProjectFxModel(add);

                if(observable.wasRemoved())
                    for(ProjectFxModel removed: observable.getRemoved())
                        onRemoveProjectFxModel(removed);
            }

        });

        flowNodes.addListener((MapChangeListener<? super Long, ? super Node>) change -> {
            if(change.wasAdded())
                flowPane.getChildren().add(change.getValueAdded());
            if(change.wasRemoved())
                flowPane.getChildren().remove(change.getValueRemoved());
        });

        model.project.obsList.forEach(this::onAddProjectFxModel);
    }

    private void onAddProjectFxModel(ProjectFxModel add){
        if(!flowNodes.containsKey(add.getId())){
            FXMLLoader loader = guiFxService.getLoader(Project_ItemFxController.FXML_PATH, Project_ItemFxController.HASE_BEAN);
            Node node = guiFxService.getPain(loader);
            Project_ItemFxController project_item = loader.<Project_ItemFxController>getController();
            project_item.init(this,add);
            flowNodes.put(add.getId(),node);
        }
    }

    private void onRemoveProjectFxModel(ProjectFxModel removed){
        flowNodes.remove(removed.getId());
    }

    @FXML
    public void onClickAddNewProject(){
        FXMLLoader loader = guiFxService.getLoader(Project_AddProjectFxController.FXML_PATH,Project_AddProjectFxController.HASE_BEAN);
        VBox node = (VBox) guiFxService.getPain(loader);
        Project_AddProjectFxController controller = loader.<Project_AddProjectFxController>getController();
        controller.setProjectFxService(projectFxService);

        Stage popupStage = new Stage();
        Scene popupScene = new Scene(node);
        popupStage.setScene(popupScene);
        popupStage.initOwner(guiFxService.stage);


        controller.setStage(popupStage);
        popupStage.show();
    }

    public void goToProject(ProjectFxModel projectFxModel) {
        projectFxService.showProject(projectFxModel);
    }
}
