package com.dragon.lib;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yonglong on 2017/12/23.
 */

public class RegexUtils {
    private List<LinkData> regexUrl(String result) {
        List<LinkData> datas = new ArrayList<>();
        // 使用Jsoup解析网页内容
        Document document = Jsoup.parse(result);

        Elements classes = document.getElementsByClass("co_area2");
        for (Element cate : classes) {
            Elements links = cate.getElementsByAttribute("title");
            for (Element link : links) {
                String linkHref = link.attr("a");
                System.out.println(linkHref);
            }
        }

        // 获取文档标题
//        String title = document.title();
//        System.out.println(title);
        return datas;
    }
}
