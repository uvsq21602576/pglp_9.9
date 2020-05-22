package fr.uvsq.uvsq21602576.pglp_9_9.exceptions;

/**
 * Exception.
 * En cas d'acces à un fils inexistant du dessin.
 * @author Flora
 */
public class NoFilsException extends DessinException {

    /**
     * Constructeur.
     * Crée une exception avec le nom de l'objet concerné.
     * @param nomObjet Nom de l'objet concerné
     * @param nomDessin Nom du dessin concerné
     */
    public NoFilsException(final String nomObjet, final String nomDessin) {
        super("Objet " + nomObjet + " non existant dans le dessin " + nomDessin
                + ".");
    }
}
