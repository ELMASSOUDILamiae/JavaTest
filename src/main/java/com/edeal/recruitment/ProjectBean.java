package com.edeal.recruitment;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import com.edeal.frontline.AccessDeniedException;
import com.edeal.frontline.BasicBean;
public  class ProjectBean extends ProjectGenericBean implements BasicBean {

	private static final String toStringSeparator = " - ";
	private String titre;
	private int numero;
    private LocalDate dateDebut;
    private LocalDate dateFin;

	public ProjectBean(String titre, int numero, LocalDate dateDebut, LocalDate dateFin) {
		this.titre = titre;
		this.numero = numero;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
    public ProjectBean(HttpSession session) {
        this.titre = (String) session.getAttribute("titre");
        this.numero = (Integer) session.getAttribute("numero");
        this.dateDebut =(LocalDate) session.getAttribute("dateDebut");
        this.dateFin = (LocalDate) session.getAttribute("dateFin");
    }

	public String getTitre() {
		return titre;
	}

	public int getNumero() {
		return numero;
	}

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}


    public String toString() {
        return new StringBuilder()
            .append(this.numero)
            .append(toStringSeparator)
            .append(StringUtils.isEmpty(this.titre) ? "-?-" : this.titre)
            .toString();
    }


	@Override
	public Period getDuration() {
        return Period.between(this.dateDebut, this.dateFin);

	}

	@Override
	public String getDurationToString() {
		return getDuration().getYears() + "Year(s)" + toStringSeparator + this.getDuration().getMonths()
				+ "Month(s)" + toStringSeparator + this.getDuration().getDays() + "Day(s)";
	}

	@Override
	public String getDurationToString(String flag) {
	    ChronoUnit unit;
	    switch (flag) {
	        case "YEAR":
	            unit = ChronoUnit.YEARS;
	            break;
	        case "MONTH":
	            unit = ChronoUnit.MONTHS;
	            break;
	        case "DAY":
	            unit = ChronoUnit.DAYS;
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid flag: " + flag);
	    }
        long result = unit.between(this.dateDebut, this.dateFin);
        return result + " " + unit.toString().toLowerCase();

	}

	public void populateFromJson(JSONObject json, boolean save) throws AccessDeniedException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if (!save) {
            throw new AccessDeniedException("Cannot populate from JSON when save flag is false");
        }
	    try {
	        if (json.has("titre")) {
	            this.titre = json.getString("titre");
	        }
	        if (json.has("numero")) {
	            this.numero = json.getInt("numero");
	        }
	        if (json.has("dateDebut")) {
	            this.dateDebut =LocalDate.parse(json.getString("dateDebut"), formatter);
	        }
	        if (json.has("dateFin")) {
	            this.dateFin = LocalDate.parse(json.getString("dateFin"), formatter);
	        }
	    } catch (Exception e) {
	        throw new AccessDeniedException("access denied exception ", e);
	    }		
	}

	


}

