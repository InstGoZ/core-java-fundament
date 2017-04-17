package com.zeta.reflection;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * Created by Zeta on 2017/4/17.
 */
public class ObjectAnalyzer {

    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object obj){
        if (obj == null) return "null";
        if (visited.contains(obj)) return "...";
        visited.add(obj);
        Class cl = obj.getClass();
        if (cl == String.class) return (String) obj;
        if (cl.isArray()){
            StringBuilder r = new StringBuilder(cl.getComponentType() + "[]{");
            for (int i = 0; i < Array.getLength(obj); i++){
                if (i > 0) r.append(",");
                Object val = Array.get(obj, i);
                if (cl.getComponentType().isPrimitive()) r.append(val);
                else r.append(toString(val));
            }
            return r + "}";
        }
        StringBuilder r = new StringBuilder(cl.getName());
        //查看此类和超类的所有域
        do{
            r.append("[");
            Field[] fields = cl.getDeclaredFields();
            AccessibleObject.setAccessible(fields, true);
            //得到所有域的名称和值
            for (Field f : fields){
                if (!Modifier.isStatic(f.getModifiers())){
                    if (!r.toString().endsWith("[")) r.append(",");
                    r.append(f.getName()).append("=");
                    try{
                        Class t = f.getType();
                        Object val = f.get(obj);
                        if (t.isPrimitive()) r.append(val);
                        else r.append(toString(val));
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            r.append("]");
            cl = cl.getSuperclass();
        }
        while (cl != null);
        return r.toString();
    }
}
