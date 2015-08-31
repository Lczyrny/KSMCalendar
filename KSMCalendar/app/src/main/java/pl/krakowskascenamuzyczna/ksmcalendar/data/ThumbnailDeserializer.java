package pl.krakowskascenamuzyczna.ksmcalendar.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Bartos on 2015-08-31.
 */
public class ThumbnailDeserializer implements JsonDeserializer<Thumbnail> {
    @Override
    public Thumbnail deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return null;
    }
}
