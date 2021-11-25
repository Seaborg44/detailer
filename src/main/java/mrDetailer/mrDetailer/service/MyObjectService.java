package mrDetailer.mrDetailer.service;

import mrDetailer.mrDetailer.domain.MyObject;
import mrDetailer.mrDetailer.repository.MyObjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MyObjectService {

    @Autowired
    MyObjectDao myObjectDao;

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



}
