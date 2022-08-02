package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.home.controller.HomeController;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container {
    private static Map<Class, Object> objects;
    static {
        objects = new HashMap<>();
        objects.put(ArticleController.class,new ArticleController());
        objects.put(HomeController.class, new HomeController());
    }
    public static <T> T getObj(Class cls){
        return (T)objects.get(cls);
    }

    public static List<String> getControllerNames() {
        List<String> names = new ArrayList<>();

        Reflections ref = new Reflections("com.ll.exam"); // com.ll.exam 하위 폴더를 싹 조사하겠다.
        for (Class<?> cls : ref.getTypesAnnotatedWith(Controller.class)) { // Controller로 어노테이션 되어 있는 클래스를 가져와.
            String name = cls.getSimpleName(); // HomeController
            name = name.replace("Controller", ""); // Home, Controller 용어 제거
            name = Ut.str.decapitalize(name); // home (소문자로 변환 )

            names.add(name.replace("Controller", name));
        }

        return names;
    }
}