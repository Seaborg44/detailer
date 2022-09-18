package mrDetailer.mrDetailer.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(
        name = "MyObject.fetchMaxIdOfMyObject",
        query = "SELECT max(id) FROM MyObject"
)
public class MyObject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @OneToMany(targetEntity = FileNames.class,
    mappedBy = "myObject",
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER)
    private List<FileNames> fileNames;
    private LocalDate localDate;
    public static int templateId;


    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setFileNames(List<FileNames> fileNames) {
        for(FileNames fileName : fileNames) {
            fileName.setMyObject(this);
        }
        this.fileNames = fileNames;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}