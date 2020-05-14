package fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions;

/**
 * Exception.
 * En cas d'erreur lors de la lecture d'un objet.
 * @author Flora
 */
public class LectureException extends JDBCException {

    /**
     * Constructeur.
     * Crée une excpetion avec le nom d'objet lu et la raison de l'exception.
     * @param nom Nom de l'objet lu
     * @param message Raison du lancement
     */
    public LectureException(final String nom, final String message) {
        super("Erreur lors de la lecture de " + nom + " :\n\t" + message);
    }
}
