package mrDetailer.mrDetailer.repository;

import mrDetailer.mrDetailer.domain.FileNames;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileNamesDao extends CrudRepository<FileNames, Long> {

    @Query
    List<String> fetchFileNames(Long id);
}
