package dao;

import dto.MemberDto;
import dto.PerformanceDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerformanceDao {
  private String drv = "com.mysql.cj.jdbc.Driver";
  private String url = "jdbc:mysql://localhost:3306/reservationdb";
  private String user = "reservation";
  private String pass = "0000";

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;


  public PerformanceDao() {
    try {
      Class.forName(drv);
    } catch (ClassNotFoundException e) {
      System.out.println("드라이버 로드 실패");
    }
  }

  private void close() {
    try {
      if (rs != null) rs.close();
      if (pstmt != null) pstmt.close();
      if (conn != null) conn.close();
    } catch (SQLException e) {

    }
  }


  public List<PerformanceDto> selectPList() {
    List<PerformanceDto> pList = null;
    String query = "SELECT * FROM performance";
    try {
      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        if (pList == null) {
          pList = new ArrayList<>();
        }
        PerformanceDto data = new PerformanceDto();
        data.setP_no(rs.getInt(1));
        data.setP_name(rs.getString(2));
        data.setP_age(rs.getInt(3));
        data.setP_actor(rs.getString(4));
        data.setP_rtime(rs.getString(5));
        data.setP_story(rs.getString(6));

        pList.add(data);

      }
    } catch (SQLException e) {
      pList = null;
    } finally {
      close();
    }
    return pList;
  }


  public PerformanceDto selectPData(int menu) {
    PerformanceDto data = null;
    String query = "select p_name, p_age  from performance where p_no =?";
    try {
      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, menu);
      rs = pstmt.executeQuery();

      if (rs.next()) {
        data = new PerformanceDto();
        data.setP_name(rs.getString(1));
        data.setP_age(rs.getInt(2));
      }
    } catch (SQLException e) {
      data = null;
    } finally {
      close();
    }
    return data;
  }
}//class end
