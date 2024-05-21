package sa.osama_alharbi.prj.testers.assistance.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.dto.PathDTO;
import sa.osama_alharbi.prj.testers.assistance.entity.Path;
import sa.osama_alharbi.prj.testers.assistance.enums.PathSlmType;
import sa.osama_alharbi.prj.testers.assistance.repo.PathRepo;
import sa.osama_alharbi.prj.testers.assistance.service.on.OnPathCRUD;
import sa.osama_alharbi.prj.testers.assistance.service.on.OnPathSlmCRUD;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PathService {
    private final PathRepo pathRepo;
    private final FindPathSlmService findPathSlmService;
    private final OnFxService onFxService;

    @PostConstruct
    private void init(){
        onFxService.onPathSlmCRUD = new OnPathSlmCRUD() {
            @Override
            public List<PathDTO> getAllRecommendedXPath(String fullXPath) {
                return findPathSlmService.getRecommendedPath(fullXPath);
            }

            @Override
            public int countPaths(PathSlmType pathSlmType, String path) {
                return findPathSlmService.countBy(pathSlmType,path);
            }

        };
        onFxService.onPathCRUD = new OnPathCRUD() {
            @Override
            public List<Path> getAllFromDb(long elementId) {
                return PathService.this.getPathByElementId(elementId);
            }

            @Override
            public void delete(List<Path> deletedPaths) {
                PathService.this.delete(deletedPaths);
            }

            @Override
            public void add(List<Path> lst) {
                save(lst);
            }

            @Override
            public Path getPathById(long id) {
                return PathService.this.getPathById(id);
            }

            @Override
            public List<Path> getPathByIds(List<Long> ids) {
                return PathService.this.getPathByIds(ids);
            }
        };
    }

    public List<Path> getAllPaths() {
        return pathRepo.findAll();
    }

    public Path getPathById(Long id) {
        return pathRepo.findById(id).orElse(null);
    }

    public List<Path> getPathByIds(List<Long> ids) {
        return pathRepo.findByIdIn(ids);
    }

    public Path add(Path path) {
        return pathRepo.save(path);
    }

    public List<Path> save(List<Path> path) {
        return pathRepo.saveAll(path);
    }

    public List<Path> getPathByElementId(Long elementId) {
        return pathRepo.findByElementId(elementId);
    }

    public void delete(Long elementId) {
        pathRepo.deleteById(elementId);
    }

    public void delete(Path path) {
        pathRepo.delete(path);
    }

    public void delete(List<Path> lst) {
        pathRepo.deleteAll(lst);
    }
}

