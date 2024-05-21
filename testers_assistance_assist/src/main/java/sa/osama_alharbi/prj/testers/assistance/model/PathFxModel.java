package sa.osama_alharbi.prj.testers.assistance.model;

import javafx.beans.property.*;
import lombok.Data;
import lombok.Getter;
import sa.osama_alharbi.prj.testers.assistance.dto.PathDTO;
import sa.osama_alharbi.prj.testers.assistance.entity.Path;
import sa.osama_alharbi.prj.testers.assistance.enums.PathSlmType;

public class PathFxModel {
    private LongProperty id;
    private StringProperty path;
    private BooleanProperty isSelected;
    private IntegerProperty counter;
    private LongProperty elementId;
    private IntegerProperty pathTypeId;
    private ObjectProperty<PathSlmType> pathSlmType;
    @Getter
    private Path pathModel;

    public PathFxModel(Path pathModel) {
        this.id = new SimpleLongProperty(pathModel.getId());
        this.path = new SimpleStringProperty(pathModel.getPath());
        this.isSelected = new SimpleBooleanProperty(pathModel.isSelected());
        this.counter = new SimpleIntegerProperty(pathModel.getCounter());
        this.elementId = new SimpleLongProperty(pathModel.getElementId());
        this.pathTypeId = new SimpleIntegerProperty(pathModel.getPathTypeId());
        this.pathSlmType = new SimpleObjectProperty<>();
        this.pathTypeId.addListener((observable, oldValue, newValue) -> {
            if(newValue == null){
                pathSlmType.set(null);
            }else{
                pathSlmType.set(PathSlmType.getTypeById(newValue.intValue()));
            }
        });
        pathSlmType.set(PathSlmType.getTypeById(pathModel.getPathTypeId()));
        this.pathModel = pathModel;
    }

    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getPath() {
        return path.get();
    }

    public StringProperty pathProperty() {
        return path;
    }

    public void setPath(String path) {
        this.path.set(path);
    }

    public boolean isIsSelected() {
        return isSelected.get();
    }

    public BooleanProperty isSelectedProperty() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected.set(isSelected);
    }

    public int getCounter() {
        return counter.get();
    }

    public IntegerProperty counterProperty() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter.set(counter);
    }

    public long getElementId() {
        return elementId.get();
    }

    public LongProperty elementIdProperty() {
        return elementId;
    }

    public void setElementId(long elementId) {
        this.elementId.set(elementId);
    }

    public int getPathTypeId() {
        return pathTypeId.get();
    }

    public IntegerProperty pathTypeIdProperty() {
        return pathTypeId;
    }

    public void setPathTypeId(int pathTypeId) {
        this.pathTypeId.set(pathTypeId);
    }

    public PathSlmType getPathSlmType() {
        return pathSlmType.get();
    }

    public ObjectProperty<PathSlmType> pathSlmTypeProperty() {
        return pathSlmType;
    }
}
