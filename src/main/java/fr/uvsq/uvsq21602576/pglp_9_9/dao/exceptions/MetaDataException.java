package fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions;

/**
 * Exception.
 * En cas d'erreur lors d'accès aux métadata de la base de donnée.
 * @author Flora
 */
public class MetaDataException extends JDBCException {

    /**
     * Constructeur.
     * Crée une exception avec la raison de son lancement.
     * @param message Raison d'erreur.
     */
    public MetaDataException(final String message) {
        super("Erreur d'accession aux MétaDonnées :\n\t" + message);
    }
}
