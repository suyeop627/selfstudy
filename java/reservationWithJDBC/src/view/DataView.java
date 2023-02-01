package view;


import dto.MemberDto;
import dto.PerformanceDto;
import dto.ScheduleDto;
import dto.BookingDto;

import java.util.ArrayList;
import java.util.List;

public class DataView {
  private InOutClass ioc = new InOutClass();


  public int startMenu() {
    ioc.println("=========================================================");
    ioc.println("\t\t『공연 예매 프로그램』");
    ioc.println("=========================================================");
    ioc.println("\t\t1. 로그인");
    ioc.println("\t\t2. 회원가입");
    ioc.println("\t\t3. 계정삭제");
    ioc.println("\t\t0. 종료");
    ioc.println("---------------------------------------------------------");
    return ioc.inputNumber("입력>");
  }

  public void printMsg(String str) {
    ioc.println(str);
  }


  public int loggedMenu(MemberDto logInUser) {
    ioc.println("---------------------------------------------------------");
    ioc.println("\t「" + logInUser.getM_id() + "(" + logInUser.getM_name() + ")" + "님의 마이페이지」");
    ioc.println("---------------------------------------------------------");
    ioc.println("\t\t1. 공연 확인");
    ioc.println("\t\t2. 예매하기");
    ioc.println("\t\t3. 예매확인");
    ioc.println("\t\t4. 예매취소");
    ioc.println("\t\t0. 돌아가기");
    ioc.println("---------------------------------------------------------");
    return ioc.inputNumber("입력>");
  }

  public void printPerformanceName(List<PerformanceDto> pList) {
    if (pList == null) {
      ioc.println("데이터가 없습니다.");
    }
    for (PerformanceDto p : pList) {
      ioc.println(p.getP_no() + ". " + p.getP_name());
    }

  }

  public int inputMenuNum() {
    ioc.println("원하시는 메뉴 번호를 입력해주세요.");
    return ioc.inputNumber("입력>");
  }

  public void printPerformance(List<PerformanceDto> pList) {
    ioc.println("1. 공연 확인");
    ioc.println("---------------------------------------------------------");

    if (pList == null) {
      ioc.println("데이터가 없습니다.");
    }
    for (PerformanceDto p : pList) {
      ioc.println("\t\t제목 : " + p.getP_name());
      ioc.println("관람등급 : " + p.getP_age());
      ioc.println("배우진 : " + p.getP_actor());
      ioc.println("러닝타임 : " + p.getP_rtime().substring(0, 2) + "시간 " + p.getP_rtime().substring(3, 5) + "분");
      ioc.println("줄거리 : " + p.getP_story());
      ioc.println("---------------------------------------------------------");
    }
  }

  public void goBack() {
    ioc.println("0.돌아가기");
    while (true) {
      ioc.println("---------------------------------------------------------");
      int menu = ioc.inputNumber("입력>");

      if (menu == 0) {
        printMsg("이전메뉴로 돌아갑니다.");
        break;
      } else {
        printMsg("이전메뉴로 돌아가시려면 '0'번을 눌러주세요.");
        ioc.println("---------------------------------------------------------");

      }
    }
  }

  public ScheduleDto printPerformanceDate(List<ScheduleDto> sList) {
    ScheduleDto returnData = null;
    int menu = 0;
    if (sList == null) {
      ioc.println("데이터가 없습니다.");
      ioc.println("---------------------------------------------------------");
      return returnData;
    }
    for (ScheduleDto s : sList) {
      ioc.println("|" + (sList.indexOf(s) + 1) + ". " + s.getS_date() + "  | " + s.getS_start().substring(0, 5) + "~" + s.getS_end().substring(0, 5) + " |");
      ioc.println("---------------------------------------------------------");
    }
    ioc.println("원하시는 시간의 번호를 입력해주세요");
    ioc.println("---------------------------------------------------------");
    menu = ioc.inputNumber("입력>");
    ioc.println("---------------------------------------------------------");
    returnData = sList.get(menu - 1);
    return returnData;
  }


  public String inputId() {
    ioc.println("아이디를 입력해주세요");
    return ioc.inputString("ID : ");
  }

  public String inputPwd() {
    return ioc.inputString("PWD : ");
  }

  public void chosenPName(PerformanceDto pDto) {
    ioc.println("---------------------------------------------------------");
    ioc.println("<" + pDto.getP_name() + "(" + pDto.getP_age() + "세 관람가)>");
    ioc.println("---------------------------------------------------------");
  }


  public String printAvailSeats(List<BookingDto> bkdList, ScheduleDto sData) {
    printAllSeats(bkdList, sData);
    printMsg("원하시는 좌석의 번호를 입력해주세요");

    return ioc.inputString("입력>");

  }

  public void printAvailSeats(List<MemberDto> bkdList, MemberDto mDataToPrint, List<MemberDto> allUserBooking) {
    printAllSeats(bkdList, mDataToPrint, allUserBooking);

    goBack();

  }

  private void printAllSeats(List<BookingDto> bkdList, ScheduleDto sData) {
    ArrayList<String> bookedSNo = new ArrayList<>();
    int allseats = sData.getS_seats();
    if (!(bkdList == null)) {
      for (BookingDto b : bkdList) {
        bookedSNo.add(b.getB_seat());
      }
      ioc.print("⬜ : 예매 가능 좌석\n🟥 : 예매 불가능 좌석\n\n");
      //출력
      for (int i = 1; i <= (allseats / 10); i++) {
        for (int j = 1; j <= 10; j++) {
          String seatNo = addZeroToSeatNo(i, j);

          if ((bookedSNo.contains((i * 10 + j - 10) + ""))) {
            switch (j) {
              case 5:
                ioc.print("[" + seatNo + "🟥]\t\t");
                break;
              case 10:
                ioc.println("[" + seatNo + "🟥]");
                break;
              default:
                ioc.print("[" + seatNo + "🟥]");
            }
          } else {
            switch (j) {
              case 5:
                ioc.print("[" + seatNo + "⬜]\t\t");
                break;
              case 10:
                ioc.println("[" + seatNo + "⬜]");
                break;
              default:
                ioc.print("[" + seatNo + "⬜]");
                break;
            }
          }
        }
      }
    } else {
      for (int i = 1; i <= (allseats / 10); i++) {
        for (int j = 1; j <= 10; j++) {
          String seatNo = addZeroToSeatNo(i, j);
          switch (j) {
            case 5:
              ioc.print("[" + seatNo + "⬜]\t\t");
              break;
            case 10:
              ioc.println("[" + seatNo + "⬜]");
              break;
            default:
              ioc.print("[" + seatNo + "⬜]");
              break;
          }
        }
      }
    }
  }


  private void printAllSeats(List<MemberDto> bkdList, MemberDto mDataToPrint, List<MemberDto> allUserBooking) {
    ArrayList<String> bookedSNo = new ArrayList<>();
    ArrayList<String> userSNO = new ArrayList<>();
    int allseats = mDataToPrint.getS_seats();
    //이미 좌석 번호 배열에 저장
    for (MemberDto m : bkdList) {
      bookedSNo.add(m.getB_seat());
    }
    for (MemberDto m : allUserBooking) {
      if (mDataToPrint.getB_no() == m.getB_no()) {
        userSNO.add(m.getB_seat());
      }
    }
    ioc.print("✅ : '" + mDataToPrint.getM_name() + "'님의 좌석\n⬜ : 예매 가능 좌석\n🟥 : 예매 불가능 좌석\n\n");
    //출력
    for (int i = 1; i <= (allseats / 10); i++) {
      for (int j = 1; j <= 10; j++) {

        String seatNo = addZeroToSeatNo(i, j);
        if ((userSNO.contains((i * 10 + j - 10) + ""))) {
          switch (j) {
            case 5:
              ioc.print("[" + seatNo + "✅]\t\t");
              break;
            case 10:
              ioc.println("[" + seatNo + "✅]");
              break;
            default:
              ioc.print("[" + seatNo + "✅]");
          }
        } else if ((bookedSNo.contains((i * 10 + j - 10) + ""))) {
          switch (j) {
            case 5:
              ioc.print("[" + seatNo + "🟥]\t\t");
              break;
            case 10:
              ioc.println("[" + seatNo + "🟥]");
              break;
            default:
              ioc.print("[" + seatNo + "🟥]");
          }
        } else {
          switch (j) {
            case 5:
              ioc.print("[" + seatNo + "⬜]\t\t");
              break;
            case 10:
              ioc.println("[" + seatNo + "⬜]");
              break;
            default:
              ioc.print("[" + seatNo + "⬜]");
              break;
          }
        }
      }
    }
  }

  private String addZeroToSeatNo(int i, int j) {
    int seatInt = i * 10 + j - 10;
    String seatNo = null;
    if (seatInt < 10 && !(j == 0)) {
      seatNo = "" + 0 + j;
    } else {
      seatNo = "" + seatInt;
    }
    return seatNo;
  }

  public MemberDto printBookedList(List<MemberDto> allUserBooking) {
    MemberDto mDataToPrint = null;
    int menu = -1;
    int bookIdx = 1;
    if(allUserBooking.get(0).getB_no()==0){
      return mDataToPrint;
    }

    for (MemberDto m : allUserBooking) {
      ioc.println("예매 번호 (" + bookIdx + ")");
      ioc.println("공연 이름     :" + m.getP_name());
      ioc.println("예매자 아이디 : " + m.getM_id());
      ioc.println("예매자 성명   : " + m.getM_name());
      ioc.println("날짜 및 장소  : " + m.getS_date() + "(" + m.getS_hall() + ")");
      ioc.println("시간         : " + m.getS_start().substring(0, 5) + "~" + m.getS_end().substring(0, 5));
      ioc.println("예매좌석      : " + m.getB_seat());
      ioc.println("---------------------------------------------------------");
      bookIdx++;
    }
    ioc.println("좌석표를 보시려면 예매 번호를 입력해주세요");
    menu = ioc.inputNumber("입력>");

    mDataToPrint = allUserBooking.get(menu - 1);

    return mDataToPrint;
  }

  public MemberDto regiDataInput() {
    MemberDto mData = new MemberDto();

    ioc.println("회원가입");
    ioc.println("형식에 맞춰 정보를 입력해주세요");
    ioc.println("---------------------------------------------------------");

    mData.setM_id(ioc.inputString("ID : "));
    mData.setM_pwd(ioc.inputString("PWD : "));
    mData.setM_name(ioc.inputString("이름 : "));
    mData.setM_phone(ioc.inputString("연락처(000-0000-0000) : "));
    mData.setM_birth(ioc.inputString("생년월일(yyyy-mm-dd) : "));
    mData.setM_account(ioc.inputString("계좌번호 : "));

    return mData;
  }

  public int printBeforDelete( List<MemberDto> allUserBooking) {
    int returnAgree = 0;

    ioc.println("계정 삭제를 진행하기 전에, 예약내역을 조회합니다.");
    if (allUserBooking.get(0).getB_no() !=0){
      ioc.println("예매내역이 존재합니다.");
      ioc.println("예매를 취소하신 뒤, 다시 시도해주세요.");
      ioc.println("---------------------------------------------------------");

  } else {
    ioc.println("예매내역이 없습니다.");
    returnAgree = askContinueDelete("계정 삭제를 계속 진행하시겠습니다?(y/n)");
      ioc.println("---------------------------------------------------------");
  }


    return returnAgree;
  }


  public int askContinueDelete(String str) {
    String agree = ioc.inputString(str);
    int returnAgree = 0;
    if (agree.equals("y")) {
      returnAgree = 1;
    } else {
      ioc.println("취소되었습니다.");
      ioc.println("---------------------------------------------------------");
    }
    return returnAgree;

  }
}