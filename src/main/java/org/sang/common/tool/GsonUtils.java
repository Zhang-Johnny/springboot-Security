package org.sang.common.tool;

import org.sang.common.Utils;
import org.sang.common.tool.Strings;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.joda.time.DateTime;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Gson 帮助类
 *
 */
public class GsonUtils {

    private static GsonUtils gsonUtils = new GsonUtils();
    public static GsonUtils init(){
        return gsonUtils;
    }

    /**
     * 获取Gson解析对象
     * @return
     */
    private Gson getGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeSerializer());
        gsonBuilder.registerTypeAdapter(Date.class, new DateSerializer());
        gsonBuilder.registerTypeAdapter(BigDecimal.class, new BigDecimalSerializer());
        gsonBuilder.registerTypeAdapter(Double.class, new DoubleSerializer());
        return gsonBuilder.create();
    }
    /**
     * Gson解析org.joda.time.DateTime类型 返回毫秒
     */
    private class DateTimeSerializer implements JsonSerializer<DateTime> {
        @Override
        public JsonElement serialize(DateTime src, Type typeOfSrc, JsonSerializationContext context) {
            if(src != null){
                return new JsonPrimitive(src.getMillis());
            }
            return null;
        }
    }
    /**
     * Gson解析java.util.Date类型 返回毫秒
     */
    private class DateSerializer implements JsonSerializer<Date> {
        @Override
        public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
            if(src != null){
                return new JsonPrimitive(src.getTime());
            }
            return null;
        }
    }
    /**
     * Gson解析java.math.BigDecimal类型 处理科学技术法，小数点后是零的忽略
     */
    private class BigDecimalSerializer implements JsonSerializer<BigDecimal> {
        @Override
        public JsonElement serialize(BigDecimal src, Type typeOfSrc, JsonSerializationContext context) {
            if(src != null){
                return new JsonPrimitive(Utils.formatNumbersToString0(src));
            }
            return null;
        }
    }
    /**
     * Gson解析java.lang.Double 处理科学技术法，小数点后是零的忽略
     */
    private class DoubleSerializer implements JsonSerializer<Double> {
        @Override
        public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
            if(src != null){
                return new JsonPrimitive(Utils.formatNumbersToString0(src));
            }
            return null;
        }
    }

    /**
     * 获取对象中List集合的属性
     * @param dataSource
     * @param propertyAllName 从对象的下个属性开始  如传入：dealins.ancheYear ，返回 dealins
     * @return
     */
    public static String getListTypePropertyName(Object dataSource, String propertyAllName)
    {
        StringBuffer sb = new StringBuffer();
        if(dataSource == null || Strings.isBlank(propertyAllName)){
            return null;
        }
        Gson gson = init().getGson();
        JsonObject object =  (JsonObject)gson.toJsonTree(dataSource);
        String[] propertys = propertyAllName.trim().split("\\.");
        JsonElement valueObj = object.get(propertys[0]);
        sb.append(propertys[0]);
        if(valueObj != null && valueObj.isJsonObject()){
            for (int i = 1; i < propertys.length; i++){
                valueObj = valueObj.getAsJsonObject().get(propertys[i]);
                sb.append(".");
                sb.append(propertys[i]);
                if(valueObj == null
                        || !valueObj.isJsonObject()
                        || valueObj.isJsonArray()){
                    break;
                }
            }
        }
        if(valueObj != null && valueObj.isJsonArray()){
            return sb.toString();
        }
        return "";
    }

    public static boolean isJsonListType(Object obj)
    {
        if(obj == null){
            return false;
        }
        Gson gson = init().getGson();
        JsonElement object =  gson.toJsonTree(obj);
        if(object != null && object.isJsonArray()){
            return true;
        }
        return false;
    }
    /**
     * 根据属性名，从数据源中，判断是否是集合类型
     * @param dataSource 数据源
     * @param propertyName 属性名
     * @return Json格式的list集合
     */
    public static boolean isJsonListType(Object dataSource, String propertyName)
    {
        if(dataSource == null || Strings.isBlank(propertyName)){
            return false;
        }
        Gson gson = init().getGson();
        JsonObject object =  (JsonObject)gson.toJsonTree(dataSource);
        JsonElement jsonElement = object.get(propertyName);
        if(jsonElement != null && jsonElement.isJsonArray()){
           return true;
        }
        return false;
    }
    /**
     * 根据属性名，从数据源中，判断是否是集合类型
     * @param element Json格式对象
     * @param propertyName 属性名
     * @return Json格式的list集合
     */
    public static boolean isJsonListType(JsonElement element, String propertyName)
    {
        if(element == null || Strings.isBlank(propertyName)){
            return false;
        }
        if(!element.isJsonObject()){
            return false;
        }
        JsonElement jsonElement = element.getAsJsonObject().get(propertyName);
        if(jsonElement != null && jsonElement.isJsonArray()){
            return true;
        }
        return false;
    }
    /**
     * 根据属性名，从数据源中，获取Json格式的list集合
     * @param dataSource 数据源
     * @param propertyName 属性名，从对象的下个属性开始 根据属性一直向下获取，直到获取到list
     * @return Json格式的list集合
     */
    public static ArrayList<JsonElement> getJsonListFromIData(Object dataSource, String propertyName)
    {
        if(dataSource == null || Strings.isBlank(propertyName)){
            return null;
        }
        Gson gson = init().getGson();
        JsonElement jsonElement = gson.toJsonTree(dataSource);
//        JsonElement jsonElement = object.get(propertyName);
//        if(jsonElement != null && jsonElement.isJsonArray()){
//            JsonArray jsonArr = jsonElement.getAsJsonArray();
//            Gson googleJson = GsonUtils.init().getGson();
//            return googleJson.fromJson(jsonArr, new TypeToken<List<JsonElement>>(){}.getType());
//        }
        return getJsonListFromJson(jsonElement, propertyName);
    }
    /**
     * 根据属性名，从数据源中，获取Json格式的list集合
     * @param element 数据源
     * @param propertyName 属性名，从对象的下个属性开始 根据属性一直向下获取，直到获取到list
     * @return Json格式的list集合
     */
    public static ArrayList<JsonElement> getJsonListFromJson(JsonElement element, String propertyName)
    {
        if(element == null || Strings.isBlank(propertyName)){
            return null;
        }
        if(!element.isJsonObject()){
            return null;
        }
        JsonObject object = element.getAsJsonObject();
        String[] propertys = propertyName.trim().split("\\.");
        JsonElement valueObj = object.get(propertys[0]);
        if(valueObj != null && valueObj.isJsonObject()){
            for (int i = 1; i < propertys.length; i++){
                valueObj = valueObj.getAsJsonObject().get(propertys[i]);
                if(valueObj == null
                        || !valueObj.isJsonObject()
                        || valueObj.isJsonArray()){
                    break;
                }
            }
        }
        if(valueObj != null && valueObj.isJsonArray()){
            JsonArray jsonArr = valueObj.getAsJsonArray();
            Gson googleJson = GsonUtils.init().getGson();
            return googleJson.fromJson(jsonArr, new TypeToken<List<JsonElement>>(){}.getType());
        }
//        JsonElement jsonElement = element.getAsJsonObject().get(propertyName);
//        if(jsonElement != null && jsonElement.isJsonArray()){
//            JsonArray jsonArr = jsonElement.getAsJsonArray();
//            Gson googleJson = GsonUtils.init().getGson();
//            return googleJson.fromJson(jsonArr, new TypeToken<List<JsonElement>>(){}.getType());
//        }
        return null;
    }
    /**
     * 根据属性名，从Json格式对象中获取值
     * @param element Json格式对象
     * @param propertyName 属性名
     * @return
     */
    public static String getValueFromJson(JsonElement element, String propertyName)
    {
        if(element == null || Strings.isBlank(propertyName)){
            return null;
        }
        if(!element.isJsonObject()){
            return null;
        }
        JsonObject object = element.getAsJsonObject();
        String[] propertys = propertyName.trim().split("\\.");
        JsonElement valueObj = object.get(propertys[0]);
        for (int i = 1; i < propertys.length; i++){
            if(valueObj != null){
                if(valueObj.isJsonObject()){
                    valueObj = valueObj.getAsJsonObject().get(propertys[i]);
                }else if(valueObj.isJsonPrimitive()){
                    break;
                }
            }
        }
        if(valueObj != null && valueObj.isJsonPrimitive()){
            return valueObj.getAsString();
        }
        return null;
    }
    /**
     * 根据属性名，从数据源中获取值
     * @param dataSource 数据源
     * @param propertyName 属性名
     * @return
     */
    public static String getValueFromIData(Object dataSource, String propertyName)
    {
        if(dataSource == null || Strings.isBlank(propertyName)){
            return null;
        }
        Gson gson = init().getGson();
        JsonElement object =  gson.toJsonTree(dataSource);
        if(object.isJsonObject()){
            return getValueFromJson(object, propertyName);
        }
        return null;
    }
    public static <T> String getValueFromObj(T dataSource, String propertyName)
    {
        if(dataSource == null || Strings.isBlank(propertyName)){
            return null;
        }
        Gson gson = init().getGson();
        JsonElement object =  gson.toJsonTree(dataSource);
        if(object.isJsonObject()){
            return getValueFromJson(object, propertyName);
        }
        return null;
    }
    public static <T> String toJsonStrFromObj(T obj){
        String result = "";
        if(obj != null){
            Gson gson = init().getGson();
            return gson.toJson(obj);
        }
        return result;
    }
}
