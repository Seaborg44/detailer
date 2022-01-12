package mrDetailer.mrDetailer.service;

import mrDetailer.mrDetailer.domain.FileNames;
import mrDetailer.mrDetailer.domain.MyObject;
import mrDetailer.mrDetailer.repository.FileNamesDao;
import mrDetailer.mrDetailer.repository.MyObjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MyObjectService {

    @Autowired
    MyObjectDao myObjectDao;
    @Autowired
    FileNamesDao fileNamesDao;

    public List<MyObject> getAllObjects() {
        return (List<MyObject>) myObjectDao.findAll();
    }

    public Optional<MyObject> getObjectById(Long id) {
        return myObjectDao.findById(id);
    }

    public void deleteObjectById(Long id ) {
        myObjectDao.deleteById(id);
    }

    public void addMyObject (MyObject myObject) {
        myObjectDao.save(myObject);
    }

    public void saveFileName(FileNames fileName) {
        fileNamesDao.save(fileName);
    }

    public int fetchMaxIdFromDB() {
        return myObjectDao.fetchMaxIdOfMyObject();
    }

    public List<String> fetchListOfFileNames(Long id) {
        List<String> fetchedList = fileNamesDao.fetchFileNames(id);
        return fetchedList;
    }



}
