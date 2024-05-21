package sa.osama_alharbi.prj.testers.assistance.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sa.osama_alharbi.prj.testers.assistance.entity.Cookies;
import sa.osama_alharbi.prj.testers.assistance.enums.PathSlmType;
import sa.osama_alharbi.prj.testers.assistance.model.CookiesFxModel;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.utils.Fx;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@RequiredArgsConstructor
public class Pages_CookiesFxController implements Initializable {
    public static boolean HASE_BEAN = true;
    public static URL FXML_PATH = Fx.getViewXmlFilePath(Pages_CookiesFxController.class);
    private final Model model;
    @FXML
    public AnchorPane root;

    @FXML
    public TableView<CookiesFxModel> tbl;
    @FXML
    public TableColumn<CookiesFxModel,String> keyTbc,valueTbc;
    @FXML
    public TableColumn<CookiesFxModel,Number> favoriteTbc,deleteTbc;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tbl.setItems(model.cookies.obsList);
        keyTbc.setCellValueFactory(cell -> cell.getValue().keyProperty());
        valueTbc.setCellValueFactory(cell -> cell.getValue().valueProperty());
        favoriteTbc.setCellValueFactory(cell -> cell.getValue().idProperty());
        deleteTbc.setCellValueFactory(cell -> cell.getValue().idProperty());
        favoriteTbc.setCellFactory(param -> new TableCell<>(){
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if(!empty && item != null){
                    Button btn = new Button("Favorite");
                    btn.setOnAction(event -> {
                    });
                    FontAwesomeIconView fontAwesomeIconView = new FontAwesomeIconView(FontAwesomeIcon.STAR);
                    fontAwesomeIconView.setGlyphSize(18);
                    fontAwesomeIconView.setFill(Color.ORANGE);

                    btn.setGraphic(fontAwesomeIconView);
                    btn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    btn.setStyle("-fx-background-color: transport;");
                    setGraphic(btn);
                }else{
                    setText(null);
                    setGraphic(null);
                }
            }
        });
        deleteTbc.setCellFactory(param -> new TableCell<>(){
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if(!empty && item != null){
                    Button btn = new Button("Favorite");
                    btn.setOnAction(event -> {

                    });
                    FontAwesomeIconView fontAwesomeIconView = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                    fontAwesomeIconView.setGlyphSize(18);
                    fontAwesomeIconView.setFill(Color.BLACK);

                    btn.setGraphic(fontAwesomeIconView);
                    btn.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                    btn.setStyle("-fx-background-color: transport;");
                    setGraphic(btn);
                }else{
                    setText(null);
                    setGraphic(null);
                }
            }
        });
    }

    @FXML
    public void onClickAdd(){

    }
}
