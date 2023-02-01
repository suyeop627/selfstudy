package com.controller;

import com.dto.DiaryInfo;
import com.view.InOutClass;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

public class DiaryController {
  ArrayList<DiaryInfo> dlist = new ArrayList<>();
  private InOutClass io = new InOutClass();

  Calendar cal = Calendar.getInstance();
  int year = cal.get(Calendar.YEAR);
  int mon = cal.get(Calendar.MONTH) + 1;
  int day = cal.get(Calendar.DATE);
  int hour = cal.get(Calendar.HOUR);
  int min = cal.get(Calendar.MINUTE);
  int sec = cal.get(Calendar.SECOND);

  String time = hour + "시 " + min + "분 " + sec + "초";
  String today = year + "년 " + mon + "월 " + day + "일";
  String head = today + "의 일기";
  FileWriter fw = null;
  BufferedWriter bw = null;
  DiaryInfo di = new DiaryInfo();

  public void run() {
    io.println("==================================");
    io.println("\t\t\t일기장\n \t오늘 날짜 : " + today);
    io.println("==================================");
    io.println(today);
    int menu = -1;
    while (true) {
      if (menu == 0) {
        io.println("종료");
        return;
      }

      printMenu();
      menu = io.inputNumber("메뉴 번호>");
      System.out.println("----------------------------------");


      switch (menu) {

        case 1: //일기 작성
          saveDiary();
          break;
        case 2:  //일기 읽기
          readDiary();
          break;
        case 3:  //일기 수정
          editDiary();
          break;
        case 4: //일기 삭제
          deleteDiary();
          break;
        default:
          io.println("0~5까지의 숫자를 입력하세요");
          System.out.println("----------------------------------");
      }//switch end
    }//while end
  }//run end


  //일기 작성
  private void saveDiary() {

    String path = "diary";
    File folder = new File(path);

    try {
      if (!folder.isDirectory()) {
        if (folder.mkdir()) {
          System.out.println("일기장 폴더를 생성했습니다.(폴더명 : diary) \n");


        } else {
          System.out.println("폴더 생성에 실패했습니다.");
          System.out.println("----------------------------------");
        }
      }//if end

      File diary = new File("diary\\" + head + ".txt");

      boolean b = diary.createNewFile();
      if (b) {
        io.println("오늘의 일기를 작성합니다.");
        System.out.println("----------------------------------");


        di.setTitle(io.inputString("제목 : "));
        di.setWeather(io.inputString("날씨 : "));
        di.setContent(io.inputString("내용 : "));
        di.setDate("작성 날짜 : " + today);
        di.setUpdate("수정 시간 : " + time);
        dlist.add(di);

        fw = new FileWriter(diary, true);
        bw = new BufferedWriter(fw);

        bw.write("제목 : " + di.getTitle() + "\n");
        bw.write("날씨 : " + di.getWeather() + "\n");
        bw.write("내용 : " + di.getContent() + "\n");
        bw.write(di.getDate() + "\n");
        bw.write(di.getUpdate() + "\n");

        bw.flush();
      } else {
        io.println("오늘의 일기를 이미 작성하셨습니다.");
        io.println("수정을 하시려면 메뉴에서 3을 눌러주세요.");
        io.println("==================================");
      }
    } catch (FileNotFoundException fe) {
      fe.printStackTrace();
    } catch (IOException ie) {
      ie.printStackTrace();
    } finally {
      try {
        bw.close();
        fw.close();

      } catch (IOException e) {
        e.printStackTrace();
      } catch (NullPointerException ne) {
      }
    }
  }


  //일기 읽기
  private void readDiary() {
    printList();


    io.println("읽고 싶은 일기의 작성 날짜를 입력하세요");
    int y = io.inputNumber("연>");
    int m = io.inputNumber("월>");
    int d = io.inputNumber("일>");
    System.out.println("----------------------------------");
    FileReader fr = null;
    BufferedReader br = null;

    String dateOfDiary = y + "년 " + m + "월 " + d + "일";
    String titleOfDairy = dateOfDiary + "의 일기";


    try {
      File DiaryToRead = new File("diary\\" + titleOfDairy + ".txt");
      if (DiaryToRead.exists()) {
        fr = new FileReader(DiaryToRead);
        br = new BufferedReader(fr);
        String str = null;

        while ((str = br.readLine()) != null) {
          io.println(str);
        }
        System.out.println("----------------------------------");
      } else {
        io.println("\n"+dateOfDiary + "에 작성한 일기가 없습니다.");
        io.println("==================================");
      }
    } catch (FileNotFoundException fe) {
    } catch (IOException e) {
    } finally {
      try {
        br.close();
        fr.close();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (NullPointerException ne) {
      }
    }
  }

  //일기 수정
  private void editDiary() {
    printList();
    io.println("수정할 일기의 작성 날짜를 입력하세요");
    System.out.println("----------------------------------");
    int y = io.inputNumber("연>");
    int m = io.inputNumber("월>");
    int d = io.inputNumber("일>");


    String dateOfDiary = y + "년 " + m + "월 " + d + "일";
    String titleOfDairy = dateOfDiary + "의 일기";
    FileWriter fw = null;
    BufferedWriter bw = null;

    try {
      File DiaryToWrite = new File("diary\\" + titleOfDairy + ".txt");
      File folder = new File("diary");
      if (!folder.isDirectory()) {
        folder.mkdir();
      }
      if (DiaryToWrite.exists()) {
        io.println("1. 내용 추가");
        io.println("2. 새로 작성");
        int editcase = io.inputNumber("입력>");


        switch (editcase) {
          case 1:
            System.out.println("----------------------------------");
            io.println("추가할 내용을 적어주세요");
            fw = new FileWriter(DiaryToWrite, true);
            bw = new BufferedWriter(fw);0

            di.setContent(io.inputString("내용 추가 : "));
            di.setDate("작성 날짜 : " + today);
            di.setUpdate("수정 시간 : " + time);
            dlist.add(di);

            bw.write("\n 추가 내용 : " + di.getContent() + "\n");
            bw.write(di.getDate() + "\n");
            bw.write("수정 시간 : " + di.getUpdate() + "\n");
            break;

          case 2:
            System.out.println("----------------------------------");
            io.println("일기 내용을 새로 작성합니다.");
            fw = new FileWriter(DiaryToWrite);
            bw = new BufferedWriter(fw);

            di.setWeather(io.inputString("날씨 : "));
            di.setTitle(io.inputString("제목 : "));
            di.setContent(io.inputString("내용 : "));
            di.setDate("작성 날짜 : " + today);
            di.setUpdate("수정 시간 : " + time);
            dlist.add(di);


            bw.write("제목 : " + di.getTitle() + "\n");
            bw.write("날씨 : " + di.getWeather() + "\n");
            bw.write("내용 : " + di.getContent() + "\n");
            bw.write(di.getDate() + "\n");
            bw.write("수정 시간 : " + di.getUpdate() + "\n");
            break;

          default:
            io.println(("\n"+dateOfDiary + " 에 작성한 일기를 수정했습니다."));
        }

        bw.flush();
        io.println("");
      } else {
        io.println("\n"+dateOfDiary + " 에 작성한 일기가 없습니다.");
        io.println("==================================");
      }

    } catch (Exception e) {

    } finally {
      try {
        bw.close();
        fw.close();
      } catch (IOException io) {
      } catch (NullPointerException ne) {
      }
    }
  }


  //일기 삭제
  private void deleteDiary() {
    printList();
    io.println("삭제할 일기의 작성 날짜를 입력하세요");
    int y = io.inputNumber("연>");
    int m = io.inputNumber("월>");
    int d = io.inputNumber("일>");

    String dateOfDiary = y + "년 " + m + "월 " + d + "일";
    String titleOfDairy = dateOfDiary + "의 일기";
    File DiaryToDelete = new File("diary\\" + titleOfDairy + ".txt");
    if (DiaryToDelete.exists()) {
      DiaryToDelete.delete();
/*
      String deleteInList = dateOfDiary;
      int i ;
      DiaryInfo dd =  null;
      for(i=0; i<dlist.size(); i++){
        dd = dlist.get(i);
        if(deleteInList.equals( di.getDate())){
          io.println(dd.toString());
          io.println("-------------------------------");
          break;
        }
      }
      dlist.remove(i);-
*/
      io.println("\n"+titleOfDairy + "가 삭제되었습니다.");
      io.println("==================================");
    } else {
      io.println("\n"+dateOfDiary + "에 작성한 일기가 없습니다.");
      io.println("==================================");
    }
  }

  private void printMenu() {

    io.println("1. 일기 작성");
    io.println("2. 일기 읽기");
    io.println("3. 일기 수정");
    io.println("4. 일기 삭제");
    io.println("0. 종료");
  }

  private void printList() {
    File diaryList = new File("diary");
    io.println("일기 목록");
    if (diaryList.exists()) {

      File[] list = diaryList.listFiles();
      for (File f : list) {
        if (f.isFile()) {
          System.out.println(f.getName());
        }
      }
    }
    System.out.println("----------------------------------");
  }
}//class end