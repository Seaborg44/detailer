package mrDetailer.mrDetailer.domain;

import javax.persistence.*;

@Entity
@NamedQuery(
        name = "FileNames.fetchFileNames",
        query = "SELECT fileName FROM FileNames WHERE MYOBJECT_ID= :id"
)
public class FileNames {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fileName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MYOBJECT_ID")
    private MyObject myObject ;

    public FileNames() {
    }

    public FileNames(long id, String fileName) {
        this.id = id;
        this.fileName = fileName;
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

    public MyObject getMyObject() {
        return myObject;
    }

    public void setMyObject(MyObject myObject) {
        this.myObject = myObject;
    }


}
