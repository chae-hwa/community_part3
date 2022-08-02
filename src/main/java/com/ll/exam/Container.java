package com.ll.exam;

import com.ll.exam.article.controller.ArticleController;

public class Container {
    private static ArticleController articleController;

    static { // 클래스가 로딩될 때 1번만 실행
        articleController = new ArticleController();
    }
    public static ArticleController getArticleController() {
        return articleController; // 객체 재탕
    }
}