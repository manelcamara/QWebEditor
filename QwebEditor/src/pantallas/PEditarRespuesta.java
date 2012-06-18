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
import javax.swing.JFrame;
import util.ArbolCuestionario;
import util.Util;
import beans.Cuestionario;
import beans.Pregunta;
import beans.Respuesta;

/**
 * The Class PEditarRespuesta: pantalla per a editar respostes
 */
public class PEditarRespuesta extends javax.swing.JDialog {

	private static final long serialVersionUID = 193116195951856669L;
	private Cuestionario cuestionario;
	private JFrame parent;
	private int idPregunta;
	private int idRespuesta;
	private Pregunta pregunta;
	private Respuesta respuesta;
	@SuppressWarnings("unused")
	private ArbolCuestionario arbol;
	private boolean isNuevaRespuesta;
	private String idioma;
	private ResourceBundle bundle;
	private JDialog esteDialog;
	
	/**
	 * Instantiates a new p editar respuesta.
	 *
	 * @param f the f
	 * @param cuestionario the cuestionario
	 * @param idPregunta the id pregunta
	 * @param idioma the idioma
	 */
	public PEditarRespuesta(JFrame f, Cuestionario cuestionario, int idPregunta, String idioma) {
		//nova resposta
		parent = f;
		this.cuestionario = cuestionario;
		this.idPregunta = idPregunta;
		isNuevaRespuesta = true;
		idRespuesta = cuestionario.getPregunta(idPregunta).getNumeroDeRespuestas();
		pregunta = cuestionario.getPregunta(idPregunta);
		this.idioma = idioma;
		bundle = java.util.ResourceBundle.getBundle("i18n/"+ idioma);
		esteDialog = this;
		initComponents();
	}
	
	/**
	 * Instantiates a new p editar respuesta.
	 *
	 * @param f the f
	 * @param cuestionario the cuestionario
	 * @param idPregunta the id pregunta
	 * @param idRespuesta the id respuesta
	 * @param idioma the idioma
	 */
	public PEditarRespuesta(JFrame f, Cuestionario cuestionario, int idPregunta, int idRespuesta, String idioma) {
		//editar resposta existent
		parent = f;
		this.idPregunta = idPregunta;
		this.idRespuesta = idRespuesta;
		this.cuestionario = cuestionario;
		isNuevaRespuesta = false;
		pregunta = cuestionario.getPregunta(idPregunta);
		respuesta = pregunta.getRespuesta(idRespuesta);
		this.idioma = idioma;
		bundle = java.util.ResourceBundle.getBundle("i18n/"+ idioma);
		esteDialog = this;
		initComponents();
	}

    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        
        setTitle(bundle.getString("PEditarRespuesta")); // NOI18N
        setLocation(300,200);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setIconImage(Util.getIconImage());

        jLabel1.setText(bundle.getString("TituloCuestionario"));
        jLabel4.setText(bundle.getString("Pregunta"));
        jLabel6.setText(bundle.getString("Respuesta"));
        jLabel5.setText(bundle.getString("TipoDeRespuesta"));
        jLabel3.setText(bundle.getString("VisualizadorCuestionario"));
        jTextField1.setText(cuestionario.getTituloCuestionario());
        jTextField1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(2);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 11));
        jScrollPane2.setViewportView(jTextArea1);
        jTextArea1.setText(pregunta.getTextoPregunta());
		jTextArea1.setEditable(false);
		jTextArea1.setLineWrap(true);
		jTextArea1.setWrapStyleWord(true);
		jTextArea1.setBackground(null);
		jTextArea2.setColumns(20);
        jTextArea2.setRows(2);
        jTextArea2.setFont(new java.awt.Font("Tahoma", 0, 11));
        jScrollPane3.setViewportView(jTextArea2);
		jTextArea2.setLineWrap(true);
		jTextArea2.setWrapStyleWord(true);
		if (!isNuevaRespuesta){
			jTextArea2.setText(respuesta.getTextoRespuesta());
			if (respuesta.isTipoRespuesta()){
				jRadioButton1.setSelected(true);
			} else {
				jRadioButton2.setSelected(true);
			}
		}
		buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText(bundle.getString("RespuestaCorrecta"));
        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText(bundle.getString("RespuestaIncorrecta"));
        jButton5.setText(bundle.getString("GuardarRespuesta"));
        jButton8.setText(bundle.getString("Salir"));
        jButton2.setText(bundle.getString("Ayuda"));
        jButton2.setFocusable(false);
        jButton1.setText(bundle.getString("EliminarRespuesta"));
        jButton5.addActionListener(new ListenerBotones());
		jButton8.addActionListener(new ListenerBotones());
		jButton1.addActionListener(new ListenerBotones());
		jButton2.addActionListener(new ListenerBotones());
		
		//visualitzador de l'arbre qüestionari
		jScrollPane1 = new javax.swing.JScrollPane(new ArbolCuestionario(cuestionario));
		jScrollPane1.setAutoscrolls(true);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                                .addGap(154, 154, 154))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addGap(51, 51, 51)))
                        .addComponent(jButton8))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4))
                                .addGap(70, 70, 70))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 163, Short.MAX_VALUE)
                                .addComponent(jButton2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jRadioButton1)
                                    .addGap(18, 18, 18)
                                    .addComponent(jRadioButton2))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
                                    .addComponent(jScrollPane3))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(47, 47, 47)
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 7, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton8)
                            .addComponent(jButton1))
                        .addGap(27, 27, 27)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setVisible(true);
    }
    private class ListenerBotones implements ActionListener {
		public void actionPerformed(ActionEvent evt){
			if (evt.getSource()==jButton8) {
				dispose();
				new PEditarPregunta(parent, cuestionario, idioma, idPregunta);
			}else if (evt.getSource()==jButton5){
				if (jTextArea2.getText().equals("")){
					new PMensaje(esteDialog, bundle.getString("RespuestaVacia"), idioma);
				} else if (jRadioButton1.isSelected() && pregunta.contieneRespuestaCorrecta()){
					new PMensaje(esteDialog, bundle.getString("YaHayRespuestaCorrecta"), idioma);
				} else if (!jRadioButton1.isSelected() && !jRadioButton2.isSelected()){
					new PMensaje(esteDialog, bundle.getString("FaltaTipoRespuesta"), idioma);
				} else {
					if (isNuevaRespuesta){
						respuesta = new Respuesta();
						respuesta.setIdRespuesta(idRespuesta);
						pregunta.addRespuesta(respuesta);
					}
					respuesta.setTextoRespuesta(jTextArea2.getText());
					respuesta.setTipoRespuesta(jRadioButton1.isSelected());
					dispose();
					new PEditarPregunta(parent, cuestionario, idioma, idPregunta);
				}
			} else if (evt.getSource()==jButton2){
				Util.mostrarAyuda("autoevaluacion", idioma);
			} else if (evt.getSource()==jButton1){
				if (!isNuevaRespuesta) pregunta.removeRespuesta(idRespuesta);
				dispose();
				new PEditarPregunta(parent, cuestionario, idioma, idPregunta);
			}
		}
	}

    // Variables declaration
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration
}

