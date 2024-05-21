package sa.osama_alharbi.prj.testers.assistance.service.on;

import sa.osama_alharbi.prj.testers.assistance.entity.Element;
import sa.osama_alharbi.prj.testers.assistance.entity.Path;

import java.util.List;

public interface OnElementCRUD {
    List<Element> getAllByPageId(Long pageId);
    void updatePathId(Element element, Long pathId);
    void updateName(Element element, String name);

    long countNumberOfElementsByPageId(long id);
}
