package thermaltool;

import static java.lang.Math.abs;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import javax.swing.JOptionPane;

/**
 *
 * @author Louis
 */
public class UI extends javax.swing.JFrame {

    /**
     * Creates new form UI
     */
    public UI() {
        initComponents();
        clothingMenu = new ClothingMenu(this);
    }
    
    public void setClo(double clo) {
        CLO = clo;
        System.out.println(clo);
    }
    
    private boolean verifyValues() {
        String unsetValues = "";
        if(CLO == -1) {
            unsetValues += "Invalid clothing value.\n";
        }
        try {
            MET = Double.parseDouble(mRate.getText());
        } catch (NumberFormatException ex) {
            unsetValues += "Invalid metabolic rate value.\n";
        }
        try {
            WME = Double.parseDouble(externalWork.getText());
        } catch(NumberFormatException ex) {
            unsetValues += "Invalid external work value.\n";
        }
        try {
            TA = Double.parseDouble(airTemp.getText());
        } catch (NumberFormatException ex) {
            unsetValues += "Invalid air temperature value.\n";
        }
        try {
            TR = Double.parseDouble(mrTemp.getText());
        } catch(NumberFormatException ex) {
            unsetValues += "Invalid mean radiant temp value.\n";
        }
        try {
            VEL = Double.parseDouble(relAirVel.getText());
        } catch(NumberFormatException ex) {
            unsetValues += "Invalid relative air velocity value.\n";
        }
        if(relHumidity.isSelected()) {
            try {
                RH = Double.parseDouble(waterVal.getText());
                PA = 0;
            } catch (NumberFormatException ex) {
                unsetValues += "Invalid relative humidity value.\n";
            }
        } else {
            try {
                PA = Double.parseDouble(waterVal.getText());
                RH = 0;
            } catch (NumberFormatException ex) {
                unsetValues += "Invalid water vapour pressure value.\n";
            }
        }
        if(!unsetValues.isEmpty()) {
            JOptionPane.showMessageDialog(this, unsetValues, "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    private double FNPS(double t) {
        return exp(16.6536-4030.183/(t+235));
    }
    
    private void calculateValues() {
        if(verifyValues()) {
            if(PA == 0) {
                PA = RH * 10 * FNPS(TA);
            }
            double PMV, PPD;
            double ICL = .155 * CLO;
            double M = MET * 58.15;
            double W = WME * 58.15;
            double MW = M - W;
            double FCL;
            if(ICL <= .078) {
                FCL = 1 + 1.29 * ICL;
            } else {
                FCL = 1.05 + 0.645 * ICL;
            }
            double HCF = 12.1 * sqrt (VEL);
            double TAA = TA + 273;
            double TRA = TR + 273;
            double TCLA = TAA + (35.5-TA) / (3.5*(6.45*ICL+.1));
            double P1 = ICL * FCL;
            double P2 = P1 * 3.96;
            double P3 = P1 * 100;
            double P4 = P1 * TAA;
            double P5 = 308.7 - .028 * MW + P2 * pow((TRA/100), 4);
            double XN = TCLA / 100; 
            double XF = XN;
            double HC;
            int N = 0;
            double EPS = .00015;
            do {
                XF = (XF + XN)/2;
                double HCN = 2.38*pow(abs(100*XF-TAA), .25);
                if(HCF>HCN) {
                    HC = HCF;
                } else {
                    HC = HCN;
                }
                XN = (P5 + P4 * HC - P2 * pow(XF, 4)) / (100 + P3 * HC);
                N = N + 1;
            } while(abs(XN - XF) > EPS || N > 150);
            if(N <= 150) {
                double TCL = 100 * XN - 273;
                double HL1 = 3.05 * .001 * (5733-6.99 * MW-PA);
                double HL2;
                if(MW > 58.15) {
                    HL2 = .42 * (MW - 58.15);
                } else {
                    HL2 = 0;
                }
                double HL3 = 1.7 * .00001 * M * (5867-PA);
                double HL4 = .0014 * M * (34 - TA);
                double HL5 = 3.96 * FCL * (pow(XN, 4) - pow(TRA/100, 4));
                double HL6 = FCL * HC * (TCL - TA);
                double TS = .303 * exp(-.036 * M) + .028;
                PMV = TS * (MW - HL1 - HL2 - HL3 - HL4 - HL5 - HL6); 
                PPD = 100.0 - 95.0 * exp(-.03353 * pow(PMV, 4) - .2179 * pow(PMV, 2));
            } else {
                PMV = -1;
                PPD = 100;
            }
            JOptionPane.showMessageDialog(this, "PMV: " + String.format("%1$,.2f", PMV) + "\nPPD: " + String.format("%1$,.2f", PPD), 
                    "Calculation Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        mRate = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        externalWork = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        airTemp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        mrTemp = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        relAirVel = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        relHumidity = new javax.swing.JRadioButton();
        waterVapPress = new javax.swing.JRadioButton();
        waterVal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        calculate = new javax.swing.JButton();
        chooseClothing = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 200));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("sansserif", 2, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(30, 30, 30));
        jLabel2.setText("Metabolic Rate");

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setText("PMV and PPD Tool");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("sansserif", 2, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(30, 30, 30));
        jLabel4.setText("External Work");

        jLabel5.setFont(new java.awt.Font("sansserif", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(30, 30, 30));
        jLabel5.setText("Air Temperature");

        jLabel6.setFont(new java.awt.Font("sansserif", 2, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(30, 30, 30));
        jLabel6.setText("Mean Radiant Temperature");

        jLabel7.setFont(new java.awt.Font("sansserif", 2, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(30, 30, 30));
        jLabel7.setText("Relative Air Velocity");

        jLabel8.setFont(new java.awt.Font("sansserif", 2, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(30, 30, 30));

        relHumidity.setText("Relative Humidity");
        relHumidity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relHumidityActionPerformed(evt);
            }
        });

        waterVapPress.setText("Water Vapour Pressure");
        waterVapPress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                waterVapPressActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("sansserif", 2, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(30, 30, 30));
        jLabel9.setText("Clothing");

        calculate.setText("Calculate");
        calculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateActionPerformed(evt);
            }
        });

        chooseClothing.setText("Choose Clothing");
        chooseClothing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseClothingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mRate, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mrTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(airTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(externalWork, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(relAirVel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(relHumidity)
                            .addComponent(waterVapPress))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(waterVal, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chooseClothing)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(calculate, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(chooseClothing))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(mRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(externalWork, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(airTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mrTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(relAirVel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(relHumidity)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(waterVapPress))
                    .addComponent(waterVal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                .addComponent(calculate)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        

    private void chooseClothingActionPerformed(java.awt.event.ActionEvent evt) {                                               
        clothingMenu.setVisible(true);
    }                                              

    private void relHumidityActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if(waterVapPress.isSelected()) {
            waterVapPress.setSelected(false);
        }
    }                                           

    private void waterVapPressActionPerformed(java.awt.event.ActionEvent evt) {                                              
        if(relHumidity.isSelected()) {
            relHumidity.setSelected(false);
        }
    }                                             

    private void calculateActionPerformed(java.awt.event.ActionEvent evt) {                                          
        calculateValues();
    }                                         

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }
    
    private double CLO = -1;
    private double MET;
    private double WME;
    private double TA;
    private double TR;
    private double VEL;
    private double PA;
    private double RH;
    private final ClothingMenu clothingMenu;

    // Variables declaration - do not modify                     
    private javax.swing.JTextField airTemp;
    private javax.swing.JButton calculate;
    private javax.swing.JButton chooseClothing;
    private javax.swing.JTextField externalWork;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField mRate;
    private javax.swing.JTextField mrTemp;
    private javax.swing.JTextField relAirVel;
    private javax.swing.JRadioButton relHumidity;
    private javax.swing.JTextField waterVal;
    private javax.swing.JRadioButton waterVapPress;
    // End of variables declaration                   
}
