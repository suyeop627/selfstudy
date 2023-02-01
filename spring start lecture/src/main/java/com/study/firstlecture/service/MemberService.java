package com.study.firstlecture.service;

import com.study.firstlecture.domain.Member;
import com.study.firstlecture.repository.MemberRepository;
import com.study.firstlecture.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
//@Service//어노테이션을 해야, 스프링 컨테이너에 MemberService를 등록해준다.

@Transactional //jpa를 쓰려면 항상 transaction이 있어야한다.
public class MemberService {

  //테스트할때 저장공간이 따로 사용됨. -> 외부에서 넣어주도록 생성자로 DI주입
  //private final MemberRepository memberRepository = new MemoryMemberRepository();

//각 클래스마다 repository가 필요할 경우, 각각 만드는게 아니라, 외부에서 넣어주도록 설계.(di)
  private final MemberRepository memberRepository;
  @Autowired
   public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }


  //회원가입

  public Long join(Member member) {    //같은 이름이 있는 중복 회원이 없도록 개발해야함.

    /*
    Optional<Member> result = memberRepository.findByName(member.getName());
    //ctlr alt v
    result.ifPresent(m->{ //멤버의 값이 있으면,
      throw new IllegalStateException("이미 존재하는 회원입니다.");  //optional로 감싸서 할 수 있는 예외처리. Optional을 감싸지 않으면, if null로 처리해야함

  });
      //Optiona메서드
    // get() : 값 바로 가져오기(권장하는 방법은 아님) ,
    // orElseGet() : 값이 있으면 꺼내고 없으면 디폴트 반환
    //ifPresent() : 값이 존재하는 지 확인
    //Optional로 반환 받는건 권장하지 않음.
    //Optional로 반환이 됐기때문에 아래와 같이 작성할 수 있음

     */

    long start = System.currentTimeMillis();  //밀리초로 시작시간 측정
    //메서드 추출(ctrl alt m)
    try {
      validateDuplicateMember(member);   //중복회원 검증

      memberRepository.save(member); //회원 정보 저장
      return member.getId();
    }finally{      //예외 발생에 상관없이 로직이 끝날 때 시간을 측정하기 위해서 finally블럭에 종료시간측정, 걸린 시간 출력하도록 설계.
      long finish = System.currentTimeMillis();
      long timeMs = finish-start;
      System.out.println("join = " + timeMs + "ms");
    }
  }

  private void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName()).ifPresent(m -> {
          throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
  }

  //전체회원 조회
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memberId){
    return memberRepository.findById(memberId);
  }
}
