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
    /** Nom de la variable. */
    private String variable;
    /** Point haut-gauche. */
    private Point hg;
    /** Point bas-droit. */
    private Point bd;
    /** Rectangle créé. */
    private Rectangle rectangle;

    /**
     * Constructeur.
     * Crée la commande, avec le moteur et les arguments de création du
     * rectangle.
     * @param e Etat du logiciel
     * @param v Nom de la variable
     * @param p1 Point haut-gauche
     * @param p2 Point bas-droit
     */
    public CommandeCreationRectangle(final Etat e, final String v,
            final Point p1, final Point p2) {
        this.etat = e;
        this.variable = v;
        this.hg = p1;
        this.bd = p2;
        rectangle = null;
    }

    /**
     * Execute la commande.
     * Crée un rectangle avec les arguments ennoncé
     * et l'insert dans le dessin courant.
     * @throws CommandeImpossibleException Si le dessin courant a déjà une forme
     *         du même nom.
     */
    @Override
    public void execute() throws CommandeImpossibleException {
        this.rectangle = new Rectangle(variable, hg, bd);
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
