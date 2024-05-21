package sa.osama_alharbi.prj.testers.assistance.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.PageFxModel;
import sa.osama_alharbi.prj.testers.assistance.service.CodingFxService;
import sa.osama_alharbi.prj.testers.assistance.utils.Fx;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class Pages_CodeFxController implements Initializable {
    public static boolean HASE_BEAN = true;
    public static URL FXML_PATH = Fx.getViewXmlFilePath(Pages_CodeFxController.class);
    private final CodingFxService codingFxService;
    private final Model model;
    @FXML
    public AnchorPane root;
    @FXML
    public TextArea codeTxt;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        model.page.selectedPage.addListener((observable, oldValue, newValue) -> {
            clear();
            if(newValue != null){
                codeTxt.setText(codingFxService.generateUICodeForPageId(newValue.getId()));
            }
        });
    }
    @FXML
    public void onClickGenerateCode(){
        clear();
        PageFxModel pageModel = model.page.selectedPage.get();
        if(pageModel != null){
            codeTxt.setText(codingFxService.generateUICodeForPageId(pageModel.getId()));
        }
    }
    public void clear(){
        codeTxt.setText("");
    }
}
