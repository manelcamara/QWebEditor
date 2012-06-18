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
package util;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import beans.Cuestionario;
import beans.Pregunta;
import beans.Respuesta;

/**
 * The Class ArbolCuestionario: utilitat per mostrar un qüestionari en edició en forma
 * d'arbre
 */
public class ArbolCuestionario extends JTree{

	private static final long serialVersionUID = 4133618337090951269L;
	private Cuestionario cuestionario;
	private ResourceBundle bundle;
	
	/**
	 * Instantiates a new arbol cuestionario.
	 *
	 * @param c the c
	 */
	public ArbolCuestionario (Cuestionario c){
		cuestionario = c;
		bundle = java.util.ResourceBundle.getBundle("i18n/"+ cuestionario.getIdioma());
		this.setModel(creaArbol());
	}
	
	private DefaultTreeModel creaArbol() {
		String tipoCuestionario = cuestionario.getTipoCuestionario();
		DefaultMutableTreeNode cuest = new DefaultMutableTreeNode(tipoCuestionario);
	    DefaultTreeModel modelo = new DefaultTreeModel(cuest);
	   	    
	    // Construccion de los datos del arbol
	    DefaultMutableTreeNode titulo = new DefaultMutableTreeNode(cuestionario.getTituloCuestionario());
	    modelo.insertNodeInto(titulo, cuest, 0);
	    DefaultMutableTreeNode preguntas = null;
	    if (tipoCuestionario.equals("autoevaluacion")){
	    	preguntas = new DefaultMutableTreeNode(bundle.getString("Preguntas"));
	    } else if (tipoCuestionario.equals("tutorial")){
	    	preguntas = new DefaultMutableTreeNode(bundle.getString("Pantallas"));
	    }
	    modelo.insertNodeInto(preguntas, titulo, 0);
	    ArrayList<Pregunta> listaPreguntas = cuestionario.getListaPreguntas();
	    for (int i=0; i<listaPreguntas.size();i++){
	   	 DefaultMutableTreeNode pregunta = null;
	   	 if (tipoCuestionario.equals("autoevaluacion")){
	   		 pregunta = new DefaultMutableTreeNode(listaPreguntas.get(i).getTextoPregunta());
	   	 } else if (tipoCuestionario.equals("tutorial")){
	   		 pregunta = new DefaultMutableTreeNode(listaPreguntas.get(i).getTextoCortoPregunta());
	   	 }
	   	 DefaultMutableTreeNode imagen = new DefaultMutableTreeNode(listaPreguntas.get(i).getImagenPregunta());
	   	 //i
	   	 modelo.insertNodeInto(pregunta, preguntas, 0);
	   	 modelo.insertNodeInto(imagen, preguntas, 1);
	   	 if (cuestionario.getTipoCuestionario().equals("autoevaluacion")){
	   		 ArrayList<Respuesta> listaRespuestas = listaPreguntas.get(i).getListaRespuestas();
	   		 for (int j=0;j<listaRespuestas.size();j++){
	   			 DefaultMutableTreeNode respuesta = new DefaultMutableTreeNode(listaRespuestas.get(j).getTextoRespuesta());
	   			 DefaultMutableTreeNode tipoRespuesta = new DefaultMutableTreeNode(listaRespuestas.get(j).getTipoRespuesta());
	   			 modelo.insertNodeInto(respuesta, pregunta, 0);
	   			 modelo.insertNodeInto(tipoRespuesta, pregunta,1);
	   		 }
	   	 }
	    }
		return modelo;
	}

}
