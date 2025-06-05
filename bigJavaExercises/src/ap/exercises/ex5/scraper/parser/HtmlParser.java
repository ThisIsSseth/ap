package ap.exercises.ex5.scraper.parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HtmlParser {

    public static String getFirstUrl(String htmlLine) {
        String url = null;
        int startIndex = htmlLine.indexOf("href=\"");
        if (startIndex >= 0) {
            try {
                int hrefLength = "href\"".length();
                int endIndex = htmlLine.indexOf("\"", startIndex + hrefLength + 1);
                url = htmlLine.substring(startIndex + hrefLength + 1, endIndex);
            } catch (Exception e) {
            }
        }
        return url;
    }

    private static List<String> getAllUrlsFromHtmlLinesStream(Stream<String> htmlLinesStream) throws IOException {
        List<String> urls = htmlLinesStream
                .map(line -> getFirstUrl(line))
                .filter(s -> s != null)
                .collect(Collectors.toList());
        return urls;
    }

    public static List<String> getAllUrlsFromFile(String filePath) throws IOException {
        return getAllUrlsFromHtmlLinesStream(Files.lines(Path.of(filePath)));
    }

    public static List<String> getAllUrlsFromList(List<String> htmlLines) throws IOException {
        return getAllUrlsFromHtmlLinesStream(htmlLines.stream());
    }

    public static String getFirstSrc (String htmlLine) {
        String src = null;
        if (htmlLine.contains(".png")) {
            int endIndex = htmlLine.indexOf(".png") + 4;
            int startIndex = htmlLine.indexOf("\"") < endIndex ? htmlLine.indexOf("\"")+1 : 0;
            src = htmlLine.substring(startIndex, endIndex);
        }
        if (htmlLine.contains(".jpg")) {
            int endIndex = htmlLine.indexOf(".jpg") + 4;
            int startIndex = htmlLine.indexOf("\"") < endIndex ? htmlLine.indexOf("\"") +1 : 0;
            src = htmlLine.substring(startIndex, endIndex);
        }
        return src;

    }
    public static List<String> getAllSrcFromHtmlLinesStream(Stream<String> htmlLinesStream) throws IOException {
        List<String> srcs = htmlLinesStream
                .map( line -> getFirstSrc(line))
                .collect(Collectors.toList());
        return srcs;
    }

    public static List<String> getAllDomainSpecificUrlsFromList(List<String> htmlLines) throws IOException {
        List<String> urls = getAllUrlsFromList(htmlLines).stream()
                .filter(s -> s.contains("znu"))
                .toList();
        return urls;
    }
}