package sa.osama_alharbi.prj.testers.assistance.model.models;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sa.osama_alharbi.prj.testers.assistance.entity.Project;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.ProjectFxModel;

import java.util.HashMap;
import java.util.List;

public class ProjectMModel {
    protected Model model;
    public final Get get;
    public final Add add;
    public final Edit edit;
    public final Delete delete;
    public final ObservableList<ProjectFxModel> obsList;
    private final HashMap<Long,ProjectFxModel> obsMap;
    public final ObjectProperty<ProjectFxModel> selectedProject;

    public ProjectMModel(Model model) {
        this.get = new Get();
        this.add = new Add();
        this.edit = new Edit();
        this.delete = new Delete();
        this.obsList = FXCollections.observableArrayList();
        this.selectedProject = new SimpleObjectProperty<>(null);
        this.obsMap = new HashMap<>();
        this.model = model;
    }

    public class Get{
        public Get() {
        }

        public ProjectFxModel getSelectedProject() {
            return selectedProject.get();
        }
    }

    public class Add{
        public Add() {
        }

        public void addAll(List<ProjectFxModel> allProjects) {
            obsList.clear();
            obsMap.clear();
            allProjects.forEach(project -> obsMap.put(project.getId(),project));
            obsList.addAll(obsMap.values());
        }

        public void add(ProjectFxModel projectFxModel) {
            if(!obsMap.containsKey(projectFxModel.getId())){
                obsMap.put(projectFxModel.getId(),projectFxModel);
                obsList.add(projectFxModel);
            }else{
                //TODO edit
                edit.edit(projectFxModel);
            }
        }
    }

    public class Edit{
        public Edit() {
        }

        public void editSelectedProject(ProjectFxModel projectFxModel) {
            selectedProject.set(projectFxModel);
        }

        public void edit(ProjectFxModel projectFxModel) {
            if(obsMap.containsKey(projectFxModel.getId())){
                ProjectFxModel oldProjectFxModel = obsMap.get(projectFxModel.getId());
                //TODO edit proprieties
                oldProjectFxModel.setTitle(projectFxModel.getTitle());
            }else{
                add.add(projectFxModel);
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
