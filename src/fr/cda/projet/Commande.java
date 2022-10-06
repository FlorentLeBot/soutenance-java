package fr.cda.projet;

import java.lang.ref.Reference;
import java.util.*;

/**
 * Definition de la classe Commande
 */
public class Commande {
    /**
     * Attributs de la classe Commande
     */
    private int numero;                         // numero de la commande
    private String date;                        // date de la commande (format JJ/MM/AAAA)
    private String client;                      // nom et prenom du client

    private ArrayList<String> references = new ArrayList<>();       // tableau de toutes les references

    private boolean isLivree;

    private String messageNonLivree;

    /**
     * Constructeur de la classe Commande
     *
     * @param numero the numero
     * @param date   the date
     * @param client the client
     */
    Commande(int numero, String date, String client) {
        this.numero = numero;
        this.date = date;
        this.client = client;
        isLivree = false;
        messageNonLivree = "";
    }


    /**
     * methode toString() : affichage d'un bon de commande
     * @return String
     */

    @Override
    public String toString() {
        return  "Commande                   : " + numero + '\n' +
                "     Date                         : " + date + '\n' +
                "     Client                       : " + client + '\n' +
                "     Reference                 : " + "\n" +
                afficherLesReferences() + '\n' +
                "----------------------------------------------------------";
    }

    /**
     * methode afficherLesCommandesNonLivrees() : affichage des commandes non livrees
     *
     * @return String string
     */
    public String afficherLesCommandesNonLivrees(){
        return "Commande : " + numero + '\n' +
                "client : " + client + '\n' +
                "Date : " + date + '\n' +
                "reference : " +   '\n';
    }


    // ajouter une reference dans l'ArrayList references

    /**
     * methode ajouterRef() : ajouter une reference dans l'ArrayList des references
     *
     * @param ref the ref
     */
    public void ajouterRef(String ref)
    {
            references.add(ref);
    }

    /**
     * methode afficherLesReferences() : affichage d'une ou plusieurs references dans la commande
     *
     * @return String string
     */
// affiche les references d'une commande
    public String afficherLesReferences() {
        String res = "";
        for (String reference : references) {
            res += "             " + reference + "\n";
        }
        return res;
    }

    /*
     * getter et setter
     */

    /**
     * Sets livree.
     *
     * @param livree the livree
     */
    public void setLivree(boolean livree) {
        this.isLivree = livree;
    }

    /**
     * Is livree boolean.
     *
     * @return the boolean
     */
    public boolean isLivree() {
        return isLivree;
    }

    /**
     * methode getNumero() : recuperation du numero de la commande
     *
     * @return int : retourne le numero de la commande
     */
    public int getNumero() {
        return numero;
    }

    /**
     * methode getReferences() : recuperation de l'arraylist des reference
     *
     * @return ArrayList references
     */
    public ArrayList<String> getReferences() {
        return references;
    }

    /**
     * Gets message non livree.
     *
     * @return the message non livree
     */
    public String getMessageNonLivree() {
        return messageNonLivree + "\n";
    }

    /**
     * Sets message non livree.
     *
     * @param messageNonLivree the message non livree
     */
    public void setMessageNonLivree(String messageNonLivree) {
        this.messageNonLivree = messageNonLivree;
    }
}