package mrDetailer.mrDetailer.service;

import mrDetailer.mrDetailer.domain.MyObject;
import mrDetailer.mrDetailer.mapper.MyObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TemplateService {

    public TemplateService() {
    }

    public void addImagesToMainPageTemplate(MyObject myObject) throws IOException {
        File file = new File("C:\\java\\Projects\\mrDetailer\\src\\main\\resources\\templates\\MainPage.html");
        Document document = Jsoup.parse(file, "utf-8");
        Element article = document.selectFirst("article");
        article.before("\n" +
                "        <article> \n" +
                "        <p>" + myObject.getText() +"</p> \n" + photoIterator(myObject) +
                "        <h2>Post Format Image</h2> \n" +
                "        <hr class=\"title-underline\"> \n" +
                "        <div class=\"post-meta\"> <span><<i class=\"fa fa-bandcamp\"></i> " + myObject.getId() + "</span> \n" +
                "        <div class=\"post-meta\"> <span><i class=\"fa fa-calendar\"></i> " + myObject.getLocalDate() + "</span> \n" +
                "        </div> \n" +
                "       </article> ");
        FileWriter writer = new FileWriter(file);
        writer.write(document.toString());
        writer.flush();
        writer.close();
    }

    private String photoIterator (MyObject myObject) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myObject.getFileNames().size(); i++) {
           sb.append("        <img class=\"img-thumbnail\" src=\"img\\" + myObject.getFileNames().get(i).getFileName()
                   + MyObjectMapper.extension + "\"> \n" );
        }
        return sb.toString();
    }
}
