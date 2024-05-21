package sa.osama_alharbi.prj.testers.assistance.model.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sa.osama_alharbi.prj.testers.assistance.entity.Cookies;
import sa.osama_alharbi.prj.testers.assistance.entity.Element;
import sa.osama_alharbi.prj.testers.assistance.model.CookiesFxModel;
import sa.osama_alharbi.prj.testers.assistance.model.ElementFxModel;
import sa.osama_alharbi.prj.testers.assistance.model.Model;

import java.util.List;

public class CookiesMModel {
    protected Model model;
    public final Get get;
    public final Add add;
    public final Edit edit;
    public final Delete delete;
    public final ObservableList<CookiesFxModel> obsList;

    public CookiesMModel(Model model) {
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

//        public void addAll(List<Cookies> allCookies) {
//            obsList.addAll(allCookies.stream().map(CookiesFxModel::new).toList());
//        }

        public void addAllWithClear(List<Cookies> allCookies) {
            obsList.clear();
            obsList.addAll(allCookies.stream().map(CookiesFxModel::new).toList());
        }

        public void add(Cookies cookies) {
            if(model.page.selectedPage.isNotNull().get()){
                if(model.page.selectedPage.get().idProperty().getValue().equals(cookies.getPageId())){
                    obsList.add(new CookiesFxModel(cookies));
                }
            }
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
