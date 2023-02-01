package service;

import dao.*;
import dto.*;

import java.util.List;

public class DataService {

  PerformanceDao pDao = new PerformanceDao();
  ScheduleDao sDao = new ScheduleDao();
  MemberDao mDao = new MemberDao();
  BookingDao bDao = new BookingDao();
  TicketDao tDao = new TicketDao();

  public List<PerformanceDto> getPList() {

    List<PerformanceDto> pList = pDao.selectPList();

    return pList;
  }

  public List<ScheduleDto> getBookingDate(int menu) {//메뉴 받아서 해당 공연의 시간 조회
    List<ScheduleDto> sList = sDao.selectSDate(menu);
    return sList;
  }


  public MemberDto checkID(String inputID) {
    MemberDto userData = mDao.checkID(inputID);

    return userData;
  }

  public PerformanceDto getPDto(int menu) {
    PerformanceDto chosenPData = pDao.selectPData(menu);
    return chosenPData;
  }

  public List<BookingDto> getAllBookedData(ScheduleDto sData) {
    List<BookingDto> bkedList = bDao.selectAllBookedData(sData);
    return bkedList;
  }
  public List<MemberDto> getAllBookedData(MemberDto mDataToPrint) {
    List<MemberDto> bkedList = bDao.selectAllBookedData(mDataToPrint);
    return bkedList;
  }
  public String insertTicket(ScheduleDto sData, BookingDto userReservation) {
    String msg = null;

    int Tres =tDao.insertTData(sData, userReservation);

    if(Tres ==0) {//삽입 실패.
      msg = "선택하신 공연의 티켓 발행에 실패했습니다.";
    }
    else{
      msg = "선택하신 공연의 티켓 발행되었습니다. \n날짜 : " + sData.getS_date() + "\n시간 : " + sData.getS_start().substring(0,5);
    }
  return msg;
  }


  public String insertBooking(MemberDto logInUser, String chosenSeat) {
    String msg = null;

    int Tres =bDao.insertBData(logInUser.getM_id(), chosenSeat);

    if(Tres ==0) {//삽입 실패.
      msg = "예매에 실패했습니다.";
    }
    else{
      msg = "예매가 완료되었습니다. \n예매내역 확인은 '마이페이지'의 '3.예매확인'에서 하실 수 있습니다.";
    }
    return msg;
  }


  public BookingDto getBookedData(String chosenSeat) {
    BookingDto userReservation = bDao.selectBookedData( chosenSeat);
    return userReservation;
  }

  public List<MemberDto> getUserBData(MemberDto logInUser) {
    List<MemberDto> userBdataList = bDao.selectUserBooking(logInUser);
     return userBdataList;
  }

  public String transferRData(MemberDto newMData) {
    String msg = null;

    int res = mDao.inputRegiData(newMData);

    if( res != 0){
      msg = "'" + newMData.getM_name()+"'님의 회원가입이 완료됐습니다.";

    }
    else{
      msg = "회원가입에 실패 했습니다.";

    }
    return msg;
  }

  public String transferDataToDelete(MemberDto logInUser) {
    String str =null;
    int result = mDao.deleteUser(logInUser);
    if(result!=0){
      str = "삭제 완료.";
    }else{
      str = "삭제 실패";
    }
    return str;
  }

  public String deleteTicket(MemberDto logInUser) {
    String str =null;
    int result =   tDao.deleteTicket(logInUser);
    if(result!=0){
      str = "티켓 발행이 취소됐습니다.";
    }else{
      str = "티켓 발행이 취소되지 않았습니다.";
    }
    return str;

  }

  public String deleteBooking(MemberDto logInUser) {
    String str =null;
    int result =   bDao.deleteBooking(logInUser);
    if(result!=0){
      str = "예매가 취소됐습니다.";
    }else{
      str = "예매가 취소되지 않았습니다.";
    }
    return str;

  }
}


