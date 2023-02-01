package com.study.firstlecture.repository;

import com.study.firstlecture.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//구현체
//@Repository
public class MemoryMemberRepository implements MemberRepository {
  private static Map<Long, Member> store = new HashMap<>();
  //실무에서는 동시성 문제가 고려되어, ConcurrentHashMap, AtomicLong을 사용할 것을 고려하지만,
  //예제이므로 Long, HashMap 사용.

  private static long sequence = 0L;  //id(key값)를 생성함.

  @Override
  public Member save(Member member) { //멤버의 이름을 받아옴
    member.setId((++sequence));//id 세팅 순차적으로 증가하는 값을 임의의 id로 부여 -> primary key 로 사용.
    store.put(member.getId(), member);  //key : id / value : member 를 map에 저장
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id)); //store에서 id를 받아서 반환
    //null이 반환될 가능성이 있으면, Optional.ofNullable로 감싸서 반환. -> 이상태로 받으면 클라이언트에서 사용할 수 있음

  }

  @Override
  public Optional<Member> findByName(String name) {
   return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
  }

  @Override
  public List<Member> findAll() {//자바에서 실무할땐 List를 많이 사용함
    return new ArrayList<>(store.values());
  }
  //implements 하고, alt enter -> implement method ->구현할 메서드 선택 가능

  public void clearStore() {
    store.clear(); //.clear() store를 초기화
  }
}
