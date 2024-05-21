package sa.osama_alharbi.prj.testers.assistance.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.ProjectFxModel;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectFxService {
    private final ViewFxService viewFxService;
    private final ControllerFxService controllerFxService;
    private final Model model;
    private final OnFxService on;

    @PostConstruct
    protected void init(){
        
    }

    public void showProject(ProjectFxModel projectFxModel){
//        controllerFxService.pages.setProjectFxModel(projectFxModel);
        viewFxService.gui_Root.setCenter(viewFxService.gui_Pages);
        model.project.edit.editSelectedProject(projectFxModel);
        on.onPagesCRUD.showPagesModelByProjectModel(projectFxModel.getProjectModel().getId());
    }

    public void addNewProject(String title,String baseUrl){
        on.onProjectCRUD.addNewProject(title,baseUrl);
    }

}

