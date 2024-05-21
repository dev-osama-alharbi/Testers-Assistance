package sa.osama_alharbi.prj.testers.assistance.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.dto.PathDTO;
import sa.osama_alharbi.prj.testers.assistance.entity.Path;
import sa.osama_alharbi.prj.testers.assistance.enums.PathSlmType;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.PathFxModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PathFxService {

    private final Model model;
    private final OnFxService onFxService;

    public void updateElementPaths(String fullXPath){
        //TODO this method need more code to be fast to execute like two for
        if(model.element.get.getSelectedElement() == null){
            return;
        }
        long elementId = model.element.get.getSelectedElement().getId();
        //get all current paths
        List<Path> dbPathList = onFxService.onPathCRUD.getAllFromDb(elementId);
        HashMap<String, Path> dbPathMap = new HashMap<>();
        for(Path dbPath :dbPathList)
            dbPathMap.put(dbPath.getPath(),dbPath);

        //get recommended paths
        List<PathDTO> selenumPathList = onFxService.onPathSlmCRUD.getAllRecommendedXPath(fullXPath);
        HashMap<String, PathDTO> selenumPathMap = new HashMap<>();
        for(PathDTO slmPath :selenumPathList)
            selenumPathMap.put(slmPath.getPath(),slmPath);

        //compare two list and create some list [new,deleted,edit_counter]
        List<PathDTO> newPath = new ArrayList<>();
        List<Path> deletedPaths = new ArrayList<>();
        List<PathDTO> editPathDtos = new ArrayList<>();


        for(PathDTO slmPath :selenumPathList){
            if(dbPathMap.containsKey(slmPath.getPath())){
                //check if need to edit
                Path dbPath = dbPathMap.get(slmPath.getPath());
                PathSlmType dbPathSlmType = PathSlmType.getTypeById(dbPath.getPathTypeId());
                if(slmPath.getPathType().getPathType().type.equals(dbPathSlmType.type)){
                    if(onFxService.onPathSlmCRUD.countPaths(dbPathSlmType,slmPath.getPath()) == dbPath.getCounter()){
                        //Nothing to do
                    }else{
                        //Edit
                        editPathDtos.add(slmPath);
                        editPathDtos.add(slmPath);
                    }
                }
            }else{
                //add new
                newPath.add(slmPath);
            }
        }

        for(Path dbPath :dbPathList){
            if(selenumPathMap.containsKey(dbPath.getPath())){
                //check if need to edit
                PathSlmType dbPathSlmType = PathSlmType.getTypeById(dbPath.getPathTypeId());
                PathDTO slmPath = selenumPathMap.get(dbPath.getPath());
                if(slmPath.getPathType().getPathType().type.equals(dbPathSlmType.type)){
                    if(onFxService.onPathSlmCRUD.countPaths(dbPathSlmType,slmPath.getPath()) == dbPath.getCounter()){
                        //Nothing to do
                    }else{
                        //Edit
                        editPathDtos.add(slmPath);
                    }
                }
            }else{
                //delete old
                deletedPaths.add(dbPath);
            }
        }

        //delete old and edit what need to edit and create new
        //delete
        onFxService.onPathCRUD.delete(deletedPaths);
        //add
        List<Path> addList = new ArrayList<>();
        for (PathDTO pathDTO : newPath){
            addList.add(Path
                    .builder()
                            .path(pathDTO.getPath())
                            .isSelected(false)
                            .counter(onFxService.onPathSlmCRUD.countPaths(PathSlmType.getTypeById(pathDTO.getPathType().getPathType().id),pathDTO.getPath()))
                            .elementId(elementId)
                            .pathTypeId(pathDTO.getPathType().getPathType().id)
                    .build());
        }
        onFxService.onPathCRUD.add(addList);
        //edit
        //TODO edit
        //TODO sort recommended
        model.path.add.addAll(onFxService.onPathCRUD.getAllFromDb(elementId));
    }

    public void showElementPaths(){
        //TODO this method need more code to be fast to execute like two for
        if(model.element.get.getSelectedElement() == null){
            return;
        }
        long elementId = model.element.get.getSelectedElement().getId();
        model.path.add.addAll(onFxService.onPathCRUD.getAllFromDb(elementId));
    }

    public String getCodePath(PathSlmType pathSlmType,String path){
        return switch (pathSlmType) {
            case FULL,XPATH -> path;
            case ID -> "#"+path;
            case NAME, CSS_SELECTOR, LINK_TEXT, PARTIAL_LINK_TEXT -> "XXX."+path;
            case TAG_NAME -> "//"+path;
            case CLASS_NAME -> "."+path;
        };
    }

    public String getCodeBy(PathSlmType pathSlmType,String path){
        return switch (pathSlmType) {
            case FULL,XPATH -> "By by = By.xpath(\""+path+"\");";
            case ID -> "By by = By.id(\""+path+"\");";
            case NAME -> "By by = By.name(\""+path+"\");";
            case TAG_NAME -> "By by = By.tagName(\""+path+"\");";
            case CLASS_NAME -> "By by = By.className(\""+path+"\");";
            case CSS_SELECTOR -> "By by = By.cssSelector(\""+path+"\");";
            case LINK_TEXT -> "By by = By.linkText(\""+path+"\");";
            case PARTIAL_LINK_TEXT -> "By by = By.partialLinkText(\""+path+"\");";
        };
    }

    public PathFxModel getPathById(long id) {
        return new PathFxModel(onFxService.onPathCRUD.getPathById(id));
    }
}

