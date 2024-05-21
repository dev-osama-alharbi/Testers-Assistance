package sa.osama_alharbi.prj.testers.assistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.osama_alharbi.prj.testers.assistance.entity.Page;

import java.util.List;

@Repository
public interface PageRepo extends JpaRepository<Page, Long> {
    List<Page> findByProject_Id(Long id);

}
