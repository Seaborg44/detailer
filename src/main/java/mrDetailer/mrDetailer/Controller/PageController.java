package mrDetailer.mrDetailer.Controller;

import mrDetailer.mrDetailer.domain.FileUtil;
import mrDetailer.mrDetailer.domain.MyObjectDto;
import mrDetailer.mrDetailer.service.TemplateService;
import mrDetailer.mrDetailer.mapper.MyObjectMapper;
import mrDetailer.mrDetailer.service.MyObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class PageController {

    @Autowired
    FileUtil fileUtil;
    @Autowired
    MyObjectService myObjectService;
    @Autowired
    MyObjectMapper myObjectMapper;

    @Autowired
    TemplateService templateService;

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
        templateService.addImagesToGalleryTemplate(myObjectMapper.mapToMyObject(myObjectDto));

        return "redirect:/";
    }

    @RequestMapping(value = "/gallery", method = RequestMethod.GET)
    public String GalleryPage() {

        return "gallery2.html";
    }

    @RequestMapping(value = "/tariff", method = RequestMethod.GET)
    public String TariffPage() {

        return "tariff.html";
    }
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String aboutUsPage() {

        return "about us.html";
    }
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String productsPage() {

        return "products.html";
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String contactPage() {

        return "Contact.html";
    }

    @RequestMapping("/delete")
    public String deleteObject(@RequestParam(value = "id") long id, RedirectAttributes redirectAttributes)
            throws IOException {

        fileUtil.deleteFiles(id);
        myObjectService.deleteObjectById(id);
        templateService.removeFromMainPageTemplate(id);
        templateService.removeFromGallery(id);

        return "redirect:/";
    }

}


