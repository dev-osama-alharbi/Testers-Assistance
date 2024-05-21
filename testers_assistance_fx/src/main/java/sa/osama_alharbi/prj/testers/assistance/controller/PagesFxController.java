package sa.osama_alharbi.prj.testers.assistance.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.PageFxModel;
import sa.osama_alharbi.prj.testers.assistance.service.GUIFxService;
import sa.osama_alharbi.prj.testers.assistance.service.OnFxService;
import sa.osama_alharbi.prj.testers.assistance.service.PagesFxService;
import sa.osama_alharbi.prj.testers.assistance.service.ViewFxService;
import sa.osama_alharbi.prj.testers.assistance.utils.Fx;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class PagesFxController implements Initializable {
    public static boolean HASE_BEAN = true;
    public static URL FXML_PATH = Fx.getViewXmlFilePath(PagesFxController.class);
    private final ViewFxService viewFxService;
    private final GUIFxService gui;
    public final Model model;
    private final PagesFxService pagesFxService;
    private final Pages_InfoFxController pages_infoFxController;
    public final OnFxService onFxService;
    @FXML
    public ListView<PageFxModel> lst;
    @FXML
    public VBox root;
    @FXML
    public Tab infoTab,cookiesTab,codeTab;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lst.setItems(model.page.obsList);
        this.lst.setCellFactory(stringListView -> new ListCell<>(){
            @Override
            protected void updateItem(PageFxModel item, boolean empty) {
                super.updateItem(item, empty);
                if(!empty && item != null){
                    setGraphic(generateItem(item));
                }else{
                    setText(null);
                    setGraphic(null);
                }
            }
        });
        this.lst.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue != null ){
                model.page.edit.editSelectedPage(newValue);
                pages_infoFxController.setPageFxModel(newValue);
//                controllerGUI.pageInfo.setFullXPath(newValue,this);
//                controllerGUI.pagePath.setFullXPath(newValue,this);

            }else{
                model.page.edit.editSelectedPage(null);
                pages_infoFxController.setPageFxModel(null);
                this.lst.getSelectionModel().clearSelection();
            }
        });
        infoTab.setContent(viewFxService.gui_Pages_info);
        cookiesTab.setContent(viewFxService.gui_Pages_cookies);
        codeTab.setContent(viewFxService.gui_Pages_code);
    }

    @FXML
    public void onClickAddNewPage(){
        FXMLLoader loader = gui.getLoader(Pages_AddPageFxController.FXML_PATH,Pages_AddPageFxController.HASE_BEAN);
        VBox node = (VBox) gui.getPain(loader);
        Pages_AddPageFxController controller = loader.<Pages_AddPageFxController>getController();
        controller.setPagesFxController(this);
        controller.setProjectFxModel(model.project.get.getSelectedProject());
        controller.setOnFxService(onFxService);

        Stage popupStage = new Stage();
        Scene popupScene = new Scene(node);
        popupStage.setScene(popupScene);
        popupStage.initOwner(gui.stage);


        controller.setStage(popupStage);
        popupStage.show();

//        viewFxService.gui_Root.setCenter(viewFxService.gui_Page);
//        pagesFxService.showPage();
    }

    private Pane generateItem(PageFxModel item){
        FXMLLoader lod = gui.getLoader(Pages_ItemFxController.FXML_PATH, Pages_ItemFxController.HASE_BEAN);
        VBox pane = (VBox) gui.getPain(lod);
        Pages_ItemFxController controller = lod.<Pages_ItemFxController>getController();
        controller.setPageFxModel(item);
        controller.setPagesFxController(this);
        return pane;
    }

    public void showPage(PageFxModel pageFxModel){
        pagesFxService.showPage(pageFxModel);
    }
}
