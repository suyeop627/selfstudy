package com.study.firstlecture.controller;

import com.fasterxml.jackson.databind.deser.BasicDeserializerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class StudyController {
  @GetMapping("hello")
  public String hello(Model model) {
    model.addAttribute("data", "hello!!");
    return "hello";
  }

  @GetMapping("hello-mvc")
  public String helloMvo(@RequestParam("name") String name, Model model) {
    model.addAttribute("name", name);
    return "hello-template";
  }

  @GetMapping("hello-string")
  @ResponseBody //http에서 body부의 데이터를 직접 넣어주는 어노테이션으로, 리턴값이 요청한 클라이언트에 그대로 전달된다.
  public String helloString(@RequestParam("name") String name) {//
    return "hello " + name;
  }

  @GetMapping("hello-api")
  @ResponseBody
public Hello helloApi(@RequestParam("name") String name){ //객체를 반환(api방식)
    Hello hello = new Hello();
    hello.setName(name);
    return hello;
  }


  static class Hello{//static으로 객체를 만들면 외부 클래스 안에서 사용할 수 있다.
    private String name; //접근 제어자가 private이므로 외부에서 바로 접근 불가능


    public String getName(){  //메서드를 통해 private 제어자를 가진 멤버에 접근
      return name;
    }
    public void setName(String name){
      this.name = name;
    }
  }
}