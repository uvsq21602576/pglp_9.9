package fr.uvsq.uvsq21602576.pglp_9_9.exceptions;

/**
 * Pour toutes les excpetions relatives au logiciel de dessin.
 * @author Flora
 */
public class DessinException extends Exception {

    /**
     * Constructeur.
     * Crée une exception avec la raison de son lancement.
     * @param message Raison de son lancement
     */
    public DessinException(final String message) {
        super(message);
    }
}
