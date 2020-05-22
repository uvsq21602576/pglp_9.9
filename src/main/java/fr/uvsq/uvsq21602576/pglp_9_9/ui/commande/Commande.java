package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;

/**
 * Interface commande.
 * @author Flora
 */
public interface Commande {

    /**
     * Code à lancer à l'éxecution de cette commande.
     * @throws CommandeImpossibleException Si la commande
     *         se révèle impossible à effectuer.
     */
    void execute() throws CommandeImpossibleException;
}
