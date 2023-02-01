package dao;

import dto.BookingDto;
import dto.MemberDto;
import dto.ScheduleDto;

import java.sql.*;

public class TicketDao {

  private String drv = "com.mysql.cj.jdbc.Driver";
  private String url = "jdbc:mysql://localhost:3306/reservationdb";
  private String user = "reservation";
  private String pass = "0000";

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;

  public TicketDao(){
    try {
      Class.forName(drv);
    } catch (ClassNotFoundException e) {
      //
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

  public int insertTData(ScheduleDto sData, BookingDto userReservation) {

    int result = 0;

    String tQuery = "INSERT INTO ticket VALUES(null,?, ?,?,?)";

    try {
      conn = DriverManager.getConnection(url, user,pass);
      pstmt = conn.prepareStatement(tQuery);
      //쿼리문의 '?'부분 채우기
      pstmt.setInt(1, sData.getS_no());
      pstmt.setInt(2, sData.getP_no());
      pstmt.setInt(3, userReservation.getB_no());
      pstmt.setString(4, userReservation.getM_id());

      result = pstmt.executeUpdate();

    } catch (SQLException e) {
      result = 0;
    } finally {
      close();
    }
    return result;
  }

  public int deleteTicket(MemberDto logInUser) {
    int result = 0;

    String query = "DELETE FROM ticket WHERE m_id = ?";

    try{
      conn = DriverManager.getConnection(url, user,pass);
      pstmt = conn.prepareStatement(query);


      pstmt.setString(1, logInUser.getM_id());


      result = pstmt.executeUpdate();

    }catch(SQLException e){
      //e.printStackTrace();
      result = 0;
    }finally {
      close();
    }

    return result;

  }
}
