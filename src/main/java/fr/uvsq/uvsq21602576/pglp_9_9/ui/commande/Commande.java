package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Interface commande.
 * @author Flora
 */
public interface Commande {

    /**
     * Code à lancer à l'éxecution de cette commande.
     * @throws CommandeImpossibleException Si la commande
     *         se révèle impossible à effectuer.
     * @throws UndoImpossibleException Si la commande undo échoue.
     */
    void execute() throws CommandeImpossibleException, UndoImpossibleException;
}
