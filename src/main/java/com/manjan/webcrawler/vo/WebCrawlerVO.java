package com.manjan.webcrawler.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class WebCrawlerVO implements Serializable {

    private String url;
    private String searchText;
    private HashSet<String> links = new HashSet<>();
    private List<String> matchedLinks = new ArrayList<>();

    public WebCrawlerVO(){

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public HashSet<String> getLinks() {
        return links;
    }

    public void setLinks(HashSet<String> links) {
        this.links = links;
    }

    public List<String> getMatchedLinks() {
        return matchedLinks;
    }

    public void setMatchedLinks(List<String> matchedLinks) {
        this.matchedLinks = matchedLinks;
    }
}
