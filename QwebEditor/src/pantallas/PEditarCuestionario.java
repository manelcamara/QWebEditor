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
import javax.swing.JDialog;
import javax.swing.JFrame;
import util.ArbolCuestionario;
import util.Util;
import beans.Cuestionario;

/**
 * The Class PEditarCuestionario: pantalla edició qüestionaris (autoavaluació)
 */
public class PEditarCuestionario extends javax.swing.JDialog{

	private static final long serialVersionUID = 7785536649779074360L;
	private JFrame parent;
	private Cuestionario cuestionario;
	private java.util.ResourceBundle bundle;
	private String idioma;
	private JDialog esteDialog;
    
    /**
     * Instantiates a new p editar cuestionario.
     *
     * @param parent the parent
     * @param cuestionario the cuestionario
     * @param idioma the idioma
     */
    public PEditarCuestionario(JFrame parent, Cuestionario cuestionario, String idioma) {
    	this.parent = parent;
    	this.cuestionario = cuestionario;
    	bundle = java.util.ResourceBundle.getBundle("i18n/"+ idioma);
    	this.idioma = idioma;
    	esteDialog = this;
        initComponents();
    }

    private void initComponents() {
    	
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        
        setTitle(bundle.getString("PEditarCuestionario")); // NOI18N
        setLocation(300,200);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setIconImage(Util.getIconImage());
        
        jLabel1.setText(bundle.getString("TituloCuestionario"));
        jLabel4.setText(bundle.getString("Idioma"));
        jLabel5.setText(bundle.getString("TextoPresentacion"));
        jTextField1.setText(cuestionario.getTituloCuestionario());
        jTextField1.setEditable(false);
        if (cuestionario.getIdioma().equals("ca_ES")){
        	jTextField2.setText(bundle.getString("Catalan"));
        } else {
        	jTextField2.setText(bundle.getString("Español"));
        }
        jTextField2.setEditable(false);
        jTextArea1.setText(cuestionario.getPresentacion());
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 11));
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jLabel2.setText(bundle.getString("Preguntas"));
        jComboBox1 = new javax.swing.JComboBox(cuestionario.getVectorPreguntas());
        
        jTextArea1.setColumns(20);
        jTextArea1.setRows(3);
        jScrollPane2.setViewportView(jTextArea1);
        
        jLabel3.setText(bundle.getString("VisualizadorCuestionario"));
        jButton5.setText(bundle.getString("NuevaPregunta"));
        jButton6.setText(bundle.getString("EditarPregunta"));
        jButton7.setText(bundle.getString("GuardarCuestionario"));
        jButton8.setText(bundle.getString("Salir"));
        jButton1.setText(bundle.getString("Ayuda"));
        jButton1.setFocusable(false);
        
        //ActionListeners dels botons
        jButton5.addActionListener(new ListenerBotones());
		jButton6.addActionListener(new ListenerBotones());
		jButton7.addActionListener(new ListenerBotones());
		jButton8.addActionListener(new ListenerBotones());
		jButton1.addActionListener(new ListenerBotones());
		
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
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                        .addComponent(jButton8)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setVisible(true);
    }

    private class ListenerBotones implements ActionListener {
		public void actionPerformed(ActionEvent evt){
			if (evt.getSource()==jButton5) {
				//nueva pregunta
				dispose();
				new PEditarPregunta(parent, cuestionario, idioma);
			}else if(evt.getSource()==jButton8){
				//salir
				dispose();
			}else if (evt.getSource()==jButton7){
				//guardar cuestionario
				cuestionario.setPresentacion(jTextArea1.getText());
					if (cuestionario.validarCuestionario()) {
						Util.cuestionarioToZIP(cuestionario, idioma);
						Util.borrarDirectorioTemporal();
						dispose();
					} else {
						new PMensaje(esteDialog, bundle.getString("CuestionarioNoValido"), idioma);
					}
			} else if (evt.getSource()==jButton6){
				//editar pregunta
				if (cuestionario.getNumeroDePreguntas()==0){
					new PMensaje(esteDialog, bundle.getString("CuestionarioSinPreguntas"), idioma);
				} else {
					dispose();
					new PEditarPregunta(parent, cuestionario, idioma, jComboBox1.getSelectedIndex());
				}
			} else if (evt.getSource()==jButton1){
				//ayuda
				Util.mostrarAyuda("autoevaluacion", idioma);
			}
		}
	}
    // Variables declaration
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;//idioma
    private javax.swing.JLabel jLabel5;//presentacion
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;//presentacion
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;//idioma
    // End of variables declaration

}

