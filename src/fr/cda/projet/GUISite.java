package fr.cda.projet;

import fr.cda.ihm.*;

/**
 * The type Gui site.
 */
// Classe de definition de l'IHM principale du compte
//
public class GUISite implements FormulaireInt
{
    private Site site;  // Le site
    private Formulaire form;

    /**
     * Instantiates a new Gui site.
     *
     * @param site the site
     */
// Constructeur
    //
    public GUISite(Site site)
    {
        this.site = site;

        // Creation du formulaire
        Formulaire form = new Formulaire("Site de vente",this,1100,730);
        
        //  Creation des elements de l'IHM

        // bouton pour afficher tous les produits

        form.setPosition(20,10);
        form.addLabel("Afficher tous les produits du stock");
        form.addButton("AFF_STOCK","Tous le stock");
        form.addLabel("");

        // bouton pour afficher toutes les commandes
        form.addLabel("Afficher tous les bons de commande");
        form.addButton("AFF_COMMANDES","Toutes les commandes");
        form.addLabel("");

        // bouton pour afficher une commande
        form.addText("NUM_COMMANDE","Numero de commande",true,"1");
        form.addButton("AFF_COMMANDE","Afficher");
        form.addLabel("");

        // bouton pour modifier la commande
        form.addButton("MODIFIER_COMMANDE","Modifier");
        form.addLabel("");

        // bouton pour livrer les commandes
        form.addButton("LIVRER","Livrer");
        form.addLabel("");

        // bouton pour afficher les commandes non livrées
        form.addButton("COMMANDES_NON_LIVREES","Commandes non livrées");
        form.addLabel("");

        // bouton pour calculer les ventes
        form.addButton("VENTES","Calculer ventes");
        form.addLabel("");

        // afficher les resultats
        form.setPosition(400,0);
        form.addZoneText("RESULTATS","Resultats",
                            true,
                            "",
                            600,700);

        // Affichage du formulaire
        form.afficher();
    }

    // Methode appellee quand on clique dans un bouton
    //
    public void submit(Formulaire form,String nomSubmit) {
        String res = "";

        // affichage de tous les produits du stock
        //
        if (nomSubmit.equals("AFF_STOCK"))
            res = site.listerTousProduits();

        // affichage de toutes les commandes
        //
        if (nomSubmit.equals("AFF_COMMANDES"))
            res = site.listerToutesCommandes();

        // affichage des commandes non livrees

        if (nomSubmit.equals("COMMANDES_NON_LIVREES"))
            res = site.listerCommandeNonLivree();

        // Vendre les produits
        //
        if (nomSubmit.equals("LIVRER"))
            res = site.livraison();

        // Calculer ventes
        //
        if (nomSubmit.equals("VENTES")) {
            res = "Solde: " + site.getSolde() + "€\n";
            res += site.calculerSolde();
        }

        // affichage d'une commande
        if (nomSubmit.equals("AFF_COMMANDE")) {
            try {
                String numStr = form.getValeurChamp("NUM_COMMANDE");
                int num = Integer.parseInt(numStr);
                res = site.listerCommande(num);
            } catch (Exception e) {
                res = "Vous devez entrer un entier";
            }
        }

        // modifier la commande

        if (nomSubmit.equals("REF"))
        {
            GUIModifierCommande modifierCommande = new GUIModifierCommande();
        }

        if (nomSubmit.equals("FERMER"))
            form.fermer();

        if (res.length() != 0)
            form.setValeurChamp("RESULTATS", res);
    }

    /**
     * Afficher resultat.
     *
     * @param res the res
     */
    public void AfficherResultat(String res){
        form.setValeurChamp("RESULTATS",res);
    }

}