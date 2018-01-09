package com.hernandez.mickael.mynews.utils;


import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.hernandez.mickael.mynews.models.main.Article;
import com.hernandez.mickael.mynews.models.main.MediaMetadatum;
import com.hernandez.mickael.mynews.models.main.Medium;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleDeserializerJson implements JsonDeserializer<Article> {

@Override
    public Article deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    // The article as json object to test elements
    JsonObject decodeObj = json.getAsJsonObject();

    Gson gson = new Gson();

    // Converts json to an article object
    Article article = gson.fromJson(json, Article.class);

    // If there is no picture, fill the article object with empty Media and MediaMetadatum
    /*if (decodeObj.get("media") == null || !decodeObj.get("media").isJsonArray()) {
        Medium m = new Medium();
        MediaMetadatum mmd = new MediaMetadatum("");
        ArrayList<MediaMetadatum> ammd = new ArrayList<>();
        ammd.add(mmd);
        m.setMediaMetadata(ammd);
        m.setUrl("");
        List<Medium> values = new ArrayList<>();
        values.add(m);
    }*/
    return article;
    }
}