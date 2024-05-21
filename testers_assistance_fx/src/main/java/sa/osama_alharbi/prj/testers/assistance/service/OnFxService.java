package sa.osama_alharbi.prj.testers.assistance.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.entity.Element;
import sa.osama_alharbi.prj.testers.assistance.service.on.*;

@Slf4j
@Service
public class OnFxService {
    public OnProjectCRUD onProjectCRUD;
    public OnPagesCRUD onPagesCRUD;
    public OnManageSlmCRUD onManageSlmCRUD;
    public OnPathSlmCRUD onPathSlmCRUD;
    public OnPathCRUD onPathCRUD;
    public OnElementCRUD onElementCRUD;
    public OnSlmCRUD onSlmCRUD;
    public OnCookiesSlmCRUD onCookiesSlmCRUD;
}
