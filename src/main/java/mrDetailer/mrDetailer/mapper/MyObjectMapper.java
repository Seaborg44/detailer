package mrDetailer.mrDetailer.mapper;

import mrDetailer.mrDetailer.domain.FileNames;
import mrDetailer.mrDetailer.domain.MyObject;
import mrDetailer.mrDetailer.domain.MyObjectDto;
import mrDetailer.mrDetailer.service.MyObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyObjectMapper {

    @Autowired
    MyObjectService myObjectService;
    public static String extension="";

    public MyObject mapToMyObject(MyObjectDto myObjectDto) throws IOException {
        List<FileNames> fileNamesList = new ArrayList<>();
        MyObject myObject = new MyObject(myObjectDto.getId(), myObjectDto.getText(),
                fileNamesList, myObjectDto.getLocalDate());

        for (int i = 0; i < myObjectDto.getPhotos().size(); i++) {
            FileNames imagename = new FileNames();
            imagename.setFileName(myObjectDto.getPhotos().get(i).getOriginalFilename());
            fileNamesList.add(imagename);
        }
        myObject.setFileNames(fileNamesList);
        return myObject;
    }

    public MyObjectDto mapToMyObjectDto(MyObject myObject) throws IOException {
        List<MultipartFile> multipartFileList = new ArrayList<>();
        File file = null;

        for (int i = 0; i < myObject.getFileNames().size(); i++) {
            try {
                file = new File("src/main/resources/static/img/" + myObject.getFileNames().get(i).getFileName() + ".jpg");
                extension= "jpg";
                if(!file.exists()) {
                    file = new File("src/main/resources/static/img/" + myObject.getFileNames().get(i).getFileName() + ".png");
                    extension=".png";
                } if(!file.exists()) {
                    file = new File("src/main/resources/static/img/" + myObject.getFileNames().get(i).getFileName() + ".jpeg");
                    extension = ".jpeg";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            multipartFileList.add(fromFileToMultipartFile(file));
        }

        return new MyObjectDto(myObject.getId(),myObject.getText(), multipartFileList, myObject.getLocalDate());
    }

    private static MultipartFile fromFileToMultipartFile(File file) throws IOException {
        InputStream stream =  new FileInputStream(file);
        MultipartFile multipartFileToSend = new MockMultipartFile("file", file.getName(), MediaType.TEXT_HTML_VALUE, stream);

        return multipartFileToSend;
    }


}
