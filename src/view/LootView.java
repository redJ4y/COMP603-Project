package view;

// @author jared
import model.entity.Item;
import java.awt.BorderLayout;

public class LootView extends javax.swing.JPanel {

    private final ViewManager viewManager;

    private final ItemDisplay item;

    /**
     * Creates new form LootView
     */
    public LootView(ViewManager viewManager) {
        this.viewManager = viewManager;
        initComponents();
        item = new ItemDisplay(null);
        itemHolder.setLayout(new BorderLayout());
        itemHolder.add(item, BorderLayout.CENTER);
        item.setAsEmptySlot();
    }

    /* Prepare the panel with new information */
    public void prepPanel(Item loot, int numCoins, boolean invFull) {
        numCoinsText.setText("You search the corpse and find " + numCoins + " coins!");
        item.setItem(loot);
        if (invFull) {
            collectButton.setEnabled(false);
            invFullLabel.setVisible(true);
        } else {
            collectButton.setEnabled(true);
            invFullLabel.setVisible(false);
        }
    }

    public void invNotFull() { // the player has dropped something
        collectButton.setEnabled(true);
        invFullLabel.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        numCoinsText = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        itemHolder = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        collectButton = new javax.swing.JButton();
        leaveButton = new javax.swing.JButton();
        invFullLabel = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(378, 32767));

        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 0, 11)); // NOI18N
        jLabel1.setText(". . .");

        numCoinsText.setFont(new java.awt.Font("Palatino Linotype", 0, 11)); // NOI18N
        numCoinsText.setText("You search the corpse and find 999 coins!");

        jLabel3.setFont(new java.awt.Font("Palatino Linotype", 0, 11)); // NOI18N
        jLabel3.setText("And, what's this?");

        jLabel4.setFont(new java.awt.Font("Palatino Linotype", 0, 11)); // NOI18N
        jLabel4.setText(". . .");

        itemHolder.setMaximumSize(new java.awt.Dimension(366, 58));
        itemHolder.setMinimumSize(new java.awt.Dimension(366, 58));
        itemHolder.setPreferredSize(new java.awt.Dimension(366, 58));

        javax.swing.GroupLayout itemHolderLayout = new javax.swing.GroupLayout(itemHolder);
        itemHolder.setLayout(itemHolderLayout);
        itemHolderLayout.setHorizontalGroup(
            itemHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 366, Short.MAX_VALUE)
        );
        itemHolderLayout.setVerticalGroup(
            itemHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 14)); // NOI18N
        jLabel2.setText("Loot");

        collectButton.setText("Collect");
        collectButton.setFocusable(false);
        collectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                collectButtonActionPerformed(evt);
            }
        });

        leaveButton.setText("Leave");
        leaveButton.setFocusable(false);
        leaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leaveButtonActionPerformed(evt);
            }
        });

        invFullLabel.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        invFullLabel.setForeground(new java.awt.Color(255, 153, 153));
        invFullLabel.setText("Inventory Full");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numCoinsText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(invFullLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(collectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(leaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(itemHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numCoinsText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(0, 0, 0)
                .addComponent(jLabel4)
                .addGap(68, 68, 68)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(itemHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(collectButton)
                    .addComponent(leaveButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(invFullLabel)
                .addContainerGap(142, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void collectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_collectButtonActionPerformed
        collectButton.setEnabled(false);
        viewManager.collectLoot();
    }//GEN-LAST:event_collectButtonActionPerformed

    private void leaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leaveButtonActionPerformed
        viewManager.leavePressed(GameAreaOptions.LOOT);
    }//GEN-LAST:event_leaveButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton collectButton;
    private javax.swing.JLabel invFullLabel;
    private javax.swing.JPanel itemHolder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton leaveButton;
    private javax.swing.JLabel numCoinsText;
    // End of variables declaration//GEN-END:variables
}
