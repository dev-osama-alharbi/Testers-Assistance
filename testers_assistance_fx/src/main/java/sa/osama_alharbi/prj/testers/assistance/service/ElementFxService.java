package sa.osama_alharbi.prj.testers.assistance.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.dto.PathDTO;
import sa.osama_alharbi.prj.testers.assistance.entity.Element;
import sa.osama_alharbi.prj.testers.assistance.entity.Path;
import sa.osama_alharbi.prj.testers.assistance.enums.PathSlmType;
import sa.osama_alharbi.prj.testers.assistance.model.Model;
import sa.osama_alharbi.prj.testers.assistance.model.PathFxModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ElementFxService {

    private final Model model;
    private final OnFxService onFxService;

    public void updateName(Element element, String name){
        onFxService.onElementCRUD.updateName(element,name);
    }
}

