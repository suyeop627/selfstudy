package com.study.firstlecture.repository;

import com.study.firstlecture.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository {


  private final DataSource dataSource; //dataSource를 스프링에서 주입받아야 한다. application.properties에 작성한거로, 스프링 부트가 dataSource(접속정보)를 주입하도록 한다.->databaseConnection을 얻기 위한 절차.


  public JdbcMemberRepository(DataSource dataSource) {
    this.dataSource = dataSource;
  }


  @Override
  public Member save(Member member) {
    String sql = "insert into member(name) values(?)";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null; //결과를 받

    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      //Statement.RETURN_GENERATED_KEYS) insert하고 id값을 반환하도록 함
      pstmt.setString(1, member.getName());
      pstmt.executeUpdate();//DB에 쿼리가 전송됨
      rs = pstmt.getGeneratedKeys();//Statement.RETURN_GENERATED_KEYS)와 매칭해서, 키값을 반환한다.

      if (rs.next()) {
        member.setId(rs.getLong(1));
      } else {
        throw new SQLException("id 조회 실패");
      }

      return member;
    } catch (Exception e) { //connection은 사용할때만 연결되고, 필요한 처리가 끝나면 닫아야한다.
      throw new IllegalStateException(e);
    } finally { //connection은 사용할때만 연결되고, 필요한 처리가 끝나면 닫아야한다.
      close(conn, pstmt, rs);
    }
  }


  @Override
  public Optional<Member> findById(Long id) {
    String sql = "select * from member where id = ?";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setLong(1, id);
      rs = pstmt.executeQuery();

      if(rs.next()) {
        Member member = new Member();
        member.setId(rs.getLong("id"));
        member.setName(rs.getString("name"));

        return Optional.of(member);  //값이 없을 경우도 있으므로, Optional 객체로 감싸서 봔환
      } else {
        return Optional.empty();
      }
    } catch (Exception e) {
      throw new IllegalStateException(e);
    } finally {
      close(conn, pstmt, rs);
    }
  }
  @Override
  public List<Member> findAll() {
    String sql = "select * from member";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      List<Member> members = new ArrayList<>();

      while(rs.next()) {
        Member member = new Member();
        member.setId(rs.getLong("id"));
        member.setName(rs.getString("name"));
        members.add(member);
      }

      return members;
    } catch (Exception e) {
      throw new IllegalStateException(e);
    } finally {
      close(conn, pstmt, rs);
    }
  }
  @Override
  public Optional<Member> findByName(String name) {
    String sql = "select * from member where name = ?";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      conn = getConnection();
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, name);
      rs = pstmt.executeQuery();
      if(rs.next()) {
        Member member = new Member();
        member.setId(rs.getLong("id"));
        member.setName(rs.getString("name"));
        return Optional.of(member);
      }
      return Optional.empty();
    } catch (Exception e) {
      throw new IllegalStateException(e);
    } finally {
      close(conn, pstmt, rs);
    }
  }

  //스프링 프레임워크를 사용할때, DataSourceUtils를 통해서 커넥션을 획득해야 한다.
  private Connection getConnection() {
    return DataSourceUtils.getConnection(dataSource);
  }

  private void close(Connection conn, PreparedStatement pstmt, ResultSet rs)
  {
    try {
      if (rs != null) {
        rs.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (pstmt != null) {
        pstmt.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      if (conn != null) {
        close(conn);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  //커넥션을 닫을떄도, DatasourceUtils를 통해서 release하도록 한다.
  private void close(Connection conn) throws SQLException {
    DataSourceUtils.releaseConnection(conn, dataSource);
  }
}