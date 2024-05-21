package sa.osama_alharbi.prj.testers.assistance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sa.osama_alharbi.prj.testers.assistance.entity.Cookies;
import sa.osama_alharbi.prj.testers.assistance.entity.Project;

import java.util.List;

@Repository
public interface CookiesRepo extends JpaRepository<Cookies, Long> {
    List<Cookies> findByPageId(Long pageId);
}
