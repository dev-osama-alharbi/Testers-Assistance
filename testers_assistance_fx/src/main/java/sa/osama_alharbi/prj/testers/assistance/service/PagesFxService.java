package sa.osama_alharbi.prj.testers.assistance.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.PageFxModel;

@Service
@Slf4j
@RequiredArgsConstructor
public class PagesFxService {
    private final ViewFxService viewFxService;
    private final OnFxService onFxService;
    private final ControllerFxService controllerFxService;
    private final Model model;

    @PostConstruct
    protected void init(){

    }

    public void showPage(PageFxModel pageFxModel) {
//        controllerFxService.pages.setProjectFxModel(projectFxModel);
        viewFxService.gui_Root.setCenter(viewFxService.gui_Page);
        model.page.edit.editSelectedPage(pageFxModel);
        model.element.add.addAllWithClear(onFxService.onElementCRUD.getAllByPageId(pageFxModel.getId()));
    }

    public long countNumberOfElements(PageFxModel pageFxModel) {
        return onFxService.onElementCRUD.countNumberOfElementsByPageId(pageFxModel.getId());
    }

    public void updateTitle(String title, PageFxModel pageFxModel) {
        onFxService.onPagesCRUD.updateTitle(title,pageFxModel);
    }

//    public void showProject(ProjectFxModel projectFxModel){
////        controllerFxService.pages.setProjectFxModel(projectFxModel);
//        viewFxService.gui_Root.setCenter(viewFxService.gui_Pages);
//        model.project.edit.editSelectedProject(projectFxModel);
//        on.onPagesCRUD.showPagesModelByProjectModel(projectFxModel.getProjectModel());
//    }
}

