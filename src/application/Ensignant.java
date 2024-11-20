package application;

import javafx.beans.property.SimpleStringProperty;

public class Ensignant {
    private final SimpleStringProperty cne;
    private final SimpleStringProperty nom;
    private final SimpleStringProperty prenom;
    private final SimpleStringProperty filiere;
    private final SimpleStringProperty matiere;


    public Ensignant(String cne, String nom, String prenom, String filiere,String matiere) {
        this.cne = new SimpleStringProperty(cne);
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.filiere = new SimpleStringProperty(filiere);
        this.matiere = new SimpleStringProperty(matiere);

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
    public String getMatiere() { 
        return matiere.get(); 
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
    public void setMatiere(String filiere) { 
        this.matiere.set(filiere); 
    }

}
