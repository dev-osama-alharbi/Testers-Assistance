package sa.osama_alharbi.prj.testers.assistance.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "project")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false,columnDefinition = "varchar(100)")
    private String title;

    @Column(name = "base_url", nullable = false,columnDefinition = "Text")
    private String baseUrl;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "project_id",
            referencedColumnName = "id"
    )
    private List<GlobalCookies> globalCookies;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "project_id",
            referencedColumnName = "id"
    )
    private List<Page> pages;
}
