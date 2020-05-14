package fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions;

/**
 * Exception.
 * En cas d'erreur lors de la creation des tables dans la base de donnée.
 * @author Flora
 */
public class CreationTableException extends JDBCException {

    /**
     * Constructeur.
     * Crée une exception avec la raison de son lancement et du nom de la table.
     * @param nomTable Nom de la table
     * @param message Raison d'erreur
     */
    public CreationTableException(final String nomTable, final String message) {
        super("Erreur lors de la creation de la table " + nomTable + ":\n\t"
                + message);
    }
}
