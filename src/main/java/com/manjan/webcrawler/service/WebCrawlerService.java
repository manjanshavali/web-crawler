package com.manjan.webcrawler.service;

import com.manjan.webcrawler.vo.WebCrawlerVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class WebCrawlerService {
    private static final Logger logger = LoggerFactory.getLogger(WebCrawlerService.class);

    public WebCrawlerVO WebCrawler(WebCrawlerVO webCrawlerVO)throws Exception {
        logger.info("WebCrawlerService - START");
        HashSet<String> links = new HashSet<>();
        List<List<String>> subLinks = new ArrayList<>();
        List matchedList = new ArrayList();
        String url = StringUtils.isEmpty(webCrawlerVO.getUrl()) ? "http://www.google.com" : webCrawlerVO.getUrl().trim();
        logger.info("WebCrawlerService - requested web url: {}",url);
        String searchText = StringUtils.isEmpty(webCrawlerVO.getSearchText()) ? "google" : webCrawlerVO.getSearchText();
        logger.info("WebCrawlerService requested searchText: {}",searchText);
        getPageLinks(url, links);
        getSubLinks(searchText,links, subLinks);
        searchedText(searchText, subLinks, matchedList);
        webCrawlerVO.setLinks(links);
        webCrawlerVO.setMatchedLinks(matchedList);
        logger.info("WebCrawlerService - END");
        return webCrawlerVO;
    }

    public void getPageLinks(String URL, HashSet<String> links) {
        if (!links.contains(URL)) {
            try {
                Document document = Jsoup.connect(URL).get();
                Elements otherLinks = document.select("a[href]");
                for (Element page : otherLinks) {
                    if (links.add(URL)) {
                        logger.info("just to see in log what are the links adding: {}",URL);
                    }
                    getPageLinks(page.attr("abs:href"), links);
                }
            } catch (IOException e) {
                logger.error("Error occured while getting page links : {}",e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public void getSubLinks(String searchText, HashSet<String> links, List<List<String>> subLinks) {
        links.forEach(x -> {
            Document document;
            try {
                document = Jsoup.connect(x).get();
                Elements elements = document.select("a[href]");
                for (Element element : elements) {
                    if (element.text().matches("^.*?("+searchText+").*$")) {//Please change this reqular expression depends on requirement to match the text
                        logger.info("just to see in log what are the sublinks adding: {}",element.attr("abs:href"));
                        ArrayList<String> temporary = new ArrayList<>();
                        temporary.add(element.text());
                        temporary.add(element.attr("abs:href"));
                        subLinks.add(temporary);
                    }
                }
            } catch (IOException e) {
                logger.error("Error occured while getting sub links : {}",e.getMessage());
                e.printStackTrace();
            }
        });
    }

    public void searchedText(String filename, List<List<String>> subLinks, List<String> matchedList) {
        try {
            subLinks.forEach(a -> {
                    String temp = "text: " + a.get(0) + " (link: " + a.get(1) + ")\n";
                    matchedList.add(temp);
                    logger.info(temp);
            });
            //writer.close();
        } catch (Exception e) {
            logger.error("Error occured while searching the text : {}",e.getMessage());
            e.printStackTrace();
        }
    }
}
