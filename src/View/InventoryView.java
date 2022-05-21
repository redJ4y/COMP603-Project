package View;

// @author jared
import Model.Entity.Armor;
import Model.Entity.Inventory;
import Model.Entity.Item;
import Model.Entity.Player;
import Model.Entity.Potion;
import Model.Entity.Weapon;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

public class InventoryView extends javax.swing.JPanel {

    private final ViewManager viewManager;

    private final ItemDisplay equippedWeapon;
    private final ItemDisplay equippedArmor;
    private final ItemDisplay selectedItem;
    private final DefaultListModel inventoryModel; // model behind inventoryList
    private List<Item> inventoryItems; // store for display purposes
    private int eqWeaponIndex, eqArmorIndex; // store equipped item indexes
    private boolean confirmDrop; // true when trying to drop an equipped item

    /**
     * Creates new form InventoryView
     */
    public InventoryView(ViewManager viewManager) {
        this.viewManager = viewManager;
        initComponents();
        disableButtons();
        confirmDrop = false;
        // initialize item displays:
        equippedWeapon = new ItemDisplay(null);
        equippedArmor = new ItemDisplay(null);
        selectedItem = new ItemDisplay(null);
        equippedWeaponHolder.setLayout(new BorderLayout());
        equippedArmorHolder.setLayout(new BorderLayout());
        selectedItemHolder.setLayout(new BorderLayout());
        equippedWeaponHolder.add(equippedWeapon, BorderLayout.CENTER);
        equippedArmorHolder.add(equippedArmor, BorderLayout.CENTER);
        selectedItemHolder.add(selectedItem, BorderLayout.CENTER);
        equippedWeapon.setAsEmptySlot();
        equippedArmor.setAsEmptySlot();
        selectedItem.setAsInvisible();
        // initialize the inventory list stuff:
        inventoryModel = new DefaultListModel();
        inventoryList.setModel(inventoryModel);
        inventoryItems = new ArrayList<>(1); // default value to avoid errors
    }

    public void updateInventory(Player player) {
        Inventory inventory = player.getInventory();
        inventoryItems = inventory.getItems();
        eqWeaponIndex = inventory.getEquippedWeaponIndex();
        eqArmorIndex = inventory.getEquippedArmorIndex();

        topText.setText(player.getName() + " | " + player.getStats().getHealth() + " [Health] | " + player.getCoins() + " Coins");
        numItemsLabel.setText(inventoryItems.size() + "/" + inventory.MAX_SIZE);
        if (inventory.getEquippedWeaponIndex() < 0) { // faster way of checking for null
            equippedWeapon.setAsEmptySlot();
        } else {
            equippedWeapon.setItem(inventory.getEquippedWeapon());
        }
        if (inventory.getEquippedArmorIndex() < 0) { // faster way of checking for null
            equippedArmor.setAsEmptySlot();
        } else {
            equippedArmor.setItem(inventory.getEquippedArmor());
        }
        // update/prep inventoryList:
        updateInventoryList();
        disableButtons();
        if (!selectedItem.isDisplayingMessage()) { // only clear if it was displaying an item
            selectedItem.setAsInvisible();
        }
        confirmDrop = false;
    }

    private void updateInventoryList() {
        inventoryModel.clear();
        int index = 0;
        for (Item current : inventoryItems) {
            // MUST maintain indexing of the inventory
            inventoryModel.add(index++, current.getName());
        }
    }

    private void disableButtons() {
        consumeButton.setEnabled(false);
        dropButton.setEnabled(false);
        equipButton.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        equippedWeaponHolder = new javax.swing.JPanel();
        equippedArmorHolder = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        topText = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        selectedItemHolder = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        inventoryList = new javax.swing.JList<>();
        equipButton = new javax.swing.JButton();
        consumeButton = new javax.swing.JButton();
        dropButton = new javax.swing.JButton();
        numItemsLabel = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        setPreferredSize(new java.awt.Dimension(378, 458));

        equippedWeaponHolder.setMaximumSize(new java.awt.Dimension(366, 58));
        equippedWeaponHolder.setMinimumSize(new java.awt.Dimension(366, 58));
        equippedWeaponHolder.setPreferredSize(new java.awt.Dimension(366, 58));

        javax.swing.GroupLayout equippedWeaponHolderLayout = new javax.swing.GroupLayout(equippedWeaponHolder);
        equippedWeaponHolder.setLayout(equippedWeaponHolderLayout);
        equippedWeaponHolderLayout.setHorizontalGroup(
            equippedWeaponHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        equippedWeaponHolderLayout.setVerticalGroup(
            equippedWeaponHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
        );

        equippedArmorHolder.setMaximumSize(new java.awt.Dimension(366, 58));
        equippedArmorHolder.setMinimumSize(new java.awt.Dimension(366, 58));

        javax.swing.GroupLayout equippedArmorHolderLayout = new javax.swing.GroupLayout(equippedArmorHolder);
        equippedArmorHolder.setLayout(equippedArmorHolderLayout);
        equippedArmorHolderLayout.setHorizontalGroup(
            equippedArmorHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        equippedArmorHolderLayout.setVerticalGroup(
            equippedArmorHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel1.setText("Equipped Armor");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel2.setText("Equipped Weapon");

        topText.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        topText.setText("Jared | 183 [Health] | 226 Coins");

        selectedItemHolder.setMaximumSize(new java.awt.Dimension(366, 58));
        selectedItemHolder.setMinimumSize(new java.awt.Dimension(366, 58));

        javax.swing.GroupLayout selectedItemHolderLayout = new javax.swing.GroupLayout(selectedItemHolder);
        selectedItemHolder.setLayout(selectedItemHolderLayout);
        selectedItemHolderLayout.setHorizontalGroup(
            selectedItemHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 376, Short.MAX_VALUE)
        );
        selectedItemHolderLayout.setVerticalGroup(
            selectedItemHolderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 58, Short.MAX_VALUE)
        );

        inventoryList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        inventoryList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                inventoryListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(inventoryList);

        equipButton.setText("Equip");
        equipButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                equipButtonActionPerformed(evt);
            }
        });

        consumeButton.setText("Consume");
        consumeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consumeButtonActionPerformed(evt);
            }
        });

        dropButton.setText("Drop");
        dropButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropButtonActionPerformed(evt);
            }
        });

        numItemsLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        numItemsLabel.setText("0/10");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(equippedWeaponHolder, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
            .addComponent(equippedArmorHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(equipButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dropButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(numItemsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(consumeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jSeparator1)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(topText, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(selectedItemHolder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equippedWeaponHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(equippedArmorHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(numItemsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(equipButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(consumeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dropButton))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectedItemHolder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void equipButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_equipButtonActionPerformed
        int selectedIndex = inventoryList.getSelectedIndex();
        disableButtons(); // quickly disable buttons
        if (selectedIndex >= 0 && selectedIndex < inventoryItems.size()) { // validate
            Item selection = inventoryItems.get(selectedIndex);
            if (selection instanceof Weapon || selection instanceof Armor) { // further validate
                selectedItem.becomeLabel(selection.getName() + " equipped.");
                viewManager.equipOrConsumePressed(selectedIndex);
            }
        }
    }//GEN-LAST:event_equipButtonActionPerformed

    private void inventoryListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_inventoryListValueChanged
        if (!evt.getValueIsAdjusting()) {
            confirmDrop = false;
            int selectedIndex = inventoryList.getSelectedIndex();
            if (selectedIndex < 0) {
                // no selection...
                disableButtons();
                if (!selectedItem.isDisplayingMessage()) { // only clear if it was displaying an item
                    selectedItem.setAsInvisible();
                }
            } else if (selectedIndex < inventoryItems.size()) { // validate
                Item selection = inventoryItems.get(selectedIndex);
                selectedItem.setItem(selection);
                // enable correct buttons:
                dropButton.setEnabled(true);
                if (selection instanceof Weapon || selection instanceof Armor) {
                    consumeButton.setEnabled(false); // disable incorrect button
                    if (selectedIndex != eqWeaponIndex && selectedIndex != eqArmorIndex) {
                        // only enable equip button if item is un-equiped
                        equipButton.setEnabled(true);
                    } else {
                        equipButton.setEnabled(false); // disable incorrect button
                    }
                } else if (selection instanceof Potion) {
                    equipButton.setEnabled(false); // disable incorrect button
                    consumeButton.setEnabled(true);
                }
            }
        }
    }//GEN-LAST:event_inventoryListValueChanged

    private void consumeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consumeButtonActionPerformed
        int selectedIndex = inventoryList.getSelectedIndex();
        disableButtons(); // quickly disable buttons
        if (selectedIndex >= 0 && selectedIndex < inventoryItems.size()) { // validate
            Item selection = inventoryItems.get(selectedIndex);
            if (selection instanceof Potion) { // further validate
                selectedItem.becomeLabel(selection.getName() + " consumed.", ((Potion) selection).getSpecsString());
                viewManager.equipOrConsumePressed(selectedIndex);
            }
        }
    }//GEN-LAST:event_consumeButtonActionPerformed

    private void dropButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropButtonActionPerformed
        int selectedIndex = inventoryList.getSelectedIndex();
        disableButtons(); // quickly disable buttons
        if (selectedIndex >= 0 && selectedIndex < inventoryItems.size()) { // validate
            if (!confirmDrop && (selectedIndex == eqWeaponIndex || selectedIndex == eqWeaponIndex)) {
                confirmDrop = true; // require confirmation
                selectedItem.becomeLabel("Item equipped! Press drop again to confirm.");
                dropButton.setEnabled(true); // re-enable drop button
            } else {
                confirmDrop = false;
                selectedItem.becomeLabel(inventoryItems.get(selectedIndex).getName() + " dropped.");
                viewManager.dropPressed(selectedIndex);
            }
        }
    }//GEN-LAST:event_dropButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton consumeButton;
    private javax.swing.JButton dropButton;
    private javax.swing.JButton equipButton;
    private javax.swing.JPanel equippedArmorHolder;
    private javax.swing.JPanel equippedWeaponHolder;
    private javax.swing.JList<String> inventoryList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel numItemsLabel;
    private javax.swing.JPanel selectedItemHolder;
    private javax.swing.JLabel topText;
    // End of variables declaration//GEN-END:variables
}
