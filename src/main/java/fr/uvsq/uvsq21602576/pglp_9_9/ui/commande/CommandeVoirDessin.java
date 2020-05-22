package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DeplacementImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Commande pour voir un sous dessin du dessin courant.
 * @author Flora
 */
public class CommandeVoirDessin implements CommandeUndoable {
    /** Etat actuel du logiciel. */
    private Etat etat;
    /** Nom du sous-dessin. */
    private String ssDessin;

    /**
     * Constructeur.
     * Crée la commande, avec le moteur et le nom du sous dessin.
     * @param e Etat du logiciel
     * @param nom Nom du sous dessin
     */
    public CommandeVoirDessin(final Etat e, final String nom) {
        this.etat = e;
        this.ssDessin = nom;
    }

    /**
     * Execute la commande.
     * Se déplace sur le sous-dessin.
     * @throws CommandeImpossibleException Si il n'y a pas de dessin parent
     */
    @Override
    public void execute() throws CommandeImpossibleException {
        try {
            this.etat.voirSousDessin(this.ssDessin);
        } catch (DeplacementImpossibleException e) {
            throw new CommandeImpossibleException(e.getMessage());
        }
        this.etat.ajouteDansHistorique(this);
    }

    /**
     * Annule la commande.
     * Reviens sur le dessin parent.
     */
    @Override
    public void undo() throws UndoImpossibleException {
        try {
            this.etat.retourSurDessin();
        } catch (DeplacementImpossibleException e) {
            throw new UndoImpossibleException(e.getMessage());
        }
    }

}
