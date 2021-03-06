package mrDetailer.mrDetailer.repository;

import mrDetailer.mrDetailer.domain.MyObject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyObjectDao extends CrudRepository<MyObject, Long> {

    @Query(nativeQuery = true)
    Integer fetchMaxIdOfMyObject();

}
