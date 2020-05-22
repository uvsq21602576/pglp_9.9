package fr.uvsq.uvsq21602576.pglp_9_9.exceptions;

/**
 * En cas de problème ave le dessin global.
 * @author Flora
 */
public class DessinGlobalException extends DessinException {

    /**
     * Cosntrcuteur.
     * Crée une excetion avec la raison de son lancement.
     * @param message Raison de son lancement.
     */
    public DessinGlobalException(final String message) {
        super(message);
    }
}
