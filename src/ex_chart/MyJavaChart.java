package ex_chart;

import java.awt.EventQueue;
import java.awt.Font;
import java.io.IOException;
import java.nio.charset.spi.CharsetProvider;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.xml.parsers.FactoryConfigurationError;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;



import javax.swing.JPanel;
import java.awt.Color;

public class MyJavaChart {

   private JFrame frame;


   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               MyJavaChart window = new MyJavaChart();
               window.frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }


   public MyJavaChart() throws NumberFormatException, IOException {
      initialize();
   }


   private void initialize() throws NumberFormatException, IOException {
      frame = new JFrame();
      frame.setBounds(100, 100, 962, 606);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      SpringLayout springLayout = new SpringLayout();
      frame.getContentPane().setLayout(springLayout);
      
      JPanel panel = new JPanel();
      panel.setBackground(Color.BLACK);
      springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, frame.getContentPane());
      springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, frame.getContentPane());
      springLayout.putConstraint(SpringLayout.SOUTH, panel, 557, SpringLayout.NORTH, frame.getContentPane());
      springLayout.putConstraint(SpringLayout.EAST, panel, 936, SpringLayout.WEST, frame.getContentPane());
      frame.getContentPane().add(panel);
      
      
      ReadCSV readCSV = new ReadCSV();
      ArrayList<EnergyVO> list = readCSV.getList();
      
      
      // 그래프의 값과 정보를 기억하고 있는 객체를 생성
      // 바차트
      DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
      //파이차트
      DefaultPieDataset dataset2 = new DefaultPieDataset();
      
      
      
      // 그래프 데이터 삽입
      // 1. 크기   2. 범례   3. x축
      for(int i = 0; i < list.size(); i++) {
         dataset1.addValue(list.get(i).getUsage(), list.get(i).getMonth(), list.get(i).getDivision());
         dataset2.setValue(list.get(i).getDivision(), list.get(i).getUsage()); // * 파이차트는 .addValue가 아니라 .setValue
      }
      
      
      // barChart그래프를 그리는 객체 생성
      // 1.그래프의 제목   2.x축 설명   3.y축 설명    4.그래프의 데이터   5.그래프의 종류(Horizontal/vertical)    6.범례사용여부(T/F)   7.툴팁 사용여부(T/F)   8.확대사용여부(T/F)
      JFreeChart barChart = ChartFactory.createBarChart("사과판매량", "구분", "판매수", dataset1, PlotOrientation.VERTICAL, true, true, true);
      
      // pieChart그래프를 그리는 객체 생성
      // 1. 그래프의 제목   2. 데이터    3.범례사용여부(T/F)   4.툴팁 사용여부(T/F)   5.확대사용여부(T/F)
      JFreeChart pieChart = ChartFactory.createPieChart("기간별 사용량", dataset2, /*아래 필요없는 정보 지우기 true->false*/false, true, true);
                                                                                
      
      // 한글 인코딩
      // 한글 글씨체를 저장할 객체 생성
      Font f = new Font("Gulim", Font.BOLD, 14);
      // 그래프 제목에 한글 적용
      barChart.getTitle().setFont(f);
      pieChart.getTitle().setFont(f);
      // x축 y축 한글적용
      CategoryPlot plot1 = barChart.getCategoryPlot();
      PiePlot plot2 = (PiePlot)pieChart.getPlot();
      plot2.setLabelFont(f);
      // x축 한글
      plot1.getDomainAxis().setLabelFont(f);
      plot1.getDomainAxis().setTickLabelFont(f);
      // y축 한글
      plot1.getRangeAxis().setLabelFont(f);
      plot1.getRangeAxis().setTickLabelFont(f);
      
      
      
      // 그려진 그래프를 차트패널에 올리기
//      ChartPanel chartPanel = new ChartPanel(barChart);
      ChartPanel chartPanel = new ChartPanel(barChart);
      // 그래프 크기조절
      chartPanel.setPreferredSize(new java.awt.Dimension(700,500));
      panel.add(chartPanel);
      
   }

}