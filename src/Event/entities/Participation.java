/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.entities;

/**
 *
 * @author Moez
 */
public class Participation {
    private int id;
    private int idE;
    private int idU;
    private String dateParticipation;
    private String nomEvent;
    private String lieuEvent;
    private String dateEvent;

    public Participation() {
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public String getLieuEvent() {
        return lieuEvent;
    }

    public void setLieuEvent(String lieuEvent) {
        this.lieuEvent = lieuEvent;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

    public Participation(int id, int idE, int idU, String dateParticipation) {
        this.id = id;
        this.idE = idE;
        this.idU = idU;
        this.dateParticipation = dateParticipation;
    }

    public Participation(int id, int idE, int idU, String dateParticipation, String nomEvent, String lieuEvent, String dateEvent) {
        this.id = id;
        this.idE = idE;
        this.idU = idU;
        this.dateParticipation = dateParticipation;
        this.nomEvent = nomEvent;
        this.lieuEvent = lieuEvent;
        this.dateEvent = dateEvent;
    }

    public Participation(int idE, int idU, String dateParticipation, String nomEvent, String lieuEvent, String dateEvent) {
        this.idE = idE;
        this.idU = idU;
        this.dateParticipation = dateParticipation;
        this.nomEvent = nomEvent;
        this.lieuEvent = lieuEvent;
        this.dateEvent = dateEvent;
    }
    
    

    public Participation(int idE, int idU, String dateParticipation) {
        this.idE = idE;
        this.idU = idU;
        this.dateParticipation = dateParticipation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdE() {
        return idE;
    }

    public void setIdE(int idE) {
        this.idE = idE;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getDateParticipation() {
        return dateParticipation;
    }

    public void setDateParticipation(String dateParticipation) {
        this.dateParticipation = dateParticipation;
    }

    @Override
    public String toString() {
        return "Participation{" + "idE=" + idE + ", idU=" + idU + ", dateParticipation=" + dateParticipation + ", nomEvent=" + nomEvent + ", lieuEvent=" + lieuEvent + ", dateEvent=" + dateEvent + '}';
    }

    
    
    
    
    
    
}

