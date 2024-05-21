package sa.osama_alharbi.prj.testers.assistance.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sa.osama_alharbi.prj.testers.assistance.model.PathFxModel;
import sa.osama_alharbi.prj.testers.assistance.service.ElementFxService;
import sa.osama_alharbi.prj.testers.assistance.utils.Fx;
import sa.osama_alharbi.prj.testers.assistance.model.ElementFxModel;

import java.net.URL;

@Component
@RequiredArgsConstructor
public class Page_InfoFxController {
    private final PageFxController pageFxController;
    private ElementFxModel elementFxModel;
    private final ElementFxService elementFxService;
    public static boolean HASE_BEAN = true;
    public static URL FXML_PATH = Fx.getViewXmlFilePath(Page_InfoFxController.class);
    @FXML public AnchorPane root;
    @FXML public TextField nameTxt,tagTxt,shortPathTxt;
    @FXML public ImageView img;

    @FXML
    public void onClickImg(){
        pageFxController.showImage(this.elementFxModel.getPhoto());
    }

    @FXML
    public void onClickSaveInfo(){
        if(this.elementFxModel == null){
            return;
        }
        elementFxService.updateName(this.elementFxModel.getElementModel(),nameTxt.getText());
    }

    public void setFullXPath(ElementFxModel elementFxModel) {
        this.elementFxModel = elementFxModel;
        if(elementFxModel == null){
            clear();
            return;
        }
        img.setImage(new Image(elementFxModel.getPhoto()));
//        nameTxt.textProperty().bind(elementFxModel.nameProperty());
        nameTxt.setText(elementFxModel.getName());
        tagTxt.textProperty().bind(elementFxModel.tagProperty());
//        shortPathTxt.textProperty().bind(elementFxModel.path_idProperty().asString());
        this.elementFxModel.path_idProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue == null){
                if(shortPathTxt.textProperty().isBound()){
                    shortPathTxt.textProperty().unbind();
                }
                shortPathTxt.setText("NaN");
            }else{
                PathFxModel pathFxModel = this.pageFxController.getPathById(newValue.intValue());
                shortPathTxt.textProperty().bind(pathFxModel.pathProperty());
            }
        });
        if(elementFxModel.getPath_id() != 0){
            //TODO remove this
            PathFxModel pathFxModel = this.pageFxController.getPathById(elementFxModel.getPath_id());
            if(pathFxModel != null){
                shortPathTxt.textProperty().bind(pathFxModel.pathProperty());
            }
        }
    }

    private void clear() {
        img.setImage(null);
        if(nameTxt.textProperty().isBound()){
            nameTxt.textProperty().unbind();
        }
        nameTxt.setText("");
        if(tagTxt.textProperty().isBound()){
            tagTxt.textProperty().unbind();
        }
        tagTxt.setText("");
        nameTxt.setText("");
        if(shortPathTxt.textProperty().isBound()){
            shortPathTxt.textProperty().unbind();
        }
        shortPathTxt.setText("");
    }
}
