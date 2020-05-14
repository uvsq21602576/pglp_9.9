package fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions;

/**
 * Exception.
 * En cas d'erreur lors de la mise à jour de donnée.
 * @author Flora
 */
public class MisAJourException extends JDBCException {

    /**
     * Constructeur.
     * Crée une exception avec la raison de son lancement.
     * @param nom Nom de l'objet mis à jour
     * @param message Raison de son lancement
     */
    public MisAJourException(final String nom, final String message) {
        super("Erreur lors de la mis à jour de l'objet " + nom + " :\n\t"
                + message);
    }
}
