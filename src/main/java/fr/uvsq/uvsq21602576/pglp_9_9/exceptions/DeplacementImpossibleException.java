package fr.uvsq.uvsq21602576.pglp_9_9.exceptions;

/**
 * En cas de problème lors d'un déplacement vers un sous dessin.
 * @author Flora
 */
public class DeplacementImpossibleException extends DessinException {

    /**
     * Constructeur.
     * Crée ue exception avec le nom du sous dessin associé et la raison de son
     * lancement.
     * @param nomSsDessin Nom du sous dessin
     * @param message Raison de son lancement.
     */
    public DeplacementImpossibleException(final String nomSsDessin,
            final String message) {
        super("Déplacement vers " + nomSsDessin + " impossible : " + message);
    }
}
