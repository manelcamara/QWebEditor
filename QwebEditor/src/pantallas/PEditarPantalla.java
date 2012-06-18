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
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import util.ArbolCuestionario;
import util.Util;
import beans.Cuestionario;
import beans.Pregunta;

/**
 * The Class PEditarPantalla: pantalla per a editar pantalles (tutorials)
 */
public class PEditarPantalla extends javax.swing.JDialog {
	
	private static final long serialVersionUID = 3321474332480780738L;
	private JFrame parent;
	private Cuestionario cuestionario;
	private int idPregunta;
	private Pregunta pregunta;
	@SuppressWarnings("unused")
	private ArbolCuestionario arbol;
	private boolean isNuevaPregunta;
	private java.util.ResourceBundle bundle;
	private String idioma;
	private JDialog esteDialog;
	private String nombreImagen;
	
	/**
	 * Instantiates a new p editar pantalla.
	 *
	 * @param parent the parent
	 * @param cuestionario the cuestionario
	 * @param idioma the idioma
	 */
    public PEditarPantalla(JFrame parent, Cuestionario cuestionario, String idioma) {
    	this.parent = parent;
    	this.cuestionario = cuestionario;
    	this.idioma = idioma;
    	bundle = java.util.ResourceBundle.getBundle("i18n/"+ idioma);
    	idPregunta = cuestionario.getNumeroDePreguntas()+1;
		pregunta = new Pregunta();
		isNuevaPregunta = true;
		pregunta.setIdPregunta(idPregunta);
		this.cuestionario.addPregunta(pregunta);
		esteDialog = this;
		jLabel6 = new javax.swing.JLabel();
        initComponents();
    }
    
    /**
     * Instantiates a new p editar pantalla.
     *
     * @param parent the parent
     * @param cuestionario the cuestionario
     * @param idioma the idioma
     * @param idPregunta the id pregunta
     */
    public PEditarPantalla(JFrame parent, Cuestionario cuestionario, String idioma, int idPregunta) {
    	this.parent = parent;
    	this.cuestionario = cuestionario;
    	this.idioma = idioma;
    	bundle = java.util.ResourceBundle.getBundle("i18n/"+ idioma);
    	this.idPregunta = idPregunta;
		isNuevaPregunta = false;
		this.cuestionario = cuestionario;
		pregunta = this.cuestionario.getPregunta(idPregunta);
		esteDialog = this;
		jLabel6 = new javax.swing.JLabel(pregunta.getImagenPregunta());
		nombreImagen = pregunta.getImagenPregunta();
        initComponents();
    }
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        
        setTitle(bundle.getString("PEditarPantalla")); // NOI18N
        setLocation(300,200);
        setResizable(false);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setIconImage(Util.getIconImage());

        jLabel1.setText(bundle.getString("TituloTutorial"));
        jLabel2.setText(bundle.getString("TituloPantalla"));
        jTextField1.setText(cuestionario.getTituloCuestionario());
        jTextField1.setEditable(false);
        jTextField3.setText(pregunta.getTextoCortoPregunta());
        jLabel4.setText(bundle.getString("ExplicacionPantalla"));
        jTextArea1.setColumns(20);
        jTextArea1.setRows(3);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 0, 11));
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setText(pregunta.getTextoPregunta());
        jScrollPane2.setViewportView(jTextArea1);
        jLabel5.setText(bundle.getString("ImagenPregunta"));
        jLabel3.setText(bundle.getString("VisualizadorTutorial"));
        jButton7.setText(bundle.getString("GuardarPantalla"));
        jButton8.setText(bundle.getString("Salir"));
        if (nombreImagen != null){
        	jButton9.setText("eliminar imagen");
        } else{
        	jButton9.setText(bundle.getString("ElegirImagen"));
        }
        jButton1.setText(bundle.getString("EliminarPantalla"));
        jButton2.setText(bundle.getString("Ayuda"));
        jButton2.setFocusable(false);
        
        
        //ActionListeners dels botons
		jButton7.addActionListener(new ListenerBotones());
		jButton8.addActionListener(new ListenerBotones());
		jButton1.addActionListener(new ListenerBotones());
		jButton2.addActionListener(new ListenerBotones());
		jButton9.addActionListener(new ListenerBotones());
		
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
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
                            .addComponent(jLabel2))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton8))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                                .addComponent(jButton2)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(19, 19, 19))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jButton8)
                            .addGap(16, 16, 16)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setVisible(true);
    }
    private class ListenerBotones implements ActionListener {
		public void actionPerformed(ActionEvent evt){
			if (evt.getSource()==jButton8){
				//salir
				if (isNuevaPregunta){
					cuestionario.removePregunta(idPregunta-1);
				}
				//Util.eliminaRecurso(nombreImagen);
				dispose();
				new PEditarTutorial(parent, cuestionario, idioma);
			}else if (evt.getSource()==jButton7){
				//desar pantalla
				if (jTextArea1.getText().equals("")){
					new PMensaje(esteDialog, bundle.getString("ExplicacionPantallaVacia"), idioma);
				} else if (jTextField3.getText().equals("")){
					new PMensaje(esteDialog, bundle.getString("TituloPantallaVacio"), idioma);
				} else {
					pregunta.setTextoCortoPregunta(jTextField3.getText());
					pregunta.setTextoPregunta(jTextArea1.getText());
					if (nombreImagen == null && pregunta.getImagenPregunta() != null) 
													Util.eliminaRecurso(pregunta.getImagenPregunta());
					pregunta.setImagenPregunta(nombreImagen);
					dispose();
					new PEditarTutorial(parent, cuestionario, idioma);
				}
			} else if (evt.getSource()==jButton9){
				//triar o eliminar imatge
				if (nombreImagen != null){
					jLabel6.setText("");
					Util.eliminaRecurso(nombreImagen);
					pregunta.setImagenPregunta(null);
					nombreImagen = null;
					jButton9.setText("ElegirImagen");
					jButton9.setFocusable(false);
				} else {
					JFileChooser fileChooser = new JFileChooser();
					int seleccion = fileChooser.showOpenDialog(parent);
					if (seleccion == JFileChooser.APPROVE_OPTION){
						String rutaImagen = fileChooser.getSelectedFile().getAbsolutePath();
						if (Util.validarImagen(rutaImagen)){
							nombreImagen = fileChooser.getSelectedFile().getName();
							//pregunta.setImagenPregunta(nombreImagen);
							File imagenOrigen = new File(rutaImagen);
							Util.guardarImagen(imagenOrigen, idioma);
							jLabel6.setText(nombreImagen);
							jButton9.setText(bundle.getString("EliminarImagen"));
							jButton9.setFocusable(false);
						} else {
							new PMensaje(esteDialog, bundle.getString("imagenNoValida"), idioma);
						}
					}
				}
				
			} else if (evt.getSource()==jButton1){
				//eliminar pantalla
				if (isNuevaPregunta){
					cuestionario.removePregunta(idPregunta-1);
				} else {
					Util.eliminaRecurso(nombreImagen);
					cuestionario.removePregunta(idPregunta);
				}
				dispose();
				new PEditarTutorial(parent, cuestionario, idioma);
			} else if (evt.getSource()==jButton2){
				Util.mostrarAyuda("tutorial", idioma);
			}
		}
	}
    
    // Variables declaration
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JButton jButton9;
    // End of variables declaration
}


