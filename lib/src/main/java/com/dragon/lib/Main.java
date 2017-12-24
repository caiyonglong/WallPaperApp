package com.dragon.lib;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String args[]) {
        String result = ApiUtils.getInfosFromUrl("http://www.dy2018.com/");
//        String result = "http://www.dy2018.com/";
//        System.out.println(result);

        Document document = Jsoup.parse(result);

        Elements classes = document.getElementsByClass("co_area2");
        for (Element cate : classes) {
            Elements links = cate.getElementsByAttribute("title");
            for (Element link : links) {
                String linkHref = link.attr("a");
                System.out.println(linkHref);
            }
        }

    }
}
