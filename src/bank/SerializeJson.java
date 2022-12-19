package bank;

import com.google.gson.*;

import java.lang.reflect.Type;

public class SerializeJson implements JsonSerializer<Transaction> {
    /**
     * Erstellt aus einem {@link Transaction} Objekt ein JSON Element
     * @param transaction
     * @param type
     * @param jsonSerializationContext
     * @return Das aus dem Objekt erzeugte Json Element
     */
    @Override
    public JsonElement serialize(Transaction transaction, Type type, JsonSerializationContext jsonSerializationContext) {

        /*Gson gson = new Gson();
        JsonObject obj = new JsonObject();

        // classname reinpacken
        //Klassenname in GROßSCHRIFT
        obj.addProperty("CLASSNAME", transaction.getClass().getSimpleName().toUpperCase());

        // ganzen inhalt reinpacken

        obj.add("INSTANCE",gson.toJsonTree(transaction,type));  // toJsonTree gibt JsonElement wieder
        return obj;*/

        JsonObject jsonObject = new JsonObject();

        if (transaction instanceof Transfer)
        {
            if (transaction instanceof IncomingTransfer){
                //String json = new GsonBuilder().create().toJson(transaction,type);
                jsonObject.add("CLASSNAME",new JsonPrimitive(transaction.getClass().getSimpleName()));
                JsonObject jsonObject1 = new JsonObject();
                jsonObject1.add("sender",new JsonPrimitive(((IncomingTransfer) transaction).getSender()));
                jsonObject1.add("recipient",new JsonPrimitive(((IncomingTransfer) transaction).getRecipient()));
                jsonObject1.add("date",new JsonPrimitive(transaction.getDate()));
                jsonObject1.add("amount",new JsonPrimitive(transaction.getAmount()));
                jsonObject1.add("description", new JsonPrimitive(transaction.getDescription()));
                jsonObject.add("INSTANCE",jsonObject1);

            } else if (transaction instanceof OutgoingTransfer){
                jsonObject.add("CLASSNAME",new JsonPrimitive(transaction.getClass().getSimpleName()));
                JsonObject jsonObject1 = new JsonObject();
                jsonObject1.add("sender",new JsonPrimitive(((OutgoingTransfer) transaction).getSender()));
                jsonObject1.add("recipient",new JsonPrimitive(((OutgoingTransfer) transaction).getRecipient()));
                jsonObject1.add("date",new JsonPrimitive(transaction.getDate()));
                jsonObject1.add("amount",new JsonPrimitive(transaction.getAmount()));
                jsonObject1.add("description", new JsonPrimitive(transaction.getDescription()));
                jsonObject.add("INSTANCE",jsonObject1);
            }
        } else if (transaction instanceof Payment){
            jsonObject.add("CLASSNAME",new JsonPrimitive(transaction.getClass().getSimpleName()));
            JsonObject jsonObject1 = new JsonObject();
            jsonObject1.add("incomingInterest",new JsonPrimitive(((Payment) transaction).getIncomingInterest()));
            jsonObject1.add("outgoingInterest",new JsonPrimitive(((Payment) transaction).getOutgoingInterest()));
            jsonObject1.add("date",new JsonPrimitive(transaction.getDate()));
            jsonObject1.add("amount",new JsonPrimitive(((Payment) transaction).getOutgoingInterest()));
            jsonObject1.add("description", new JsonPrimitive(transaction.getDescription()));
            jsonObject.add("INSTANCE",jsonObject1);
        }
        return jsonObject;
    }
}