package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions;

/**
 * Exception pour toutes les eceptions concernant les commandes.
 * @author Flora
 */
public class CommandeException extends Exception {

    /**
     * Constructeur.
     * Crée une exception avec la raison de son lancement.
     * @param message Raison de son lancement
     */
    public CommandeException(final String message) {
        super(message);
    }
}
