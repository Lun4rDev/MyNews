package com.hernandez.mickael.mynews.utils;


import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.hernandez.mickael.mynews.models.MediaMetadatum;
import com.hernandez.mickael.mynews.models.Medium;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ResultsDeserializerJson implements JsonDeserializer<Medium> {

@Override
    public Medium deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Log.d("deserializeatteint", "yeyeye");
        JsonElement metadata = json.getAsJsonObject().get("media-metadata");
        //element.isJsonArray();
        //Log.d("APITESTtostr", metadata.toString());
        if(metadata.isJsonArray()) {
            Log.d("APITEST", "Array not found");
            return new Medium(Arrays.asList(context.<MediaMetadatum[]>deserialize(json.getAsJsonObject().getAsJsonArray("media-metadata"), MediaMetadatum[].class)));
        } else if(metadata.getAsString().equals("")) {
            return new Medium(Collections.<MediaMetadatum>emptyList());
        } else {
            Log.d("ResultsDeserializerJson", metadata.toString());
            throw new JsonParseException("Unsupported type of multimedia element");
        }
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