package controller;

import dto.*;
import service.DataService;
import view.DataView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReservationController {
  DataView dView = new DataView();
  DataService dServ = new DataService();

  public void run() {
    int menu = -1;

    while (true) {
      menu = dView.startMenu();
      if (menu == 0) {
        dView.printMsg("프로그램 종료.");
        break;
      }
      switch (menu) {
        case 1://로그인
          MemberDto logInUser = logIn();
          if (logInUser == null) {
            break;
          } else {
            onLogin(logInUser);
          }
          break;
        case 2://회원가입
          registration();
          break;
        case 3://회원탈퇴

          deleteUser();


          break;
        default:
          dView.printMsg("0~3까지의 숫자를 입력하세요.");
          break;
      }//switch end
    }//while end
  }//run end

  private void deleteUser() {
    String str = null;
    MemberDto logInUser = logIn();
    if (logInUser == null) {
      dView.printMsg("입력하신 정보와 일치하는 계정이 없습니다.");
    } else {
      dView.printMsg("계정 삭제를 진행합니다.");


      //예약내역 조회
      List<MemberDto> allUserBooking = dServ.getUserBData(logInUser);


      //예약내역 삭제할지 물어보고
      int agreeToUserDelete = dView.printBeforDelete(allUserBooking);


      if (agreeToUserDelete == 1) {
        //삭제
        String result = dServ.transferDataToDelete(logInUser);
        dView.printMsg(result);
      }


    }


  }

  private void registration() {
    MemberDto newMData = dView.regiDataInput();
    String msg = dServ.transferRData(newMData);
    dView.printMsg(msg);
  }

  private MemberDto logIn() {
    String inputID = dView.inputId();
    MemberDto logInUser = dServ.checkID(inputID);
    if (logInUser == null) {
      dView.printMsg("일치하는 아이디가 없습니다.");
      return null;
    } else {
      String inputPwd = dView.inputPwd();
      if (inputPwd.equals(logInUser.getM_pwd())) {
        dView.printMsg("로그인 되었습니다.");
      } else {
        dView.printMsg("비밀번호가 틀렸습니다.");
        return null;
      }
    }
    return logInUser;
  }

  private void onLogin(MemberDto logInUser) {
    int menu = -1;

    while (true) {
      menu = dView.loggedMenu(logInUser);
      if (menu == 0) {
        dView.printMsg("이전 화면으로 돌아갑니다.");
        break;
      }
      switch (menu) {
        case 1://1.상영중 공연 확인
          showPerformance();
          break;
        case 2://2.예매하기
          bookPerformance(logInUser);
          break;
        case 3://3.예매확인
          checkBooking(logInUser);
          break;
        case 4: //4.예매취소
          cancelBooking(logInUser);
          break;
        default:
          dView.printMsg("0~4까지의 숫자를 입력하세요.");
          break;
      }//switch end
    }//while end
  }

  private void cancelBooking(MemberDto logInUser) {
    //예약내역 삭제
    //티켓 취소
    String resultOfTdelete = dServ.deleteTicket(logInUser);
    dView.printMsg(resultOfTdelete);
    //예매 삭제
    String resultOfBdelete = dServ.deleteBooking(logInUser);
    dView.printMsg(resultOfBdelete);


  }

  private void checkBooking(MemberDto logInUser) {
    //유저 아이디로 예매된 거 조회
    try {
      List<MemberDto> allUserBooking = dServ.getUserBData(logInUser);
      MemberDto mDataToPrint = dView.printBookedList(allUserBooking);

      List<MemberDto> bookedList = dServ.getAllBookedData(mDataToPrint);
      dView.printAvailSeats(bookedList, mDataToPrint, allUserBooking);
    } catch (NullPointerException npe) {
      dView.printMsg("예매 내역이 없습니다.");

    }

  }


  public void bookPerformance(MemberDto logInUser) {


    //공연확인(이름출력)
    List<PerformanceDto> pList = dServ.getPList();
    dView.printPerformanceName(pList);
    int menu = dView.inputMenuNum();//볼 연극의 p_no 저장

    //(이름 선택 출력
    PerformanceDto pDto = dServ.getPDto(menu);
    dView.chosenPName(pDto);

    //(날짜 선택)
    List<ScheduleDto> sListDate = dServ.getBookingDate(menu);
    ScheduleDto sData = dView.printPerformanceDate(sListDate);

    //예매된 좌석 출력 + 예매할 입력받기
    List<BookingDto> bookedList = dServ.getAllBookedData(sData);
    String chosenSeat = dView.printAvailSeats(bookedList, sData);

    //DB 입력

    //booking 데이터 입력
    String bMsg = dServ.insertBooking(logInUser, chosenSeat);
    dView.printMsg(bMsg);

    //방금 예매한 booking데이터 가져오기
    BookingDto userReservation = dServ.getBookedData(chosenSeat);

    //ticket 데이터 입력
    String tMsg = dServ.insertTicket(sData, userReservation);
    dView.printMsg(tMsg);
  }

  private void showPerformance() {
    List<PerformanceDto> pList = dServ.getPList();
    dView.printPerformance(pList);
    dView.goBack();
  }
}//class end
