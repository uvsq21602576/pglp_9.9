package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.ui.DrawingUI;

/**
 * Commande.
 * Affiche le dessin en cours.
 * @author Flora
 */
public class CommandeAfficheDessin implements Commande {

    /**
     * Interface Utilisateur.
     */
    private DrawingUI ui;

    /**
     * Constrcuteur.
     * Crée la commande avec l'interface utilisateur utilisé.
     * @param dUI Interface utilisateur
     */
    public CommandeAfficheDessin(final DrawingUI dUI) {
        this.ui = dUI;
    }

    /**
     * Affiche le dessin courant dans sa totalité.
     */
    @Override
    public void execute() {
        this.ui.afficheDessin();
    }

}
