package fr.cda.projet;

// Classe de definition d'un produit du stock

/**
 * Classe Produit
 */

public class Produit {
    // les attributs d'un Produit

    /**
     * Attributs de la classe Produit
     */
    private String reference;      // reference du produit
    private String nom;            // nom du produit
    private double prix;           // prix du produit
    private int quantite;          // quantite du produit en stock

    // constructeurs de la classe Produit

    /**
     * Constructeur de la classe Produit
     * @param reference
     * @param nom
     * @param prix
     * @param quantite
     */
    public Produit(String reference,
                   String nom,
                   double prix,
                   int quantite) {
        this.reference = reference;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Produit(String reference) {
        this.reference = reference;
    }

    public Produit(){}

    // conversion en chaine
    // Le signe % indique le début d'un spécificateur de format.
    //

    /**
     * Methode toString() : retourne les informations du produit
     * @return String
     */
    public String toString() {
        return String.format("%-25s %-50s %3.2f   %3d", reference, nom, prix, quantite);
    }

    /**
     * getter et setter de la classe Produit
     * @return les attributs
     */

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
        if (this.quantite < 0)
            this.quantite = 0;
    }
}