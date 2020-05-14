package fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions;

/**
 * Exception.
 * En cas de recherche non concluante.
 * @author Flora
 */
public class InexistantException extends JDBCException {

    /**
     * Constructeur.
     * Crée une exception avec le nom de l'objet non trouvé.
     * @param nom Nom de l'objet non trouvé
     */
    public InexistantException(final String nom) {
        super("Objet " + nom + " introuvable.");
    }
}
