package org.sang.common.tool;

import org.sang.common.IAuxiliaryTool;
import org.sang.common.IModuleEntity;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AuxiliaryTool implements IAuxiliaryTool {
    private static final Map<String, Object> projectValue = new ConcurrentHashMap<>();
    private final String nullValue = "value-null";

    public <T>T getProjectValue(String key){
        if(projectValue.containsKey(key)){
            Object object = projectValue.get(key);
            if(object != null){
                if(nullValue.equals(object.toString())){
                    return null;
                }
            }
            return (T)object;
        }
        return null;
    }
    @Override
    public IModuleEntity addAutomaticModul(Class<IModuleEntity> klass) {
        try {
            Field field= klass.getDeclaredField("id");

            if(field!=null){

            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T addAutomaticTimeOrId(Class<T> klass) {
        try {

            T value2=klass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
