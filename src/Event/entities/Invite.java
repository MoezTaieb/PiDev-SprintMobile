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
public class Invite {
    
    private int id;
    private int idEvenement;
    private String nomInvite;
    private String prenomInvite;
   

    public Invite() {
    }

    public Invite(int id, String nomInvite, String prenomInvite) {
        this.id = id;
        this.nomInvite = nomInvite;
        this.prenomInvite = prenomInvite;
    }

   
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomInvite() {
        return nomInvite;
    }

    public void setNomInvite(String nomInvite) {
        this.nomInvite = nomInvite;
    }

    public String getPrenomInvite() {
        return prenomInvite;
    }

    public void setPrenomInvite(String prenomInvite) {
        this.prenomInvite = prenomInvite;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    @Override
    public String toString() {
        return "Invite{" + "nomInvite=" + nomInvite + ", prenomInvite=" + prenomInvite + ", idEvenement=" + idEvenement + '}';
    }
    
    
    
    
}

