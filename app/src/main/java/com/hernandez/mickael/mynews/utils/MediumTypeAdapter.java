package com.hernandez.mickael.mynews.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.hernandez.mickael.mynews.models.Medium;

import java.io.IOException;

/**
 * Created by Mickael Hernandez on 15/11/2017.
 */

public class MediumTypeAdapter extends TypeAdapter<Medium> {

    @Override
    public void write(JsonWriter out, Medium value) throws IOException {

    }

    @Override
    public Medium read(JsonReader in) throws IOException {
        final Medium medium = new Medium();
        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "type":
                    medium.setType(in.nextString());
                    break;
                case "subtype":
                    medium.setSubtype(in.nextString());
                    break;
                case "caption":
                    medium.setCaption(in.nextString());
                    break;
                case "copyright":
                    medium.setCopyright(in.nextString());
                    break;
                case "approved_for_syndication":
                    medium.setApprovedForSyndication(in.nextInt());
                    break;
            }
        }
        return null;
    }
}
