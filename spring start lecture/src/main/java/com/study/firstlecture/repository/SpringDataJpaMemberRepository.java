package com.study.firstlecture.repository;

import com.study.firstlecture.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {//인터페이스는 다중상속 가능
  //JpaRepository를 상속받으며, <Entity, Id의 타입>,

  @Override
  Optional<Member> findByName(String name); //메서드명만 작성하면 자동완성 됨.
  //findBy이름, findByAAndB, findByAOrB등의 형식으로 조회할 기준을 내 프로젝트에 맞춰서 사용할 수도 있다.
  //jpql : select m from Member m where m.name=? 를 자동으로 생성해준다.

}
