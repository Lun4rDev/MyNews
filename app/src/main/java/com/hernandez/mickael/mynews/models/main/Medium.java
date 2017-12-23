package com.hernandez.mickael.mynews.models.main;

import java.util.Collections;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hernandez.mickael.mynews.models.main.MediaMetadatum;

public class Medium {

    public Medium() {
        this.mediaMetadata = Collections.emptyList();
    }

    public Medium(List<MediaMetadatum> medium) {
        this.mediaMetadata = medium;
    }

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("subtype")
    @Expose
    private String subtype;
    @SerializedName("caption")
    @Expose
    private String caption;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("approved_for_syndication")
    @Expose
    private Integer approvedForSyndication;

    @SerializedName("media-metadata")
    @Expose
    //@JsonAdapter(MediumAdapterFactory.class)
    private List<MediaMetadatum> mediaMetadata = null;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
    return type;
    }

    public void setType(String type) {
    this.type = type;
    }

    public String getSubtype() {
    return subtype;
    }

    public void setSubtype(String subtype) {
    this.subtype = subtype;
    }

    public String getCaption() {
    return caption;
    }

    public void setCaption(String caption) {
    this.caption = caption;
    }

    public String getCopyright() {
    return copyright;
    }

    public void setCopyright(String copyright) {
    this.copyright = copyright;
    }

    public Integer getApprovedForSyndication() {
    return approvedForSyndication;
    }

    public void setApprovedForSyndication(Integer approvedForSyndication) {
    this.approvedForSyndication = approvedForSyndication;
    }

    public List<MediaMetadatum> getMediaMetadata() {
    return mediaMetadata;
    }

    public void setMediaMetadata(List<MediaMetadatum> mediaMetadata) {
    this.mediaMetadata = mediaMetadata;
    }

}