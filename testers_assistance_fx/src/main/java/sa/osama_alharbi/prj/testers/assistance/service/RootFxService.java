package sa.osama_alharbi.prj.testers.assistance.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.model.Model;

@Service
@Slf4j
@RequiredArgsConstructor
public class RootFxService {
    private final Model model;
    private final ViewFxService viewFxService;

    @PostConstruct
    protected void init(){
        
    }

    public void showHome(){
        viewFxService.gui_Root.setCenter(viewFxService.gui_Project);
        model.project.edit.editSelectedProject(null);
        model.page.edit.editSelectedPage(null);
        model.element.edit.editSelectedElement(null);
    }

    public void showPages() {
        viewFxService.gui_Root.setCenter(viewFxService.gui_Pages);
        model.page.edit.editSelectedPage(null);
        model.element.edit.editSelectedElement(null);
    }
}

