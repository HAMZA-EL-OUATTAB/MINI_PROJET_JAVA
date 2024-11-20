package application;

import javafx.beans.property.SimpleStringProperty;

public class Etudiant {
    private final SimpleStringProperty cne;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty prenom;
    private final SimpleStringProperty filiere;

    public Etudiant(String cne, String nom, String prenom, String filiere) {
        this.cne = new SimpleStringProperty(cne);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.filiere = new SimpleStringProperty(filiere);
    }

    public String getCne() { 
        return cne.get(); 
    }

    public String getNom() { 
        return nom.get(); 
    }

    public String getPrenom() { 
        return prenom.get(); 
    }

    public String getFiliere() { 
        return filiere.get(); 
    }

    public void setCne(String cne) { 
        this.cne.set(cne); 
    }

    public void setNom(String nom) { 
        this.nom.set(nom); 
    }

    public void setPrenom(String prenom) { 
        this.prenom.set(prenom); 
    }

    public void setFiliere(String filiere) { 
        this.filiere.set(filiere); 
    }
}
