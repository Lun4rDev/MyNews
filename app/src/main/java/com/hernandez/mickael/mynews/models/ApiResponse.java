package com.hernandez.mickael.mynews.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiResponse {

    private Meta meta;
    private List<Article> mArticles = null;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Article> getArticles() {
        return mArticles;
    }

    public void setArticles(List<Article> articles) { this.mArticles = articles; }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}