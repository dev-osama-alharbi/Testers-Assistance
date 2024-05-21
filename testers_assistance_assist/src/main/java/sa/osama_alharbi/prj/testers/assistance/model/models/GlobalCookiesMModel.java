package sa.osama_alharbi.prj.testers.assistance.model.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sa.osama_alharbi.prj.testers.assistance.entity.GlobalCookies;
import sa.osama_alharbi.prj.testers.assistance.model.GlobalCookiesFxModel;
import sa.osama_alharbi.prj.testers.assistance.model.Model;

import java.util.List;

public class GlobalCookiesMModel {
    protected Model model;
    public final Get get;
    public final Add add;
    public final Edit edit;
    public final Delete delete;
    public final ObservableList<GlobalCookiesFxModel> obsList;

    public GlobalCookiesMModel(Model model) {
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

        public void addAll(List<GlobalCookies> allGlobalCookies) {
            obsList.addAll(allGlobalCookies.stream().map(GlobalCookiesFxModel::new).toList());
        }

        public void add(GlobalCookies globalCookies) {
            obsList.add(new GlobalCookiesFxModel(globalCookies));
        }
    }

    public class Edit{
        public Edit() {
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
