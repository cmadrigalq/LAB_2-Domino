package Json;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class Json {

    static JSONSerializer serializer;
    static JSONDeserializer desSerializer;

    static {
        serializer = new JSONSerializer();
        desSerializer = new JSONDeserializer();
    }


    static public String toJson(Object s) {
        return serializer.serialize(s);
    }

    static public Object toObject(String str, Class _class) {
        return desSerializer.deserialize(str, _class);
    }

}
