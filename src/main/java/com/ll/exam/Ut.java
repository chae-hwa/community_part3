package com.ll.exam;

import java.lang.reflect.InvocationTargetException;

public class Ut {
    public static class cls{
        public static Object newObj(Class cls, Object defaultValue){ // 클래스가 들어오면
            try {
                return cls.getDeclaredConstructor().newInstance(); // 그 안의 클래스 객체를 생성해준다.
            } catch (InstantiationException e) {
                return defaultValue;  // 오류가 날 때마다 defaultValue로 반환
            } catch (IllegalAccessException e) {
                return defaultValue;
            } catch (InvocationTargetException e) {
                return defaultValue;
            } catch (NoSuchMethodException e) {
                return defaultValue;
            }
        }
    }

    public static class str {

        public static String decapitalize(String string) {
            if (string == null || string.length() == 0) {
                return string;
            }

            char c[] = string.toCharArray();
            c[0] = Character.toLowerCase(c[0]);

            return new String(c);
        }
    }
}