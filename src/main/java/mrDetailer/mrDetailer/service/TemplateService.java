package mrDetailer.mrDetailer.service;

import mrDetailer.mrDetailer.domain.MyObject;
import mrDetailer.mrDetailer.mapper.MyObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

@Component
public class TemplateService {

    @Autowired
    MyObjectService myObjectService;

    public TemplateService() {
    }

    public void addImagesToMainPageTemplate(MyObject myObject) throws IOException {
        File file = new File("C:\\java\\Projects\\mrDetailer\\src\\main\\resources\\templates\\MainPage.html");
        Document document = Jsoup.parse(file, "utf-8");
        Elements articles = document.getElementsByClass("hidden-class");
        Optional<Element> article = articles.stream().findFirst();
        Element article1 = article.get();
        article1.before("\n" +
                "        <div class=\"hidden-class\">" + myObjectService.fetchMaxIdFromDB() + "</div>\n" +
                "        <article>  \n" +
                "        <p>" + myObject.getText() + "</p> \n" + photoIterator(myObject) +
                "        <h2>Post Format Image</h2> \n" +
                "        <hr class=\"title-underline\"> \n" +
                "        <div class=\"post-meta\"> <span><<i class=\"fa fa-arrow-circle-o-right\"></i> " + myObjectService.fetchMaxIdFromDB() + "</span> \n" +
                "        <div class=\"post-meta\"> <span><i class=\"fa fa-calendar\"></i> " + myObject.getLocalDate() + "</span> \n" +
                "        </div> \n" +
                "       </article> \n");
        FileWriter writer = new FileWriter(file);
        writer.write(document.toString());
        writer.flush();
        writer.close();
    }

    private String photoIterator(MyObject myObject) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myObject.getFileNames().size(); i++) {
            sb.append("        <img class=\"img-thumbnail\" src=\"img\\" + myObject.getFileNames().get(i).getFileName()
                    + MyObjectMapper.extension + "\"" + "name=\"" + myObjectService.fetchMaxIdFromDB() + "\">" + "\n");
        }
        return sb.toString();
    }

    public void removeFromMainPageTemplate(Long id) throws IOException {
        File file = new File("C:\\java\\Projects\\mrDetailer\\src\\main\\resources\\templates\\MainPage.html");
        Document document = Jsoup.parse(file, "utf-8");
        Elements articles = document.getElementsByClass("hidden-class");
        Elements article = articles.select(":containsOwn(" + id + ")");
        Elements nextLine = article.last().getAllElements();
        Elements allAfterNextLine = nextLine.nextAll();
        Element toBeRemoved = allAfterNextLine.get(0);
        toBeRemoved.remove();
        nextLine.remove();
        FileWriter writer = new FileWriter(file);
        writer.write(document.toString());
        writer.flush();
        writer.close();
    }

    public void removeFromGallery(Long id) throws IOException {
        File file = new File("C:\\java\\Projects\\mrDetailer\\src\\main\\resources\\templates\\gallery2.html");
        Document document = Jsoup.parse(file, "utf-8");
        Elements articles = document.getElementsByClass("hide-number");
        Elements article = articles.select(":containsOwn(" + id + ")");
        Elements nextLine = article.last().getAllElements();
        Elements allAfterNextLine = nextLine.nextAll();
        Element elementInQuestion = allAfterNextLine.get(0);
        Element toBeRemoved = elementInQuestion.firstElementSibling().parent();
        toBeRemoved.remove();
        FileWriter writer = new FileWriter(file);
        writer.write(document.toString());
        writer.flush();
        writer.close();
    }

    public void addImagesToGalleryTemplate(MyObject myObject) throws IOException {
        File file = new File("C:\\java\\Projects\\mrDetailer\\src\\main\\resources\\templates\\gallery2.html");
        Document document = Jsoup.parse(file, "utf-8");
        Elements articles = document.getElementsByClass("hidden-class");
        Optional<Element> article = articles.stream().findFirst();
        Element article1 = article.get();
        article1.before("\n" +
                "        <div class=\"hidden-class\">\n" +
                "        <div class=\"hide-number\">" + myObjectService.fetchMaxIdFromDB() + "</div>\n" +
                "        <article>  \n" +
                "        <p>" + myObject.getText() + "</p> \n" + photoIterator(myObject) +
                "        <h2>Post Format Image</h2> \n" +
                "        <hr class=\"title-underline\"> \n" +
                "        <div class=\"post-meta\"> <span><<i class=\"fa fa-arrow-circle-o-right\"></i> " + myObjectService.fetchMaxIdFromDB() + "</span> \n" +
                "        <div class=\"post-meta\"> <span><i class=\"fa fa-calendar\"></i> " + myObject.getLocalDate() + "</span> \n" +
                "        </div> \n" +
                "       </article> ");
        FileWriter writer = new FileWriter(file);
        writer.write(document.toString());
        writer.flush();
        writer.close();
    }
}
