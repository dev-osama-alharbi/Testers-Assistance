package sa.osama_alharbi.prj.testers.assistance.init;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.ProjectFxModel;
import sa.osama_alharbi.prj.testers.assistance.service.ElementService;
import sa.osama_alharbi.prj.testers.assistance.service.PageService;
import sa.osama_alharbi.prj.testers.assistance.service.ProjectService;

@Component
@Slf4j
@RequiredArgsConstructor
public class OnStart {
    private final Model model;
    private final ProjectService projectService;
    private final PageService pageService;
    private final ElementService elementService;
    @PostConstruct
    public void initialize(){
        model.project.add.addAll(projectService.getAllProjects().stream().map(ProjectFxModel::new).toList());
//        model.page.add.addAll(pageService.getAllPages());
        model.element.add.addAllWithClear(elementService.getAllElements());
    }
}
