package com.study.firstlecture.service;

import com.study.firstlecture.domain.Member;
import com.study.firstlecture.repository.MemberRepository;
import com.study.firstlecture.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링 컨테이너와 테스트를 함께 실행
/*
DB에는 transation이라는 개념이 있어서, commit을 하기 전에는 실제 반영이 되지 않는다.
@Transactional 을 테스트 케이스에 작성하면, 테스트를 실행할때 transation을 실행하고, test종료시 rollback한다.
->테스트를 반복적으로 수행할 수 있게 한다.(다음 테스트에 영향을 주지 않도록한다.)

@AfterEach로 초기화 메서드를 따로 생성하지 않아도 된다.
*/

//참고> 통합테스트보단 작은 단위로 쪼개서 단위테스트로 실행할 수 있도록 테스트를 설계하는 것이 좋다.
@Transactional    //@AfterEach
class MemberServiceIntergrationTest {



  //생성자로 주입하는게 권장되긴 하지만, 테스트는 편의상 필드 주입으로 간단하게 작성하기도 한다.
  @Autowired MemberService memberService;
  @Autowired MemberRepository memberRepository;  // configuration 해놓은 것을 기반으로 구현체가 끌어올려진다.


  @Test //DB가 연결된 테스트를 할땐, DB를 초기화 하고 실행해야 하므로, 테스트 전용 DB를 만들어서 쓰거나 로컬에 따로 만들어서 사용한다.

  void 회원가입() {
    //given
    Member member = new Member();
    member.setName("spring");

    //when
    Long saveId = memberService.join(member);


    //then
    Member findMember = memberService.findOne(saveId).get();
    assertEquals(member.getName(), findMember.getName());
  }

  @Test
  public void 중복_회원_예외() {

    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");


    memberService.join(member1);

    IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


  }

  @Test
  void findMembers() {

  }

  @Test
  void findOne() {
  }
}