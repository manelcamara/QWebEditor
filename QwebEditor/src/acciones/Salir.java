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
import java.io.File;
import javax.swing.AbstractAction;
import util.Util;

/**
 * The Class Salir: classe acció encarregada de fer la sortida de l'aplicació
 */
public class Salir extends AbstractAction {
	private static final long serialVersionUID = -172988704786350774L;
	
	/**
	 * Instantiates a new salir.
	 *
	 * @param text the text
	 * @param idioma the idioma
	 */
	public Salir(String text, String idioma){
		super(text);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Util.borrarDirectorioTemporal();
		File tomcat = new File("tomcat.8081");
		tomcat.mkdir();
		Util.borrarDirectorio(tomcat);
		System.exit(0);
	}

}
