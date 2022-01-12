package mrDetailer.mrDetailer.UnitTests;

import mrDetailer.mrDetailer.Controller.PageController;
import mrDetailer.mrDetailer.domain.FileNames;
import mrDetailer.mrDetailer.domain.MyObject;
import mrDetailer.mrDetailer.domain.MyObjectDto;
import mrDetailer.mrDetailer.mapper.MyObjectMapper;
import mrDetailer.mrDetailer.service.MyObjectService;
import mrDetailer.mrDetailer.service.TemplateService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class Unittests {

    @Autowired
    TemplateService templateService;

    MyObjectMapper myObjectMapper = new MyObjectMapper();

    @Autowired
    PageController pageController;
    @Autowired
    MyObjectService myObjectService;




    @Test
    public void testOfMappers() throws IOException {
        //given
        byte[] fileContent = "oki".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile filePart1 = new MockMultipartFile("file", "orig", null, fileContent);
        MockMultipartFile filePart2 = new MockMultipartFile("file", "orig", null, fileContent);
        List<MultipartFile> list = new ArrayList<>();
        list.add(filePart1);
        list.add(filePart2);
        MyObjectDto myObjectDto = new MyObjectDto(1l,"testmaptoobject", list,LocalDate.now());
        List<FileNames> listOfFileNames = new ArrayList<>();
        listOfFileNames.add(new FileNames(1l,"22g0az"));
        listOfFileNames.add(new FileNames(2l, "10"));
        MyObject myObject = new MyObject(24l, "wiktoria", listOfFileNames, LocalDate.now());
        //when
        MyObject transformedObject = myObjectMapper.mapToMyObject(myObjectDto);
        MyObjectDto transformedObjectDto = myObjectMapper.mapToMyObjectDto(myObject);
        //then
        Assertions.assertEquals(2, transformedObjectDto.getPhotos().size());
        Assertions.assertEquals(2, transformedObject.getFileNames().size()); //ok

    }
    @Test
    public void testTemplateImages() throws IOException {
        //given

        List<FileNames> listOfFileNames = new ArrayList<>();
        listOfFileNames.add(new FileNames(1l,"2wp8bqh"));
        listOfFileNames.add(new FileNames(2l, "10"));
        MyObject myObject = new MyObject(24l, "Jadwiga", listOfFileNames, LocalDate.now());
        //
        templateService.addImagesToMainPageTemplate(myObject);
    }



    @Test
    public void testDeleteMapping() throws IOException {
//        pageController.deleteObject(2); // dziala na samym id
    }

    @Test
    public void testOneToMany() throws IOException {
        byte[] fileContent = "oki".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile filePart1 = new MockMultipartFile("file", "orig", null, fileContent);
        MockMultipartFile filePart2 = new MockMultipartFile("file", "orig", null, fileContent);
        List<MultipartFile> list = new ArrayList<>();
        list.add(filePart1);
        list.add(filePart2);
        MyObjectDto myObjectDto = new MyObjectDto(1l,"testmaptoobject", list,LocalDate.now());

        //when
        MyObject transformedObject = myObjectMapper.mapToMyObject(myObjectDto);
        //then
        Assertions.assertEquals(2, transformedObject.getFileNames().size()); //ok
    }
    @Test
    public void testHQLfetchMaxID(){
        int maxId = myObjectService.fetchMaxIdFromDB();
        Assertions.assertEquals(6, maxId); //ok
    }

    @Test
    public void testAddToMyMainTemplate() throws IOException {
        List<FileNames> listOfFileNames = new ArrayList<>();
        listOfFileNames.add(new FileNames(1l,"2wp8bqh"));
        listOfFileNames.add(new FileNames(2l, "10"));
        MyObject myObject = new MyObject(24l, "wiktoria", listOfFileNames, LocalDate.now());
        templateService.addImagesToMainPageTemplate(myObject); //ok
    }

    @Test
    public void testRemoveFromMainTemplate() throws IOException {
        templateService.removeFromMainPageTemplate(2l); //ok
    }

    @Test
    public void testFetchListOfFileNames() {
        List<String> fetchedList = myObjectService.fetchListOfFileNames(5l);

        System.out.println(fetchedList.get(1)); //ok
    }

    @Test
    public void testRemoveFromGalleryTemplate() throws IOException {
        templateService.removeFromGallery(2l); //ok
    }

    @Test
    public void addToGalleryTemplate() throws IOException {
        List<FileNames> listOfFileNames = new ArrayList<>();
        listOfFileNames.add(new FileNames(1l,"107usd5"));
        listOfFileNames.add(new FileNames(2l, "15qx18o"));
        MyObject myObject = new MyObject((long) myObjectService.fetchMaxIdFromDB(),
                                "wiktoria", listOfFileNames, LocalDate.now());
        templateService.addImagesToGalleryTemplate(myObject); //ok
    }
}