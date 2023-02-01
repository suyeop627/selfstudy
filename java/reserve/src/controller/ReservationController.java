package controller;

import dto.ReservationInfo;
import view.InOutClass;
import java.util.ArrayList;

public class ReservationController {
  private InOutClass io = new InOutClass();
  ArrayList<ReservationInfo> reservationAl = new ArrayList<>();


  public void run() {

    int menu = -1;
    io.println("================================================================");
    io.println("좌석 예약 프로그램 ");
    io.println("================================================================");

    while (true) {
      menuShow();

      menu = io.inputNumber("입력>");

      if (menu == 0) {
        io.println("▶ 프로그램 종료");
        return;
      }

      switch (menu) {

        case 1:
          checkSeat();
          break;
        case 2:
          reserve();
          break;

        case 3:
          outputData();
          break;
        case 4 :
          deleteData();



        default:
          io.println("0~4번까지 입력하세요.");
      }
    }//while end
  }//run end

  private void menuShow() {
    io.print("| 1.좌석 확인 |");
    io.print(" 2.예약 |");
    io.print(" 3.예약 확인 |");
    io.print(" 4.예약 수정 |");
    io.print(" 5.예약 취소 |");
    io.println(" 0.종료 |");
  }

  //좌석확인
  String seat[] = {"A", "B", "C"};
  ArrayList<String> chosenSeat = new ArrayList<>();     //선택한 좌석 번호만 저장하는 배열
  public void checkSeat() {
    io.println("================================================================");
    io.println("▶ 좌석 확인 ");
    io.println("----------------------------------------------------------------");
    for (int i = 0; i <= 2; i++) {
      for (int j = 1; j <= 5; j++) {
        String seatrow = seat[i];
        String seat = seatrow + j;      //A1~C5 좌석 번호 부여

        if (chosenSeat.contains(seat)) {    //생성되는 좌석번호가 선택 좌석 배열에 있다면
          for (ReservationInfo ri : reservationAl) {    //예약 정보가 저장된 배열의 객체들 중에
            if ((seatrow + j).equals(ri.getSeatNumber())) {   //좌석 번호와 같은 예약 번호가 담긴 객체를 찾고

              if (j < 5) {      //해당 객체의 Name 값 을 출력(5번째마다 줄바꿈)
                System.out.print("["+ seatrow + j +" '"+ ri.getName() + "'님]");

              } else {
                System.out.println("["+ seatrow + j +" '"+ ri.getName() + "'님]");
              }
            }
          }
        } else {        //생성되는 좌석번호가 좌석 배열에 없다면 [좌석번호 예약가능] 출력
          if (j < 5) {
            System.out.print("[" + seatrow + j + " 예약가능]");

          } else {
            System.out.println("[" + seatrow + j + " 예약가능]");
          }
        }
      }
    }
    io.println("================================================================");
  }


  //예약



  public void reserve() {
    io.println("================================================================");
    io.println("▶ 예약자 정보 입력");
    io.println("----------------------------------------------------------------");
    ReservationInfo reservInfo = new ReservationInfo();

    reservInfo.setName(io.inputString("이름 : "));
    reservInfo.setPhone(io.inputString("연락처 : "));


    reservInfo.setSeatNumber(io.inputString("좌석 번호 : "));
    chosenSeat.add(reservInfo.getSeatNumber());   //입력받은 좌석번호만 따로 배열에 저장

    io.println("정보 입력이 완료됐습니다.");
    io.println("예약 확인을 진행해주세요.");
    io.println("================================================================");
    reservationAl.add(reservInfo);
  }


  //예약확인

  private void outputData() {

    //저장된 정보가 있는지 확인
    if (reservationAl.size() == 0) {
      io.println("정보가 없습니다.\n");
      return;   //메소드 종료
    }
    io.println("================================================================");
    io.println("▶ 예약 좌석 확인");
    io.println("----------------------------------------------------------------");

      for (int i = 0; i <= 2; i++) {
        for (int j = 1; j <= 5; j++) {
          String seatrow = seat[i];
          String seat = seatrow + j;

          if (chosenSeat.contains(seat)) {
            for (ReservationInfo ri : reservationAl) {
              if ((seatrow + j).equals(ri.getSeatNumber())) {

                if (j < 5) {
                  System.out.print("["+ seatrow + j +" '"+ ri.getName() + "'님]");

                } else {
                  System.out.println("["+ seatrow + j +" '"+ ri.getName() + "'님]");
                }
              }
            }
          } else {
            if (j < 5) {
              System.out.print("[" + seatrow + j + " 예약가능]");

            } else {
              System.out.println("[" + seatrow + j + " 예약가능]");
            }
          }
        }
      }
    io.println("----------------------------------------------------------------");
    io.println("▶ 예약자 정보 확인");
    io.println("----------------------------------------------------------------");

    for (ReservationInfo ri : reservationAl) {
      io.println("이름 : " + ri.getName());
      io.println("연락처 : " + ri.getPhone());
      io.println("좌석 번호 : " + ri.getSeatNumber());

      io.println("----------------------------------------------------------------");
    }
    io.println("예약 정보와 일치하는 지 확인해주세요.");
    io.println("================================================================");

  }//outputData end



  // 삭제 메소드

  public void deleteData(){
    io.println("================================================================");
    io.println("▶ 예약 취소");
    io.println("----------------------------------------------------------------");

    String sname = io.inputString("예약자 이름 : ");
    int i ;     //for문 밖에서 i를 사용하기 위해 for문 밖에 선언
    ReservationInfo ri = null;     //여기서 p는 데이터를 직접 담고 있는 것이 아니라, 주소를 담고 있는 참조형 변수임.
    for(i = 0;i<reservationAl.size();i++){
      ri = reservationAl.get(i);
      if(sname.equals(ri.getName())){
        io.println("이름 : " + ri.getName());
        io.println("연락처 : " + ri.getPhone());
        io.println("좌석 번호 : " + ri.getSeatNumber());

        io.println("----------------------------------------------------------------");
        break; //for(루프)를 멈추는 break 사용. - 검색할땐 메소드를 종료하기 위해 return을 사용, 이번엔 추가작업(수정)을 하기위해 break으로 for문만 종료
      }
    }//for end
    //검색 결과가 없을 경우 처리
    if(i == reservationAl.size()){//위의 for문에서 선형검색을 완료했을 때까지 일치하는 값이 없을 경우.
      io.println("검색 결과가 없습니다.");
      return;  //메소드 종료.
    }

    //삭제할 연락처를 검색 성공
    String yn = io.inputString("삭제하시겠습니까?(y)");

    if(yn.equals("y")){ //입력값이 "y"라면
      reservationAl.remove(i);//i번째 에서 찾는다면 해당 i 값에서 break되기 때문에, i번째 제거하면 원하는 값을 제거할 수 있음
      // reservationAl.remove(p)로 작성해도 같은 결과.(i번째에서 찾은 데이터의 참조 주소가 p에 담겨있음)
      chosenSeat.remove(i);
      io.println(("삭제완료.\n"));
      return;
    }
    //입력값이 "y"가 아니라면
    io.println("삭제 취소");

  }

//추가할 내용
  //예약      //중복 예약 막기    //좌석 예약 시, 정해진 입력값을 받도록.

  //예약확인    - 좌석 출력 삭제하고, 이름으로 검색 -> 좌석 출력되도록. (좌석확인으로 자리 확인하라고 안내)

  //예약자 검색

  //예약 수정

  //예약 취소






}
