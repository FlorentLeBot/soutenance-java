package fr.cda.projet;

import fr.cda.ihm.Formulaire;
import fr.cda.ihm.FormulaireInt;

import java.text.ParseException;

public class GUIModifierCommande {
    private GUISite formPP; // le formulaire principal du programme
    private Site site;      // objet site
/*
    public GUIAModifierCommande(GUISite formPP, Site s) {

        this.formPP = formPP;
        this.site= s;

        Formulaire form = new Formulaire("Site de vente", this.formPP, 350, 250);
        form.setPosition(20, 10);

        form.addText("REF", "Categorie", true, "");

        // bouton de validation

        form.addButton("VALIDER", "Valider");

        form.afficher();
    }

    @Override
    public void submit(Formulaire form, String nom) throws ParseException {
        if (nom.equals("VALIDER")){
            Commande c = new Commande(form.getValeurChamp("REF"));
            this.site.modifierCommande(c);
            this.formPP.AfficherResultat(site.listerToutesCommandes());
            form.fermer();
        }
    }

 */
}

