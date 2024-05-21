package sa.osama_alharbi.prj.testers.assistance.model;

import javafx.beans.property.*;
import lombok.Getter;
import sa.osama_alharbi.prj.testers.assistance.entity.Path;
import sa.osama_alharbi.prj.testers.assistance.entity.PathType;

public class PathTypeFxModel {
    private IntegerProperty id;
    private StringProperty type;
    @Getter
    private PathType pathTypeModel;

    public PathTypeFxModel(PathType pathTypeModel) {
        this.id = new SimpleIntegerProperty(pathTypeModel.getId());
        this.type = new SimpleStringProperty(pathTypeModel.getType());
        this.pathTypeModel = pathTypeModel;
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }
}
