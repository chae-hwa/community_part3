package com.ll.exam.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 이 어노테이션은 메서드에 붙는 것.
@Retention(RetentionPolicy.RUNTIME) // 중요하지 않음. 몰라도 됨.(없으면 실행 중 오류 발생)
public @interface GetMapping { // Container의 GetMapping은 메서드에 붙어서 8번째 코드 정의
    String value(); // 어노테이션 달 때, value(String) 입력 가능하게 함.
}