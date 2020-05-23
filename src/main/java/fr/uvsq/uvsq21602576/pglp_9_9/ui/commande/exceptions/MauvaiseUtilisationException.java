package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions;

/**
 * Exception en cas de mauvais utilisation d'une commande.
 * @author Flora
 */
public class MauvaiseUtilisationException extends CommandeException {

    /**
     * Constructeur.
     * Crée une exception avec le nom de la commande quelle elle correspond et
     * la raison de son lancement.
     * @param commande Nom de la commande
     * @param message Raison de son lancement
     */
    public MauvaiseUtilisationException(final String commande,
            final String message) {
        super("La commande " + commande + " est mal utilisée : " + message
                + ".");
    }
}
