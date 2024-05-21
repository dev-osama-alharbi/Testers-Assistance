package sa.osama_alharbi.prj.testers.assistance.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.entity.Element;
import sa.osama_alharbi.prj.testers.assistance.entity.Page;
import sa.osama_alharbi.prj.testers.assistance.entity.Project;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.PageFxModel;
import sa.osama_alharbi.prj.testers.assistance.model.ProjectFxModel;
import sa.osama_alharbi.prj.testers.assistance.repo.PageRepo;
import sa.osama_alharbi.prj.testers.assistance.service.on.OnPagesCRUD;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PageService {
    private final PageRepo pageRepo;
    private final Model model;
    private final OnFxService onFxService;

    @PostConstruct
    public void init(){
        onFxService.onPagesCRUD = new OnPagesCRUD() {
            @Override
            public PageFxModel addNewPage(Page page) {
                Page newPage = addPage(page);
                model.page.add.addIfSameProjectId(newPage);
                return new PageFxModel(newPage);
            }

            @Override
            public PageFxModel editPage(Page page) {
                return null;
            }

            @Override
            public boolean deletePage(Long id) {
                return false;
            }

            @Override
            public boolean showPagesModelByProjectModel(Long projectId) {
                List<Page> pages = getAllByProjectId(projectId);
                model.page.add.addAllAndClear(pages,projectId);
                return false;
            }

            @Override
            public void updateTitle(String title, PageFxModel pageFxModel) {
                PageService.this.updateTitle(title,pageFxModel);
            }

            @Override
            public Page getPageById(Long pageId) {
                return PageService.this.getPageById(pageId);
            }
        };
    }

    private void updateTitle(String title, PageFxModel pageFxModel) {
        Page oldPage = pageRepo.findById(pageFxModel.getId()).orElse(null);
        if(oldPage == null){
            return;
        }else{
            oldPage.setTitle(title);
            oldPage = pageRepo.save(oldPage);
            model.page.edit.edithTitle(oldPage);
        }

    }

    public List<Page> getAllPages() {
        return pageRepo.findAll();
    }

    public Page getPageById(Long id) {
        return pageRepo.findById(id).orElse(null);
    }

    public Page addPage(Page page) {
        return pageRepo.save(page);
    }

    public List<Page> getAllByProjectId(long projectId) {
        return pageRepo.findByProject_Id(projectId);
    }
}

