package sa.osama_alharbi.prj.testers.assistance.service.on;

import sa.osama_alharbi.prj.testers.assistance.entity.Cookies;

import java.util.List;

public interface OnCookiesSlmCRUD {
    List<Cookies> getAllByPageId(Long pageId);
}
