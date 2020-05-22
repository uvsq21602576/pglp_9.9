package fr.uvsq.uvsq21602576.pglp_9_9.ui;

import java.util.ArrayList;
import java.util.List;

import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeUndoable;

/**
 * Pour garder en mémoire l'état du logiciel.
 * @author Flora
 */
public class Etat {
    /**
     * Historique de commandes annulables.
     */
    private Historique historique;
    /**
     * Dessin global du logiciel.
     * Stocke tous les dessins et élements.
     */
    private Dessin global;
    /**
     * Dessin sur lequel l'utilisateur est en train de travailler.
     */
    private Dessin enCours;
    /**
     * Liste arborescence suivie pour arriver au dessin en cours.
     */
    private ArrayList<Dessin> suiviDessin;

    public Etat() {
        historique = new Historique();
        global = new Dessin("GLOBAL");
        enCours = global;
        suiviDessin = new ArrayList<>();
    }

    public void ajouteDansHistorique(final CommandeUndoable c) {
        historique.ajoute(c);
    }

    /**
     * Retourne l'historique.
     * @return Historique
     */
    public List<CommandeUndoable> getHistorique() {
        return historique.getHistorique();
    }

    public Dessin getDessinCourant() {
        return enCours;
    }

    public Dessin getGlobal() {
        return global;
    }
    
    public void voirSousDessin(final String nom) {
        Dessin ssD = this.enCours.
    }
}
