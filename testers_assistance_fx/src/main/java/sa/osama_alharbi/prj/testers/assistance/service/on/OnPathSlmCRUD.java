package sa.osama_alharbi.prj.testers.assistance.service.on;

import sa.osama_alharbi.prj.testers.assistance.dto.PathDTO;
import sa.osama_alharbi.prj.testers.assistance.entity.Element;
import sa.osama_alharbi.prj.testers.assistance.enums.PathSlmType;

import java.util.List;

public interface OnPathSlmCRUD {
    List<PathDTO> getAllRecommendedXPath(String fullXPath);
    int countPaths(PathSlmType pathSlmType, String path);
}
