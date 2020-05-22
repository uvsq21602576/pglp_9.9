package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.Historique;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Commande pour annuler la dernière commande faite.
 * @author Flora
 */
public class CommandeUndo implements Commande {
    /**
     * Historique de commande.
     */
    private Historique historique;

    /**
     * Constrcuteur.
     * Crée la commande à partir de l'historique.
     * @param h Historique.
     */
    public CommandeUndo(final Historique h) {
        this.historique = h;
    }

    /**
     * Execution.
     * Annule la dernieère commande de l'historique.
     * @throws CommandeImpossibleException En cas d'echec de l'annulation.
     */
    public void execute() throws CommandeImpossibleException {
        try {
            historique.retourArriere();
        } catch (UndoImpossibleException e) {
            throw new CommandeImpossibleException(e.getMessage());
        }
        System.out.println("undo effectué");
    }

}
