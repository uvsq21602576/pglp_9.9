package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Triangle;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.Etat;
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
    /** Arguments devant permttre la creation du triangle. */
    private Object[] arguments;
    /** Triangle créé. */
    private Triangle triangle;

    /**
     * Constructeur.
     * Crée la commande, avec le moteur et les arguments de création du
     * triangle.
     * @param e Etat du logiciel
     * @param args Arguments de création du triangle : String, Point, Point,
     *        Point
     */
    public CommandeCreationTriangle(final Etat e, final Object... args) {
        this.etat = e;
        this.arguments = args;
        triangle = null;
    }

    /**
     * Execute la commande.
     * Crée un triangle avec les arguments ennoncé, si cela est possible
     * et l'insert dans le dessin courant.
     * @throws CommandeImpossibleException Si les arguments ne sont pas du bon
     *         type, ou bon nombre. Ou si le dessin courant a déjà une forme du
     *         même nom.
     */
    @Override
    public void execute()
            throws CommandeImpossibleException, UndoImpossibleException {
        if (arguments.length != 4) {
            throw new CommandeImpossibleException("Mauvais nombre d'argument.");
        }
        String nom;
        if (arguments[0] instanceof String) {
            nom = (String) arguments[0];
        } else {
            throw new CommandeImpossibleException("Aucun nom saisi.");
        }
        Point p1;
        if (arguments[1] instanceof Point) {
            p1 = (Point) arguments[1];
        } else {
            throw new CommandeImpossibleException("Aucun centre saisi.");
        }
        Point p2;
        if (arguments[2] instanceof Point) {
            p2 = (Point) arguments[2];
        } else {
            throw new CommandeImpossibleException("Aucun rayon saisi.");
        }
        Point p3;
        if (arguments[3] instanceof Point) {
            p3 = (Point) arguments[3];
        } else {
            throw new CommandeImpossibleException("Aucun rayon saisi.");
        }
        this.triangle = new Triangle(nom, p1, p2, p3);
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
