package sa.osama_alharbi.prj.testers.assistance.model.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.PathTypeFxModel;
import sa.osama_alharbi.prj.testers.assistance.model.TestTypeFxModel;

import java.util.HashMap;
import java.util.List;

public class PathTypeMModel {
    protected Model model;
    public final Get get;
    public final Add add;
    public final ObservableList<PathTypeFxModel> obsList;
    private final HashMap<Integer, PathTypeFxModel> obsMap;

    public PathTypeMModel(Model model) {
        this.get = new Get();
        this.add = new Add();
        this.obsList = FXCollections.observableArrayList();
        this.obsMap = new HashMap<>();
        this.model = model;
    }

    public class Get{
        public Get() {
        }
    }

    public class Add{
        public Add() {
        }

        public void addAll(List<PathTypeFxModel> pathTypeFxModels) {
            obsList.clear();
            obsMap.clear();
            pathTypeFxModels.forEach(pathTypeFxModel -> obsMap.put(pathTypeFxModel.getId(),pathTypeFxModel));
            obsList.addAll(obsMap.values());
        }
    }
}
