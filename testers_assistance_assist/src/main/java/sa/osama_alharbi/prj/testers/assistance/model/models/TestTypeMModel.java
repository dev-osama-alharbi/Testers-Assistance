package sa.osama_alharbi.prj.testers.assistance.model.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.TestTypeFxModel;

import java.util.HashMap;
import java.util.List;

public class TestTypeMModel {
    protected Model model;
    public final Get get;
    public final Add add;
    public final ObservableList<TestTypeFxModel> obsList;
    private final HashMap<Integer,TestTypeFxModel> obsMap;

    public TestTypeMModel(Model model) {
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

        public void addAll(List<TestTypeFxModel> testTypeFxModels) {
            obsList.clear();
            obsMap.clear();
            testTypeFxModels.forEach(testType -> obsMap.put(testType.getId(),testType));
            obsList.addAll(obsMap.values());
        }
    }
}
