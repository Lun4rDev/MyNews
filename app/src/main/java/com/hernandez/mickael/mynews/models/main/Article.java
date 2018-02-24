package com.hernandez.mickael.mynews.models.main;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/** POJO class used with the Most Popular and Top Stories API */
public class Article {

    public Article(String title, String date, String section){
        this.title = title;
        this.publishedDate = date;
        this.section = section;
    }

    @SerializedName(value="url", alternate="web_url")
    @Expose
    private String url;

    @SerializedName(value="section", alternate="section_name")
    @Expose
    private String section;

    @SerializedName(value="subsection", alternate="subsection_name")
    @Expose
    private String subsection;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("published_date")
    @Expose
    private String publishedDate;

    @SerializedName("source")
    @Expose
    private String source;

    /*@SerializedName("id")
    @Expose
    private Long id;

    @SerializedName("asset_id")
    @Expose
    private Long assetId;

    @SerializedName("views")
    @Expose
    private Integer views;

    @SerializedName("des_facet")
    @Expose
    private List<String> desFacet = null;

    @SerializedName("org_facet")
    @Expose
    private List<String> orgFacet = null;

    @SerializedName("per_facet")
    @Expose
    private List<String> perFacet = null;

    @SerializedName("geo_facet")
    @Expose
    private List<String> geoFacet = null;*/

    @SerializedName(value="media", alternate="multimedia")
    @Expose
    private List<Medium> media = null;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getSubsection() {
        return subsection;
    }

    public void setSubsection(String subsection) {
        this.subsection = subsection;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssetId() {
        return assetId;
    }

    public void setAssetId(Long assetId) {
        this.assetId = assetId;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public List<String> getDesFacet() {
        return desFacet;
    }

    public void setDesFacet(List<String> desFacet) {
        this.desFacet = desFacet;
    }

    public List<String> getOrgFacet() {
        return orgFacet;
    }

    public void setOrgFacet(List<String> orgFacet) {
        this.orgFacet = orgFacet;
    }

    public List<String> getPerFacet() {
        return perFacet;
    }

    public void setPerFacet(List<String> perFacet) {
        this.perFacet = perFacet;
    }

    public List<String> getGeoFacet() {
        return geoFacet;
    }

    public void setGeoFacet(List<String> geoFacet) {
        this.geoFacet = geoFacet;
    }*/

    public List<Medium> getMedia() {
        return media;
    }

    public void setMedia(List<Medium> media) {
        this.media = media;
    }

}