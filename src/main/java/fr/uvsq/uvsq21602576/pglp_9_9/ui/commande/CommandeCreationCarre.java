package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Carre;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.Etat;
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
    /** Arguments devant permttre la creation du carre. */
    private Object[] arguments;
    /** Carré créé. */
    private Carre carre;

    /**
     * Constructeur.
     * Crée la commande, avec le moteur et les arguments de création du carre.
     * @param e Etat du logiciel
     * @param args Arguments de création du carre : String, Point, int
     */
    public CommandeCreationCarre(final Etat e, final Object... args) {
        this.etat = e;
        this.arguments = args;
        carre = null;
    }

    /**
     * Execute la commande.
     * Crée un carre avec les arguments ennoncé, si cela est possible
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
        int longueur;
        if (arguments[2] instanceof Integer) {
            longueur = (int) arguments[2];
        } else {
            throw new CommandeImpossibleException("Aucun rayon saisi.");
        }
        this.carre = new Carre(nom, hg, longueur);
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
