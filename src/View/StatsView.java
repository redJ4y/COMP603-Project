package View;

// @author jared
public class StatsView extends javax.swing.JPanel {

    /**
     * Creates new form StatsView
     */
    public StatsView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titleText = new javax.swing.JLabel();
        healthText = new javax.swing.JLabel();
        dmText = new javax.swing.JLabel();
        apmText = new javax.swing.JLabel();
        protText = new javax.swing.JLabel();
        agilText = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));

        titleText.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        titleText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleText.setText("Jared's Stats");

        healthText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        healthText.setText("158 [Health]");

        dmText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dmText.setText("0 [Damage Modifier]");

        apmText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        apmText.setText("0 [Armor Piercing Modifier]");

        protText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        protText.setText("0 +0 [Protection]");

        agilText.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        agilText.setText("0 +0 [Agility]");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(healthText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dmText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(apmText, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(protText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(agilText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(15, 15, 15))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titleText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(titleText)
                .addGap(36, 36, 36)
                .addComponent(healthText)
                .addGap(18, 18, 18)
                .addComponent(dmText)
                .addGap(18, 18, 18)
                .addComponent(apmText)
                .addGap(18, 18, 18)
                .addComponent(protText)
                .addGap(18, 18, 18)
                .addComponent(agilText)
                .addContainerGap(188, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel agilText;
    private javax.swing.JLabel apmText;
    private javax.swing.JLabel dmText;
    private javax.swing.JLabel healthText;
    private javax.swing.JLabel protText;
    private javax.swing.JLabel titleText;
    // End of variables declaration//GEN-END:variables
}
