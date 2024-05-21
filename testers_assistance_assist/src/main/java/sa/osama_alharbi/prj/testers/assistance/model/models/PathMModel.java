package sa.osama_alharbi.prj.testers.assistance.model.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sa.osama_alharbi.prj.testers.assistance.entity.Path;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.PathFxModel;

import java.util.List;

public class PathMModel {
    protected Model model;
    public final Get get;
    public final Add add;
    public final Edit edit;
    public final Delete delete;
    public final ObservableList<PathFxModel> obsList;

    public PathMModel(Model model) {
        this.get = new Get();
        this.add = new Add();
        this.edit = new Edit();
        this.delete = new Delete();
        this.obsList = FXCollections.observableArrayList();
        this.model = model;
    }

    public class Get{
        public Get() {
        }
    }

    public class Add{
        public Add() {
        }

        public void addAll(List<Path> allFromDb) {
            obsList.clear();
            obsList.addAll(allFromDb.stream().map(PathFxModel::new).toList());
        }
    }

    public class Edit{
        public Edit() {
        }
    }

    public class Delete{
        public Delete() {
        }
    }
}
