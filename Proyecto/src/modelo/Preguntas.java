package modelo;

import java.util.List;

public class Preguntas {

	private Integer idPregunta; //Tambien sirve como numero de la pregunta.
	private String pregunta;
	private String respuestaCorrecta;
	private List<String> listaRespuesta;
	/*
	 * Lista de respuestas para mostrarlas en las opciones
	 * Esta en orden (A,B,C,D)
	 */

	public Integer getIdPregunta() {
		return idPregunta;
	}
	public void setIdPregunta(Integer idPregunta) {
		this.idPregunta = idPregunta;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getRespuesta() {
		return respuestaCorrecta;
	}
	public void setRespuesta(String respuesta) {
		this.respuestaCorrecta = respuesta;
	}
	public List<String> getListaRespuesta() {
		return listaRespuesta;
	}
	public void setListaRespuesta(List<String> listaRespuesta) {
		this.listaRespuesta = listaRespuesta;
	}
	@Override
	public String toString() {
		return "Preguntas [idPregunta=" + idPregunta + ", pregunta=" + pregunta + ", respuestaCorrecta="
				+ respuestaCorrecta + ", listaRespuesta=" + listaRespuesta + "]";
	}






}
