package fr.uvsq.uvsq21602576.pglp_9_9.exceptions;

/**
 * Exception.
 * En cas d'ajout dans un dessin d'une forme ou dessin déjà existant.
 * @author Flora
 */
public class DejaExistantException extends DessinException {

    /**
     * Constructeur.
     * Crée une exception avec le nom de l'objet concerné.
     * @param nomObjet Nom de l'objet concerné
     * @param nomDessin Nom du dessin concerné
     */
    public DejaExistantException(final String nomObjet,
            final String nomDessin) {
        super("Objet " + nomObjet + " déjà existant dans le dessin " + nomDessin
                + ".");
    }
}
