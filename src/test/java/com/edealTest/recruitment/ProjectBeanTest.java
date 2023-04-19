package com.edealTest.recruitment;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpSession;

import com.edeal.recruitment.ProjectBean;
import com.edeal.recruitment.ProjectGenericBean;

public class ProjectBeanTest {

    @Test
    public void testSaveProjectBean() throws Exception {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String titre = "Projet Recrutement";
        int numero = 10;
        LocalDate dateDebut =  LocalDate.now();
        LocalDate dateFin = LocalDate.parse("12/11/2027", formatter);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("titre", titre);
        session.setAttribute("numero", numero);
        session.setAttribute("dateDebut", dateDebut);
        session.setAttribute("dateFin", dateFin);

        // Create JSON string from input data
        String json = String.format("{\"titre\":\"%s\",\"numero\":%d,\"dateDebut\":\"%s\",\"dateFin\":\"%s\"}",
                titre, numero, dateDebut.format(formatter), dateFin.format(formatter));

        // Save ProjectBean object using saveBean method
        ProjectBean savedBean = (ProjectBean)ProjectGenericBean.saveBean(session, json, ProjectBean.class, true);

        // Verify that saved bean has the correct properties
        Assert.assertEquals(titre, savedBean.getTitre());
        Assert.assertEquals(numero, savedBean.getNumero());
        Assert.assertEquals(dateDebut, savedBean.getDateDebut());
        Assert.assertEquals(dateFin, savedBean.getDateFin());
}}
