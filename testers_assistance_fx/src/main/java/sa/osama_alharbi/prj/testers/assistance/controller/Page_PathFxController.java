package sa.osama_alharbi.prj.testers.assistance.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sa.osama_alharbi.prj.testers.assistance.enums.PathSlmType;
import sa.osama_alharbi.prj.testers.assistance.model.ElementFxModel;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.PathFxModel;
import sa.osama_alharbi.prj.testers.assistance.service.OnFxService;
import sa.osama_alharbi.prj.testers.assistance.service.PathFxService;
import sa.osama_alharbi.prj.testers.assistance.utils.Fx;
import sa.osama_alharbi.prj.testers.assistance.view.ViewGUIIndex;

import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

@Component
@Slf4j
@RequiredArgsConstructor
public class Page_PathFxController implements Initializable {
    public static boolean HASE_BEAN = true;
//    public static URL FXML_PATH = ViewGUIIndex.class.getResource( Page_PathFxController.class.getSimpleName()+".fxml");
    public static URL FXML_PATH = Fx.getViewXmlFilePath(Page_PathFxController.class);
    private final Model model;
    private final PathFxService pathFxService;
    private final OnFxService onFxService;
    @FXML
    public TableView<PathFxModel> tbl;
//    public ObservableList<PathFxModel> obsPath;
    @FXML
    public TableColumn<PathFxModel, Boolean> checkCol;
    @FXML
    public TableColumn<PathFxModel, String> copyByCol,copyPathCol,pathCol,counterCol;
    @FXML
    public TableColumn<PathFxModel, Number> typeCol;

    @FXML
    public TextField fullXPathTxt;

    @FXML
    public AnchorPane root;

    @FXML
    public ComboBox<String> typeCombo;
    private Clipboard clipboard;
    private ToggleGroup toggleGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clipboard = Clipboard.getSystemClipboard();
        toggleGroup = new ToggleGroup();
//        this.obsPath = FXCollections.observableArrayList();
//        this.tbl.setItems(this.obsPath);
        this.tbl.setItems(this.model.path.obsList);
        this.tbl.setFocusTraversable(false);

        //TODO setCellFactory-> TableCell =>[checkCol,copyByCol,copyPathCol]
        checkCol.setCellValueFactory(cell -> cell.getValue().isSelectedProperty());
        copyByCol.setCellValueFactory(cell -> cell.getValue().pathProperty());
        copyPathCol.setCellValueFactory(cell -> cell.getValue().pathProperty());
        pathCol.setCellValueFactory(cell -> cell.getValue().pathProperty());
        typeCol.setCellValueFactory(cell -> cell.getValue().pathTypeIdProperty());
        counterCol.setCellValueFactory(cell -> cell.getValue().counterProperty().asString());
        counterCol.setCellFactory(param -> new TableCell<>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if(!empty && item != null){
                    TableRow<PathFxModel> tableRow = getTableRow();
                    PathFxModel pathFxModel = tableRow.getItem();
                    if(pathFxModel.getCounter() == 1){
                        if(PathSlmType.getTypeById(pathFxModel.getPathTypeId()) == PathSlmType.FULL){
                            tableRow.getStyleClass().add("table-row-path-full");
                        }else{
                            tableRow.getStyleClass().add("table-row-path-good");
                        }
                    }else{
                        tableRow.getStyleClass().add("table-row-path-not");
                    }
                    setText(item);
                }else{
                    setText(null);
                    setGraphic(null);
                    getTableRow().getStyleClass().clear();
                }
            }
        });
        checkCol.setCellFactory(param -> new TableCell<>(){
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if(!empty && item != null){
                    PathFxModel pathFxModel = getTableRow().getItem();

                    RadioButton radioButton = new RadioButton();
                    radioButton.setToggleGroup(toggleGroup);
                    radioButton.setSelected(item);
                    radioButton.setText("");
                    radioButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
                        if(newValue){
                            onFxService.onElementCRUD.updatePathId(pathFxModel.getPathModel().getElement(), pathFxModel.getId());
                        }
                    });
                    ElementFxModel elementFxModel = model.element.get.getSelectedElement();
                    if(elementFxModel != null){
                        if(elementFxModel.getPath_id() == pathFxModel.getId()){
                            radioButton.setSelected(true);
                        }else{
                            radioButton.setSelected(false);
                        }
                    }
                    setGraphic(radioButton);
                }else{
                    setText(null);
                    setGraphic(null);
                }
            }
        });
        typeCol.setCellFactory(param -> new TableCell<>(){
            @Override
            protected void updateItem(Number item, boolean empty) {
                super.updateItem(item, empty);
                if(!empty && item != null){
                    String type = Objects.requireNonNull(PathSlmType.getTypeById(item.intValue())).type;
                    setText(type);
//                    setText(getTableRow().getItem().getPathType().get().type);
                }else{
                    setText(null);
                    setGraphic(null);
                }
            }
        });
        copyByCol.setCellFactory(param -> new TableCell<>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if(!empty && item != null){
                    PathSlmType pathSlmType = PathSlmType.getTypeById(getTableRow().getItem().getPathTypeId());
                    Button btn = new Button("By");
                    btn.setOnAction(event -> {
                        ClipboardContent content = new ClipboardContent();
                        content.putString(pathFxService.getCodeBy(pathSlmType,item));
                        clipboard.setContent(content);
                    });
                    FontAwesomeIconView fontAwesomeIconView = new FontAwesomeIconView(FontAwesomeIcon.COPY);
                    fontAwesomeIconView.setGlyphSize(18);
                    if(PathSlmType.getTypeById(getTableRow().getItem().getPathTypeId()) == PathSlmType.FULL){
                        fontAwesomeIconView.setFill(Color.BLACK);
                    }else {
                        fontAwesomeIconView.setFill(Color.WHITE);
                    }
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
        copyPathCol.setCellFactory(param -> new TableCell<>(){
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if(!empty && item != null){
                    PathSlmType pathSlmType = PathSlmType.getTypeById(getTableRow().getItem().getPathTypeId());
                    Button btn = new Button("P");
                    btn.setOnAction(event -> {
                        ClipboardContent content = new ClipboardContent();
                        content.putString(pathFxService.getCodePath(pathSlmType,item));
                        clipboard.setContent(content);
                    });
                    FontAwesomeIconView fontAwesomeIconView = new FontAwesomeIconView(FontAwesomeIcon.COPY);
                    fontAwesomeIconView.setGlyphSize(18);
                    if(PathSlmType.getTypeById(getTableRow().getItem().getPathTypeId()) == PathSlmType.FULL){
                        fontAwesomeIconView.setFill(Color.BLACK);
                    }else {
                        fontAwesomeIconView.setFill(Color.WHITE);
                    }
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
//        counterCol.setCellFactory(param -> new TableCell<>(){
//            @Override
//            protected void updateItem(String item, boolean empty) {
//                super.updateItem(item, empty);
//                if(!empty && item != null){
//                    setText("");
////                    setText(findPathSlmService.countBy(getTableRow().getItem().getPathType().get(),getTableRow().getItem().getPath().get())+"");
//                }else{
//                    setText(null);
//                    setGraphic(null);
//                }
//            }
//        });

    }

    @FXML
    public void onClickCopyFullXPath() {

    }

    @FXML
    public void onClickRefresh() {
        pathFxService.updateElementPaths(this.fullXPathTxt.getText());
    }
    @FXML
    public void onClickShort() {

    }

//    public void setFullXPath(String fullXPath, WebDriver webDriver) {
    public void setFullXPath(String fullXPath) {
        if(fullXPath == null){
            this.fullXPathTxt.setText("");
            model.path.obsList.clear();
            return;
        }
        this.fullXPathTxt.setText(fullXPath);
        model.path.obsList.clear();
        pathFxService.showElementPaths();
//        this.obsPath.addAll(this.onFxService.onPathSlmCRUD.getAllRecommendedXPath(fullXPath).stream().map(pathDTO -> new PathFxModel()).toList());
//        this.obsPath.addAll(findPathSlmService.getRecommendedPath(fullXPath).stream().map(PathFxModel::new).toList());
    }

//    public void setFullXPath(ElementFxModel newValue, PageFxController pageFxController) {
//        this.fullXPathTxt.setText(newValue.getFullXpath());
//        this.obsPath.clear();
////        this.obsPath.addAll(findPathSlmService.getRecommendedPath(fullXPath).stream().map(PathFxModel::new).toList());
//    }
}
