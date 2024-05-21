package sa.osama_alharbi.prj.testers.assistance.model;

import javafx.beans.property.*;
import lombok.Getter;
import sa.osama_alharbi.prj.testers.assistance.entity.GlobalCookies;

public class GlobalCookiesFxModel {
    private LongProperty id;
    private StringProperty key;
    private StringProperty value;
    private LongProperty projectId;
    @Getter
    private GlobalCookies globalCookiesModel;

    public GlobalCookiesFxModel(GlobalCookies globalCookiesModel){
        this.id = new SimpleLongProperty(globalCookiesModel.getId());
        this.key = new SimpleStringProperty(globalCookiesModel.getKey());
        this.value = new SimpleStringProperty(globalCookiesModel.getValue());
        this.projectId = new SimpleLongProperty(globalCookiesModel.getProjectId());
        this.globalCookiesModel = globalCookiesModel;
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

    public String getKey() {
        return key.get();
    }

    public StringProperty keyProperty() {
        return key;
    }

    public void setKey(String key) {
        this.key.set(key);
    }

    public String getValue() {
        return value.get();
    }

    public StringProperty valueProperty() {
        return value;
    }

    public void setValue(String value) {
        this.value.set(value);
    }

    public long getProjectId() {
        return projectId.get();
    }

    public LongProperty projectIdProperty() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId.set(projectId);
    }
}
