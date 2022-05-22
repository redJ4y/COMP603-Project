package View;

// @author jared
import java.util.List;

public class GameplayView extends javax.swing.JPanel {

    private final ViewManager viewManager;

    /**
     * Creates new form GameplayView
     */
    public GameplayView(ViewManager viewManager) {
        this.viewManager = viewManager;
        initComponents();
        disableButtons();
    }

    public void addText(String text) {
        textArea.append(text);
        textArea.setCaretPosition(textArea.getDocument().getLength()); // auto scroll
    }

    public void enableButtons(List<GameplayButtons> buttons) {
        disableButtons(); // for good measure, should already be disabled
        for (GameplayButtons current : buttons) {
            switch (current) {
                case N:
                    northButton.setEnabled(true);
                    break;
                case E:
                    eastButton.setEnabled(true);
                    break;
                case S:
                    southButton.setEnabled(true);
                    break;
                case W:
                    westButton.setEnabled(true);
                    break;
                case YES:
                    yesButton.setEnabled(true);
                    break;
                case NO:
                    noButton.setEnabled(true);
                    break;
                case ADVENTURE:
                    adventureButton.setEnabled(true);
                    break;
                case ATTACK:
                    attackButton.setEnabled(true);
                    break;
                case RUN:
                    runButton.setEnabled(true);
                    break;
            }
        }
    }

    private void disableButtons() {
        northButton.setEnabled(false);
        eastButton.setEnabled(false);
        southButton.setEnabled(false);
        westButton.setEnabled(false);
        adventureButton.setEnabled(false);
        attackButton.setEnabled(false);
        runButton.setEnabled(false);
        yesButton.setEnabled(false);
        noButton.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        directionPanel = new javax.swing.JPanel();
        northButton = new javax.swing.JButton();
        eastButton = new javax.swing.JButton();
        southButton = new javax.swing.JButton();
        fakeButton = new javax.swing.JButton();
        westButton = new javax.swing.JButton();
        controlPanel = new javax.swing.JPanel();
        adventureButton = new javax.swing.JButton();
        attackButton = new javax.swing.JButton();
        runButton = new javax.swing.JButton();
        yesButton = new javax.swing.JButton();
        noButton = new javax.swing.JButton();
        quitButton = new javax.swing.JButton();

        scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setFocusable(false);

        textArea.setEditable(false);
        textArea.setBackground(new java.awt.Color(51, 51, 51));
        textArea.setColumns(20);
        textArea.setFont(new java.awt.Font("Palatino Linotype", 0, 10)); // NOI18N
        textArea.setRows(5);
        textArea.setFocusable(false);
        scrollPane.setViewportView(textArea);

        northButton.setText("N");
        northButton.setFocusable(false);
        northButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                northButtonActionPerformed(evt);
            }
        });

        eastButton.setText("E");
        eastButton.setFocusable(false);
        eastButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eastButtonActionPerformed(evt);
            }
        });

        southButton.setText("S");
        southButton.setFocusable(false);
        southButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                southButtonActionPerformed(evt);
            }
        });

        fakeButton.setText(" ");
        fakeButton.setEnabled(false);
        fakeButton.setFocusable(false);

        westButton.setText("W");
        westButton.setFocusable(false);
        westButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                westButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout directionPanelLayout = new javax.swing.GroupLayout(directionPanel);
        directionPanel.setLayout(directionPanelLayout);
        directionPanelLayout.setHorizontalGroup(
            directionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(directionPanelLayout.createSequentialGroup()
                .addGroup(directionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, directionPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(westButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fakeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eastButton))
                    .addGroup(directionPanelLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(directionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(southButton)
                            .addComponent(northButton))))
                .addContainerGap())
        );
        directionPanelLayout.setVerticalGroup(
            directionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(directionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(northButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(directionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eastButton)
                    .addComponent(fakeButton)
                    .addComponent(westButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(southButton)
                .addContainerGap())
        );

        adventureButton.setText("Adventure");
        adventureButton.setFocusable(false);
        adventureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adventureButtonActionPerformed(evt);
            }
        });

        attackButton.setText("Attack");
        attackButton.setFocusable(false);
        attackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attackButtonActionPerformed(evt);
            }
        });

        runButton.setText("Run");
        runButton.setFocusable(false);
        runButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runButtonActionPerformed(evt);
            }
        });

        yesButton.setText("Yes");
        yesButton.setFocusable(false);
        yesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesButtonActionPerformed(evt);
            }
        });

        noButton.setText("No");
        noButton.setFocusable(false);
        noButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noButtonActionPerformed(evt);
            }
        });

        quitButton.setText("Save/Quit");
        quitButton.setFocusable(false);
        quitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(controlPanelLayout.createSequentialGroup()
                        .addComponent(yesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(noButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(quitButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPanelLayout.createSequentialGroup()
                        .addComponent(adventureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(attackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(runButton, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)))
                .addContainerGap())
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adventureButton)
                    .addComponent(attackButton)
                    .addComponent(runButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(yesButton)
                    .addComponent(noButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quitButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(directionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(directionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(controlPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void eastButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eastButtonActionPerformed
        disableButtons();
        viewManager.gameplayButtonPressed(GameplayButtons.E);
    }//GEN-LAST:event_eastButtonActionPerformed

    private void southButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_southButtonActionPerformed
        disableButtons();
        viewManager.gameplayButtonPressed(GameplayButtons.S);
    }//GEN-LAST:event_southButtonActionPerformed

    private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runButtonActionPerformed
        disableButtons();
        viewManager.gameplayButtonPressed(GameplayButtons.RUN);
    }//GEN-LAST:event_runButtonActionPerformed

    private void adventureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adventureButtonActionPerformed
        disableButtons();
        viewManager.gameplayButtonPressed(GameplayButtons.ADVENTURE);
    }//GEN-LAST:event_adventureButtonActionPerformed

    private void attackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attackButtonActionPerformed
        disableButtons();
        viewManager.gameplayButtonPressed(GameplayButtons.ATTACK);
    }//GEN-LAST:event_attackButtonActionPerformed

    private void yesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesButtonActionPerformed
        disableButtons();
        viewManager.gameplayButtonPressed(GameplayButtons.YES);
    }//GEN-LAST:event_yesButtonActionPerformed

    private void noButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noButtonActionPerformed
        disableButtons();
        viewManager.gameplayButtonPressed(GameplayButtons.NO);
    }//GEN-LAST:event_noButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitButtonActionPerformed
        disableButtons();
        viewManager.gameplayButtonPressed(GameplayButtons.QUIT);
    }//GEN-LAST:event_quitButtonActionPerformed

    private void northButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_northButtonActionPerformed
        disableButtons();
        viewManager.gameplayButtonPressed(GameplayButtons.N);
    }//GEN-LAST:event_northButtonActionPerformed

    private void westButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_westButtonActionPerformed
        disableButtons();
        viewManager.gameplayButtonPressed(GameplayButtons.W);
    }//GEN-LAST:event_westButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adventureButton;
    private javax.swing.JButton attackButton;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JPanel directionPanel;
    private javax.swing.JButton eastButton;
    private javax.swing.JButton fakeButton;
    private javax.swing.JButton noButton;
    private javax.swing.JButton northButton;
    private javax.swing.JButton quitButton;
    private javax.swing.JButton runButton;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton southButton;
    private javax.swing.JTextArea textArea;
    private javax.swing.JButton westButton;
    private javax.swing.JButton yesButton;
    // End of variables declaration//GEN-END:variables
}
