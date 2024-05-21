package sa.osama_alharbi.prj.testers.assistance.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sa.osama_alharbi.prj.testers.assistance.entity.PathType;
import sa.osama_alharbi.prj.testers.assistance.repo.PathTypeRepo;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PathTypeService {
    private final PathTypeRepo pathTypeRepo;

    public List<PathType> getAllPathTypes() {
        return pathTypeRepo.findAll();
    }

    public PathType getPathTypeById(Integer id) {
        return pathTypeRepo.findById(id).orElse(null);
    }

    public void add(ArrayList<PathType> pathTypes) {
        pathTypeRepo.saveAll(pathTypes);
    }
}