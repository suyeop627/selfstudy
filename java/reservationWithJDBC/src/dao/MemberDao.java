package dao;

import dto.MemberDto;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MemberDao {
  private String drv = "com.mysql.cj.jdbc.Driver";
  private String url = "jdbc:mysql://localhost:3306/reservationdb";
  private String user = "reservation";
  private String pass = "0000";

  private Connection conn;
  private PreparedStatement pstmt;
  private ResultSet rs;


  public MemberDao() {
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

  public MemberDto checkID(String inputID) {
    MemberDto logInUser = null;
    List<MemberDto> mList = null;
    String query = "SELECT * FROM member";
    try {
      conn = DriverManager.getConnection(url, user, pass);
      pstmt = conn.prepareStatement(query);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        if (mList == null) {
          mList = new ArrayList<>();
        }
        MemberDto data = new MemberDto();
        data.setM_id(rs.getString(1));
        data.setM_pwd(rs.getString(2));
        data.setM_name(rs.getString(3));
        data.setM_phone(rs.getString(4));
        data.setM_birth(rs.getString(5));
        data.setM_account(rs.getString(6));

        mList.add(data);
      }
      for (MemberDto m : mList) {
        if (m.getM_id().equals(inputID)) {
          logInUser = m;
        }
      }
    } catch (SQLException e) {
      logInUser = null;
    } finally {
      close();
    }
    return logInUser;
  }

  public int inputRegiData(MemberDto newMData) {
    int result = 0;

    String query = "INSERT INTO member VALUE(?,?,?,?,?,? )";

    try{
      conn = DriverManager.getConnection(url, user,pass);
      pstmt = conn.prepareStatement(query);


      pstmt.setString(1, newMData.getM_id());
      pstmt.setString(2, newMData.getM_pwd());
      pstmt.setString(3, newMData.getM_name());
      pstmt.setString(4, newMData.getM_phone());
      pstmt.setDate(5, Date.valueOf(newMData.getM_birth()));
      pstmt.setString(6, newMData.getM_account());

      result = pstmt.executeUpdate();

    }catch(SQLException e){
      //e.printStackTrace();
      result = 0;
    }finally {
      close();
    }

    return result;
  }

  public int deleteUser(MemberDto logInUser) {
    int result = 0;

    String query = "DELETE FROM member WHERE m_id = ?";

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




