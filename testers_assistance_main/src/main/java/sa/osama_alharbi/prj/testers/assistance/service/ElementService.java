package sa.osama_alharbi.prj.testers.assistance.service;

import jakarta.annotation.PostConstruct;
import javafx.application.Platform;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.repo.ElementRepo;
import sa.osama_alharbi.prj.testers.assistance.entity.Element;
import sa.osama_alharbi.prj.testers.assistance.service.on.OnElementCRUD;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ElementService {
    private final ElementRepo elementRepo;
    private final Model model;
    private final OnFxService onFxService;

    @PostConstruct
    public void init(){
        onFxService.onElementCRUD = new OnElementCRUD() {
            @Override
            public List<Element> getAllByPageId(Long pageId) {
                return ElementService.this.getAllByPageId(pageId);
            }

            @Override
            public void updatePathId(Element element, Long pathId) {
                ElementService.this.updatePathId(element,pathId);
            }

            @Override
            public void updateName(Element element, String name) {
                ElementService.this.updateName(element,name);
            }

            @Override
            public long countNumberOfElementsByPageId(long pageId) {
                return ElementService.this.countNumberOfElementsByPageId(pageId);
            }
        };
    }

    private List<Element> getAllByPageId(Long pageId) {
        return elementRepo.findByPageId(pageId);
    }

    public List<Element> getAllElements() {
        return elementRepo.findAll();
    }

    public Element getElementById(Long id) {
        return elementRepo.findById(id).orElse(null);
    }

    public Element add(Element element) {
        Element result = elementRepo.save(element);
        Platform.runLater(() -> model.element.add.add(result));
//        model.element.add.add(result);

        return result;
    }

    public void updatePathId(Element element, Long pathId) {
        Element oldElement = elementRepo.findById(element.getId()).orElse(null);
        if(oldElement == null){
            return;
        }else{
            oldElement.setPathId(pathId);
            oldElement = elementRepo.save(oldElement);
            Element finalOldElement = oldElement;
            Platform.runLater(() -> {
                model.element.edit.edithPathId(finalOldElement);
            });
        }
    }

    private void updateName(Element element, String name) {
        Element oldElement = elementRepo.findById(element.getId()).orElse(null);
        if(oldElement == null){
            return;
        }else{
            oldElement.setName(name);
            oldElement = elementRepo.save(oldElement);
            model.element.edit.edithName(oldElement);
        }
    }
    private long countNumberOfElementsByPageId(long pageId){
        return elementRepo.countByPageId(pageId);
    }
}

