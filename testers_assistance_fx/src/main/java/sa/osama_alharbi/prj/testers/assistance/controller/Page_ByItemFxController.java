package sa.osama_alharbi.prj.testers.assistance.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import sa.osama_alharbi.prj.testers.assistance.model.ElementFxModel;
import sa.osama_alharbi.prj.testers.assistance.model.PathFxModel;
import sa.osama_alharbi.prj.testers.assistance.utils.Fx;

import java.net.URL;
import java.util.ResourceBundle;

public class Page_ByItemFxController implements Initializable {
    public static boolean HASE_BEAN = false;
//    public static URL FXML_PATH = ViewGUIIndex.class.getResource( Page_ByItemFxController.class.getSimpleName()+".fxml");
    public static URL FXML_PATH = Fx.getViewXmlFilePath(Page_ByItemFxController.class);
    private ElementFxModel elementFxModel;
    private PageFxController pageFxController;

    @FXML
    public VBox root;

    @FXML
    public Label nameTagTxt,selectedPathTypeTxt;
    private StringProperty nameProperty,tagProperty;

    @FXML
    public FontAwesomeIconView isCorrectWayFont;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nameProperty = new SimpleStringProperty("");
        tagProperty = new SimpleStringProperty("");
        nameTagTxt.textProperty().bind(Bindings.concat(nameProperty," : ",tagProperty));
    }
    @FXML
    public void onClickDelete(ActionEvent event) {

    }

    public void setElementFxModel(ElementFxModel elementFxModel,PageFxController pageFxController) {
        this.elementFxModel = elementFxModel;
        this.pageFxController = pageFxController;
        nameProperty.bind(elementFxModel.nameProperty());
        tagProperty.bind(elementFxModel.tagProperty());
        this.elementFxModel.path_idProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == null){
                if(selectedPathTypeTxt.textProperty().isBound()){
                    selectedPathTypeTxt.textProperty().unbind();
                }
                selectedPathTypeTxt.setText("NaN");
            }else{
                PathFxModel pathFxModel = this.pageFxController.getPathById(newValue.intValue());
                selectedPathTypeTxt.setText(pathFxModel.getPathSlmType().type);
            }
        });
        if(elementFxModel.getPath_id() == 0){
            //TODO remove this
            return;
        }
        PathFxModel pathFxModel = this.pageFxController.getPathById(elementFxModel.getPath_id());
        if(pathFxModel != null){
            selectedPathTypeTxt.setText(pathFxModel.getPathSlmType().type);
        }
    }
}
