package com.study.firstlecture.service;

import com.study.firstlecture.domain.Member;
import com.study.firstlecture.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
//테스트 할때 메서드 명은 한글로 수정해서 써도 상관없음.
  //빌드할때 포함되지 않으며, 그냥 보기 편하게 적는게 편함



  /*
  아래와 같이 하면, 다른 클래스를 테스트할 때 각 다른 repository를 사용하게 되는 문제가 있다.
  저장공간을 공유하게 하기 위해서 DI 사용해야함.

  MemberService memberService = new MemberService();
  MemoryMemberRepository memberRepository = new MemoryMemberRepository();
  //테스트를 위해서, 저장된 값을 없애기 위해 MemoryMemberRepository 인스턴스 추가
*/

  //di로 적용
  MemberService memberService;
  MemoryMemberRepository memberRepository;
  @BeforeEach
  public void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }


  @AfterEach //각 메서드가 실행되고나서 매번 실행되는 일종의 콜벡 메서드 정의
  public void afterEach() {
    memberRepository.clearStore();
  }



  @Test
  void 회원가입() { //test할 때 given, when, then으로 주석 깔고 시작하는게 보기 편함
    //given
    Member member = new Member();
    member.setName("hello");

    //when
    Long saveId = memberService.join(member); //저장


    //then
    Member findMember = memberService.findOne(saveId).get();
    assertEquals(member.getName(), findMember.getName());
  }

  @Test
  public void 중복_회원_예외(){
    //given  중복된 이름의 회원 저장
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    //when
    memberService.join(member1);
    //memberService.join(member2)이 로직을 실행하면, IllegalStateException.class이 예외가 터져야함을 나타냄
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    //위 메서드는 반환이 가능.
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
  /*
    try{

      memberService.join(member2);  //spring이름의 member1이 저장된 상태에서 같은 이름의 member2를 저장하려고 할 경우
      fail();//무조건 실패임을 나타내는 메서드 //윗 라인에서 예외가 발생하고 catch로 넘어가야 하는데, 여기까지 내려오면 검증 실패임
    }catch(IllegalStateException e){
      //예외 발생시키기 성공.
      assertThat(e.getMessage()).isEqualTo(("이미 존재하는 회원입니다."));//예외 발생시 출력할 문구가 정상 출력된느지
    }
*/

    //then
  }

  @Test
  void findMembers() {

  }

  @Test
  void findOne() {
  }
}