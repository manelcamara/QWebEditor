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
 * The Class PInput: pantalla utilitzada al llarg de l'aplicació per demanar
 * entrada de dades a l'usuari
 */
public class PInput extends javax.swing.JDialog {

	private static final long serialVersionUID = 4405074383560176869L;
	private ResourceBundle bundle;
	private String mensaje;
	private JDialog parent;
	private String idioma;
	private JDialog esteDialog;
	private String titulo;
	private String presentacion;
	private String idiomaCuestionario;
	
	/**
	 * Instantiates a new p input.
	 *
	 * @param parent the parent
	 * @param mensaje the mensaje
	 * @param idioma the idioma
	 */
    public PInput(JDialog parent, String mensaje, String idioma) {
    	super(parent, true);
    	bundle = java.util.ResourceBundle.getBundle("i18n/"+ idioma);
    	this.mensaje = mensaje;
    	this.parent = parent;
    	this.idioma = idioma;
    	esteDialog = this;
        initComponents();
    }
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();

        setTitle(bundle.getString("PInput"));
        setLocationRelativeTo(parent);
        setMinimumSize(new java.awt.Dimension(191, 148));
        setResizable(false);
        setIconImage(Util.getIconImage());

        jButton3.setText(bundle.getString("Aceptar"));
        jButton1.setText(bundle.getString("Cancelar"));
        jLabel1.setText(mensaje);
        jLabel2.setText(bundle.getString("TextoPresentacion"));
        jLabel3.setText(bundle.getString("Idioma"));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 11));
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);
        jComboBox1.addItem(new ItemCombo(bundle.getString("Catalan"), "ca_ES"));
        jComboBox1.addItem(new ItemCombo(bundle.getString("Español"), "es_ES"));
                
        jButton3.addActionListener(new ListenerBotones());
        jButton1.addActionListener(new ListenerBotones());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3)
                                    .addGap(8, 8, 8)
                                    .addComponent(jButton1))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(73, 73, 73)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField1)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE))))
                            .addContainerGap()))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton1))
                        .addGap(19, 19, 19))))
        );

        pack();
        setVisible(true);
    }
    private class ListenerBotones implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent evt) {
			if (evt.getSource()==jButton1){
				dispose();
			} else if (evt.getSource()==jButton3){
				if (jTextField1.getText().equals("")) {
					new PMensaje(esteDialog, bundle.getString("ErrorTituloCuestionario"), idioma);
				} else if (jTextArea1.getText().equals("")) {
					new PMensaje(esteDialog, bundle.getString("ErrorTextoPresentacionCuestionario"), idioma);
				} else {
					titulo = jTextField1.getText();
					presentacion = jTextArea1.getText();
					idiomaCuestionario = ((ItemCombo)jComboBox1.getSelectedItem()).getValor();
					esteDialog.setVisible(false);
				}
			}
			
		}
    	
    }
    private class ItemCombo{
    	private String etiqueta;
    	private String valor;
    	public ItemCombo(String etiqueta, String valor){
    		this.etiqueta = etiqueta;
    		this.valor = valor;
    	}
    	private String getValor(){
    		return valor;
    	}
    	@Override
    	public String toString(){
    		return etiqueta;
    	}
    }
    
    /**
     * Gets the titulo.
     *
     * @return the titulo
     */
    public String getTitulo(){
    	return titulo;
    }
    
    /**
     * Gets the presentacion.
     *
     * @return the presentacion
     */
    public String getPresentacion(){
    	return presentacion;
    }
    
    /**
     * Gets the idioma cuestionario.
     *
     * @return the idioma cuestionario
     */
    public String getIdiomaCuestionario(){
    	return idiomaCuestionario;
    }
    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;//presentacion
    private javax.swing.JLabel jLabel3;//idioma
    private javax.swing.JTextField jTextField1;
    private javax.swing.JScrollPane jScrollPane1;//contenidor de la presentació
    private javax.swing.JTextArea jTextArea1;//presentació
    private javax.swing.JComboBox jComboBox1;//idioma
    // End of variables declaration
}
