package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Cercle;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Commande.
 * Pour crée une cercle et l'enregistrer dans le dessin courant du moteurdessin.
 * @author Flora
 */
public class CommandeCreationCercle implements CommandeUndoable {
    /** Etat actuel du logiciel. */
    private Etat etat;
    /** Arguments devant permttre la creation du cercle. */
    private Object[] arguments;
    /** Cercle créé. */
    private Cercle cercle;

    /**
     * Constructeur.
     * Crée la commande, avec le moteur et les arguments de création du cercle.
     * @param e Etat du logiciel
     * @param args Arguments de création du cercle : String, Point, int
     */
    public CommandeCreationCercle(final Etat e, final Object... args) {
        this.etat = e;
        this.arguments = args;
        cercle = null;
    }

    /**
     * Execute la commande.
     * Crée un cercle avec les arguments ennoncé, si cela est possible
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
        Point centre;
        if (arguments[1] instanceof Point) {
            centre = (Point) arguments[1];
        } else {
            throw new CommandeImpossibleException("Aucun centre saisi.");
        }
        int rayon;
        if (arguments[2] instanceof Integer) {
            rayon = (int) arguments[2];
        } else {
            throw new CommandeImpossibleException("Aucun rayon saisi.");
        }
        this.cercle = new Cercle(nom, centre, rayon);
        try {
            this.etat.getDessinCourant().ajoute(this.cercle);
        } catch (DejaExistantException e) {
            throw new CommandeImpossibleException(e.getMessage());
        }
        this.etat.ajouteDansHistorique(this);
    }

    /**
     * Annule la commande.
     * Retire le cercle précédemment créé du dessin.
     */
    @Override
    public void undo() throws UndoImpossibleException {
        this.etat.getDessinCourant().retire(cercle);
    }

}
