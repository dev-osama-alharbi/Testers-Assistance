package sa.osama_alharbi.prj.testers.assistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.osama_alharbi.prj.testers.assistance.entity.Path;

import java.util.Collection;
import java.util.List;

@Repository
public interface PathRepo extends JpaRepository<Path, Long> {
    List<Path> findByElementId(Long elementId);

    List<Path> findByIdIn(Collection<Long> ids);
}
