package sa.osama_alharbi.prj.testers.assistance.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lombok.Setter;
import sa.osama_alharbi.prj.testers.assistance.model.PageFxModel;
import sa.osama_alharbi.prj.testers.assistance.utils.Fx;

import java.net.URL;

public class Pages_ItemFxController {
    public static boolean HASE_BEAN = false;
    public static URL FXML_PATH = Fx.getViewXmlFilePath(Pages_ItemFxController.class);

    @FXML
    public VBox root;

    @FXML
    public Label titleTxt,linkTxt;
    private PageFxModel pageFxModel;
    @Setter
    private PagesFxController pagesFxController;

    public void setPageFxModel(PageFxModel pageFxModel){
        this.pageFxModel = pageFxModel;
        this.titleTxt.textProperty().bind(this.pageFxModel.titleProperty());
        this.linkTxt.textProperty().bind(this.pageFxModel.urlProperty());
    }

}
