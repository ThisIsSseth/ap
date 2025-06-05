package ap.exercises.ex5.scraper.store;

import ap.exercises.ex5.scraper.parser.HtmlDirectoryComposer;
import ap.exercises.ex5.scraper.utils.DirectoryTools;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

public class HtmlFileManager {

    private String saveFileBasePath;
    private static int saveCounter = 0;

    public HtmlFileManager(String saveFileBasePath) {
//        this.saveFileBasePath = DirectoryTools.createDirectoryWithTimeStamp(saveFileBasePath);

        this.saveFileBasePath = saveFileBasePath;
        DirectoryTools.createDirectory(saveFileBasePath);
    }

    public void save(List<String> lines, String htmlName) {
        try {
            String saveHtmlFileAddress = getSaveHtmlFileAddress(htmlName);
            DirectoryTools.createDirectory(new File(saveHtmlFileAddress).getParent());
            PrintWriter out = new PrintWriter(saveHtmlFileAddress);
            for (String line : lines) {
                out.println(line);
            }
            out.close();

            System.out.println("save counter: " + saveCounter);
        } catch (Exception e) {
            System.out.println("save failed: " + e.getMessage());
        }

    }

    public void save(List<String> lines) {
        save(lines, "");
    }


    public String getSaveHtmlFileAddress(String htmlName) {
        String p = htmlName.isEmpty() ? "" : htmlName;
        saveCounter++;
        return saveFileBasePath + p + "/" + saveCounter;
    }
}
