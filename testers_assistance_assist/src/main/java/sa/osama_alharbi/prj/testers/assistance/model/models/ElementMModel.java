package sa.osama_alharbi.prj.testers.assistance.model.models;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sa.osama_alharbi.prj.testers.assistance.entity.Element;
import sa.osama_alharbi.prj.testers.assistance.model.ElementFxModel;
import sa.osama_alharbi.prj.testers.assistance.model.Model;

import java.util.List;

public class ElementMModel {
    protected Model model;
    public final Get get;
    public final Add add;
    public final Edit edit;
    public final Delete delete;
    public final ObservableList<ElementFxModel> obsList;
    public final ObjectProperty<ElementFxModel> selectedElementFxModel;

    public ElementMModel(Model model) {
        this.get = new Get();
        this.add = new Add();
        this.edit = new Edit();
        this.delete = new Delete();
        this.obsList = FXCollections.observableArrayList();
        this.selectedElementFxModel = new SimpleObjectProperty<>(null);
        this.model = model;
    }

    public class Get{
        public Get() {
        }

        public ElementFxModel getSelectedElement() {
            return selectedElementFxModel.get();
        }
    }

    public class Add{
        public Add() {
        }

        public void addAllWithClear(List<Element> allElements) {
            obsList.clear();
            obsList.addAll(allElements.stream().map(ElementFxModel::new).toList());
        }

        public void add(Element element) {
            obsList.add(new ElementFxModel(element));
        }

        public void addSelectedElement(ElementFxModel newValue) {
            selectedElementFxModel.set(newValue);
        }
    }

    public class Edit{
        public Edit() {
        }

        public void editSelectedElement(ElementFxModel elementFxModel) {
            selectedElementFxModel.set(elementFxModel);
        }

        public void edithPathId(Element newElement) {
            if(selectedElementFxModel.isNotNull().get()){
                if(newElement.getId().equals(selectedElementFxModel.get().getId())){
                    selectedElementFxModel.get().setPath_id(newElement.getPathId());
                }
            }
            for(ElementFxModel obsFxElement: obsList){
                if(newElement.getId().equals(obsFxElement.getId())){
                    obsFxElement.setPath_id(newElement.getPathId());
                    return;
                }
            }
        }

        public void edithName(Element newElement) {
            if(newElement.getId().equals(selectedElementFxModel.get().getId())){
                selectedElementFxModel.get().setName(newElement.getName());
            }
            for(ElementFxModel obsFxElement: obsList){
                if(newElement.getId().equals(obsFxElement.getId())){
                    obsFxElement.setName(newElement.getName());
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

        public void clearSelectedElement() {
            selectedElementFxModel.set(null);
        }
    }
}
