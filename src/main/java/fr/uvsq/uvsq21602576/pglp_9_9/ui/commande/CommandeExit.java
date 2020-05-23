package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.ui.DrawingUI;

/**
 * Commande pour demander l'arret du programme.
 * @author Flora
 */
public class CommandeExit implements Commande {
    /**
     * Interface utilisateur.
     * Pour arreter le programme et prévenir l'utilisateur.
     */
    private DrawingUI ui;

    /**
     * Constructeur.
     * Crée une commande avec l'iterface utilisateur
     * @param dUI Interface utilisateur
     */
    public CommandeExit(final DrawingUI dUI) {
        this.ui = dUI;
    }

    /**
     * Previens l'arret du programme.
     * En modifiant Arret.
     */
    public void execute() {
        this.ui.arrete();
    }

}
