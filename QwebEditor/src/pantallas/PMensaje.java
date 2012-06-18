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
package pantallas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import javax.swing.JDialog;

import util.Util;

/**
 * The Class PMensaje: pantalla que s'utilitza al llarg de l'aplicació per a mostrar
 * missatges a l'usuari
 */
public class PMensaje extends javax.swing.JDialog {

	private static final long serialVersionUID = -8079844738081332260L;
	private ResourceBundle bundle;
	private String mensaje;
	private JDialog parent;
    
    /**
     * Instantiates a new p mensaje.
     *
     * @param parent the parent
     * @param mensaje the mensaje
     * @param idioma the idioma
     */
	//missatge estàndard
    public PMensaje(JDialog parent, String mensaje, String idioma) {
    	super(parent, true);
    	bundle = java.util.ResourceBundle.getBundle("i18n/"+ idioma);
    	this.mensaje = mensaje;
    	this.parent = parent;
        initComponents();
    }
    //missatge corresponen a excepció
    /**
     * Instantiates a new p mensaje.
     *
     * @param parent the parent
     * @param e the e
     * @param idioma the idioma
     */
    public PMensaje(JDialog parent, Exception e, String idioma) {
    	super(parent, true);
    	bundle = java.util.ResourceBundle.getBundle("i18n/"+ idioma);
    	this.mensaje = bundle.getString("excepcion") + "\n\n" + e.getMessage();
    	this.parent = parent;
        initComponents();
    }
    //missatge corresponent a error
    /**
     * Instantiates a new p mensaje.
     *
     * @param parent the parent
     * @param e the e
     * @param idioma the idioma
     */
    public PMensaje(JDialog parent, Error e, String idioma) {
    	super(parent, true);
    	bundle = java.util.ResourceBundle.getBundle("i18n/"+ idioma);
    	this.mensaje = bundle.getString("excepcion") + "\n\n" + e.getMessage();
    	this.parent = parent;
        initComponents();
    }
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setTitle(bundle.getString("PMensaje"));
        setLocationRelativeTo(parent);
        setMinimumSize(new java.awt.Dimension(150, 100));
        setResizable(false);
        setIconImage(Util.getIconImage());

        jButton3.setText(bundle.getString("Aceptar"));
        jButton3.addActionListener(new ListenerBotones());

        jTextArea1.setBackground(null);
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 11));
        jScrollPane1.setViewportView(jTextArea1);
        jTextArea1.setText(mensaje);
        jScrollPane1.setBorder(null);
        jTextArea1.setBorder(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );
        pack();
        setVisible(true);
    }
    private class ListenerBotones implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent evt) {
			if (evt.getSource()==jButton3){
				dispose();
			}
		}
    }

    // Variables declaration
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration
}