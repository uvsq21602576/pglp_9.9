package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Cercle;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
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
    /** Nom de la variable. */
    private String variable;
    /** Centre. */
    private Point centre;
    /** Rayon. */
    private int rayon;
    /** Cercle créé. */
    private Cercle cercle;

    /**
     * Constructeur.
     * Crée la commande, avec le moteur et les arguments de création du cercle.
     * @param e Etat du logiciel
     * @param v Nom de la variable
     * @param p Centre du cercle
     * @param r Rayon du cercle
     */
    public CommandeCreationCercle(final Etat e, final String v, final Point p,
            final int r) {
        this.etat = e;
        this.variable = v;
        this.centre = p;
        this.rayon = r;
        cercle = null;
    }

    /**
     * Execute la commande.
     * Crée un cercle avec les arguments ennoncé
     * et l'insert dans le dessin courant.
     * @throws CommandeImpossibleException Si le dessin courant a déjà une forme
     *         du même nom.
     */
    @Override
    public void execute() throws CommandeImpossibleException {
        if (rayon < 0) {
            throw new CommandeImpossibleException("Le rayon d'un cercle est positif.");
        }
        this.cercle = new Cercle(variable, centre, rayon);
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
