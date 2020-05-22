package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Rectangle;
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
    /** Nombre d'arguments necessaire à la création du rectangle. */
    private static final int NB_ARG_NECESSAIRE = 3;

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
    public void execute() throws CommandeImpossibleException {
        if (arguments.length != NB_ARG_NECESSAIRE) {
            throw new CommandeImpossibleException("Mauvais nombre d'argument.");
        }
        int iArg = 0;
        String nom;
        if (arguments[iArg] instanceof String) {
            nom = (String) arguments[iArg];
        } else {
            throw new CommandeImpossibleException("Aucun nom saisi.");
        }
        iArg++;
        Point hg;
        if (arguments[iArg] instanceof Point) {
            hg = (Point) arguments[iArg];
        } else {
            throw new CommandeImpossibleException(
                    "Aucun point haut-gauche saisi.");
        }
        iArg++;
        Point bd;
        if (arguments[iArg] instanceof Point) {
            bd = (Point) arguments[iArg];
        } else {
            throw new CommandeImpossibleException(
                    "Aucun point bas-droit saisi.");
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
