package com.rdc.bms.q_comic.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import org.json.JSONObject;

/**
 * Created by ThatNight on 2017.5.13.
 */

public class GsonUtil {
    private static Gson sGson;
    private static GsonBuilder sBuilder = null;

    static {
        if (sGson == null) {
            synchronized (GsonUtil.class) {
                if (sGson == null) {
                    sGson = new Gson();
                }
            }
        }
    }

    public static Gson getGson() {
        if (sGson != null) {
            return sGson;
        }
        return new Gson();
    }

    public static Gson getGsonHasBuilder() {
        Gson gson = null;
        if (sBuilder == null) {
            sBuilder = new GsonBuilder();
        }
        gson = sBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson;
    }

    /**
     * 转成json
     *
     * @param o
     * @return
     */
    public static String gsonToJson(Object o) {
        String json = null;
        if (sGson != null) {
            json = sGson.toJson(o);
        }
        return json;
    }

    public static <T> String gsonToJsonArray(List<T> objectList) {
        String json = "[";
        for (int i = 0; i < objectList.size(); i++) {
            if (i != objectList.size() - 1) {
                json += sGson.toJson(objectList.get(i)) + ",";
            } else {
                json += sGson.toJson(objectList.get(i));
            }
        }
        return json + "]";
    }

    /**
     * 转成bean类
     *
     * @param response
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T gsonToBean(String response, Class<T> bean) {
        T t = null;
        if (sGson != null) {
            t = sGson.fromJson(response, bean);
        }
        return t;
    }

    public static <T> List<T> gsonToBeanIncludeDate(String response, Class<T> cls){
        List<T> list = new ArrayList<>();
        Gson gson = GsonUtil.getGsonHasBuilder();
        JsonObject object = new JsonParser().parse(response).getAsJsonObject();
        JsonArray array=  object.getAsJsonArray("data");
        for (JsonElement element : array) {
            list.add(gson.fromJson(element, cls));
        }
        return list;


    }

    /**
     * 转成list
     *
     * @param response
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> gsonToList(String response, Class<T> cls) {
        List<T> list = new ArrayList<>();
        if (sGson != null) {
            JsonArray array = new JsonParser().parse(response).getAsJsonArray();
            for (JsonElement element : array) {
                list.add(sGson.fromJson(element, cls));
            }
        }
        return list;
    }

    /**
     * 转成list,包含Date的
     *
     * @param response
     * @param <T>
     * @return
     */
    public static <T> List<T> gsonToListIncludeDate(String response, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            Gson gson = GsonUtil.getGsonHasBuilder();
            JsonArray arry = new JsonParser().parse(response).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 转成list中有map的
     *
     * @param response
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, T>> gsonToListMaps(String response) {
        List<Map<String, T>> list = null;
        if (sGson != null) {
            list = sGson.fromJson(response, new TypeToken<List<Map<String, T>>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成map
     *
     * @param response
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> gsonToMaps(String response) {
        Map<String, T> map = null;
        if (sGson != null) {
            map = sGson.fromJson(response, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    public static Gson getGsonHasTypeAdapterBuilder() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<Map<String, Object>>() {
                }.getType(), new JsonDeserializer<Map<String, Object>>() {
                    @Override
                    public Map<String, Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                        Map<String, Object> map = new HashMap<>();
                        JsonObject jsonObject = json.getAsJsonObject();
                        Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                        for (Map.Entry<String, JsonElement> stringJsonElementEntry : entrySet) {
                            map.put(stringJsonElementEntry.getKey(), stringJsonElementEntry.getValue());
                        }
                        return map;
                    }
                }).create();
        return gson;
    }

//    /**
//     * 从一个JSON 对象字符格式中得到一个java对象
//     *
//     * @param jsonString
//     * @param beanCalss
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    public static <T> T jsonToBean(String jsonString, Class<T> beanCalss) {
//
////        JSONObject jsonObject = JSONObject.fromObject(jsonString);
//        T bean = (T) JSONObject.toBean(jsonObject, beanCalss);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.getJSONObject()
//
//        return bean;
//
//    }

}
