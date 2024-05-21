package sa.osama_alharbi.prj.testers.assistance.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import sa.osama_alharbi.prj.testers.assistance.entity.Project;

public class ProjectFxModel {
    private LongProperty id;
    private StringProperty title;
    private StringProperty baseUrl;
    @Getter
    private Project projectModel;
    public ProjectFxModel(Project projectModel){
        this.id = new SimpleLongProperty(projectModel.getId());
        this.title = new SimpleStringProperty(projectModel.getTitle());
        this.baseUrl = new SimpleStringProperty(projectModel.getBaseUrl());
        this.projectModel = projectModel;
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

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getBaseUrl() {
        return baseUrl.get();
    }

    public StringProperty baseUrlProperty() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl.set(baseUrl);
    }
}
