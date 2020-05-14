package fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions;

/**
 * Exceptions.
 * Pour les exceptions liées au JDBC.
 * @author Flora
 */
public class JDBCException extends Exception {

    /**
     * Constructeur.
     * Exception avec raison du lancement.
     * @param message Raison du lancement.
     */
    public JDBCException(final String message) {
        super(message);
    }
}
