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
import java.util.ResourceBundle;
import util.Util;
import acciones.EditarCuestionario;
import acciones.Help;
import acciones.NuevoCuestionario;
import acciones.Salir;

/**
 * The Class Main: JFrame amb el menú principal de l'aplicació
 */
public class Main extends javax.swing.JFrame {

	private static final long serialVersionUID = 7692985897203143855L;
	private ResourceBundle bundle;
	
	/**
	 * Instantiates a new main.
	 *
	 * @param idioma the idioma
	 */
    public Main(String idioma) {
    	this.idioma = idioma;
    	bundle = java.util.ResourceBundle.getBundle("i18n/"+ idioma);
    	Util.localizaAplicacion(bundle, idioma);
        initComponents();
    }

    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();//Objetos elearning
        jMenuItem1 = new javax.swing.JMenuItem();//nuevo autoevaluacion
        jMenuItem2 = new javax.swing.JMenuItem();//editar autoevaluacion
        jMenuItem3 = new javax.swing.JMenuItem();//salir
        jMenuItem4 = new javax.swing.JMenuItem();//ajuda qüestionaris autoavaluació
        jMenuItem7 = new javax.swing.JMenuItem();//ajuda tutorials
        jMenuItem8 = new javax.swing.JMenuItem();//sobre Q?Web
        jMenuItem5 = new javax.swing.JMenuItem();//nuevo tutorial
        jMenuItem6 = new javax.swing.JMenuItem();//editar tutorial
        jMenu2 = new javax.swing.JMenu();//salir
        jMenu3 = new javax.swing.JMenu();//ayuda
        jMenu4 = new javax.swing.JMenu();//autoevaluacion
        jMenu5 = new javax.swing.JMenu();//tutorial

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(bundle.getString("Q?WEB")); // NOI18N
        setLocation(300,200);
        setResizable(false);
        setIconImage (Util.getIconImage());

        jMenu1.setText(bundle.getString("ObjetosElearning")); // NOI18N
        jMenu4.setText(bundle.getString("CuestionarioAutoevaluacion"));
        jMenu5.setText(bundle.getString("Tutorial"));
        jMenu1.add(jMenu4);
        jMenu1.add(jMenu5);

        jMenuItem1.setText(bundle.getString("NuevoCuestionario")); // NOI18N
        jMenuItem2.setText(bundle.getString("EditarCuestionario"));
        jMenu4.add(jMenuItem1);
        jMenu4.add(jMenuItem2);
        
        jMenuItem5.setText(bundle.getString("NuevoCuestionario"));
        jMenuItem6.setText(bundle.getString("EditarCuestionario"));
        jMenu5.add(jMenuItem5);
        jMenu5.add(jMenuItem6);
        jMenuItem1.setAction(new NuevoCuestionario(bundle.getString("NuevoCuestionario"), this, idioma, "autoevaluacion"));
        jMenuItem2.setAction(new EditarCuestionario(bundle.getString("EditarCuestionario"), this, idioma, "autoevaluacion"));
        jMenuItem5.setAction(new NuevoCuestionario(bundle.getString("NuevoCuestionario"), this, idioma, "tutorial"));
        jMenuItem6.setAction(new EditarCuestionario(bundle.getString("EditarCuestionario"), this, idioma, "tutorial"));
        jMenuBar1.add(jMenu1);
        
        jMenu3.setText(bundle.getString("Ayuda"));
        jMenuItem4.setAction(new Help(bundle.getString("AyudaAutoevaluacion"), idioma));
        jMenuItem7.setAction(new Help(bundle.getString("AyudaTutorial"), idioma));
        jMenuItem8.setAction(new Help(bundle.getString("About"), idioma));
        jMenu3.add(jMenuItem4);
        jMenu3.add(jMenuItem7);
        jMenu3.add(jMenuItem8);
        jMenuBar1.add(jMenu3);

        jMenu2.setText(bundle.getString("Salir")); // NOI18N
        jMenu2.setToolTipText("");
        jMenu2.add(jMenuItem3);
        jMenuItem3.setAction(new Salir(bundle.getString("Salir"), idioma));
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 276, Short.MAX_VALUE)
        );

        pack();
        setVisible(true);
    }
    // Variables declaration
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;

    private String idioma = "";
    // End of variables declaration
}
