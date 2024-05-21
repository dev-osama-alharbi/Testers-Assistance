package sa.osama_alharbi.prj.testers.assistance.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.PageFxModel;
import sa.osama_alharbi.prj.testers.assistance.service.PagesFxService;
import sa.osama_alharbi.prj.testers.assistance.utils.Fx;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class Pages_InfoFxController implements Initializable {
    public static boolean HASE_BEAN = true;
    public static URL FXML_PATH = Fx.getViewXmlFilePath(Pages_InfoFxController.class);
    private final PagesFxService pagesFxService;
    @FXML
    public AnchorPane root;
    @FXML
    public TextField pageNameTxt,urlTxt,counterElementsTxt;

    private ObjectProperty<PageFxModel> pageFxModel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pageFxModel = new SimpleObjectProperty<>(null);
        pageFxModel.addListener((observable, oldValue, newValue) -> {
            clear();
            if(newValue != null){
                pageNameTxt.setText(newValue.getTitle());
                urlTxt.textProperty().bind(newValue.urlProperty());
//                counterElementsTxt.textProperty().bind(newValue.idProperty().asString());
                counterElementsTxt.setText(pagesFxService.countNumberOfElements(newValue)+"");
            }
        });
    }

    @FXML
    public void onClickSaveInfo(){
        if(pageFxModel.get() == null){
            return;
        }
        pagesFxService.updateTitle(pageNameTxt.getText(),pageFxModel.get());
    }

    @FXML
    public void onClickGoToElements(){
        if(pageFxModel.get() == null){
            return;
        }
        pagesFxService.showPage(pageFxModel.get());
    }

    public void setPageFxModel(PageFxModel pageFxModel){
        this.pageFxModel.setValue(pageFxModel);
    }

    private void clear() {
//        if(pageNameTxt.textProperty().isBound()){
//            pageNameTxt.textProperty().unbind();
//        }
        pageNameTxt.setText("");
        if(urlTxt.textProperty().isBound()){
            urlTxt.textProperty().unbind();
        }
        urlTxt.setText("");
//        if(counterElementsTxt.textProperty().isBound()){
//            counterElementsTxt.textProperty().unbind();
//        }
        counterElementsTxt.setText("");
    }

}
