package fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions;

/**
 * Exception.
 * En cas d'erreur lors la creation d'un objet dans la base de donnée.
 * @author Flora
 */
public class CreationObjetException extends JDBCException {

    /**
     * Constructeur.
     * Crée une exception avec la raison de son lancement.
     * @param nom Nom de l'objet créé
     * @param message Raison de son lancement.
     */
    public CreationObjetException(final String nom, final String message) {
        super("Erreur lors de la creation de l'objet " + nom + " :\n\t"
                + message);
    }
}
