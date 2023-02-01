package dao;

import dto.ScheduleDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDao {

  private String drv = "com.mysql.cj.jdbc.Driver";
  private String url = "jdbc:mysql://localhost:3306/reservationdb";
  private String user = "reservation";
  private String pass = "0000";

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;


  public ScheduleDao() {
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


  public List<ScheduleDto> selectSDate(int menu) {

    List<ScheduleDto> sList = null;
    String query = "select distinct *  from schedule where p_no = (select p_no from performance where p_no =? )";

    try {
      conn = DriverManager.getConnection(url, user, pass);

      pstmt = conn.prepareStatement(query);

      pstmt.setInt(1, menu);

      rs = pstmt.executeQuery();

      while (rs.next()) {
        if (sList == null) {
          sList = new ArrayList<>();
        }
        ScheduleDto data = new ScheduleDto();
        data.setS_no(rs.getInt(1));
        data.setP_no(rs.getInt(2));
        data.setS_date(rs.getString(3));
        data.setS_start(rs.getString(4));
        data.setS_end(rs.getString(5));
        data.setS_hall(rs.getString(6));
        data.setS_seats(rs.getInt(7));

        sList.add(data);
      }
    } catch (SQLException e) {
      sList = null;
    } finally {
      close();
    }
    return sList;
  }
}

