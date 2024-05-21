package sa.osama_alharbi.prj.testers.assistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.osama_alharbi.prj.testers.assistance.entity.Element;

import java.util.List;

@Repository
public interface ElementRepo extends JpaRepository<Element, Long> {

    List<Element> findByPageId(Long pageId);

    long countByPageId(Long pageId);
}
