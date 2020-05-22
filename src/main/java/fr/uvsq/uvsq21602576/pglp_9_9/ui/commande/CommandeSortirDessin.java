package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DeplacementImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Commande pour sortir du dessin courant pour aller dans le dessin parent.
 * @author Flora
 */
public class CommandeSortirDessin implements CommandeUndoable {
    /** Etat actuel du logiciel. */
    private Etat etat;
    /** Nom du dessin avant déplacement. */
    private String dessin;

    /**
     * Constructeur.
     * Crée la commande, avec le moteur.
     * @param e Etat du logiciel
     */
    public CommandeSortirDessin(final Etat e) {
        this.etat = e;
        dessin = null;
    }

    /**
     * Execute la commande.
     * Se déplace sur le dessin parent.
     * @throws CommandeImpossibleException Si il n'y a pas de dessin parent
     */
    @Override
    public void execute() throws CommandeImpossibleException {
        this.dessin = etat.getDessinCourant().getNom();
        try {
            this.etat.retourSurDessin();
        } catch (DeplacementImpossibleException e) {
            throw new CommandeImpossibleException(e.getMessage());
        }
        this.etat.ajouteDansHistorique(this);
    }

    /**
     * Annule la commande.
     * Reviens sur le sous-dessin.
     */
    @Override
    public void undo() throws UndoImpossibleException {
        try {
            this.etat.voirSousDessin(dessin);
        } catch (DeplacementImpossibleException e) {
            throw new UndoImpossibleException(e.getMessage());
        }
    }

}
