/*
    Copyright (C) 2012 Manel Cámara

    This file is part of Q?WebEditor and/or Q?WebScormApp.
    
    Q?WebEditor&Q?WebScormApp is free software: you can redistribute it 
    and/or modify it under the terms of the GNU General Public License
    as published by the Free Software Foundation, either version 3 
    of the License, or any later version.

    Q?WebEditor&Q?WebScormApp is distributed in the hope that it will 
    be useful, but WITHOUT ANY WARRANTY; without even the implied 
    warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
     
    See the GNU General Public License for more details at
	http://www.gnu.org/licenses/#GPL
*/
import java.util.Locale;

import pantallas.PMensaje;
import util.Util;

/**
 * The Class Entrada: classe d'entrada a l'aplicació, permet triar idioma de la GUI
 */
public class Entrada extends javax.swing.JDialog {
	
	private static final long serialVersionUID = 8035608336742131855L;
	private java.util.ResourceBundle bundle;
    
    /**
     * Instantiates a new entrada.
     */
    public Entrada() {
    	bundle = java.util.ResourceBundle.getBundle("i18n/ca_ES");
        initComponents();
    }
    
    private void initComponents() {
    	//set LookAndFeel del sistema
    	Util.setLookAndFeel("ca_es");
    	buttonGroup1 = new javax.swing.ButtonGroup();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(bundle.getString("Q?WEB"));
        setLocation(300,200);
        setMaximumSize(new java.awt.Dimension(191, 148));
        setMinimumSize(new java.awt.Dimension(191, 148));
        setResizable(false);
        setIconImage(Util.getIconImage());

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText(bundle.getString("Catalan"));
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText(bundle.getString("Español"));
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        jButton1.setText(bundle.getString("OK"));
        jButton1.addActionListener(new java.awt.event.ActionListener(){
        	public void actionPerformed(java.awt.event.ActionEvent evt){
        		jButton1ActionPerformed(evt);
        	}
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton2)
                            .addComponent(jRadioButton1))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        idioma = "ca_ES";
        Locale locale = new Locale("ca", "ES");
        Locale.setDefault(locale);
    }
    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        idioma = "es_ES";
        Locale locale = new Locale("es", "ES");
        Locale.setDefault(locale);
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt){
    	new PMensaje(this, bundle.getString("about"), idioma);
    	new Main(idioma);
    	dispose();
    	
    }
    
    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String args[]) {
        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Entrada().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private String idioma= "";
    // End of variables declaration
}
