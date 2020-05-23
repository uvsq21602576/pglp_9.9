package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Carre;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Commande.
 * Pour crée une carre et l'enregistrer dans le dessin courant du moteurdessin.
 * @author Flora
 */
public class CommandeCreationCarre implements CommandeUndoable {
    /** Etat actuel du logiciel. */
    private Etat etat;
    /** Nom de la variable. */
    private String variable;
    /** Point haut-gauche. */
    private Point hg;
    /** Longueur. */
    private int longueur;
    /** Carré créé. */
    private Carre carre;

    /**
     * Constructeur.
     * Crée la commande, avec le moteur et les arguments de création du carre.
     * @param e Etat du logiciel
     * @param v Nom de la variable
     * @param p Point haut-gauche
     * @param l Longueur d'un coté
     */
    public CommandeCreationCarre(final Etat e, final String v, final Point p, final int l) {
        this.etat = e;
        this.variable = v;
        this.hg = p;
        this.longueur = l;
        carre = null;
    }

    /**
     * Execute la commande.
     * Crée un carre avec les arguments ennoncés
     * et l'insert dans le dessin courant.
     * @throws CommandeImpossibleException Si le dessin courant a déjà une forme du même nom.
     */
    @Override
    public void execute() throws CommandeImpossibleException {
        if (longueur < 0) {
            throw new CommandeImpossibleException("La longueur de coté d'un carré est positive.");
        }
        this.carre = new Carre(variable, hg, longueur);
        try {
            this.etat.getDessinCourant().ajoute(this.carre);
        } catch (DejaExistantException e) {
            throw new CommandeImpossibleException(e.getMessage());
        }
        this.etat.ajouteDansHistorique(this);
    }

    /**
     * Annule la commande.
     * Retire le carre précédemment créé du dessin.
     */
    @Override
    public void undo() throws UndoImpossibleException {
        this.etat.getDessinCourant().retire(carre);
    }

}
