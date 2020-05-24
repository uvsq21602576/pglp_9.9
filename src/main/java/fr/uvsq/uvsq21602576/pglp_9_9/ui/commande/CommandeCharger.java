package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.DAO;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.FabriqueDAO;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.InexistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.RechercheException;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;

/**
 * Commande pour charger un dessin de la base de données.
 * @author Flora
 */
public class CommandeCharger implements CommandeUndoable {

    /** Etat actuel du logiciel. */
    private Etat etat;
    /** Nom du dessin à charger. */
    private String nomDessin;

    /**
     * Constructeur.
     * Crée une commande à partir de l'état du logiciel et du nom du dessin à
     * charger.
     * @param e Etat du logiciel
     * @param nom Nom du dessin à charger
     */
    public CommandeCharger(final Etat e, final String nom) {
        this.etat = e;
        this.nomDessin = nom;
    }

    /**
     * Execute la commande.
     * Charge une sauvegarde et le met en tant que dessin sous global.
     * @throws CommandeImpossibleException En cas d'erreur lors de la recherche
     *         dans la base de données, ou si un dessin du même nom est déjà
     *         ouvert dans le logiciel.
     */
    @Override
    public void execute() throws CommandeImpossibleException {
        DAO<Dessin> daoD = FabriqueDAO.getFabriqueDAO(FabriqueDAO.TypeDAO.JDBC)
                .getDessinDAO();
        Dessin d = null;
        try {
            d = daoD.find(nomDessin);
        } catch (InexistantException | RechercheException e) {
            throw new CommandeImpossibleException(e.getMessage());
        }
        try {
            this.etat.getGlobal().ajoute(d);
        } catch (DejaExistantException e) {
            throw new CommandeImpossibleException(e.getMessage());
        }
        this.etat.ajouteDansHistorique(this);
    }

    /**
     * Annule la commande.
     * Retire de global le dessin précédemment ajouté.
     */
    public void undo() {
        this.etat.getGlobal().retire(nomDessin);
    }
}
