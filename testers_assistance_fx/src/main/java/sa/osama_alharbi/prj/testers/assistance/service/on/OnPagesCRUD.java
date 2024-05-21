package sa.osama_alharbi.prj.testers.assistance.service.on;

import sa.osama_alharbi.prj.testers.assistance.entity.Page;
import sa.osama_alharbi.prj.testers.assistance.entity.Project;
import sa.osama_alharbi.prj.testers.assistance.model.PageFxModel;
import sa.osama_alharbi.prj.testers.assistance.model.ProjectFxModel;

public interface OnPagesCRUD {
    PageFxModel addNewPage(Page page);
    PageFxModel editPage(Page page);
    boolean deletePage(Long id);

    boolean showPagesModelByProjectModel(Long projectId);

    void updateTitle(String title, PageFxModel pageFxModel);

    Page getPageById(Long pageId);
}
