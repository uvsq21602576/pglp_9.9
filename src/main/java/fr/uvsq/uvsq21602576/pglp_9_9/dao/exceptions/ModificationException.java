package fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions;

/**
 * Exception.
 * En cas d'erreur lors de la modification de donnée.
 * @author Flora
 */
public class ModificationException extends JDBCException {

    /**
     * Constructeur.
     * Crée une exception avec la raison de son lancement.
     * @param nom Nom de l'objet modifié
     * @param message Raison de son lancement
     */
    public ModificationException(final String nom, final String message) {
        super("Erreur lors de la modification de l'objet " + nom + " :\n\t"
                + message);
    }
}
