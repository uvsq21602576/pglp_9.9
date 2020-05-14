package fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions;

/**
 * Exception.
 * En cas d'erreur lors de l'insertion de donnée.
 * @author Flora
 */
public class InsertionException extends JDBCException {

    /**
     * Constructeur.
     * Crée une exception avec la raison de son lancement.
     * @param nom Nom de l'objet inséré
     * @param message Raison de son lancement
     */
    public InsertionException(final String nom, final String message) {
        super("Erreur lors de l'insertion de l'objet " + nom + " :\n\t"
                + message);
    }
}
