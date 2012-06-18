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
import java.util.ArrayList;
import java.util.Vector;

/**
 * The Class Pregunta: classe bean que encapsula una pregunta (autoavaluació) o una 
 * pantalla (tutorial). Conté una col·lecció de respostes (si autoavaluació)
 */
public class Pregunta {
	
	private String textoPregunta;
	private String imagenPregunta;
	private String textoCortoPregunta;
	private int idPregunta;
	private ArrayList<Respuesta> listaRespuestas =  new ArrayList<Respuesta>();
	
	/**
	 * Instantiates a new pregunta.
	 */
	public Pregunta()
	{
		
	}

	/**
	 * Gets the texto pregunta.
	 *
	 * @return the texto pregunta
	 */
	public String getTextoPregunta() {
		return textoPregunta;
	}

	/**
	 * Sets the texto pregunta.
	 *
	 * @param textoPregunta the new texto pregunta
	 */
	public void setTextoPregunta(String textoPregunta) {
		this.textoPregunta = textoPregunta;
	}

	/**
	 * Gets the lista respuestas.
	 *
	 * @return the lista respuestas
	 */
	public ArrayList<Respuesta> getListaRespuestas() {
		return listaRespuestas;
	}

	/**
	 * Sets the respuestas.
	 *
	 * @param listaRespuestas the new respuestas
	 */
	public void setRespuestas(ArrayList<Respuesta> listaRespuestas) {
		this.listaRespuestas = listaRespuestas;
	}
	
	/**
	 * Adds the respuesta.
	 *
	 * @param respuesta the respuesta
	 */
	public void addRespuesta(Respuesta respuesta){
		listaRespuestas.add(respuesta);
	}
	
	/**
	 * Removes the respuesta.
	 *
	 * @param idRespuesta the id respuesta
	 */
	public void removeRespuesta(int idRespuesta){
		listaRespuestas.remove(idRespuesta);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		if (textoCortoPregunta != null) {
			return textoCortoPregunta;
		}
		return getTextoPregunta();
	}

	/**
	 * Gets the id pregunta.
	 *
	 * @return the id pregunta
	 */
	public int getIdPregunta() {
		return idPregunta;
	}

	/**
	 * Sets the id pregunta.
	 *
	 * @param idPregunta the new id pregunta
	 */
	public void setIdPregunta(int idPregunta) {
		this.idPregunta = idPregunta;
	}
	
	/**
	 * Gets the numero de respuestas.
	 *
	 * @return the numero de respuestas
	 */
	public int getNumeroDeRespuestas(){
		return listaRespuestas.size();
	}
	
	/**
	 * Gets the respuesta.
	 *
	 * @param idRespuesta the id respuesta
	 * @return the respuesta
	 */
	public Respuesta getRespuesta(int idRespuesta){
		return getListaRespuestas().get(idRespuesta);
	}
	
	/**
	 * Gets the vector respuestas.
	 *
	 * @return the vector respuestas
	 */
	public Vector<Respuesta> getVectorRespuestas(){
		Vector<Respuesta> vectorRespuestas = new Vector<Respuesta>();
		if (getListaRespuestas() !=null) {
			for (int i=0; i<getListaRespuestas().size();i++){
					vectorRespuestas.add(getListaRespuestas().get(i));
				}
		}
		return vectorRespuestas;
	}

	/**
	 * Gets the imagen pregunta.
	 *
	 * @return the imagen pregunta
	 */
	public String getImagenPregunta() {
		return imagenPregunta;
	}

	/**
	 * Sets the imagen pregunta.
	 *
	 * @param imagenPregunta the new imagen pregunta
	 */
	public void setImagenPregunta(String imagenPregunta) {
		this.imagenPregunta = imagenPregunta;
	}

	/**
	 * Gets the texto corto pregunta.
	 *
	 * @return the texto corto pregunta
	 */
	public String getTextoCortoPregunta() {
		return textoCortoPregunta;
	}

	/**
	 * Sets the texto corto pregunta.
	 *
	 * @param textoCortoPregunta the new texto corto pregunta
	 */
	public void setTextoCortoPregunta(String textoCortoPregunta) {
		this.textoCortoPregunta = textoCortoPregunta;
	}

	/**
	 * Contiene respuesta correcta.
	 *
	 * @return true, if successful
	 */
	public boolean contieneRespuestaCorrecta() {
		for (int i = 0; i < listaRespuestas.size(); i++){
			if (listaRespuestas.get(i).isTipoRespuesta()) return true;
		}
		return false;
	}

}
