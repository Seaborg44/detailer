package mrDetailer.mrDetailer.domain;

import mrDetailer.mrDetailer.service.MyObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Component
public class FileUtil {

    @Autowired
    MyObjectService myObjectService;

    public static void saveFile(String uploadDir, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(multipartFile.getOriginalFilename());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + multipartFile.getOriginalFilename(), ioe);
        }
    }
//    public void deleteFiles(long id) throws IOException {
//
//        List<String> fetchedStringList = myObjectService.fetchStringOfPhotoNames(id);
//        for (int i = 0; i < fetchedStringList.size(); i++) {
//            Path deletePath = Paths.get("src/main/resources/static/img/"
//                    + fetchedStringList.get(i));
//            Files.delete(deletePath);
//        }
//    }

}

