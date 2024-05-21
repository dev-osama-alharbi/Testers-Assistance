package sa.osama_alharbi.prj.testers.assistance.model.models;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sa.osama_alharbi.prj.testers.assistance.entity.Page;
import sa.osama_alharbi.prj.testers.assistance.model.ElementFxModel;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.PageFxModel;
import sa.osama_alharbi.prj.testers.assistance.model.ProjectFxModel;

import java.util.List;

public class PageMModel {
    protected Model model;
    public final Get get;
    public final Add add;
    public final Edit edit;
    public final Delete delete;
    public final ObservableList<PageFxModel> obsList;
    public final ObjectProperty<PageFxModel> selectedPage;
    public final LongProperty projectIdProperty;

    public PageMModel(Model model) {
        this.get = new Get();
        this.add = new Add();
        this.edit = new Edit();
        this.delete = new Delete();
        this.obsList = FXCollections.observableArrayList();
        this.selectedPage = new SimpleObjectProperty<>(null);
        this.projectIdProperty = new SimpleLongProperty(0L);
        this.model = model;
    }

    public class Get{
        public Get() {
        }

        public Long getSelectedPage() {
            if(selectedPage.isNotNull().get()){
                return selectedPage.get().getId();
            }else{
                return 0L;
            }
        }
    }

    public class Add{
        public Add() {
        }

//        public void addAll(List<Page> allPages) {
//            obsList.addAll(allPages.stream().map(PageFxModel::new).toList());
//        }

        public void addAllAndClear(List<Page> pages,Long projectId) {
            obsList.clear();
            projectIdProperty.set(0L);
            obsList.addAll(pages.stream().map(PageFxModel::new).toList());
            projectIdProperty.set(projectId);
        }

        public boolean addIfSameProjectId(Page newPage) {
            if(projectIdProperty.getValue().equals(0))
                return false;

            if(projectIdProperty.getValue().equals(newPage.getProjectId())){
                List<PageFxModel> arr = obsList.stream().toList();
                for (PageFxModel pageFxModel:arr){
                    if(newPage.getId().equals(pageFxModel.getId())){
                        return false;
                    }
                }
                obsList.add(new PageFxModel(newPage));
                return true;
            }
            return false;
        }
    }

    public class Edit{
        public Edit() {
        }

        public void editSelectedPage(PageFxModel pageFxModel) {
            selectedPage.set(pageFxModel);
        }

        public void unSelectPage() {
            selectedPage.set(null);
        }

        public void edithTitle(Page newElement) {
            if(newElement.getId().equals(selectedPage.get().getId())){
                selectedPage.get().setTitle(newElement.getTitle());
            }
            for(PageFxModel obsFxElement: obsList){
                if(newElement.getId().equals(obsFxElement.getId())){
                    obsFxElement.setTitle(newElement.getTitle());
                    return;
                }
            }
        }
    }

    public class Delete{
        public Delete() {
        }

        public void clear(){
            obsList.clear();
        }
    }
}
