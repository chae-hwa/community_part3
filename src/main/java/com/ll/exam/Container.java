package com.ll.exam;

import com.ll.exam.annotation.Autowired;
import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.Repository;
import com.ll.exam.annotation.Service;
import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.home.controller.HomeController;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Container {
    private static Map<Class, Object> objects; // articleController, homeController, articleService 3개 존재 중
    static {
        objects = new HashMap<>();

        scanComponents();
    }

    private static void scanComponents() {
        scanRepositories();
        scanService();
        scanControllers();

        resolveDependenciesAllComponents(); // 조립
    }

    private static void resolveDependenciesAllComponents() {
        for( Class cls : objects.keySet()){
            Object o = objects.get(cls);

            resolveDependencies(o);
        }
    }

    private static void resolveDependencies(Object o) {
        Arrays.asList(o.getClass().getDeclaredFields())
                .stream()
                .filter(f -> f.isAnnotationPresent(Autowired.class)) // 어노테이션이 달린 필드(변수)만 걸러내라.
                .map(field -> {
                    field.setAccessible(true); // setAccessible (외부에서 접근 가능하게 )
                    return field;
                })

                .forEach(field -> {
                    Class cls = field.getType();
                    Object dependency = objects.get(cls);

                    try {
                        field.set(o, dependency); // o객체 안에 있는 필드에다 같을 넣어주겠다
                    } catch (IllegalAccessException e) {

                    }
                });
    }
    private static void scanRepositories() {
        Reflections ref = new Reflections(APP.BASE_PACKAGE_PATH); // com.ll.exam 하위 폴더를 싹 조사하겠다.
        for (Class<?> cls : ref.getTypesAnnotatedWith(Repository.class)) { // cls를 얻어와서
            objects.put(cls, Ut.cls.newObj(cls, null)); // cls를 키로 하고 객체도 objects에 넣겠다.
        }
    }
    private static void scanService() {
        Reflections ref = new Reflections(APP.BASE_PACKAGE_PATH); // com.ll.exam 하위 폴더를 싹 조사하겠다.
        for (Class<?> cls : ref.getTypesAnnotatedWith(Service.class)) { // cls를 얻어와서
            objects.put(cls, Ut.cls.newObj(cls, null)); // cls를 키로 하고 객체도 objects에 넣겠다.
        }
    }

    private static void scanControllers() {
        Reflections ref = new Reflections(APP.BASE_PACKAGE_PATH); // com.ll.exam 하위 폴더를 싹 조사하겠다.
        for (Class<?> cls : ref.getTypesAnnotatedWith(Controller.class)) { // cls를 얻어와서
            objects.put(cls, Ut.cls.newObj(cls, null)); // cls를 키로 하고 객체도 objects에 넣겠다.
        }
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