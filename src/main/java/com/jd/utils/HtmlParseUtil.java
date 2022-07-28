package com.jd.utils;

import com.jd.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 爬取网页内容 Jsoup
 */
public class HtmlParseUtil {
//    public static void main(String[] args) throws IOException {
//        new HtmlParseUtil().parseJD("编程").forEach(System.out::println);
//    }

    public List<Content> parseJD(String key) throws IOException {
        // 获取请求 前提 需要联网
        String url = "https://search.jd.com/Search?keyword=" + key;
        Document documene = Jsoup.parse(new URL(url), 30000);
        Element element = documene.getElementById("J_goodsList");
        Elements elements = element.getElementsByTag("li");
        ArrayList<Content> goodsList = new ArrayList<>();

        for (Element elem : elements) {
            String img = elem.getElementsByTag("img").eq(0).attr("src");
            String price = elem.getElementsByClass("p-price").eq(0).text();
            String title = elem.getElementsByClass("p-name").eq(0).text();
            Content content = new Content();
            content.setImg(img);
            content.setPrice(price);
            content.setTitle(title);
            goodsList.add(content);
        }
        return goodsList;
    }
}
