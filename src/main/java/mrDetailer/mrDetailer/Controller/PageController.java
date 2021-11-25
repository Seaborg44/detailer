package mrDetailer.mrDetailer.Controller;

import mrDetailer.mrDetailer.domain.FileUtil;
import mrDetailer.mrDetailer.domain.MyObject;
import mrDetailer.mrDetailer.domain.MyObjectDto;
import mrDetailer.mrDetailer.service.TemplateService;
import mrDetailer.mrDetailer.mapper.MyObjectMapper;
import mrDetailer.mrDetailer.service.MyObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
public class PageController {

    @Autowired
    FileUtil fileUtil;
    @Autowired
    MyObjectService myObjectService;
    @Autowired
    MyObjectMapper myObjectMapper;

    TemplateService templateService = new TemplateService();

    @GetMapping
    public String HomePage() {
        return "MainPage.html";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String AdminPage(Model model) {
        model.addAttribute("Object", new MyObjectDto());
        return "admin.html";
    }

    @PostMapping("/admin")
    public String createObject(@Valid MyObjectDto myObjectDto,
                               RedirectAttributes redirectAttributes) throws IOException {

        for (int i = 0; i < myObjectDto.getPhotos().size(); i++) {
            String uploadDir = "src/main/resources/static/img";
            FileUtil.saveFile(uploadDir, myObjectDto.getPhotos().get(i));
        }
            myObjectService.addMyObject(myObjectMapper.mapToMyObject(myObjectDto));
            templateService.addImagesToMainPageTemplate(myObjectMapper.mapToMyObject(myObjectDto));

        return "redirect:/";
    }

    @RequestMapping(value = "/gallery", method = RequestMethod.GET)
    public String GalleryPage(Model model) {
//        model.addAttribute("Object", new MyObjectDto());
        return "gallery.html";
    }

    @RequestMapping("/delete")
    public String deleteObject(@RequestParam(value = "id") long id, RedirectAttributes redirectAttributes)
            throws IOException {

//        fileUtil.deleteFiles(id);
        myObjectService.deleteObjectById(id);
        return "redirect:/";
    }

}


