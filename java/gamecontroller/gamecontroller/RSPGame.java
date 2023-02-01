package gamecontroller;

import java.util.Scanner;

public class RSPGame {

  public void run() {

    Scanner scan = new Scanner(System.in);
      System.out.println("============================");
      System.out.println("가위 바위 보 게임(๑•̀ㅂ•́)و✧");
      int userwin = 0;
      int userlose = 0;
      int draw = 0;
    while (true) {
      System.out.println("============================");
      System.out.println("'가위', '바위', '보' 중에 하나를 입력해주세요. 종료는 '0'");
      System.out.print("입력>");
      String user = scan.next();
      String computer = String.valueOf(((int) (Math.random() * 3) + 1));
//1바위 2가위 3 보

      if(user.equals("0")){
        System.out.println("============================");
        System.out.println("최종 전적 : "+userwin +"승 "+draw + "무 " + userlose + "패" );
        System.out.println("게임 종료");

        System.out.println("============================");
        return;
      }

      switch (user) {
        case "바위":
          if (computer.equals("3")) {
            System.out.println("컴퓨터는 보자기 ! 당신이 졌어요...😏");
            userlose++;
            System.out.println("전적 : "+userwin+ "승 "+draw +"무 "+ userlose + "패" );
          } else if (computer.equals("2")) {
            System.out.println("컴퓨터는 가위 ! 당신이 이겼어요!!😀");
            userwin++;
            System.out.println("전적 : "+userwin+ "승 "+draw +"무 "+ userlose + "패" );
          } else {
            System.out.println("컴퓨터도 바위 ! 비겼어요");
            draw ++;
            System.out.println("전적 : "+userwin+ "승 "+draw +"무 "+ userlose + "패" );
          }
          break;

        case "가위":
          if (computer.equals("1")) {
            System.out.println("컴퓨터는 바위 ! 당신이 졌어요...😏");
            userlose++;
            System.out.println("전적 : "+userwin+ "승 "+draw +"무 "+ userlose + "패" );

          } else if (computer.equals("3")) {
            System.out.println("컴퓨터는 보자기 ! 당신이 이겼어요!!😀");
            userwin++;
            System.out.println("전적 : "+userwin+ "승 "+draw +"무 "+ userlose + "패" );
          } else {
            System.out.println("컴퓨터도 가위 ! 비겼어요");
            draw++;
            System.out.println("전적 : "+userwin+ "승 "+draw +"무 "+ userlose + "패" );
          }
          break;

        case "보":
          if (computer.equals("2")) {
            System.out.println("컴퓨터는 가위 ! 당신이 졌어요...😏");
            userlose++;
            System.out.println("전적 : "+userwin+ "승 "+draw +"무 "+ userlose + "패" );
          } else if (computer.equals("1")) {
            System.out.println("컴퓨터는 바위 ! 당신이 이겼어요!!😀");
            userwin++;
            System.out.println("전적 : "+userwin+ "승 "+draw +"무 "+ userlose + "패" );
          } else {
            System.out.println("컴퓨터도 보 ! 비겼어요");
            draw++;
            System.out.println("전적 : "+userwin+ "승 "+draw +"무 "+ userlose + "패" );
          }
          break;
      }
    }
  }
}
