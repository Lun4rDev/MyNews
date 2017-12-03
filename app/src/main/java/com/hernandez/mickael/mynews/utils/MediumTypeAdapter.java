package com.hernandez.mickael.mynews.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.hernandez.mickael.mynews.models.MediaMetadatum;
import com.hernandez.mickael.mynews.models.Medium;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Mickael Hernandez on 15/11/2017.
 */

public class MediumTypeAdapter extends TypeAdapter<Medium> {

    @Override
    public void write(JsonWriter jsonWriter, Medium list) throws IOException {
        if (list == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.beginArray();
        //for(Medium m : list){
            jsonWriter.value(list.getMediaMetadata().get(0).getUrl());
        //}
        jsonWriter.endArray();
    }

    @Override
    public Medium read(JsonReader jsonReader) throws IOException {
        JsonToken peek = jsonReader.peek();
        if(peek.equals(JsonToken.BEGIN_ARRAY)) {
            try {
                Gson gson = new Gson();
                //Type listType = new TypeToken<ArrayList<Medium>>(){}.getType();
                return gson.fromJson(jsonReader, Medium.class);
                //return Arrays.asList(new Medium(Arrays.asList(new MediaMetadatum(value))));
            } catch (NumberFormatException e) {
                throw new JsonSyntaxException(e);
            }
        } else {
            jsonReader.nextNull();
            return null;
        }
    }
}
