package ex_chart;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadCSV {

   public ArrayList<EnergyVO> getList() throws NumberFormatException, IOException { 
      
      ArrayList<EnergyVO> list = new ArrayList<EnergyVO>();
      
      // 1. 파일이 존재하는 위치와 정보를 담는 객체 생성
      String path = "/Users/kdh/Downloads/chartlib/a.csv";
      
      // 2. 파일 객체를 읽어들여서 값을 꺼내는 Input 스트림 생성
      try {
    	  FileInputStream input=new FileInputStream(path);
          InputStreamReader reader=new InputStreamReader(input,"euc-kr");
          BufferedReader sc=new BufferedReader(reader);
          sc.readLine();
          String line = sc.readLine();
         
    
         while(!(line.isEmpty())) {
         String value[] = line.split(",");
         String division = value[0];
         int usage = Integer.parseInt(value[1]);
         String month = value[2];
         EnergyVO vo = new EnergyVO(division, usage, month);
         list.add(vo);
         for (int i = 0; i < value.length; i++) {
            System.out.print(value[i] + "\t");
            
         }System.out.println(); 
         
         line = sc.readLine();
         }

      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } catch(NullPointerException e) {
    	  System.out.println("종");
      }
      return list;

   }

}
