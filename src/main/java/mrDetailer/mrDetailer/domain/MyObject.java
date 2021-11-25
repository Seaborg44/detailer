package mrDetailer.mrDetailer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MyObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @OneToMany(targetEntity = FileNames.class,
    mappedBy = "myObject",
    cascade = CascadeType.DETACH,
    fetch = FetchType.EAGER)
    private List<FileNames> fileNames;
    private LocalDate localDate;

}