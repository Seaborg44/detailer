package mrDetailer.mrDetailer.mapper;

import mrDetailer.mrDetailer.domain.FileNames;
import mrDetailer.mrDetailer.domain.MyObject;
import mrDetailer.mrDetailer.domain.MyObjectDto;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyObjectMapper {
    public static String extension="";

    public MyObject mapToMyObject (MyObjectDto myObjectDto) throws IOException {
        List<FileNames> fileNamesList = new ArrayList<>();
        for (int i = 0; i < myObjectDto.getPhotos().size(); i++) {
            FileNames imagename = new FileNames(i,myObjectDto.getPhotos().get(i).getOriginalFilename());
            fileNamesList.add(imagename);
        }
        return new MyObject(myObjectDto.getId(), myObjectDto.getText(),
                fileNamesList, myObjectDto.getLocalDate());
    }

    public MyObjectDto mapToMyObjectDto (MyObject myObject) throws IOException {

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

      // mapping for absolute path FILE<<>>MULTIPARTFILE (old - for no lists)

//    public MyObject mapToMyObject (MyObjectDto myObjectDto) throws IOException {
//        return new MyObject(myObjectDto.getId(), myObjectDto.getText(),
//                convert(myObjectDto.getPhoto()).getAbsolutePath(), myObjectDto.getLocalDate());
//    }
//
//    public MyObjectDto mapToMyObjectDto (MyObject myObject) throws IOException {
//        File file = new File(myObject.getImagePath());
//        return new MyObjectDto(myObject.getId(),myObject.getText(), fromFileToMultipartFile(file), myObject.getLocalDate());
//    }
//
//    public List<MyObjectDto> mapToMyObjectDtoList (List<MyObject> myObjectList)  {
//        return (List<MyObjectDto>) myObjectList.stream()
//                .map(mol-> {
//                    try {
//                        return new MyObjectDto(mol.getId(),mol.getText(),fromFileToMultipartFile(new File(mol.getImagePath())) );
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    return null;
//                })
//                .collect(Collectors.toList());
//    }
//    private static File convert(MultipartFile file) throws IOException {
//        Path newFile = Paths.get(file.getOriginalFilename());
//        try(InputStream is = file.getInputStream(); OutputStream os = Files.newOutputStream(newFile)) {
//            byte[] buffer = new byte[12294];
//            int read = 0;
//            while((read = is.read(buffer)) > 0) {
//                os.write(buffer,0,read);
//            }
//        }
//        return newFile.toFile();
//    }
//
    private static MultipartFile fromFileToMultipartFile (File file) throws IOException {
        InputStream stream =  new FileInputStream(file);
        MultipartFile multipartFileToSend = new MockMultipartFile("file", file.getName(), MediaType.TEXT_HTML_VALUE, stream);

        return multipartFileToSend;
    }


}
