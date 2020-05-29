/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author fedi
 */
public class User {

    /*
    il faut modifer les fichiers suivantes 
    security.yml
    Entity user 
     */
    protected int id;
    private String cinUser;
    private String nomUser;
    private String prenomUser;
    private String adresseUser;
    private String telUser;
    private String imageUrlUser;
    private String typeUser;
    protected String username;
    protected String usernameCanonical;
    protected String email;
    protected String emailCanonical;
    protected boolean enabled;
    protected String salt;
    protected String password;

    protected String enabledString;

    /**
     * Plain password. Used for model validation. Must not be persisted. *
     */
    protected String plainPassword;
    /**
     * @var \DateTime|null
     */
    protected String lastLogin;
    ;

    /**
     * Random string sent to the user email address in order to verify it.
     *  string|null
     */
    protected String confirmationToken;
    ;

    /**
     * @var \DateTime|null
     */
    protected String passwordRequestedAt;

    /**
     * @var GroupInterface[]|Collection protected $groups;
     */
    /**
     * @var array
     */
    protected String roles = "a:1:{i:0;s:10:\" ROLE_USER\";}";

    protected String rolesUser = "USER";

    
      public User(String email, String username, String password, String nomUser, String prenomUser, String adresseUser, String telUser, String cinUser) {

        this.username = username;

        this.usernameCanonical = username;

        this.email = email;

        this.emailCanonical = email;

        this.password = null;

        this.roles = "a:1:{i:0;s:10:\" ROLE_USER\";}";

        this.enabled = true;
        enabledString = "enable";
        this.cinUser = cinUser;

        this.nomUser = nomUser;

        this.prenomUser = prenomUser;

        this.adresseUser = adresseUser;

        this.telUser = telUser;

        //optionnel 
        this.imageUrlUser = null;

        this.typeUser = null;

        this.salt = null;
        this.plainPassword = null;

        Date aujourdhui = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.lastLogin = formater.format(aujourdhui);
        this.confirmationToken = null;;
        this.passwordRequestedAt = null;;

    }

    public User(int id, String username) {
        this.id = id;
        this.username = username;
    }
      
    

    public User() {
    }
    
    public String getRolesUser() {
        return rolesUser;
    }

    protected int resetCode = 0;

    protected int resetNumAttempts = 0;

    public int getResetCode() {
        return resetCode;
    }

    public void setResetCode(int resetCode) {
        this.resetCode = resetCode;
    }

    public int getResetNumAttempts() {
        return resetNumAttempts;
    }

    public void setResetNumAttempts(int resetNumAttempts) {
        this.resetNumAttempts = resetNumAttempts;
    }

    public int getid() {
        return id;
    }

    public void setid(int $id) {
        this.id = $id;
    }

    public String getCinUser() {
        return cinUser;
    }

    public void setCinUser(String cinUser) {
        this.cinUser = cinUser;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getPrenomUser() {
        return prenomUser;
    }

    public void setPrenomUser(String prenomUser) {
        this.prenomUser = prenomUser;
    }

    public String getAdresseUser() {
        return adresseUser;
    }

    public void setAdresseUser(String adresseUser) {
        this.adresseUser = adresseUser;
    }

    public String getTelUser() {
        return telUser;
    }

    public void setTelUser(String telUser) {
        this.telUser = telUser;
    }

    public String getImageUrlUser() {
        return imageUrlUser;
    }

    public void setImageUrlUser(String imageUrlUser) {
        this.imageUrlUser = imageUrlUser;
    }

    public String getTypeUser() {
        return typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {

        this.usernameCanonical = username;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.emailCanonical = email;
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getEnabledString() {
        return enabledString;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            enabledString = "Enable";
        } else {
            enabledString = "Disable";
        }

    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;

    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public String getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(String passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getRoles() {

        if (roles.compareTo("a:1:{i:0;s:10:\"ROLE_ADMIN\";}") == 0) {
            return "a:1:{i:0;s:10:\"ROLE_ADMIN\";}";
        } else if (roles.compareTo("a:1:{i:0;s:9:\"ROLE_RESP\";}") == 0) {
            return "a:1:{i:0;s:9:\"ROLE_RESP\";}";
        } else {
            return "a:1:{i:0;s:10:\" ROLE_USER\";}";
        }

    }

    public void setRoles(String roles) {
        this.roles = roles;

        if (roles.compareTo("a:1:{i:0;s:10:\"ROLE_ADMIN\";}") == 0) {
            rolesUser = "ADMIN";
        } else if (roles.compareTo("a:1:{i:0;s:9:\"ROLE_RESP\";}") == 0) {
            rolesUser = "RESP";
        } else {
            rolesUser = "USER";
        }

    }

    public void setAsUser() {
        this.roles = "a:1:{i:0;s:10:\" ROLE_USER\";}";
    }

    public void setAsRESP() {
        this.roles = "a:1:{i:0;s:9:\"ROLE_RESP\";}";
    }

    public void setAsAdmin() {
        this.roles = "a:1:{i:0;s:10:\"ROLE_ADMIN\";}";
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", cinUser=" + cinUser + ", nomUser=" + nomUser + ", prenomUser=" + prenomUser + ", adresseUser=" + adresseUser + ", telUser=" + telUser + ", imageUrlUser=" + imageUrlUser + ", typeUser=" + typeUser + ", username=" + username + ", usernameCanonical=" + usernameCanonical + ", email=" + email + ", emailCanonical=" + emailCanonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", plainPassword=" + plainPassword + ", lastLogin=" + lastLogin + ", confirmationToken=" + confirmationToken + ", passwordRequestedAt=" + passwordRequestedAt + ", roles=" + roles + '}';
    }

    /**
     * @ORM\OneToOne(targetEntity="CampBundle\Entity\Camp" ,
     * mappedBy="responsable") private $camp ;
     */
    /**
     * @ORM\OneToMany(targetEntity="EventBundle\Entity\Participation" ,
     * mappedBy="participant") private $participation ;
     */
    /**
     * @ORM\OneToMany(targetEntity="DonBundle\Entity\Argent" ,
     * mappedBy="donneur") private $argentDon ;
     *
     */
    /**
     * @ORM\OneToMany(targetEntity="CommunicationBundle\Entity\Commentaire" ,
     * mappedBy="commentateur") private $commentaires ;
     *
     */
    /**
     * @ORM\OneToMany(targetEntity="CommunicationBundle\Entity\Annonce" ,
     * mappedBy="posteur") private $annonces ;
     */
    /**
     * @ORM\OneToMany(targetEntity="CommunicationBundle\Entity\Message" ,
     * mappedBy="emetteur") private $messagesEnvoyes;
     *
     */
    /**
     * @ORM\OneToMany(targetEntity="CommunicationBundle\Entity\Message" ,
     * mappedBy="destinataire") private $messagesReçus;
     */
}
