package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.dao.DAO;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.FabriqueDAO;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.DeletionException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;

/**
 * Commande pour supprimer une sauvegarde.
 * @author Flora
 */
public class CommandeSupprimeSauvegarde implements Commande {

    /** Nom du dessin à supprimer. */
    private String nomDessin;

    /**
     * Constructeur.
     * Crée une commande à partir du nom du dessin à supprimer.
     * @param nom Nom du dessin à supprimer dans la base de donnée
     */
    public CommandeSupprimeSauvegarde(final String nom) {
        this.nomDessin = nom;
    }

    /**
     * Execute la commande.
     * Supprime le dessin dans base de données.
     */
    @Override
    public void execute() throws CommandeImpossibleException {
        DAO<Dessin> daoD = FabriqueDAO.getFabriqueDAO(FabriqueDAO.TypeDAO.JDBC)
                .getDessinDAO();
        try {
            daoD.delete(nomDessin);
        } catch (DeletionException e) {
            throw new CommandeImpossibleException(e.getMessage());
        }
    }
}
