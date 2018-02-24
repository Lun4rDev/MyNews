package com.hernandez.mickael.mynews.models.search;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/** POJO class used with the Search API */
public class Doc {

@SerializedName("web_url")
@Expose
private String webUrl;

@SerializedName("snippet")
@Expose
private String snippet;

@SerializedName("print_page")
@Expose
private String printPage;

@SerializedName("source")
@Expose
private String source;

@SerializedName("multimedia")
@Expose
private List<Multimedium> multimedia = null;

@SerializedName("headline")
@Expose
private Headline headline;

@SerializedName("pub_date")
@Expose
private String pubDate;

@SerializedName("document_type")
@Expose
private String documentType;

@SerializedName("new_desk")
@Expose
private String newDesk;

@SerializedName("section_name")
@Expose
private String section;

@SerializedName("type_of_material")
@Expose
private String typeOfMaterial;

@SerializedName("_id")
@Expose
private String id;

@SerializedName("word_count")
@Expose
private Integer wordCount;

@SerializedName("score")
@Expose
private Double score;

@SerializedName("uri")
@Expose
private String uri;


public String getWebUrl() {
return webUrl;
}

public void setWebUrl(String webUrl) {
this.webUrl = webUrl;
}

public String getSnippet() {
return snippet;
}

public void setSnippet(String snippet) {
this.snippet = snippet;
}

public String getPrintPage() {
return printPage;
}

public void setPrintPage(String printPage) {
this.printPage = printPage;
}

public String getSource() {
return source;
}

public void setSource(String source) {
this.source = source;
}

public List<Multimedium> getMultimedia() {
return multimedia;
}

public void setMultimedia(List<Multimedium> multimedia) {
this.multimedia = multimedia;
}

public Headline getHeadline() {
return headline;
}

public void setHeadline(Headline headline) {
this.headline = headline;
}


public String getPubDate() {
return pubDate;
}

public void setPubDate(String pubDate) {
this.pubDate = pubDate;
}

public String getDocumentType() {
return documentType;
}

public void setDocumentType(String documentType) {
this.documentType = documentType;
}

public String getNewDesk() {
return newDesk;
}

public void setNewDesk(String newDesk) {
this.newDesk = newDesk;
}

public String getSection() {
        return section;
    }

public void setSection(String section) {
        this.section = section;
    }

public String getTypeOfMaterial() {
return typeOfMaterial;
}

public void setTypeOfMaterial(String typeOfMaterial) {
this.typeOfMaterial = typeOfMaterial;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public Integer getWordCount() {
return wordCount;
}

public void setWordCount(Integer wordCount) {
this.wordCount = wordCount;
}

public Double getScore() {
return score;
}

public void setScore(Double score) {
this.score = score;
}

public String getUri() {
return uri;
}

public void setUri(String uri) {
this.uri = uri;
}

}