package sa.osama_alharbi.prj.testers.assistance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "test_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int")
    private Integer id;

    @Column(name = "type", nullable = false, columnDefinition = "varchar(50)")
    private String type;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "test_type_id",
            referencedColumnName = "id",
            insertable = false,
            updatable = false
    )
    private List<Page> pages;

}
