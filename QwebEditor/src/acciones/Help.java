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
import java.util.ResourceBundle;

import javax.swing.AbstractAction;

import pantallas.PMensaje;
import util.Util;

/**
 * The Class Help: classe acció que llença l'ajuda de l'editor en el navegador de l'usuari
 */
public class Help extends AbstractAction {
	private String idioma;
	private String tipoAyuda;
	private ResourceBundle bundle;

	private static final long serialVersionUID = 8047655879744098299L;
	
	/**
	 * Instantiates a new help.
	 *
	 * @param text the text
	 * @param idioma the idioma
	 */
	public Help(String text, String idioma){
		super(text);
		tipoAyuda = text;
		this.idioma = idioma;
		bundle = java.util.ResourceBundle.getBundle("i18n/"+ idioma);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (tipoAyuda.equals(bundle.getString("AyudaAutoevaluacion"))){
			Util.mostrarAyuda("autoevaluacion", idioma);
		} else if (tipoAyuda.equals(bundle.getString("AyudaTutorial"))){
			Util.mostrarAyuda("tutorial", idioma);
		} else if (tipoAyuda.equals(bundle.getString("About"))){
			new PMensaje(null, bundle.getString("about"), idioma);
		}
	}

}
