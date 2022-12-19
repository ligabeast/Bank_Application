package bank;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DeserializeJson implements JsonDeserializer<List<Transaction>> {

    /**
     * transformiert json-datei in objekt
     * @param json Json element
     * @param typeOfT klasse
     * @param context
     * @return list of object
     * @throws JsonParseException
     */
    @Override
    public List<Transaction> deserialize(JsonElement json, Type typeOfT,
                                         JsonDeserializationContext context) throws JsonParseException
    {
        List<Transaction> list = new ArrayList<>();
        // pack alle gespeicherten daten in 'jsonArray'
        JsonArray jsonArray = json.getAsJsonArray();

        for(int i=0; i < jsonArray.size(); i++) {
            //                         einzelne json-objekte
            JsonObject iteratorObj = jsonArray.get(i).getAsJsonObject();

            // Wenn Klasse = IncomingTransfer
            if(iteratorObj.get("CLASSNAME").getAsString().equals("IncomingTransfer")){
                // übergebe das objekt an 'iteratorObj'
                iteratorObj = iteratorObj.get("INSTANCE").getAsJsonObject();
                list.add(
                        new IncomingTransfer(
                                iteratorObj.get("date").getAsString(),
                                iteratorObj.get("amount").getAsDouble(),
                                iteratorObj.get("description").getAsString(),
                                iteratorObj.get("sender").getAsString(),
                                iteratorObj.get("recipient").getAsString()
                        )
                );
            }
            // Wenn Klasse = OutgoingTransfer
            else if(iteratorObj.get("CLASSNAME").getAsString().equals("OutgoingTransfer")){
                // übergebe objekt an 'iteratorObj'
                iteratorObj = iteratorObj.get("INSTANCE").getAsJsonObject();
                list.add(
                        new OutgoingTransfer(
                                iteratorObj.get("date").getAsString(),
                                iteratorObj.get("amount").getAsDouble(),
                                iteratorObj.get("description").getAsString(),
                                iteratorObj.get("sender").getAsString(),
                                iteratorObj.get("recipient").getAsString()
                        )
                );
            }
            // Wenn Klasse = Payment
            else if(iteratorObj.get("CLASSNAME").getAsString().equals("Payment")){

                iteratorObj = iteratorObj.get("INSTANCE").getAsJsonObject();
                list.add(
                        new Payment(
                                iteratorObj.get("date").getAsString(),
                                iteratorObj.get("amount").getAsDouble(),
                                iteratorObj.get("description").getAsString(),
                                iteratorObj.get("incomingInterest").getAsDouble(),
                                iteratorObj.get("outgoingInterest").getAsDouble()
                        )
                );
            }
        }
        return list;
    }
}