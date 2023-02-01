//package com.study.firstlecture.domain;
//
//public class Member {
//  private Long id;
//  private String name;
//
//  public Long getId() {
//    return id;
//  }
//
//  public void setId(Long id) {
//    this.id = id;
//  }
//
//  public String getName() {
//    return name;
//  }
//
//  public void setName(String name) {
//    this.name = name;
//  }
//}
//

package com.study.firstlecture.domain;

import javax.persistence.*;

@Entity //@Entity 어노테이션으로, 관계형 데이터베이스와 객체를 매핑
public class Member {
  @Id   //pk 매핑
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  //값을 직접 넣는 게 아니라, DB에서 값을 생성하는 것을 identity전략이라고 하는데,
  // @GeneratedValue 어노테이션을 사용하면, DB에서 id값을 생성하여 저장한다.
  // (ex, mysql의 auto_increment)
  private Long id;
  // @Column(name = "username") //db의 username 컬럼과 변수 name을 매핑할 수 있다.
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}


