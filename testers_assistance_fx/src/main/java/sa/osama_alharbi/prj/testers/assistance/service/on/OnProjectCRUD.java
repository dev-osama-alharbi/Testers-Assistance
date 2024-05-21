package sa.osama_alharbi.prj.testers.assistance.service.on;

import sa.osama_alharbi.prj.testers.assistance.model.ProjectFxModel;

public interface OnProjectCRUD {
    ProjectFxModel addNewProject(String title,String baseUrl);
    ProjectFxModel editProject();
    boolean deleteProject();
}
