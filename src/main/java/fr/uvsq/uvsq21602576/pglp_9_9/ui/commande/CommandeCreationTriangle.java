package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Triangle;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Commande.
 * Pour crée une triangle et l'enregistrer dans le dessin courant du
 * moteurdessin.
 * @author Flora
 */
public class CommandeCreationTriangle implements CommandeUndoable {
    /** Etat actuel du logiciel. */
    private Etat etat;
    /** Nom de la variable. */
    private String variable;
    /** Premier point. */
    private Point p1;
    /** Deuxième point. */
    private Point p2;
    /** Troisième point. */
    private Point p3;
    /** Triangle créé. */
    private Triangle triangle;

    /**
     * Constructeur.
     * Crée la commande, avec le moteur et les arguments de création du
     * triangle.
     * @param e Etat du logiciel
     * @param v Nom de la variable
     * @param p Premier point
     * @param pp Deuxième point
     * @param ppp Troisième point
     */
    public CommandeCreationTriangle(final Etat e, final String v, final Point p, final Point pp, final Point ppp) {
        this.etat = e;
        this.variable = v;
        this.p1 = p;
        this.p2 = pp;
        this.p3 = ppp;
        triangle = null;
    }

    /**
     * Execute la commande.
     * Crée un triangle avec les arguments ennoncé
     * et l'insert dans le dessin courant.
     * @throws CommandeImpossibleException Si le dessin courant a déjà une forme du même nom.
     */
    @Override
    public void execute() throws CommandeImpossibleException {
        this.triangle = new Triangle(variable, p1, p2, p3);
        try {
            this.etat.getDessinCourant().ajoute(this.triangle);
        } catch (DejaExistantException e) {
            throw new CommandeImpossibleException(e.getMessage());
        }
        this.etat.ajouteDansHistorique(this);
    }

    /**
     * Annule la commande.
     * Retire le triangle précédemment créé du dessin.
     */
    @Override
    public void undo() throws UndoImpossibleException {
        this.etat.getDessinCourant().retire(triangle);
    }

}
