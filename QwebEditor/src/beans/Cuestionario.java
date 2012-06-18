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
 * The Class Cuestionario: classe bean que encapsula un qüestionari (autoavaluació o
 * tutorial). Conté una col·lecció de preguntes o pantalles (ja sigui autoavaluació o
 * tutorial)
 */
public class Cuestionario {
	
	private String tituloCuestionario;
	private String tipoCuestionario;
	private String idioma;
	private String presentacion;
	private ArrayList<Pregunta> listaPreguntas = new ArrayList<Pregunta>();
	
	/**
	 * Instantiates a new cuestionario.
	 *
	 * @param tipoCuestionario the tipo cuestionario
	 */
	public Cuestionario(String tipoCuestionario)
	{
		this.setTipoCuestionario(tipoCuestionario);
	}
	
	/**
	 * Instantiates a new cuestionario.
	 */
	public Cuestionario(){
		
	}
	
	/**
	 * Gets the titulo cuestionario.
	 *
	 * @return the titulo cuestionario
	 */
	public String getTituloCuestionario() {
		return tituloCuestionario;
	}
	
	/**
	 * Sets the titulo cuestionario.
	 *
	 * @param tituloCuestionario the new titulo cuestionario
	 */
	public void setTituloCuestionario(String tituloCuestionario) {
		this.tituloCuestionario = tituloCuestionario;
	}
	
	/**
	 * Gets the lista preguntas.
	 *
	 * @return the lista preguntas
	 */
	public ArrayList<Pregunta> getListaPreguntas() {
		return listaPreguntas;
	}
	
	/**
	 * Gets the vector preguntas.
	 *
	 * @return the vector preguntas
	 */
	public Vector<Pregunta> getVectorPreguntas(){
		Vector<Pregunta> vectorPreguntes = new Vector<Pregunta>();
		if (getListaPreguntas() !=null) {
			for (int i=0; i<getListaPreguntas().size();i++){
					vectorPreguntes.add(getListaPreguntas().get(i));
				}
		}
		return vectorPreguntes;
	}
	
	/**
	 * Sets the lista preguntas.
	 *
	 * @param listaPreguntas the new lista preguntas
	 */
	public void setListaPreguntas(ArrayList<Pregunta> listaPreguntas) {
		this.listaPreguntas = listaPreguntas;
	}
	
	/**
	 * Adds the pregunta.
	 *
	 * @param pregunta the pregunta
	 */
	public void addPregunta(Pregunta pregunta){
		listaPreguntas.add(pregunta);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		return getTituloCuestionario() + "\n" + this.getIdioma() + "\n" + this.getPresentacion() +
				"\n" + this.getTipoCuestionario() + "\n" + this.getNumeroDePreguntas();
	}
	
	/**
	 * Gets the numero de preguntas.
	 *
	 * @return the numero de preguntas
	 */
	public int getNumeroDePreguntas(){
		return listaPreguntas.size();
	}
	
	/**
	 * Gets the pregunta.
	 *
	 * @param idPregunta the id pregunta
	 * @return the pregunta
	 */
	public Pregunta getPregunta(int idPregunta){
		return getListaPreguntas().get(idPregunta);
	}
	
	/**
	 * Removes the pregunta.
	 *
	 * @param idPregunta the id pregunta
	 */
	public void removePregunta(int idPregunta){
		listaPreguntas.remove(idPregunta);
	}

	/**
	 * Gets the tipo cuestionario.
	 *
	 * @return the tipo cuestionario
	 */
	public String getTipoCuestionario() {
		return tipoCuestionario;
	}

	/**
	 * Sets the tipo cuestionario.
	 *
	 * @param tipoCuestionario the new tipo cuestionario
	 */
	public void setTipoCuestionario(String tipoCuestionario) {
		this.tipoCuestionario = tipoCuestionario;
	}
	
	/**
	 * Gets the idioma.
	 *
	 * @return the idioma
	 */
	public String getIdioma() {
		return idioma;
	}
	
	/**
	 * Sets the idioma.
	 *
	 * @param idioma the new idioma
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	
	/**
	 * Gets the presentacion.
	 *
	 * @return the presentacion
	 */
	public String getPresentacion() {
		return presentacion;
	}
	
	/**
	 * Sets the presentacion.
	 *
	 * @param presentacion the new presentacion
	 */
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	
	/**
	 * Validar cuestionario.
	 *
	 * @return true, if successful
	 */
	public boolean validarCuestionario() {
		if (tipoCuestionario.equals("autoevaluacion")){
			 return validarAutoevaluacion();
		} else if (tipoCuestionario.equals("tutorial")){
			 return validarTutorial();
		}
		return false;
	}
	private boolean validarTutorial() {
		// el tutorial mínim ha de contenir una pantalla
		// la presentació no pot ser buida
		if (presentacion.equals("")) return false;
		if (listaPreguntas.size() > 0) return true;
		return false;
	}
	private boolean validarAutoevaluacion() {
		// el qüestionari mínim ha de contenir una pregunta amb una resposta vàlida
		// la presentació no pot ser buida
		if (presentacion.equals("")) return false;
		for (int i = 0; i < listaPreguntas.size(); i++){
			if (listaPreguntas.get(i).contieneRespuestaCorrecta()) return true;
		}
		return false;
	}
}
