package com.study.firstlecture.repository;

import com.study.firstlecture.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {

  private final JdbcTemplate jdbcTemplate;

  //@Autowired 생성자가 하나일경우, @Autowired를 생략할 수 있다.
  public JdbcTemplateMemberRepository(DataSource dataSource) {
    jdbcTemplate = new JdbcTemplate(dataSource);
  }

  @Override
  public Member save(Member member) {
    //테이블 명과 pk를 포함한 컬럼을 작성하면 insert문 자동 생성
    SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
    //테이블 명, pk
    jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");
    Map<String, Object> parameters = new HashMap<>();
    //name 컬럼
    parameters.put("name", member.getName());

    //쿼리 실행, 키값 반환
    Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
    member.setId(key.longValue());
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    List<Member> result     //결과가 나오는 것을 rowMapper로 매핑해줘야한다.
        = jdbcTemplate.query("select * from member where id = ? ", memberRowMapper(), id);

    return result.stream().findAny();
  }

  @Override
  public List<Member> findAll() {
    return jdbcTemplate.query("select * from member", memberRowMapper());
  }

  @Override
  public Optional<Member> findByName(String name) {
    List<Member> result = jdbcTemplate.query("select * from member where name = ? ", memberRowMapper(), name);
    return result.stream().findAny();
  }

//람다식 사용
  private RowMapper<Member> memberRowMapper() {
    return (rs, rowNum) -> {
      Member member = new Member();
      member.setId(rs.getLong("id"));
      member.setName(rs.getString("name"));
      return member;
    };
  }
  /*람다식 변환 전 코드

  private RowMapper<Member> memberRowMapper(){
    return new RowMapper<Member>() {
      @Override
      public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        Member member = new Member();
        member.setId(rs.getLong("id"));
        member.setName((rs.getString("name")));
        return member;
      }
    };
  }
  */

}