package com.ll.exam.article.controller;

import com.ll.exam.annotation.Autowired;
import com.ll.exam.annotation.Controller;
import com.ll.exam.annotation.GetMapping;
import com.ll.exam.article.Service.ArticleService;

@Controller // ArticleController가 컨트롤러다.
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/usr/article/list/{boardCode}") // /usr/article/list/free 와 같이 관련된 요청을 처리하는 함수이다.
    public void showList(){ // showList()는 usr/article/list/free 요청이 왔을 때 Get으로 실행되어야 하는 함수

    }
    @GetMapping("/usr/article/detail/{boardCode}")
    public void showDetail() {

    }

}
