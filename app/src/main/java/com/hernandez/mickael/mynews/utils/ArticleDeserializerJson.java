package com.hernandez.mickael.mynews.utils;


import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.hernandez.mickael.mynews.models.Article;
import com.hernandez.mickael.mynews.models.Medium;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArticleDeserializerJson implements JsonDeserializer<Article> {

@Override
    public Article deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    JsonObject decodeObj = json.getAsJsonObject();
    Gson gson = new Gson();
    Article article = new Article("test", "01011997", "cinema"); //= gson.fromJson(json, Article.class);
    List<Medium> values = Collections.emptyList();
    if (decodeObj.get("media") != null && decodeObj.get("media").isJsonArray()) {
        /*values = gson.fromJson(decodeObj.get("media"), new TypeToken<List<Medium>>() {
        }.getType());*/
    } else {
        //String single = gson.fromJson(decodeObj.get("value"), String.class);
        Medium single = new Medium();
        values = new ArrayList<>();
        values.add(single);
    }
    article.setMedia(values);
    return article;





    /*if (json == null || json.isJsonNull() || json.toString().equals("")) // exclusion is made here
        return null;
    //JsonArray array = new JsonArray();
    ArrayList<Medium> res = new ArrayList<>();
    for (JsonElement child : json.getAsJsonArray()) {
        Medium element = context.deserialize(child, Medium.class);
        res.add(element);
    }
    return res;
    /*JsonElement metadata = json.getAsJsonObject().get("media-metadata");
        //element.isJsonArray();
        //Log.d("APITESTtostr", metadata.toString());
        if(metadata.isJsonArray()) {
            Log.d("APITEST", "Array found");
            return new Medium(Arrays.asList(context.<MediaMetadatum[]>deserialize(json.getAsJsonObject().getAsJsonArray("media-metadata"), MediaMetadatum[].class)));
        } else if(metadata.getAsString().equals("")) {
            Log.d("ResultsDeserializerJson", "EMPTY STRING");
            return new Medium(Collections.<MediaMetadatum>emptyList());
        } else {
            Log.d("ResultsDeserializerJson", "THROW E R R O R : " + metadata.toString());
            throw new JsonParseException("Unsupported type of multimedia element");
        }*/
        /*if (json.isJsonArray()) {
            Log.d("APITEST", "Array found");
            return new Medium(Arrays.asList(context.<MediaMetadatum[]>deserialize(json.getAsJsonArray(), MediaMetadatum[].class)));
        } else {
            Log.d("APITEST", "Array not found");
            MediaMetadatum mediaMetadata = new MediaMetadatum();
            List<MediaMetadatum> mediumList = new ArrayList<>();
            mediumList.add(mediaMetadata);
            return new Medium(mediumList);
        }*/
    }
}