package gamecontroller;

import java.util.Scanner;

public class UpDownGame {
    public void run(){
      Scanner scan = new Scanner(System.in);

      System.out.println("============================");
      System.out.println("Up & Down Gameヽ(✿ﾟ▽ﾟ)ノ");
      System.out.println("============================");
      System.out.println("1부터 100까지의 숫자를 입력해주세요.");
      System.out.println("종료하시려면 '0'을 입력해주세요.");
      System.out.print("입력>");

      int rn= (int)(Math.random()*100+1);
      int count = 0;
      while(true){


        int num = scan.nextInt();


        if(num==0){
          System.out.println("============================");
          System.out.println("게임 종료");
          System.out.println("============================");
          break;
        }

         count++;
        if(rn>num){
          System.out.println("Up!");
          System.out.println("종료하시려면 '0'을 입력해주세요.");
          System.out.println("============================");
          System.out.print("입력>");

        }else if (rn<num){
          System.out.println("Down");
          System.out.println("종료하시려면 '0'을 입력해주세요.");
          System.out.println("============================");
          System.out.print("입력>");

        }else {
          System.out.println("정답입니다.");
          System.out.println(count + "번만에 맞추셨어요.");
          System.out.println("============================");
          System.out.println("게임 종료");
          System.out.println("============================");
          return;
        }
      }
    }
  }


