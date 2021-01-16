package com.algorithm;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@Inherited
@interface MyAnno {
    String value() default "有注解";
}



class Person {
    @MyAnno
    private String stra;
    private String strb;
    private String strc;

    public String getStra() {
        return stra;
    }

    public void setStra(String stra) {
        this.stra = stra;
    }

    public String getStrb() {
        return strb;
    }

    public void setStrb(String strb) {
        this.strb = strb;
    }

    public String getStrc() {
        return strc;
    }

    public void setStrc(String strc) {
        this.strc = strc;
    }

    Person(String str1, String str2, String str3){
        super();
        this.stra = str1;
        this.strb = str2;
        this.strc = str3;
    }

}

public class MyTest {
    public static void main(String[] args) {
        //初始化全都赋值无注解
        Person person = new Person("无注解","无注解","无注解");
        //解析注解
        doAnnoTest(person);
        System.out.println(person.getStra() + person.getStrb() + person.getStrc());
    }

    private static void doAnnoTest(Object obj) {
        Class clazz = obj.getClass();
        Field[] declareFields = clazz.getDeclaredFields();
        for (Field field:declareFields) {
            //检查该字段是否使用了某个注解
            if(field.isAnnotationPresent(MyAnno.class)){
                MyAnno anno = field.getAnnotation(MyAnno.class);
                if(anno!=null){
                    String fieldName = field.getName();
                    try {
                        Method setMethod = clazz.getDeclaredMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1),String.class);
                        //获取注解的属性
                        String annoValue = anno.value();
                        //将注解的属性值赋给对应的属性
                        setMethod.invoke(obj,annoValue);
                    }catch (NoSuchMethodException e){
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }
            }

        }
    }

}