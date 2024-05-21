package sa.osama_alharbi.prj.testers.assistance.model;

import javafx.beans.property.*;
import lombok.*;
import sa.osama_alharbi.prj.testers.assistance.entity.Page;

public class PageFxModel {
    private LongProperty id;
    private StringProperty title;
    private StringProperty url;
    private BooleanProperty isCompleted;
    private LongProperty projectId;
    private IntegerProperty testTypeId;
    @Getter
    private Page pageModel;

    public PageFxModel(Page pageModel){
        this.id = new SimpleLongProperty(pageModel.getId());
        this.title = new SimpleStringProperty(pageModel.getTitle());
        this.url = new SimpleStringProperty(pageModel.getUrl());
        this.isCompleted = new SimpleBooleanProperty(pageModel.isCompleted());
        this.projectId = new SimpleLongProperty(pageModel.getProjectId());
        this.testTypeId = new SimpleIntegerProperty(pageModel.getTestTypeId());
        this.pageModel = pageModel;
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

    public String getUrl() {
        return url.get();
    }

    public StringProperty urlProperty() {
        return url;
    }

    public void setUrl(String url) {
        this.url.set(url);
    }

    public boolean isIsCompleted() {
        return isCompleted.get();
    }

    public BooleanProperty isCompletedProperty() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted.set(isCompleted);
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

    public int getTestTypeId() {
        return testTypeId.get();
    }

    public IntegerProperty testTypeIdProperty() {
        return testTypeId;
    }

    public void setTestTypeId(int testTypeId) {
        this.testTypeId.set(testTypeId);
    }
}
