package com.edeal.recruitment.custom;

import java.time.LocalDate;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.edeal.frontline.AccessDeniedException;
import com.edeal.recruitment.ProjectBean;

public class ProjectBeanCustom extends ProjectBean  {

	String etat;
	public ProjectBeanCustom(String titre, int numero, LocalDate dateDebut, LocalDate dateFin, String etat) {
		super(titre, numero, dateDebut, dateFin);
		this.etat = etat;
	}

	public ProjectBeanCustom(String titre, int numero, LocalDate dateDebut, LocalDate dateFin) {
		super(titre, numero, dateDebut, dateFin);
	}
	public ProjectBeanCustom(HttpSession session,String titre, int numero, LocalDate dateDebut, LocalDate dateFin, String etat) {
		super(session);
		this.etat = (String) session.getAttribute("etat");
	}
	
	@Override
	public void populateFromJson(JSONObject json, boolean save) throws AccessDeniedException {
        super.populateFromJson(json,save);
        if (json.has("etat")) {
            this.etat = json.getString("etat");
        }

	}
}