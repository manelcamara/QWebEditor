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
import java.awt.AlphaComposite;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import pantallas.PMensaje;
import beans.Cuestionario;
import beans.Pregunta;
import beans.Respuesta;

/**
 * The Class Util: 
 * classe estàtica que concentra totes les utilitats que l'aplicació 
 * utilitza: construir i llegir xml's, comprimir i descomprimir zip's, treballar amb
 * el directori temporal de recursos, instanciar el servidor web i el navegador per
 * llençar les ajudes, manipular imatges,...
 */
public class Util {
	//mètode que rep un objecte Cuestionario i genera un arxiu xml
	/**
	 * Cuestionario to zip.
	 *
	 * @param cuestionario the cuestionario
	 * @param idioma the idioma
	 */
	public static void cuestionarioToZIP (Cuestionario cuestionario, String idioma){
		String tipoCuestionario = cuestionario.getTipoCuestionario();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document document = null;
		Text text;
			try {
				//documento
				builder = factory.newDocumentBuilder();
				DOMImplementation implementation = builder.getDOMImplementation();
				document = implementation.createDocument(null, "cuestionario", null);
				document.setXmlVersion("1.0"); // asignamos la version de nuestro XML
				
				Attr atributoTitulo = document.createAttribute("titulo");
				atributoTitulo.setValue(cuestionario.getTituloCuestionario());
				document.getDocumentElement().setAttributeNode(atributoTitulo);

				Attr atributoTipo = document.createAttribute("tipoCuestionario");
				atributoTipo.setValue(cuestionario.getTipoCuestionario());
				document.getDocumentElement().setAttributeNode(atributoTipo);
				
				Attr atributoIdioma = document.createAttribute("idioma");
				atributoIdioma.setValue(cuestionario.getIdioma());
				document.getDocumentElement().setAttributeNode(atributoIdioma);
				
				Attr atributoPresentacion = document.createAttribute("presentacion");
				atributoPresentacion.setValue(cuestionario.getPresentacion());
				document.getDocumentElement().setAttributeNode(atributoPresentacion);
				
				//preguntas
				int numPreguntas = cuestionario.getListaPreguntas().size();
				for (int i=0; i<numPreguntas;i++){
					Pregunta pregunta = cuestionario.getListaPreguntas().get(i);
					Element nodoPregunta = document.createElement("pregunta");
					document.getDocumentElement().appendChild(nodoPregunta);
					//nodo textoCortoPregunta
					if (tipoCuestionario.equals("tutorial")){
						Element nodoTextoCortoPregunta = document.createElement("textoCortoPregunta");
						nodoPregunta.appendChild(nodoTextoCortoPregunta);
						text = document.createTextNode(pregunta.getTextoCortoPregunta());
						nodoTextoCortoPregunta.appendChild(text);
					}
					//nodo textoPregunta
					Element nodoTextoPregunta = document.createElement("textoPregunta");
					nodoPregunta.appendChild(nodoTextoPregunta);
					text = document.createTextNode(pregunta.getTextoPregunta());
					nodoTextoPregunta.appendChild(text);
					//nodo imagenPregunta
					if (pregunta.getImagenPregunta()!= null){
						Element nodoImagenPregunta = document.createElement("imagenPregunta");
						nodoPregunta.appendChild(nodoImagenPregunta);
						text = document.createTextNode(pregunta.getImagenPregunta());
						nodoImagenPregunta.appendChild(text);
					}
					//respuestas
					int numRespuestas = pregunta.getListaRespuestas().size();
					for (int j=0;j<numRespuestas;j++){
						Respuesta respuesta = pregunta.getListaRespuestas().get(j);
						Element nodoRespuesta = document.createElement("respuesta");
						nodoPregunta.appendChild(nodoRespuesta);
						Element nodoTipoRespuesta = document.createElement("tipoRespuesta");
						nodoRespuesta.appendChild(nodoTipoRespuesta);
						text = document.createTextNode(respuesta.getTipoRespuesta());
						nodoTipoRespuesta.appendChild(text);
						Element nodoTextoRespuesta = document.createElement("textoRespuesta");
						nodoRespuesta.appendChild(nodoTextoRespuesta);
						text = document.createTextNode(respuesta.getTextoRespuesta());
						nodoTextoRespuesta.appendChild(text);
					}
				}
				Source source = new DOMSource(document);
				//guardem cuestionario.xml
				File ficheroXML = new File(getTmpQweb() + "/cuestionario.xml");
				Result result = new StreamResult(ficheroXML); //nombre del archivo
				Transformer transformer = TransformerFactory.newInstance().newTransformer();
				transformer.transform(source, result);
				//empaquetem zip
				File scorm = new File("scorm");
				scorm.mkdir();
				copyDirectorio(scorm, getTmpQweb(), idioma);
				zip(getTmpQweb(), cuestionario.getTituloCuestionario(), idioma);
				
			} catch (HeadlessException e) {
				new PMensaje(null, e, idioma);
			} catch (DOMException e) {
				new PMensaje(null, e, idioma);
			} catch (TransformerConfigurationException e) {
				new PMensaje(null, e, idioma);
			} catch (ParserConfigurationException e) {
				new PMensaje(null, e, idioma);
			} catch (TransformerFactoryConfigurationError e) {
				new PMensaje(null, e, idioma);
			} catch (TransformerException e) {
				new PMensaje(null, e, idioma);
			}
	}
	
	/**
	 * ZI pto cuestionario.
	 *
	 * @param parent the parent
	 * @param lang the lang
	 * @return the cuestionario
	 */
	public static Cuestionario ZIPtoCuestionario (JFrame parent, String lang){
		JFileChooser fileChooser = new JFileChooser();
		Cuestionario cuestionario = null;
		int seleccion = fileChooser.showOpenDialog(parent);
		//controlem si l'usuari tanca el JFileChooser
		if (seleccion != JFileChooser.APPROVE_OPTION) return null;
		if (seleccion == JFileChooser.APPROVE_OPTION)
		{
		   String rutaZip = fileChooser.getSelectedFile().getAbsolutePath();
		   if (!Util.validarZip(rutaZip)){
			   ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/"+ lang);
			   new PMensaje(null, bundle.getString("scormNoValido"), lang);
			   return null;
		   }
		   File scorm = fileChooser.getSelectedFile();
		   File fichero = null;
		try {
			ZipFile zipScorm = new ZipFile(scorm);
			   ZipEntry cuestionarioXML = zipScorm.getEntry("cuestionario.xml");
			   fichero = File.createTempFile("temp", ".xml");
			   FileOutputStream fos = new FileOutputStream(fichero);
			   InputStream is = zipScorm.getInputStream(cuestionarioXML);
			   int leido;
			   byte [] buffer = new byte[1024];
			   while (0<(leido=is.read(buffer))){
			      fos.write(buffer,0,leido);
			   }
			   fos.close();
			   is.close();
			   extraccionZip(zipScorm, lang);
		} catch (ZipException e) {
			new PMensaje(null, e, lang);
		} catch (FileNotFoundException e) {
			new PMensaje(null, e, lang);
		} catch (IOException e) {
			new PMensaje(null, e, lang);
		}
		   DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		   DocumentBuilder docBuilder;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(fichero);
			doc.getDocumentElement().normalize();
			cuestionario = new Cuestionario();
			Pregunta pregunta;
			Respuesta respuesta = null;
			String textoPregunta = "";
			String textoCortoPregunta = "";
			String imagenPregunta = "";
			String textoRespuesta = "";
			String tipoRespuesta = "";
			String titulo = doc.getDocumentElement().getAttribute("titulo");
			String tipoCuestionario = doc.getDocumentElement().getAttribute("tipoCuestionario");
			String presentacion = doc.getDocumentElement().getAttribute("presentacion");
			String idioma = doc.getDocumentElement().getAttribute("idioma");
			cuestionario.setTituloCuestionario(titulo);
			cuestionario.setTipoCuestionario(tipoCuestionario);
			cuestionario.setIdioma(idioma);
			cuestionario.setPresentacion(presentacion);
			NodeList listaPreguntas = doc.getElementsByTagName("pregunta");
			for (int i = 0; i < listaPreguntas.getLength() ; i ++) {
				
				Node nodoPregunta = listaPreguntas.item(i);
			    
			    if (nodoPregunta.getNodeType() == Node.ELEMENT_NODE) {
			    	pregunta = new Pregunta();
			    	Element preguntaElement = (Element) nodoPregunta;
			    	//textoPregunta
			    	NodeList textoPreguntaElementList = preguntaElement.getElementsByTagName("textoPregunta");
			    	Element textoPreguntaElement = (Element) textoPreguntaElementList.item(0);
			    	NodeList textoPreguntaNodeList = textoPreguntaElement.getChildNodes();
			    	textoPregunta = ((Node)textoPreguntaNodeList.item(0)).getNodeValue();
			    	pregunta.setTextoPregunta(textoPregunta);
			    	//textoCortoPregunta
			    	if (tipoCuestionario.equals("tutorial")){
			    		NodeList textoCortoPreguntaElementList = 
			    				preguntaElement.getElementsByTagName("textoCortoPregunta");
			    		Element textoCortoPreguntaElement = (Element)textoCortoPreguntaElementList.item(0);
			    		NodeList textoCortoPreguntaNodeList = textoCortoPreguntaElement.getChildNodes();
			    		textoCortoPregunta = ((Node)textoCortoPreguntaNodeList.item(0)).getNodeValue();
			    		pregunta.setTextoCortoPregunta(textoCortoPregunta);
			    	}
			    	//imagenPregunta
			    	NodeList imagenPreguntaElementList = preguntaElement.getElementsByTagName("imagenPregunta");
			    	if (imagenPreguntaElementList.getLength()>0){
			    		Element imagenPreguntaElement = (Element) imagenPreguntaElementList.item(0);
			    		NodeList imagenPreguntaNodeList = imagenPreguntaElement.getChildNodes();
			    		imagenPregunta = ((Node) imagenPreguntaNodeList.item(0)).getNodeValue();
			    		pregunta.setImagenPregunta(imagenPregunta);
			    	}
			    	NodeList listaRespuestas = preguntaElement.getElementsByTagName("respuesta");
			    	for (int j=0; j<listaRespuestas.getLength();j++){
			    		
			    		Node nodoRespuesta = listaRespuestas.item(j);
			    		
			    		if (nodoRespuesta.getNodeType() == Node.ELEMENT_NODE){
			    			
			    			Element respuestaElement = (Element) nodoRespuesta;
			    			NodeList textoRespuestaElementList = respuestaElement.getElementsByTagName("textoRespuesta");
			    			NodeList tipoRespuestaElementList = respuestaElement.getElementsByTagName("tipoRespuesta");
			    			Element textoRespuestaElement = (Element) textoRespuestaElementList.item(0);
			    			Element tipoRespuestaElement = (Element) tipoRespuestaElementList.item(0);
			    			NodeList textoRespuestaNodeList = textoRespuestaElement.getChildNodes();
			    			NodeList tipoRespuestaNodeList = tipoRespuestaElement.getChildNodes();
			    			textoRespuesta = ((Node)textoRespuestaNodeList.item(0)).getNodeValue();
			    			tipoRespuesta = ((Node)tipoRespuestaNodeList.item(0)).getNodeValue();
			    			respuesta = new Respuesta();
			    			respuesta.setTextoRespuesta(textoRespuesta);
			    			respuesta.setTipoRespuesta(tipoRespuesta.equals("true"));
			    			
			    		}
			    		pregunta.addRespuesta(respuesta);
			    	}
			    	cuestionario.addPregunta(pregunta);
				}
			}
		} catch (ParserConfigurationException e) {
			new PMensaje(null, e, lang);
		} catch (SAXException e) {
			new PMensaje(null, e, lang);
		} catch (IOException e) {
			new PMensaje(null, e, lang);
		}
		   
		}
		
		return cuestionario;
	}
	//copiar contenido de un archivo
	private static void copy(InputStream in, OutputStream out, String idioma){
	    byte[] buffer = new byte[1024];
	    while (true) {
	      try {
			int readCount = in.read(buffer);
			  if (readCount < 0) {
			    break;
			  }
			  out.write(buffer, 0, readCount);
		} catch (IOException e) {
			new PMensaje(null, e, idioma);
		}
	    }
	  }

	  //copiar un archivo
	  private static void copy(File fileOrigen, File fileDestino, String idioma){
		  InputStream in = null;
		  OutputStream out = null;
		  try {
			  in = new FileInputStream(fileOrigen);
			  out = new FileOutputStream(fileDestino);
			  copy (in, out, idioma);
		  } catch (FileNotFoundException e) {
			  new PMensaje (null, e, idioma);
		  } finally {
			  try {
				in.close();
				out.close();
			} catch (IOException e) {
				new PMensaje(null, e, idioma);
			}
		  }
	  }
	  //copiar un directorio
	  /**
  	 * Copy directorio.
  	 *
  	 * @param dirOrigen the dir origen
  	 * @param dirDestino the dir destino
  	 * @param idioma the idioma
  	 */
  	public static void copyDirectorio (File dirOrigen, File dirDestino, String idioma){
		  if (dirOrigen.isDirectory()) {
	          if (!dirDestino.exists()) {
	              dirDestino.mkdir();
	          }
	          String[] children = dirOrigen.list();
	          for (int i=0; i<children.length; i++) {
	              copyDirectorio(new File(dirOrigen, children[i]),
	                  new File(dirDestino, children[i]), idioma);
	          }
	      } else {
	          copy(dirOrigen, dirDestino, idioma);
	      } 
	  }
	  private static void copy(File file, OutputStream out, String idioma){
		    InputStream in = null;
		    try {
		      in = new FileInputStream(file);
		      copy(in, out, idioma);
		    } catch (FileNotFoundException e) {
				new PMensaje(null, e, idioma);
		    } finally {
		    	 try {
					in.close();
				} catch (IOException e) {
					new PMensaje(null, e, idioma);
				}
		    }
		  }
	//borrar un directorio
	  /**
	 * Borrar directorio.
	 *
	 * @param directorio the directorio
	 */
	public static void borrarDirectorio (File directorio){
		  if (directorio.isDirectory()) {
          File[] archivos = directorio.listFiles();
          	if (archivos.length == 0){
          		directorio.delete();
          	} else {
	          for (int i=0; i<archivos.length; i++) {
	              if (archivos[i].isDirectory()){
	            	  borrarDirectorio(archivos[i]);
	              } else {
	            	  archivos[i].delete();
	              }
	             directorio.delete();
	          }
          	}
	      } else directorio.delete();
	  }
	  
  	/**
  	 * Borrar directorio temporal.
  	 */
  	public static void borrarDirectorioTemporal(){
			borrarDirectorio(getTmpQweb());
	  }
	  
  	/**
  	 * Gets the tmp qweb.
  	 *
  	 * @return the tmp qweb
  	 */
  	public static File getTmpQweb(){
		  final File tmpDir = new File(System.getProperty("java.io.tmpdir") + "/qweb" );
		  tmpDir.mkdir();
		  return tmpDir;
	  }
	  
  	/**
  	 * Gets the tmp recursos.
  	 *
  	 * @return the tmp recursos
  	 */
  	public static File getTmpRecursos(){
		  final File tmpDir = new File(getTmpQweb()+ "/recursos" );
		  tmpDir.mkdir();
		  return tmpDir;
	  }
	  
  	/**
  	 * Zip.
  	 *
  	 * @param directory the directory
  	 * @param tituloCuestionario the titulo cuestionario
  	 * @param idioma the idioma
  	 */
  	public static void zip(File directory, String tituloCuestionario, String idioma){
		    File zipfile = new File(tituloCuestionario + ".zip");
		    URI base = directory.toURI();
		    Deque<File> queue = new LinkedList<File>();
		    queue.push(directory);
		    Closeable res = null;
		    try {
		      OutputStream out = new FileOutputStream(zipfile);
		      res = out;
		      ZipOutputStream zout = new ZipOutputStream(out);
		      res = zout;
		      while (!queue.isEmpty()) {
		        directory = queue.pop();
		        for (File kid : directory.listFiles()) {
		          String name = base.relativize(kid.toURI()).getPath();
		          if (kid.isDirectory()) {
		            queue.push(kid);
		            name = name.endsWith("/") ? name : name + "/";
		            zout.putNextEntry(new ZipEntry(name));
		          } else {
		            zout.putNextEntry(new ZipEntry(name));
		            copy(kid, zout, idioma);
		            zout.closeEntry();
		          }
		        }
		      }
		    } catch (FileNotFoundException e) {
				new PMensaje(null, e, idioma);
			} catch (IOException e) {
				new PMensaje(null, e, idioma);
			} finally {
		      try {
				res.close();
		      } catch (IOException e) {
		    	  new PMensaje(null, e, idioma);
		      }
		    }
		  }
	    
    	/**
    	 * Gets the icon image.
    	 *
    	 * @return the icon image
    	 */
    	public static Image getIconImage() {
	        Image icono = Toolkit.getDefaultToolkit().
	              getImage(ClassLoader.getSystemResource("resources/qweb.png"));
	        return icono;
	     }
	    
    	/**
    	 * Mostrar ayuda.
    	 *
    	 * @param tipoAyuda the tipo ayuda
    	 * @param idioma the idioma
    	 */
    	public static void mostrarAyuda(String tipoAyuda, final String idioma){
	    	try {
	    		final File tempDirAyuda = getTmpQweb();
				File scorm = new File("scorm");
				File ayuda = new File("ayuda/" + tipoAyuda + "/" + idioma);
				copyDirectorio (scorm, tempDirAyuda, idioma);
				copyDirectorio (ayuda, tempDirAyuda, idioma);
				Thread t = new Thread(new Runnable(){
					@Override
					public void run() {
						iniciarTomcat(tempDirAyuda.getAbsolutePath(), idioma);
					}});
	    		t.start();
	    		java.net.URI url = new java.net.URI("http://127.0.0.1:8081");
				Desktop.getDesktop().browse(url);
			} catch (IOException e) {
				new PMensaje(null, e, idioma);
			} catch (URISyntaxException e) {
				new PMensaje(null, e, idioma);
			}
	    }
	    private static void iniciarTomcat(String wepappdir, String idioma){
	    	Tomcat tomcat = new Tomcat();
			tomcat.setPort(8081);
			String webappDirLocation = wepappdir;
			try {
				tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());
			} catch (ServletException e) {
				new PMensaje(null, e, idioma);
			}
			try {
				tomcat.start();
				tomcat.getServer().await();
			} catch (LifecycleException e) {
				new PMensaje(null, e, idioma);
			}
	    }
	    
	    /**
    	 * Sets the look and feel.
    	 *
    	 * @param idioma the new look and feel
    	 */
    	public static void setLookAndFeel(String idioma){
	    	try {
				UIManager.setLookAndFeel(
				        UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException e) {
				new PMensaje(null, e, idioma);
			} catch (InstantiationException e) {
				new PMensaje(null, e, idioma);
			} catch (IllegalAccessException e) {
				new PMensaje(null, e, idioma);
			} catch (UnsupportedLookAndFeelException e) {
				new PMensaje(null, e, idioma);
			}
	    }
	    
    	/**
    	 * Localiza aplicacion.
    	 *
    	 * @param rb the rb
    	 * @param idioma the idioma
    	 */
    	public static void localizaAplicacion(ResourceBundle rb, String idioma){
	    	UIManager.put("FileChooser.openDialogTitleText", rb.getString("openDialogTitleText"));
	    	UIManager.put("FileChooser.lookInLabelText", rb.getString("lookInLabelText"));
	    	UIManager.put("FileChooser.filesOfTypeLabelText", rb.getString("filesOfTypeLabelText"));
	    	UIManager.put("FileChooser.upFolderToolTipText", rb.getString("upFolderToolTipText"));
	    	UIManager.put("FileChooser.fileNameLabelText", rb.getString("fileNameLabelText"));
	    	UIManager.put("FileChooser.homeFolderToolTipText", rb.getString("homeFolderToolTipText"));
	    	UIManager.put("FileChooser.newFolderToolTipText", rb.getString("newFolderToolTipText"));
	    	UIManager.put("FileChooser.listViewButtonToolTipTextlist", rb.getString("listViewButtonToolTipTextlist"));
	    	UIManager.put("FileChooser.detailsViewButtonToolTipText", rb.getString("detailsViewButtonToolTipText"));
	    	UIManager.put("FileChooser.saveButtonText", rb.getString("saveButtonText"));
	    	UIManager.put("FileChooser.openButtonText", rb.getString("openButtonText"));
	    	UIManager.put("FileChooser.cancelButtonText", rb.getString("cancelButtonText"));
	    	UIManager.put("FileChooser.updateButtonText", rb.getString("updateButtonText"));
	    	UIManager.put("FileChooser.helpButtonText", rb.getString("helpButtonText"));
	    	UIManager.put("FileChooser.saveButtonToolTipText", rb.getString("saveButtonToolTipText"));
	    	UIManager.put("FileChooser.openButtonToolTipText", rb.getString("openButtonToolTipText"));
	    	UIManager.put("FileChooser.cancelButtonToolTipText", rb.getString("cancelButtonToolTipText"));
	    	UIManager.put("FileChooser.updateButtonToolTipText", rb.getString("updateButtonToolTipText"));
	    	UIManager.put("FileChooser.helpButtonToolTipText", rb.getString("helpButtonToolTipText"));
	    	UIManager.put("FileChooser.listViewButtonToolTipText", rb.getString("listViewButtonToolTipText"));
	    	UIManager.put("OptionPane.okButtonText", rb.getString("Aceptar"));
	    	UIManager.put("OptionPane.cancelButtonText", rb.getString("Cancelar"));
	    	
	    }
	    
    	/**
    	 * Gets the defaults ui manager.
    	 *
    	 * @param componente the componente
    	 * @return the defaults ui manager
    	 */
    	public static String getDefaultsUIManager(String componente){
	    	String defaults = "";
	    	String elemento = "";
	    	UIDefaults d = UIManager.getDefaults();
	    	Enumeration<Object> claves = d.keys();
	    	while (claves.hasMoreElements()){
	    		elemento = claves.nextElement().toString();
	    		if (elemento.startsWith(componente)){
	    			defaults = defaults + elemento + "\n";
	    		}
	    	}
			return defaults;
	    	   
	    }
	    
    	/**
    	 * Elimina recurso.
    	 *
    	 * @param recurso the recurso
    	 */
    	public static void eliminaRecurso(String recurso){
	    	File archivo = new File(getTmpRecursos() + "/" + recurso);
	    	archivo.delete();
	    }
		
		/**
		 * Validar imagen.
		 *
		 * @param rutaImagen the ruta imagen
		 * @return true, if successful
		 */
		public static boolean validarImagen(String rutaImagen) {
			int  numCaracteres = rutaImagen.length();
			String extension = rutaImagen.substring(numCaracteres-4, numCaracteres);
			if (extension.equals(".gif") || extension.equals(".jpg")){
				return true;
			}
			return false;
		}
		
		/**
		 * Validar zip.
		 *
		 * @param rutaZip the ruta zip
		 * @return true, if successful
		 */
		public static boolean validarZip(String rutaZip) {
			int  numCaracteres = rutaZip.length();
			String extension = rutaZip.substring(numCaracteres-4, numCaracteres);
			if (extension.equals(".zip")){
				return true;
			}
			return false;
		}
		//extreu del zip scorm el fitxer xml i la carpeta recursos
		private static void extraccionZip(ZipFile zip, String idioma){
			//estructurem el directori temporal
			File tmpDir = getTmpQweb();
			File carpetaTempRecursos = getTmpRecursos();
			carpetaTempRecursos.mkdir();
			//iniciem extracció
			ArrayList<ZipEntry> entradas = new ArrayList<ZipEntry>();
			entradas.add(zip.getEntry("cuestionario.xml"));
			try {
				ZipInputStream zis = new ZipInputStream(new FileInputStream(zip.getName()));
				ZipEntry recurso;
				while (null != (recurso = zis.getNextEntry())){
					String nombreRecurso = recurso.getName();
					if (nombreRecurso.startsWith("recursos/") && (nombreRecurso.length() > 9)){
						entradas.add(recurso);
					}
				}
				FileOutputStream fos;
				InputStream is;
				for (int i = 0; i < entradas.size(); i++){
					fos = new FileOutputStream(tmpDir + "/" + entradas.get(i).getName());
					is = zip.getInputStream(entradas.get(i));
					int leido;
					byte[] buffer = new byte[1024];
					while (0<(leido=is.read(buffer))){
						fos.write(buffer, 0, leido);
					}
					fos.close();
					is.close();
				}
			} catch (FileNotFoundException e) {
				new PMensaje(null, e, idioma);
			} catch (IOException e) {
				new PMensaje(null, e, idioma);
			}
		}
		
		/**
		 * Guardar imagen.
		 *
		 * @param imagen the imagen
		 * @param idioma the idioma
		 */
		public static void guardarImagen(File imagen, String idioma){
			//comprovem tamany de la imatge original i si cal escalem
			String nombreImagen = imagen.getName();
			String rutaImagen = imagen.getPath();
			float maxWidth = 600;
			float maxHeigth = 600;
			float originalWidth;
			float originalHeigth;
			float ratio;
			try {
				BufferedImage image = ImageIO.read(new File(rutaImagen));
				float width = image.getWidth();
				originalWidth = width;
				float heigth = image.getHeight();
				originalHeigth = heigth;
				if (width > maxWidth){
					ratio = maxWidth/width;
					width = (int)(width*ratio);
					heigth = (int)(heigth*ratio);
				}
				if (heigth > maxHeigth){
					ratio = maxHeigth/heigth;
					width = (int)(width*ratio);
					heigth = (int)(heigth*ratio);
				}
				if (width != originalWidth || heigth != originalHeigth){
					ImageIO.write(escalarImagen(image, (int)width, (int)heigth), "gif", 
														new File(getTmpRecursos() + "/" + nombreImagen));
				} else {
					ImageIO.write(image, "gif", new File(getTmpRecursos() + "/" + nombreImagen));
				}
			} catch (IOException e) {
				new PMensaje(null, e, idioma);
			}
		}
		private static BufferedImage escalarImagen(BufferedImage imagen, int ancho, int alto){
			int type = imagen.getType() == 0? BufferedImage.TYPE_INT_ARGB : imagen.getType();
			BufferedImage imagenEscalada = new BufferedImage(ancho, alto, type);
			Graphics2D g = imagenEscalada.createGraphics();
			g.setComposite(AlphaComposite.Src);

			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
			RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			g.setRenderingHint(RenderingHints.KEY_RENDERING,
			RenderingHints.VALUE_RENDER_QUALITY);

			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);

			g.drawImage(imagen, 0, 0, ancho, alto, null);
			g.dispose();
			return imagenEscalada;
		}
}
