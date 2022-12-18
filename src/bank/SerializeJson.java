package bank;

import com.google.gson.*;
import java.lang.reflect.Type;

public class SerializeJson implements JsonSerializer<Transaction>, JsonDeserializer<Transaction> {

    @Override
    public Transaction deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        return (new Gson()).fromJson(jsonElement, Transaction.class);
    }
    @Override
    public JsonElement serialize(Transaction obj, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        Gson gson = new Gson();

        result.addProperty("CLASSNAME", obj.getClass().getSimpleName().toUpperCase());
        result.add("INSTANCE", gson.toJsonTree(obj));

            return result;
    }
}