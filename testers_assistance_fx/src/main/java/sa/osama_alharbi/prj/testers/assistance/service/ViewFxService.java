package sa.osama_alharbi.prj.testers.assistance.service;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ViewFxService {
    public BorderPane gui_Root;
    public VBox gui_Page;
    public AnchorPane gui_PageInfo;
    public AnchorPane gui_PagePath;
    public VBox gui_Pages;
    public VBox gui_Project;
    public VBox gui_Root_Header;
    public AnchorPane gui_Pages_info;
    public AnchorPane gui_Pages_cookies;
    public AnchorPane gui_Pages_code;
}
