package sa.osama_alharbi.prj.testers.assistance.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.*;
import sa.osama_alharbi.prj.testers.assistance.entity.Element;

public class ElementFxModel {
    private LongProperty id;
    private StringProperty fullXpath;
    private StringProperty name;
    private StringProperty tag;
    private StringProperty value;
    private StringProperty type;
    private StringProperty photo;
    private LongProperty path_id;
    private LongProperty page_id;
    @Getter
    private Element elementModel;

    public ElementFxModel(Element elementModel){
        this.id = new SimpleLongProperty(elementModel.getId());
        this.fullXpath = new SimpleStringProperty(elementModel.getFullXpath());
        this.name = new SimpleStringProperty(elementModel.getName());
        this.tag = new SimpleStringProperty(elementModel.getTag());
        this.value = new SimpleStringProperty(elementModel.getValue());
        this.type = new SimpleStringProperty(elementModel.getType());
        this.photo = new SimpleStringProperty(elementModel.getPhoto());
        this.path_id = new SimpleLongProperty(elementModel.getPathId());
        this.page_id = new SimpleLongProperty(elementModel.getPageId());
        this.elementModel = elementModel;
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

    public String getFullXpath() {
        return fullXpath.get();
    }

    public StringProperty fullXpathProperty() {
        return fullXpath;
    }

    public void setFullXpath(String fullXpath) {
        this.fullXpath.set(fullXpath);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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

    public String getPhoto() {
        return photo.get();
    }

    public StringProperty photoProperty() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo.set(photo);
    }

    public long getPage_id() {
        return page_id.get();
    }

    public LongProperty page_idProperty() {
        return page_id;
    }

    public void setPage_id(long page_id) {
        this.page_id.set(page_id);
    }

    public String getTag() {
        return tag.get();
    }

    public StringProperty tagProperty() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag.set(tag);
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

    public long getPath_id() {
        return path_id.get();
    }

    public LongProperty path_idProperty() {
        return path_id;
    }

    public void setPath_id(long path_id) {
        this.path_id.set(path_id);
    }
}
