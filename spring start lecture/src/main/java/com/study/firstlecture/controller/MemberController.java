package com.study.firstlecture.controller;

import com.study.firstlecture.domain.Member;
import com.study.firstlecture.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //클래스 생성후 어노테이션 해놓으면, 스프링 컨트롤러에 멤버 컨트롤러 객체를 생성해서 스프링이 관리함.
public class MemberController {
// 스프링이 관리하는 환경에서는  MemberService를 사용해야 할때, 스프링 컨테이너에서 받아쓰도록 해야한다.
//=> 하나만 생성해놓고 공용으로 사용하도록 함.(Repository도 동일)

  private final MemberService memberService;

  @Autowired  //스프링이 실행될 때, 멤버 컨트롤러가 생성되는데, 생성자에 @Autowired가 있으면, 스프링에 있는 MemberService를 가져다가 연결시켜줌
              //컨트롤러는 스프링 컨테이너에 등록이 돼서, 바로 넣어줄 수 있는데, MemberService에도 스프링이 인식할 수 있도록 어노테이션 @Service를 추가해야한다.
  public MemberController(MemberService memberService) {
    this.memberService = memberService; //컨트롤러랑 서비스랑 @Autowired로 연결하면, 컨트롤러가 생성될때, 스프링빈에 등록돼있는 서비스 객체를 가져다가 넣어준다(dependency injection)
    System.out.println("MemberService의 클래스 : " + memberService.getClass());
  }


  @GetMapping(value = "/members/new")
  public String createForm() {
    return "members/createMemberForm";
  }

  @PostMapping("/members/new")
  public String create(MemberForm form){
    Member member= new Member();
    member.setName(form.getName());

    memberService.join(member);

    return "redirect:/";
  }

  @GetMapping("/members")
  public String List(Model model){
    List<Member> members= memberService.findMembers();
    model.addAttribute("members", members);
    return "members/memberList";
  }
}