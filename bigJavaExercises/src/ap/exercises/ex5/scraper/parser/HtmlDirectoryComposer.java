package ap.exercises.ex5.scraper.parser;

import ap.exercises.ex5.scraper.abalyzer.HtmlAnalyzer;

public class HtmlDirectoryComposer {

    /**
     * Only for www.znu.ac.ir domain
     */
    public static String composeHtmlDirectory(String html) {
        String htmlDirectory = "";
        if (!html.contains(".znu.ac.ir") || !html.contains("//"))
            return htmlDirectory;
        int index = html.indexOf(".znu.ac.ir");
        String subDomain = html.substring(html.indexOf("//") + 2, index);
        if (!subDomain.equals("www")) {
            htmlDirectory = htmlDirectory + subDomain + "_";
        }
        String[] choppedDomain = html.split("/");
        if (choppedDomain.length > 3 && !choppedDomain[3].isEmpty()) {
            htmlDirectory = htmlDirectory + "/" + choppedDomain[3];
        }
        return htmlDirectory;
    }


    public static void main(String[] args) {

        for (String a : HtmlAnalyzer.getTopSpecificDomainUrls(5)) {
            System.out.println(a);
            System.out.println(composeHtmlDirectory(a));
            System.out.println("---");
        }

    }
}
