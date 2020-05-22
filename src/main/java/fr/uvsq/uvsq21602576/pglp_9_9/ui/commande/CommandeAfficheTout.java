package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.ui.DrawingUI;

/**
 * Commande.
 * Affiche tous les dessins présent dans le logiciel.
 * @author Flora
 */
public class CommandeAfficheTout implements Commande {

    /**
     * Interface Utilisateur.
     */
    private DrawingUI ui;

    /**
     * Constrcuteur.
     * Crée la commande avec l'interface utilisateur utilisé.
     * @param dUI Interface utilisateur
     */
    public CommandeAfficheTout(final DrawingUI dUI) {
        this.ui = dUI;
    }

    /**
     * Affiche tous les dessins présents.
     */
    @Override
    public void execute() {
        this.ui.afficheTout();
    }
}
