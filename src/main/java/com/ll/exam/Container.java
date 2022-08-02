package com.ll.exam;

import com.ll.exam.annotation.Controller;
import com.ll.exam.article.controller.ArticleController;
import com.ll.exam.home.controller.HomeController;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Container {
    private static final ArticleController articleController;
    private static final HomeController homeController;

    static { // 클래스가 로딩될 때 1번만 실행
        // Ut 클래스에서 객체 생성 후 형변환
        articleController = (ArticleController)Ut.cls.newObj(ArticleController.class, null); // newObj가 해당 클래스를 만들고 실패하면 null을 준다.
        homeController = (HomeController)Ut.cls.newObj(HomeController.class, null);


    }
    public static ArticleController getArticleController() {
        return articleController; // 객체 재탕
    }

    public static HomeController getHomeController() {
        return homeController;
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