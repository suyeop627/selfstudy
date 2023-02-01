package gamecontroller;

import gamecontroller.RSPGame;

import java.util.Scanner;

public class GameController {

  public void run(){
    String menu;
    Scanner scan = new Scanner(System.in);
    System.out.println("======================");
    System.out.println("★★Game controller★★");
    System.out.println("======================");


    while(true){
      System.out.print("|1.up&down |");
      System.out.print("2.가위바위보 |");
      System.out.println("0. 종료 |");
      System.out.print(("입력>"));

      menu = scan.nextLine();


      if(menu.equals("0")){
        System.out.println("ㅂㅂ~");
        break;
      }

      switch(menu){
        case "1":
          System.out.println("Up&Down 게임을 실행합니다.");
          UpDownGame up = new UpDownGame();
          up.run();
          break;
        case "2":
          System.out.println("가위바위보 게임을 실행합니다.");
          RSPGame rsp = new RSPGame();
          rsp.run();

          break;

        default :
          System.out.println("잘못 입력하셨습니다.");
          break;
      }
    }
  }
}
