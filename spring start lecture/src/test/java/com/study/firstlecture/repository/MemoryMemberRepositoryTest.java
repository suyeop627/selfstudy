package com.study.firstlecture.repository;

import com.study.firstlecture.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;  //static import Assertions


//다른데서 쓸 게 아니니까, public으로 안해도 상관없음.
class MemoryMemberRepositoryTest {//클래스 명 뒤에 Test를 붙이는 게 관례임.
  MemoryMemberRepository repository = new MemoryMemberRepository();


  //findAll()이 먼저 실행되고, findByName이 실행될 경우, findAll()에서 저장한 다른 객체를
  //findByName 이 불러와서 테스트 결과는 실패로나온다.
  // =>테스트가 끝날때마다 repository를 비워주는 메서드가 필요함.(@AfterEach)


  // => 참고로, 순서에 영향받는 테스트는 하면 안됨

  @AfterEach //각 메서드가 실행되고나서 매번 실행되는 일종의 콜벡 메서드 정의
  public void afterEach() {
    repository.clearStore();
  }

  @Test //해당 메서드만 실행 가능해진다.
  public void save() {
    Member member = new Member();
    member.setName("spring");

    repository.save(member);
    Member result = repository.findById(member.getId()).get();  //optional에서 값을 꺼낼 땐 get()으로 꺼낼 수 있다. 좋은 방법은 아니지만, 테스트에서 편하게 사용
    System.out.println("result = " + (result == member)); //soutv
    // Assertions.assertEquals(member, null);//import org.junit.jupiter.api.Assertions;
    //Assertions.assertEquals(expected, actual);


    assertThat(member).isEqualTo(result); //static import
    //import static org.assertj.core.api.Assertions.*;

  }


  @Test
  public void findByName() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member(); //shift f6 : rename
    member2.setName("spring2");
    repository.save(member2);

    //Optional<Member> spring1 = repository.findByName("spring1");    .get()을 붙이면, optional객체 내부의 값을 추출한다.
    Member result = repository.findByName("spring1").get(); //Ctrl + Alt + V : extract variables short cut

    assertThat(result).isEqualTo(member1);
  }


  @Test
  public void findAll() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    List<Member> result = repository.findAll();

    assertThat(result.size()).isEqualTo(2);
  }



}
