package sa.osama_alharbi.prj.testers.assistance.service.on;

import sa.osama_alharbi.prj.testers.assistance.entity.Path;
import sa.osama_alharbi.prj.testers.assistance.model.PathFxModel;

import java.util.List;

public interface OnPathCRUD {
    List<Path> getAllFromDb(long elementId);

    void delete(List<Path> deletedPaths);

    void add(List<Path> newPath);

    Path getPathById(long id);

    List<Path> getPathByIds(List<Long> ids);
}
