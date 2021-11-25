package mrDetailer.mrDetailer.domain;

import javax.persistence.*;

@Entity
public class FileNames {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String fileName;
    @ManyToOne
    @JoinColumn(name = "MYOBJECT_ID")
    private MyObject myObject;

    public FileNames() {
    }

    public FileNames(long id, String fileName) {
        this.id = id;
        this.fileName = fileName;
        this.myObject = new MyObject();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
