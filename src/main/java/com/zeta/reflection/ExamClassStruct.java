package com.zeta.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

public class ExamClassStruct {
    public static void main(String[] args){
        //从命令行中读入类名或者让用户输入类名
        String name;
        if (args.length > 0) name = args[0];
        else{
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name (e.g. java.util.Date): ");
            name = in.next();
        }
        try{
            //打印类名和超类名
            Class cl = Class.forName(name);
            Class supercl = cl.getSuperclass();
            String modifiers = Modifier.toString(cl.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print("class " + name);
            if (supercl != null && supercl != Object.class)
                System.out.print(" extends " + supercl.getName());
            System.out.print("\n{\n");
            //打印一个类所有的构造器
            printConstructors(cl);
            System.out.println();
            //打印一个类所有的方法
            printMethods(cl);
            System.out.println();
            //打印一个类所有的域
            printFields(cl);
            System.out.println("}");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        System.exit(0);
    }

    private static void printConstructors(Class cl){
        Constructor[] constructors = cl.getDeclaredConstructors();
        for (Constructor c : constructors){
            String name = c.getName();
            System.out.print("   ");
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(name + "(");
            //打印参数类型
            Class[] paramTypes = c.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++){
                if (j > 0) System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }
    }

    private static void printMethods(Class cl){
        Method[] methods = cl.getDeclaredMethods();
        for (Method m : methods){
            Class retType = m.getReturnType();
            String name = m.getName();
            System.out.print("   ");
            //打印修饰符、返回值和方法名
            String modifiers = Modifier.toString(m.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(retType.getName() + " " + name + "(");
            //打印参数类型
            Class[] paramTypes = m.getParameterTypes();
            for (int j = 0; j < paramTypes.length; j++){
                if (j > 0) System.out.print(", ");
                System.out.print(paramTypes[j].getName());
            }
            System.out.println(");");
        }
    }

    private static void printFields(Class cl){
        Field[] fields = cl.getDeclaredFields();
        for (Field f : fields){
            Class type = f.getType();
            String name = f.getName();
            System.out.print("   ");
            String modifiers = Modifier.toString(f.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.println(type.getName() + " " + name + ";");
        }
    }
}
