package fr.uvsq.uvsq21602576.pglp_9_9.ui;

import fr.uvsq.uvsq21602576.pglp_9_9.Arret;
import fr.uvsq.uvsq21602576.pglp_9_9.Etat;

/**
 * Interface Utilisateur.
 * @author Flora
 */
public abstract class DrawingUI {

    /** Pour prévenir de l'arrêt du logiciel. */
    private Arret arret;
    /** Etat du logiciel. */
    private Etat etat;

    /**
     * Constructeur.
     * Crée une UI avec l'état du logiciel.
     * @param e Etat du logiciel.
     * @param a Arret, pour signaler l'arret du logiciel
     */
    public DrawingUI(final Etat e, final Arret a) {
        this.etat = e;
        this.arret = a;
    }

    /**
     * Retourne l'etat du logiciel.
     * @return Etat du logiciel
     */
    protected Etat getEtat() {
        return etat;
    }

    /**
     * Arrete le logiciel.
     */
    public void arrete() {
        arret.setArret();
    }

    /**
     * Interagit avec l'utilisateur.
     * Pour une confirmation de l'écrasement de la sauvegarde.
     * @return Reponse de l'utilisateur, true si oui, sinon false
     */
    public abstract boolean demandeEcraseSauvegarde();

    /**
     * Affiche l'erreur.
     * @param e Erreur
     */
    public abstract void afficheErreur(Exception e);

    /**
     * Affiche le dessin en cours.
     */
    public abstract void afficheDessin();

    /**
     * Affiche tous les dessins.
     */
    public abstract void afficheTout();
}
