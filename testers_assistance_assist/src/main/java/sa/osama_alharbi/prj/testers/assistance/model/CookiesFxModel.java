package sa.osama_alharbi.prj.testers.assistance.model;

import javafx.beans.property.*;
import lombok.Getter;
import sa.osama_alharbi.prj.testers.assistance.entity.Cookies;

public class CookiesFxModel {
    private LongProperty id;
    private StringProperty key;
    private StringProperty value;
    private LongProperty pageId;
    @Getter
    private Cookies cookiesModel;

    public CookiesFxModel(Cookies cookiesModel){
        this.id = new SimpleLongProperty(cookiesModel.getId());
        this.key = new SimpleStringProperty(cookiesModel.getKey());
        this.value = new SimpleStringProperty(cookiesModel.getValue());
        this.pageId = new SimpleLongProperty(cookiesModel.getPageId());
        this.cookiesModel = cookiesModel;
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

    public long getPageId() {
        return pageId.get();
    }

    public LongProperty pageIdProperty() {
        return pageId;
    }

    public void setPageId(long pageId) {
        this.pageId.set(pageId);
    }
}
