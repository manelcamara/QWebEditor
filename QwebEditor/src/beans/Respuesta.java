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
package beans;

/**
 * The Class Respuesta: classe bean que encapsula una resposta (en autoavaluació)
 */
public class Respuesta {
	
	private boolean tipoRespuesta;
	private String textoRespuesta;
	private int idRespuesta;
	
	/**
	 * Instantiates a new respuesta.
	 */
	public Respuesta()
	{
		
	}

	/**
	 * Checks if is tipo respuesta.
	 *
	 * @return true, if is tipo respuesta
	 */
	public boolean isTipoRespuesta() {
		return tipoRespuesta;
	}

	/**
	 * Sets the tipo respuesta.
	 *
	 * @param tipoRespuesta the new tipo respuesta
	 */
	public void setTipoRespuesta(boolean tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}

	/**
	 * Gets the texto respuesta.
	 *
	 * @return the texto respuesta
	 */
	public String getTextoRespuesta() {
		return textoRespuesta;
	}

	/**
	 * Sets the texto respuesta.
	 *
	 * @param textoRespuesta the new texto respuesta
	 */
	public void setTextoRespuesta(String textoRespuesta) {
		this.textoRespuesta = textoRespuesta;
	}

	/**
	 * Gets the tipo respuesta.
	 *
	 * @return the tipo respuesta
	 */
	public String getTipoRespuesta() {
		
		if (isTipoRespuesta()) return "true";
		return "false";
	}

	/**
	 * Gets the id respuesta.
	 *
	 * @return the id respuesta
	 */
	public int getIdRespuesta() {
		return idRespuesta;
	}

	/**
	 * Sets the id respuesta.
	 *
	 * @param idRespuesta the new id respuesta
	 */
	public void setIdRespuesta(int idRespuesta) {
		this.idRespuesta = idRespuesta;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return getTextoRespuesta();
	}

}
