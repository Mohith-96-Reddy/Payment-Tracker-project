package payment.tracker;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class display extends javax.swing.JFrame {

    private static final long serialVersionUID=1L;
    public String Account_name;
    public display(String name,String title) {
        super(title);
        Account_name=name;
       DefaultCategoryDataset dataset1=createDataset(title);
       JFreeChart chart=ChartFactory.createLineChart(title,"Month","Spent Value",dataset1);
       ChartPanel panel=new ChartPanel(chart);
       setContentPane(panel);
       initComponents();
       img();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setBackground(new java.awt.Color(102, 102, 102));
        setFont(new java.awt.Font("AcadEref", 3, 12)); // NOI18N

        jLabel1.setBackground(new java.awt.Color(153, 153, 153));
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102), 3));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 959, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    public DefaultCategoryDataset createDataset(String Filename) {
        String series1="your Payments";
        String series2="Unique Visitors";
        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
        File myfile=new File("C:\\Payment Tracker\\database\\"+Account_name+"\\"+Filename+".txt");
        String subfile=Filename.substring(5,Filename.length());
        if( myfile.exists()){
            try{
                BufferedReader in=new BufferedReader(new FileReader("C:\\Payment Tracker\\database\\"+Account_name+"//"+Filename+".txt"));
            String words[]=null;
            String str,tmp="";Double tmpv=0.0,yv;
            while((str=in.readLine())!=null){
                words=str.split("    ");
                 yv=Double.valueOf(words[5]);
                if(tmp.equals(words[3]))
                    yv+=tmpv; 
                dataset.addValue(yv,series1,words[3]);  
                tmp=words[3];tmpv=yv;
            }
            in.close();
            }
             catch(IOException e){
                System.out.println(e);
            }
        }else if(subfile.equals(" Payaments Analysis")){
            view v=new view(Account_name);
            String[][] mnth=new String[12][50];
            int m[]=new int[12];
            for(int j=0;j<12;j++)m[j]=0;
            String words[]=null;
            String str;
            Double total=0.0;   
            try{
                String files[]=v.insert();
                for(String i:files){
                BufferedReader in=new BufferedReader(new FileReader("C:\\Payment Tracker\\database\\"+Account_name+"\\"+i+".txt"));   
                while((str=in.readLine())!=null){
                words=str.split("    ");
                if(words[2].equals(Filename.substring(0,4))){
                int mn=v.monthconveter(words[1]);
                mnth[mn][m[mn]]=str+"    "+i;
                m[mn]++;  
                }
                }    
                }
                    for(int i=0;i<12;i++){
                    Double totalspent=0.0;
                    if(m[i]>0){
                    for(int j=0;j<m[i];j++){
                    String line=mnth[i][j];
                    words=line.split("    ");
                    totalspent+=Double.valueOf(words[5]);
                }
                dataset.addValue(totalspent,"Your Payments",words[1]);
                    }
         } } catch(IOException e){
                System.out.println(e);
            } 
        }
        else
            System.out.println("File does not exists");
        return dataset;
    }
    private void img() {
       setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(".png")));
    }
}
