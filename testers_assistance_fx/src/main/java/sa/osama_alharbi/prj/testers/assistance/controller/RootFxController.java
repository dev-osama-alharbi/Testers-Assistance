package sa.osama_alharbi.prj.testers.assistance.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sa.osama_alharbi.prj.testers.assistance.service.ViewFxService;
import sa.osama_alharbi.prj.testers.assistance.utils.Fx;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class RootFxController implements Initializable {
    public static boolean HASE_BEAN = true;
    public static URL FXML_PATH = Fx.getViewXmlFilePath(RootFxController.class);
    private final ViewFxService viewFxService;

    @FXML
    public BorderPane root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        root.setTop(viewFxService.gui_Root_Header);
        root.setCenter(viewFxService.gui_Project);
    }
}
