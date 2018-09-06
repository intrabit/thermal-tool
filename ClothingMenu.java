package thermaltool;

/**
 *
 * @author Louis
 */
public class ClothingMenu extends javax.swing.JFrame {

    /**
     * Creates new form ClothingMenu
     * @param parent The parent.
     */
    public ClothingMenu(UI parent) {
        initComponents();
        this.parent = parent;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        clothingList = new javax.swing.JList<>();
        select = new javax.swing.JButton();

        setTitle("Clothing Menu");
        setLocation(new java.awt.Point(700, 100));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel3.setText("Clothing Menu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(jLabel3)
                .addContainerGap(177, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        clothingList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Panties, T-shirt, shorts, light socks, sandals", "Underpants, shirt with short sleeves, light trousers, light socks, shoes", "Panties, petticoat, stockings, dress, shoes", "Underwear, shirt, trousers, socks, shoes ", "Panties, shirt, trousers, jacket, socks, shoes", "Panties, stockings, blouse, long skirt, jacket, shoes", "Underwear with long sleeves and legs, shirt, trousers, V-neck sweater, jacket, socks, shoes  ", "Underwear with short sleeves and legs, shirt, trousers, vest, jacket, coat, socks, shoes ", "Underpants, boiler suit, socks, shoes", "Underpants, shirt, boiler suit, socks, shoes", "Underpants, shirt, trousers, smock, socks, shoes", "Underwear with short sleeves and legs, shirt, trousers, jacket, socks, shoes", "Underwear with long legs and sleeves, thermo-jacket, socks, shoes ", "Underwear with short sleeves and legs, shirt, trousers, jacket, heavy quilted outer jacket and overalls, socks, shoes, cap, gloves ", "Underwear with short sleeves and legs, shirt, trousers, jacket, heavy quilted outer jacket and overalls, socks, shoes ", "Underwear with long sleeves and legs, thermo-jacket and trousers, Parka with heavy quitting, overalls with heavy quilting, socks, shoes, cap, gloves " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(clothingList);

        select.setText("Select");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(select, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(155, 155, 155))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(select)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {                                       
        parent.setClo(cloValues[clothingList.getSelectedIndex()]);
        this.setVisible(false);
    }                                      
    
    private final double[] cloValues = {
        0.3f,
        0.5f,
        0.7f,
        0.7f,
        1.0f,
        1.1f,
        1.3f,
        1.5f,
        0.7f,
        0.8f,
        0.9f,
        1.0f,
        1.2f,
        1.4f,
        2.0f,
        2.55f
    };
    private final UI parent;
    // Variables declaration - do not modify                     
    private javax.swing.JList<String> clothingList;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton select;
    // End of variables declaration                   
}
