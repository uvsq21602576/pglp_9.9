package fr.uvsq.uvsq21602576.pglp_9_9;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DeplacementImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.NoFilsException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.ComposantDessin;
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
    private LinkedList<Dessin> parenteDessin;

    /**
     * Constructeur. Initialise un état vide.
     */
    public Etat() {
        historique = new Historique();
        global = new Dessin();
        enCours = global;
        parenteDessin = new LinkedList<>();
    }

    /**
     * Ajoute une commande pouvant être annulée dans l'historique.
     * @param c Commande à ajouter
     */
    public void ajouteDansHistorique(final CommandeUndoable c) {
        historique.ajoute(c);
    }

    /**
     * Retourne l'historique.
     * @return Historique
     */
    public Historique getHistorique() {
        return historique;
    }

    /**
     * Retorune le dessin couramment visualisé.
     * @return Dessin courant
     */
    public Dessin getDessinCourant() {
        return enCours;
    }

    /**
     * Retourne le dessin global.
     * @return Dessin global
     */
    public Dessin getGlobal() {
        return global;
    }

    /**
     * Retorune le dessin regardé en entier, c'est-à-dire un fils de global.
     * @return Dessin courant en entier.
     */
    public Dessin getTotaliteDessinCourant() {
        if (this.parenteDessin.isEmpty()) {
            return enCours;
        }
        return this.parenteDessin.getFirst();
    }

    /**
     * Retourne la parente du dessin en cours.
     * @return Parente non modifiable du dessin en cours
     */
    public List<Dessin> getParenteDessin() {
        return Collections.unmodifiableList(this.parenteDessin);
    }

    /**
     * Se déplace dans un sous dessin du dessin courant.
     * Qui devient alors le courant.
     * @param nom Nom du sous-dessin
     * @throws DeplacementImpossibleException Si aucun fils dessin de ce nom
     *         existe
     */
    public void voirSousDessin(final String nom)
            throws DeplacementImpossibleException {
        ComposantDessin ssD;
        try {
            ssD = this.enCours.getComposant(nom);
        } catch (NoFilsException e) {
            throw new DeplacementImpossibleException(nom, e.getMessage());
        }
        if (!(ssD instanceof Dessin)) {
            throw new DeplacementImpossibleException(nom, "Non un dessin.");
        }
        if (enCours.getNom() != Dessin.GLOBAL) {
            this.parenteDessin.addLast(enCours);
        }
        this.enCours = (Dessin) ssD;
    }

    /**
     * Se deplace sur le dessin parent à celui en cours.
     * @throws DeplacementImpossibleException S'il y a pas de dessin parent.
     */
    public void retourSurDessin() throws DeplacementImpossibleException {
        if (enCours.getNom() == Dessin.GLOBAL) {
            throw new DeplacementImpossibleException("le dessin parent",
                    "Vous n'êtes pas dans un dessin");
        }
        if (this.parenteDessin.isEmpty()) {
            enCours = global;
        } else {
            Dessin surDessin = this.parenteDessin.removeLast();
            enCours = surDessin;
        }
    }
}
