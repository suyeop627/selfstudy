package dao;

import dto.BookingDto;
import dto.MemberDto;
import dto.ScheduleDto;
import dto.TicketDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDao {

  private String drv = "com.mysql.cj.jdbc.Driver";
  private String url = "jdbc:mysql://localhost:3306/reservationdb";
  private String user = "reservation";
  private String pass = "0000";

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;


  public BookingDao() {
    try {
      Class.forName(drv);
    } catch (ClassNotFoundException e) {
      System.out.println("드라이버 로드 실패");
    }
  }

  public void close() {
    try {
      if (rs != null) rs.close();
      if (pstmt != null) pstmt.close();
      if (conn != null) conn.close();
    } catch (SQLException e) {
    }
  }

  public List<BookingDto> selectAllBookedData(ScheduleDto sData) {
    String date = sData.getS_date();
    String start = sData.getS_start();
    int pNo = sData.getP_no();

    List<BookingDto> bkedList = null;

    String query = "SELECT booking.b_no, booking.m_id, ticket.t_no, ticket.s_no, ticket.p_no, booking.b_seat, schedule.s_date, schedule.s_start, schedule.s_end, schedule.s_hall,schedule.s_seats " +
      "FROM ticket left JOIN booking " +
      "on booking.b_no = ticket.b_no " +
      "left JOIN schedule " +
      "on ticket.s_no = schedule.s_no " +
      "where schedule.p_no=? and s_date =? and schedule.s_start  = ?";
    try {

      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, pNo);
      pstmt.setString(2, date);
      pstmt.setString(3, start);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        if (bkedList == null) {
          bkedList = new ArrayList<>();
        }
        BookingDto data = new BookingDto();
        data.setB_no(rs.getInt(1));
        data.setM_id(rs.getString(2));
        data.setT_no(rs.getInt(3));
        data.setS_no(rs.getInt(4));
        data.setP_no(rs.getInt(5));
        data.setB_seat(rs.getString(6));
        data.setS_date(rs.getString(7));
        data.setS_start(rs.getString(8));
        data.setS_end(rs.getString(9));
        data.setS_hall(rs.getString(10));
        data.setS_seats(rs.getInt(11));

        bkedList.add(data);

      }
    } catch (SQLException e) {
      bkedList = null;
    } finally {
      close();
    }
    return bkedList;
  }

  public List<MemberDto> selectAllBookedData(MemberDto mDataToPrint) {
    String date = mDataToPrint.getS_date();
    String start = mDataToPrint.getS_start();
    int pNo = mDataToPrint.getP_no();

    List<MemberDto> bkedList = null;

    String query = "SELECT booking.b_no, booking.m_id, ticket.t_no, ticket.s_no, " +
    "ticket.p_no, booking.b_seat, schedule.s_date, schedule.s_start, schedule.s_end, schedule.s_hall,schedule.s_seats " +
    "FROM booking left JOIN ticket "+
    "on booking.b_no = ticket.b_no "+
    "left JOIN schedule "+
    "on ticket.s_no = schedule.s_no "+
    "where schedule.p_no=? and  s_date =? and schedule.s_start  = ? ";
    try {

      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      pstmt.setInt(1, pNo);
      pstmt.setString(2, date);
      pstmt.setString(3, start);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        if (bkedList == null) {
          bkedList = new ArrayList<>();
        }
        MemberDto mData = new MemberDto();
        mData.setB_no(rs.getInt(1));
        mData.setM_id(rs.getString(2));
        mData.setT_no(rs.getInt(3));
        mData.setS_no(rs.getInt(4));
        mData.setP_no(rs.getInt(5));
        mData.setB_seat(rs.getString(6));
        mData.setS_date(rs.getString(7));
        mData.setS_start(rs.getString(8));
        mData.setS_end(rs.getString(9));
        mData.setS_hall(rs.getString(10));
        mData.setS_seats(rs.getInt(11));

        bkedList.add(mData);
      }
    } catch (SQLException e) {
      bkedList = null;
    } finally {
      close();
    }
    return bkedList;
  }
  public int insertBData(String userName, String chosenSeat) {

      int result = 0;

      String tQuery = "INSERT INTO booking VALUES(null,?, ?)"; //sno pno

      try {
        conn = DriverManager.getConnection(url, user,pass);
        pstmt = conn.prepareStatement(tQuery);
        //쿼리문의 '?'부분 채우기
        pstmt.setString(1, userName);
        pstmt.setString(2, chosenSeat);


        result = pstmt.executeUpdate();

      } catch (SQLException e) {
        result = 0;
      }finally {
        close();
      }

      return result;

    }

  public BookingDto selectBookedData(String chosenSeat) {


    BookingDto userReservation = null;

    String query = "SELECT * FROM booking WHERE b_seat = ?";
    try {

      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, chosenSeat);

      rs = pstmt.executeQuery();

      if(rs.next()){
        userReservation = new BookingDto();
        userReservation.setB_no(rs.getInt(1));
        userReservation.setM_id(rs.getString(2));
        userReservation.setB_seat(rs.getString(3));

      }

    } catch (SQLException e) {
      userReservation = null;
    } finally {
      close();
    }
    return userReservation;

  }

  public    List<MemberDto> selectUserBooking(MemberDto logInUser) {

    List<MemberDto> userBdataList = null;


    String query ="select member.m_id, member.m_name, performance.p_name, ticket.t_no, ticket.s_no, ticket.p_no, booking.b_no, booking.b_seat," +
      "schedule.s_date, schedule.s_start, schedule.s_end, schedule.s_hall, schedule.s_seats "+
    "from member left join booking "+
    "on member.m_id = booking.m_id " +
    "left join ticket "+
    "on booking.b_no = ticket.t_no "+
    "left join schedule "+
    "on schedule.s_no = ticket.s_no "+
      "left join performance "+
    "on ticket.p_no = performance.p_no " +
    "where member.m_id = ? ";
    try {

      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, logInUser.getM_id());
      rs = pstmt.executeQuery();

      while (rs.next()) {
        if (userBdataList == null) {
          userBdataList = new ArrayList<>();
        }
        MemberDto mData = new MemberDto();

        mData.setM_id(rs.getString(1));
        mData.setM_name(rs.getString(2));
        mData.setP_name(rs.getString(3));
        mData.setT_no(rs.getInt(4));
        mData.setS_no(rs.getInt(5));
        mData.setP_no(rs.getInt(6));
        mData.setB_no(rs.getInt(7));
        mData.setB_seat(rs.getString(8));
        mData.setS_date(rs.getString(9));
        mData.setS_start(rs.getString(10));
        mData.setS_end(rs.getString(11));
        mData.setS_hall(rs.getString(12));
       mData.setS_seats(rs.getInt(13));

        userBdataList.add(mData);

      }

    } catch (SQLException e) {
      userBdataList = null;
    } finally {
      close();
    }
    return userBdataList;


  }

  public int deleteBooking(MemberDto logInUser) {

    int result = 0;

    String query = "DELETE FROM booking WHERE m_id = ?";

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


