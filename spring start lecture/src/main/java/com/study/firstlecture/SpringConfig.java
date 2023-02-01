package com.study.firstlecture;

import com.study.firstlecture.AOP.TimeTraceAop;
import com.study.firstlecture.repository.*;
import com.study.firstlecture.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration //스프링이 @Configuration 어노테이션을 읽고, 아래의 로직을 컨테이너에 등록한다.
public class SpringConfig {


  /*
  //, jdbc template
    private final DataSource dataSource;
    //configuration 한 것도 스프링 빈으로 관리하기 때문에, application.properties에 작성한 걸 가져와서 datasource에 주입해서 쓸 수 있다.

    @Autowired
    public SpringConfig(DataSource dataSource) {
      this.dataSource = dataSource;
    }
  */
  /*
 // jpa
  //@PersistenceContext 어노테이션으로 주입하는게 정석이지만, 작성하지 않아도 스프링이 주입해준다.
  private EntityManager em;
  @Autowired
  public SpringConfig(EntityManager em){
    this.em = em;
  }
*/
  //spring data jpa
  private final MemberRepository memberRepository;
  //스프링이 jpaRepository를 상속받는 인터페이스 SpringDataJpaMemberRepository의 구현체를 자동으로 만들어서 등록해준다.


  public SpringConfig(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }


  @Bean
  public MemberService memberService() {
   // return new MemberService(memberRepository());
    //MemberService는 MemberRepository와 의존관계를 설정했으므로,
    // MemberService가 생성될때 스프링빈에 등록된 MemberRepository와 연결되도록 함

    //spring data jpa
    return new MemberService(memberRepository);
  }




  /*스프링 데이터 jpa에서는 repository를 따로 등록하지 않아도 된다.
  @Bean
  public MemberRepository memberRepository() {//인터페이스
    //return new MemoryMemberRepository();//메모리에 저장하는 구현체
    //return new JdbcMemberRepository(dataSource); //H2 DB+순수 Jdbc를 사용하는 구현체로 변경
    //return new JdbcTemplateMemberRepository(dataSource); //H2 DB+jdbcTemplate를 사용하는 구현체로 변경
    //return new JpaMemberRepository(em); //jpa를 사용하는 구현체로 변경

  }
*/

  @Bean
  public TimeTraceAop timeTraceAop(){
    return new TimeTraceAop();
  }

}
