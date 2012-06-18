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
package acciones;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import pantallas.PEditarCuestionario;
import pantallas.PEditarTutorial;
import pantallas.PInput;
import beans.Cuestionario;

/**
 * The Class NuevoCuestionario: classe acció que permet donar d'alta nous qüestionaris
 * (autoavaluació o tutorials)
 */
public class NuevoCuestionario extends AbstractAction {
	private JFrame parent;
	private java.util.ResourceBundle bundle;
	private String idioma;
	private String tipoCuestionario;
	private static final long serialVersionUID = 8047655879744098299L;
	
	/**
	 * Instantiates a new nuevo cuestionario.
	 *
	 * @param text the text
	 * @param parent the parent
	 * @param idioma the idioma
	 * @param tipoCuestionario the tipo cuestionario
	 */
	public NuevoCuestionario(String text, JFrame parent, String idioma, String tipoCuestionario){
		super(text);
		this.parent = parent;
		this.idioma = idioma;
		this.tipoCuestionario = tipoCuestionario;
		bundle = java.util.ResourceBundle.getBundle("i18n/"+ idioma);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		PInput input;
		if (tipoCuestionario.equals("autoevaluacion")){
			input = new PInput(null, bundle.getString("TituloAutoevaluacion?"), idioma);
		} else {
			input = new PInput(null, bundle.getString("TituloTutorial?"), idioma);
		}
		String tituloNuevoCuestionario = input.getTitulo();
		String presentacionNuevoCuestionario = input.getPresentacion();
		String idiomaNuevoCuestionario = input.getIdiomaCuestionario();
		if ((tituloNuevoCuestionario != null) && (tituloNuevoCuestionario.length()>0)) {
			Cuestionario cuestionario = new Cuestionario(tipoCuestionario);
			cuestionario.setTituloCuestionario(tituloNuevoCuestionario);
			cuestionario.setPresentacion(presentacionNuevoCuestionario);
			cuestionario.setIdioma(idiomaNuevoCuestionario);
			if (tipoCuestionario.equals("autoevaluacion")){
				new PEditarCuestionario(parent, cuestionario, idioma);
			} else if (tipoCuestionario.equals("tutorial")){
				new PEditarTutorial(parent, cuestionario, idioma);
			}
		}
	}

}
