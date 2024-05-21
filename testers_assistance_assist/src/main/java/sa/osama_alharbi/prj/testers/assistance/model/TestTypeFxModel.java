package sa.osama_alharbi.prj.testers.assistance.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import sa.osama_alharbi.prj.testers.assistance.entity.PathType;
import sa.osama_alharbi.prj.testers.assistance.entity.TestType;

public class TestTypeFxModel {
    private IntegerProperty id;
    private StringProperty type;
    @Getter
    private TestType testTypeModel;

    public TestTypeFxModel(TestType testTypeModel) {
        this.id = new SimpleIntegerProperty(testTypeModel.getId());
        this.type = new SimpleStringProperty(testTypeModel.getType());
        this.testTypeModel = testTypeModel;
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
