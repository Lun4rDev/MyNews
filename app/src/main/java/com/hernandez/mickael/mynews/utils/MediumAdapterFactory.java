package com.hernandez.mickael.mynews.utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import com.hernandez.mickael.mynews.models.Medium;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MediumAdapterFactory implements TypeAdapterFactory {

    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (type.getRawType()!= Medium.class) return null;

        TypeAdapter<Medium> defaultAdapter = (TypeAdapter<Medium>) gson.getDelegateAdapter(this, type);
        return (TypeAdapter<T>) new MediumObjectAdapter(defaultAdapter);
    }

    public class MediumObjectAdapter extends TypeAdapter<Medium> {

        protected TypeAdapter<Medium> defaultAdapter;


        public MediumObjectAdapter(TypeAdapter<Medium> defaultAdapter) {
            this.defaultAdapter = defaultAdapter;
        }

        @Override
        public void write(JsonWriter out, Medium value) throws IOException {
            defaultAdapter.write(out, value);
        }

        @Override
        public Medium read(JsonReader in) throws IOException {
            /*
            This is the critical part. So if the value is a string,
            Skip it (no exception) and return null.
            */
            if (in.peek() == JsonToken.STRING) {
                in.skipValue();
                return null;
            }
            return defaultAdapter.read(in);
        }
    }
}