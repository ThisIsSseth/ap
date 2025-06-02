package ap.exercises.ex5.scraper.store;

import ap.exercises.ex5.scraper.Conf;
import ap.exercises.ex5.scraper.abalyzer.HtmlAnalyzer;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class ImageUrlsSaver {

    public static boolean saveImageUrls(List<String> imageUrls) {
        try(PrintWriter out = new PrintWriter(Conf.IMAGE_URLS_SAVE_FILE)) {
            for (String imageUrl : imageUrls) {
                out.println(imageUrl);
            }
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("save failed: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {

        saveImageUrls(HtmlAnalyzer.getAllImageUrls());
    }

}
