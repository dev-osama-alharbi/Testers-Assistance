package sa.osama_alharbi.prj.testers.assistance.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sa.osama_alharbi.prj.testers.assistance.model.ElementFxModel;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.PathFxModel;
import sa.osama_alharbi.prj.testers.assistance.service.*;
import sa.osama_alharbi.prj.testers.assistance.utils.Fx;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class PageFxController implements Initializable {
    public static boolean HASE_BEAN = true;
//    public static URL FXML_PATH = ViewGUIIndex.class.getResource( PageFxController.class.getSimpleName()+".fxml");
    public static URL FXML_PATH = Fx.getViewXmlFilePath(PageFxController.class);
    private final GUIFxService gui;
    private final ViewFxService viewGUI;
    private final ControllerFxService controllerGUI;
    private final Model model;
    private final OnFxService onFxService;
    private final PathFxService pathFxService;
    @FXML
    public ListView<ElementFxModel> lst;
    @FXML
    public VBox root;
    @FXML
    public Tab infoTab,pathTab;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        this.obsList = FXCollections.observableArrayList();
//        this.lst.setItems(this.obsList);
        this.lst.setItems(model.element.obsList);

        this.lst.setCellFactory(stringListView -> new ListCell<>(){
            @Override
            protected void updateItem(ElementFxModel item, boolean empty) {
                super.updateItem(item, empty);
                if(!empty && item != null){
                    FXMLLoader lod = gui.getLoader(Page_ByItemFxController.FXML_PATH, Page_ByItemFxController.HASE_BEAN);
                    VBox pane = (VBox) gui.getPain(lod);
                    Page_ByItemFxController controller = lod.<Page_ByItemFxController>getController();
                    controller.setElementFxModel(item,PageFxController.this);
                    setGraphic(pane);
                }else{
                    setText(null);
                    setGraphic(null);
                }
            }
        });
        this.lst.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null ){
                controllerGUI.pageInfo.setFullXPath(newValue);
//                controllerGUI.pagePath.setFullXPath(newValue,this);
                model.element.add.addSelectedElement(newValue);
                controllerGUI.pagePath.setFullXPath(newValue.getFullXpath());
            }else{
                this.lst.getSelectionModel().clearSelection();
                model.element.delete.clearSelectedElement();
                controllerGUI.pageInfo.setFullXPath(null);
                controllerGUI.pagePath.setFullXPath(null);
            }
        });

        infoTab.setContent(viewGUI.gui_PageInfo);
        pathTab.setContent(viewGUI.gui_PagePath);
    }
    @FXML
    public void onStartStopBtn(){
        onFxService.onSlmCRUD.startStop(model.page.selectedPage.get().getUrl());
    }

    public void showImage(String base64Image) {
        Fx.showImageFullPage(gui.stage,base64Image);
    }

    public PathFxModel getPathById(long pathId) {
        return pathFxService.getPathById(pathId);
    }
}
