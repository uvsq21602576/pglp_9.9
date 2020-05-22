package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Interface commande.
 * Peut être annulée.
 * @author Flora
 */
public interface CommandeUndoable extends Commande {

    /**
     * Code à lancer à l'annulation de cette commande.
     * @throws UndoImpossibleException EN cas d'echec.
     */
    void undo() throws UndoImpossibleException;
}
