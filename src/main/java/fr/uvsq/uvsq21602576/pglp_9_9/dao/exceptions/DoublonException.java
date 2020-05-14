package fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions;

/**
 * Exception.
 * En cas de duplication de clé.
 * @author Flora
 */
public class DoublonException extends JDBCException {

    /**
     * Constucteur.
     * Crée une exception de duplication, avec la valeur de la clé dupliquée.
     * @param nomId Valeur de la clé dupliquée
     */
    public DoublonException(final String nomId) {
        super("Erreur de duplication : un objet " + nomId + " existe déjà.");
    }
}
