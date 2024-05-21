package sa.osama_alharbi.prj.testers.assistance.service;

import jakarta.annotation.PostConstruct;
import javafx.application.Platform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.ProjectFxModel;
import sa.osama_alharbi.prj.testers.assistance.repo.ProjectRepo;
import sa.osama_alharbi.prj.testers.assistance.entity.Project;
import sa.osama_alharbi.prj.testers.assistance.service.on.OnProjectCRUD;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepo projectRepo;
    private final OnFxService on;
    private final Model model;

    @PostConstruct
    public void init(){
        on.onProjectCRUD = new OnProjectCRUD() {
            @Override
            public ProjectFxModel addNewProject(String title,String baseUrl) {
                Project project = addProject(Project.builder().title(title).baseUrl(baseUrl).build());
                ProjectFxModel projectFxModel = new ProjectFxModel(project);
                model.project.add.add(projectFxModel);
                return projectFxModel;
            }

            @Override
            public ProjectFxModel editProject() {
                return null;
            }

            @Override
            public boolean deleteProject() {
                return false;
            }
        };
    }

    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepo.findById(id).orElse(null);
    }

    public Project addProject(Project project) {
        return projectRepo.save(project);
    }
}
