package fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions;

/**
 * Exception en cas d'erreur de connexion.
 * @author Flora
 */
public class ConnectionException extends JDBCException {

    /**
     * Constructeur.
     * Message de l'exeption signale une erreur de connexion et la raison de
     * cette erreur.
     * @param message Raison d'erreur de connexion
     */
    public ConnectionException(final String message) {
        super("Erreur de connexion :\n\t" + message);
    }
}
