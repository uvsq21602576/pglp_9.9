package fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions;

/**
 * Exception.
 * En cas d'erreur lors de la supression de la base de donnée.
 * @author Flora
 */
public class SuppressionException extends JDBCException {

    /**
     * Constructeur.
     * Crée une exception avec le nom de l'objet et la raison de son lancement.
     * @param nom Nom de l'objet supprimé
     * @param message Raison du lancement
     */
    public SuppressionException(final String nom, final String message) {
        super("Erreur lors de la suppression de l'objet " + nom + " :\n\t"
                + message);
    }
}
