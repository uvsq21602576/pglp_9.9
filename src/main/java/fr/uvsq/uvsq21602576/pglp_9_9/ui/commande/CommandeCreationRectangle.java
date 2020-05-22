package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Rectangle;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Commande.
 * Pour crée une rectangle et l'enregistrer dans le dessin courant du
 * moteurdessin.
 * @author Flora
 */
public class CommandeCreationRectangle implements CommandeUndoable {
    /** Etat actuel du logiciel. */
    private Etat etat;
    /** Arguments devant permttre la creation du rectangle. */
    private Object[] arguments;
    /** Rectangle créé. */
    private Rectangle rectangle;

    /**
     * Constructeur.
     * Crée la commande, avec le moteur et les arguments de création du
     * rectangle.
     * @param e Etat du logiciel
     * @param args Arguments de création du rectangle : String, Point, Point
     */
    public CommandeCreationRectangle(final Etat e, final Object... args) {
        this.etat = e;
        this.arguments = args;
        rectangle = null;
    }

    /**
     * Execute la commande.
     * Crée un rectangle avec les arguments ennoncé, si cela est possible
     * et l'insert dans le dessin courant.
     * @throws CommandeImpossibleException Si les arguments ne sont pas du bon
     *         type, ou bon nombre. Ou si le dessin courant a déjà une forme du
     *         même nom.
     */
    @Override
    public void execute()
            throws CommandeImpossibleException, UndoImpossibleException {
        if (arguments.length != 3) {
            throw new CommandeImpossibleException("Mauvais nombre d'argument.");
        }
        String nom;
        if (arguments[0] instanceof String) {
            nom = (String) arguments[0];
        } else {
            throw new CommandeImpossibleException("Aucun nom saisi.");
        }
        Point hg;
        if (arguments[1] instanceof Point) {
            hg = (Point) arguments[1];
        } else {
            throw new CommandeImpossibleException("Aucun centre saisi.");
        }
        Point bd;
        if (arguments[2] instanceof Point) {
            bd = (Point) arguments[2];
        } else {
            throw new CommandeImpossibleException("Aucun rayon saisi.");
        }
        this.rectangle = new Rectangle(nom, hg, bd);
        try {
            this.etat.getDessinCourant().ajoute(this.rectangle);
        } catch (DejaExistantException e) {
            throw new CommandeImpossibleException(e.getMessage());
        }
        this.etat.ajouteDansHistorique(this);
    }

    /**
     * Annule la commande.
     * Retire le rectangle précédemment créé du dessin.
     */
    @Override
    public void undo() throws UndoImpossibleException {
        this.etat.getDessinCourant().retire(rectangle);
    }

}
