package ap.exercises.ex5.scraper.abalyzer;


import ap.exercises.ex5.scraper.Conf;
import ap.exercises.ex5.scraper.parser.HtmlParser;
import ap.exercises.ex5.scraper.utils.DirectoryTools;
import ap.exercises.ex5.scraper.utils.ObjectCounter;
import ap.exercises.ex5.scraper.utils.FileTools;

import java.util.*;
import java.util.stream.Collectors;

public class HtmlAnalyzer {
    private static List<String> fileList = DirectoryTools.getFilesAbsolutePathInDirectory(Conf.SAVE_DIRECTORY);

    public static List<String> getAllUrls() {
        List<String> urls = fileList.stream()
                .map(fileAddress -> FileTools.getTextFileLines(fileAddress))
                .filter(s -> s != null)
                .flatMap(s -> s.stream())
                .map(s -> HtmlParser.getFirstUrl(s))
                .filter(s -> s != null)
                .filter(s -> s.length() > 1)
                .collect(Collectors.toList());
        return urls;
    }

    public static List<String> getTopUrls(int k) {
        Map<String, Long> urlCount = getAllUrls().stream()
                .collect(Collectors.groupingBy(
                        s -> s,
                        Collectors.counting()
                ));

        List<String> topUrls = urlCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(k)
                .map(s -> s.getKey())
                .collect(Collectors.toList());

        return topUrls;
    }

    public static void printTopCountUrls(int k) {
        ObjectCounter<String> urlCounter = new ObjectCounter<>();
        getAllUrls().forEach(s -> urlCounter.add(s));
        for (Map.Entry<String, Integer> urlCountEntry : urlCounter.getTop(k)) {
            System.out.println(urlCountEntry.getKey() + " -> " + urlCountEntry.getValue());
        }
    }

    public static List<String> getAllImageUrls() {
        List<String> imageUrls = fileList.stream()
                .map(fileAddress -> FileTools.getTextFileLines(fileAddress))
                .filter(s -> s != null)
                .flatMap(s -> s.stream())
                .map(s -> HtmlParser.getFirstSrc(s))
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
        return imageUrls;
    }

    public static List<String> getSpecificDomainUrls(){
        List<String> znuDomainUrls = getAllUrls().stream()
                .filter(s -> s.contains("znu"))
                .toList();
        return znuDomainUrls;
    }

    public static List<String> getTopSpecificDomainUrls(int k){
        Map<String, Long> urlCount = getSpecificDomainUrls().stream()
                .collect(Collectors.groupingBy(
                        s -> s,
                        Collectors.counting()
                ));

        List<String> topUrls = urlCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(k)
                .map(s -> s.getKey())
                .collect(Collectors.toList());

        return topUrls;
    }


    public static void main(String[] args) {

        HtmlAnalyzer.printTopCountUrls(10);

        System.out.println("____________________");
        HtmlAnalyzer.getTopUrls(10).forEach(s -> System.out.println(s));

        HtmlAnalyzer.getSpecificDomainUrls().forEach(s -> System.out.println(s));

//        HtmlAnalyzer.getAllImageUrls().forEach(s -> System.out.println(s));

    }
}
