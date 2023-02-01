package com.study.firstlecture.repository;

import com.study.firstlecture.domain.Member;

import java.util.List;
import java.util.Optional;

//아직 구체적으로 정의 되지 않은 부분을 인터페이스로 생성
//바꿔넣기 가능
public interface MemberRepository {
  Member save(Member member);//회원 정보 저장
  Optional<Member> findById(Long id);//아이디로 검색된 회원 정보 반환
  Optional<Member> findByName(String name);//이름으로 검색된 회원 정보 반환
  List<Member> findAll(); //모든 회원 정보 반환
  //optional : findbyid나 findbyname에서 null이 반환받을 수도 있는데,
  // null을 받을 때 Optional로 감싸서 받는 방식을 선호한다.
  //optional<>을 사용하면, null값에 대한 처리가 가능하다.

}
