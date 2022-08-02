package com.ll.exam.article.controller;

import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;

@Controller // ArticleController가 컨트롤러다.
public class ArticleController {


    @GetMapping("usr/article/list") // usr/article/list/free와 관련된 요청을 처리하는 함수임을 알리는 것.
    public void showList(){ // showList()는 usr/article/list/free 요청이 왔을 때 Get으로 실행되어야 하는 함수

    }
}
