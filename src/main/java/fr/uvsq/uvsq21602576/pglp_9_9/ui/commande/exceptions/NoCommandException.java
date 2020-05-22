package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions;

/**
 * Exception.
 * Lancée en cas de commande inconnue.
 * @author Flora
 */
public class NoCommandException extends CommandeException {

    /**
     * Constructeur.
     * Crée une exception avec comme message "No entry for"
     * Et le nom de la commande indiquée.
     * @param commande nom de la commande inconnue.
     */
    public NoCommandException(final String commande) {
        super("No entry for \"" + commande + "\"");
    }
}
