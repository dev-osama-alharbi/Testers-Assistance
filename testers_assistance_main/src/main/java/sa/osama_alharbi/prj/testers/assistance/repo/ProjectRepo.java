package sa.osama_alharbi.prj.testers.assistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.osama_alharbi.prj.testers.assistance.entity.Project;

@Repository
public interface ProjectRepo  extends JpaRepository<Project, Long> {
}
