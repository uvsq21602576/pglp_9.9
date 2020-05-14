package fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions;

/**
 * Exception.
 * En cas d'erreur lors du remplissage d'un PreparedStatement.
 * @author Flora
 */
public class RemplissageStatementException extends JDBCException {

    /**
     * Constructeur.
     * Crée une exception avec la raison de son lancement.
     * @param message Raison de son lancement
     */
    public RemplissageStatementException(final String message) {
        super("Erreur lors du remplissage du Statement :\n\t" + message);
    }

}
