package Adapters;

/**
 * Created by Bakota on 04/03/2019.
 */

public class Contacts {
    public String Nom;
    public String Numero;
    public String Adresse;

    public Contacts() {
    }

    public Contacts(String nom, String numero, String adresse) {
        Nom = nom;
        Numero = numero;
        Adresse = adresse;
    }


    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }
}
