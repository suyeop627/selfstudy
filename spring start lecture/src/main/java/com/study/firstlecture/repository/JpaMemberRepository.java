package com.study.firstlecture.repository;

import com.study.firstlecture.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
  //bundle.gradle에 추가한 jpa라이브러리와 application.properties에 작성한 datasourse를 기반으로,
  //스프링 부트가 자동으로 EntityManager를 생성한다.
  //DB 통신과 관련된 것을 내부적으로 처리해주는 객체로, injection 받아서 사용한다.
  private final EntityManager em;

  public JpaMemberRepository(EntityManager em) {
    this.em = em;
  }

  public Member save(Member member) {
    //persist : 영속하다, 지속하다 등의 의미로, insert해서 저장하고, member에 id값을 넣어준다.
    em.persist(member);
    return member;
  }

  public Optional<Member> findById(Long id) {
    //조회할 타입, 식별자(pk)를 parameter로 넣으면 조회 후 반환한다.
    //pk기반으로 단건에 대한 처리는 sql을 작성하지 않고 처리할 수 있다. 그외에는 jpql작성
    Member member = em.find(Member.class, id);
    return Optional.ofNullable(member);
  }

  public List<Member> findAll() {
    //jpql 사용 : 테이블(* 또는 컬럼명)을 대상으로 쿼리를 보내는 것이 아니라, entity(Member m)를 대상으로 쿼리 실행
    return em.createQuery("select m from Member m", Member.class)
        .getResultList();
  }

  public Optional<Member> findByName(String name) {
    List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
        .setParameter("name", name)
        .getResultList();
    return result.stream().findAny();
  }
}

