package fr.cda.projet;

import java.util.*;

import fr.cda.util.*;

/**
 * Classe Site
 */

// Classe de definition du site de vente
public class Site {
    private ArrayList<Produit>  stock;              // tableau des produits
    private ArrayList<Commande> commandes;          // tableau des commandes
    private ArrayList<Commande> commande;           // tableau d'une commande
    private ArrayList<Commande> commandeLivree;     // tableau des commandes livrees

    private double solde;

    // constructeur de la classe Site

    /**
     * les attributs de la classe Site
     */
    public Site() {
        stock = new ArrayList<>();
        commandes = new ArrayList<>();
        commande = new ArrayList<>();
        commandeLivree = new ArrayList<>();
        solde = 0;

        // lecture du fichier data/Produits.txt
        // pour chaque ligne on cree un Produit que l'on ajoute a stock

        initialiserStock("data/Produits.txt");

        // lecture du fichier data/Commandes.txt
        // pour chaque ligne on cree une commande ou on ajoute une reference
        // d'un produit a une commande existante

        initialiserCommande("data/Commandes.txt");
    }

    // methode qui retourne sous la forme d'une chaine de caractere
    // tous les produits du stock

    /**
     * methode : listerTousProduits()
     *
     * @return String string
     */
    public String listerTousProduits() {
        String res = "";
        for (Produit produit : stock)
            res = res + produit.toString() + "\n";
        return res;
    }

    /**
     * methode : listerToutesCommandes()
     *
     * @return String string
     */
// methode qui retourne sous la forme d'une chaine de caractere
    // toutes les commandes
    public String listerToutesCommandes() {
        String res = "";
        for (Commande c : commandes)
            res = res + c.toString() + "\n";
        return res;
    }

    /**
     * Lister commande non livree string.
     *
     * @return the string
     */
    public String listerCommandeNonLivree()
    {
        String res = "";
        for (Commande c : commandes)
            res += c.toString() + "\n";
        return res;
    }

    // methode qui retourne sous la forme d'une chaine de caractere une commande en
    // fonction de son numero

    /**
     * methode : listerTousProduits()
     *
     * @param numero the numero
     * @return String string
     */
    public String listerCommande(int numero) {
        String res = "";
        for (Commande c : commandes) {
            if (c.getNumero() == numero) {
                commande.add(c);
                res += c.toString();
                break;
            }
        }
        for (Commande c : commandeLivree) {
            if (c.getNumero() == numero) {
                commande.add(c);
                res += c.toString();
                break;
            }
        }
        return res;
    }

    /**
     * Gets solde.
     *
     * @return the solde
     */
    public double getSolde()
    {
        return (solde);
    }

    /**
     * Calculer solde string.
     *
     * @return the string
     */
    public String calculerSolde()
    {
        int quantite;
        Produit p;
        String[] info;
        String res;
        ArrayList<String> ref;

        res = "";
        for (int i = 0; i < commandeLivree.size(); i++) {
            ref = commandeLivree.get(i).getReferences();
            res += "Commande " + commandeLivree.get(i).getNumero() + "\n";
            if (ref == null || ref.size() == 0)
                continue;
            //TODO BUG DANS CETTE METHODE
            for (int j = 0; j < ref.size(); j++)
            {
                info = ref.get(i).split("=");
                if (info.length != 2)
                    continue;
                try {
                    quantite = Integer.parseInt(info[1]);
                    p = getProduit(info[0]);
                } catch (Exception e) { continue; }
                 res += "\t" + (quantite * p.getPrix()) + "€ pour " + p.getNom() + "\n";
            }
        }
        return (res);
    }

    /**
     * Methode : lire le fichier des produits
     *
     * @param nomFichier
     */

    // chargement du fichier de stock
    private void initialiserStock(String nomFichier) {
        String[] lignes = Terminal.lireFichierTexte(nomFichier);
        for (String ligne : lignes) {
            System.out.println(ligne);
            String[] champs = ligne.split(";", 4);
            String reference = champs[0];
            String nom = champs[1];
            double prix = Double.parseDouble(champs[2]);
            int quantite = Math.abs(Integer.parseInt(champs[3]));
            Produit p = new Produit(reference,
                    nom,
                    prix,
                    quantite);
            stock.add(p);
        }
    }

    /**
     * Methode : lecture du fichier commande
     *
     * @param nomFichier
     */

    // chargement du fichier des commandes
    // je recherche si une commande est livree,
    private void initialiserCommande(String nomFichier) {
        String[] lignes = Terminal.lireFichierTexte(nomFichier);
        for (String ligne : lignes) {
            System.out.println(ligne);
            String[] champs = ligne.split("[;]", 4);

            int numero = Integer.parseInt(champs[0]);
            String date = champs[1];
            String client = champs[2];
            String ref = champs[3];
            // int quantite = Integer.parseInt(champs[4]);
            // j'instancie un objet Commande

            Commande c = rechercherCommande(numero);
            if (c == null){
                c = new Commande(numero, date, client);
                c.ajouterRef(ref);

                commandes.add(c);
            } else {
                c.ajouterRef(ref);
            }
        }
    }

    // methode : rechercher une commande dans le tableau des commandes
    // en fonction de son numero

    private Commande rechercherCommande(int num){
        for (Commande c : commandes) {
            if(c.getNumero() == num){
                return c;
            }
        }
        return null;
    }

    /**
     * Gets produit.
     *
     * @param ref the ref
     * @return the produit
     */
    public Produit  getProduit(String ref)
    {
        for (Produit p : stock)
            if (p.getReference().equals(ref)) // Recherche par reference
                return p; // p est trouve
        return null; // Pas trouve
    }

    /**
     * Livraison string.
     *
     * @return the string
     */
// methode : trier les commmandes livrees et non livrees dans des tableaux en fonction de
    // la quantité du stock
    public String livraison() {
        boolean livre;
        ArrayList<Produit> tmp = new ArrayList<Produit>();
        String res = "";
        String refCommande = "";
        int quantiteCommande = 0;
        int i;
        Produit p = null;
        // je parcours mes commandes et pour recuperer les references
        for (int index = 0; index < commandes.size(); index++){ // Pour chaque commande
            Commande c = commandes.get(index);
            ArrayList<String> ref;
            ref = c.getReferences();
            livre = true;
            i = 0;
            while (i < ref.size()) { // Pour chaque article
                String[] refsChamps = ref.get(i).split("="); // je parcours mes stocks pour recuperer les references
                try {
                    refCommande = refsChamps[0];
                    quantiteCommande = Integer.parseInt(refsChamps[1]);
                    p = getProduit(refCommande); // Trouve le produit associe
                    if (p == null) // Produit introuvable
                        throw new Exception();
                } catch (Exception e) { // Si erreur dans recherche
                    res += "Commande " + c.getNumero() + ": " + ref.get(i) + (p == null ? " reference introuvable" : " quantite invalide.");
                    livre = false;
                    continue;
                }

                // si la quantite de produit en stock est superieur a la quantite de la commande
                if (quantiteCommande > p.getQuantite()) {
                    livre = false;
                    res += "Commande " + c.getNumero() + ": Produit " + refCommande + ", Manque: " + (quantiteCommande - p.getQuantite()) + "\n";
                }
                else {
                    res += "Commande " + c.getNumero() + ": Produit " + refCommande + ", Reste: " + (p.getQuantite() - quantiteCommande) + "\n";
                    tmp.add(p);
                }
                i++;
            }
            if (livre)
            {
                commandeLivree.add(c);
                commandes.remove(index);
                i = -1;
                while (++i < tmp.size()) {
                    Produit prod = tmp.get(i);
                   try {
                        solde += Integer.parseInt(c.getReferences().get(i).split("=")[1]) * prod.getPrix();
                    } catch (Exception e) { continue; }
                    p = getProduit(prod.getReference());
                    p.setQuantite(prod.getQuantite() - quantiteCommande);
                }
                tmp.clear();
            }
        }
        return res;
    }
}