package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.DAO;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.FabriqueDAO;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.CreationObjetException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.DoublonException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.InexistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.MisAJourException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.DrawingUI;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;

/**
 * Commande pour sauvegarder un dessin.
 * @author Flora
 */
public class CommandeSauvegarde implements Commande {

    /** Etat du logiciel. */
    private Etat etat;
    /** Interface Utilisateur. */
    private DrawingUI ui;

    /**
     * Constructeur.
     * Crée une commande avec l'etat du logiciel et l'interface utilisateur
     * utilisée.
     * @param e Etat du logiciel
     * @param dUI Interface utilisateur
     */
    public CommandeSauvegarde(final Etat e, final DrawingUI dUI) {
        this.etat = e;
        this.ui = dUI;
    }

    /**
     * Execute la commande.
     * Sauvegarde le dessin en cours.
     * Si il existe déjà une sauvegarde, demannde à l'utilsateur s'il veut
     * l'écraser.
     * Si oui, modifie la sauvegarde, sinon renvoie une exception.
     * @throws CommandeImpossibleException EN cas d'erreur lors de la sauvegarde
     *         ou de refus d'écraser.
     */
    @Override
    public void execute() throws CommandeImpossibleException {
        Dessin aSauv = this.etat.getTotaliteDessinCourant();
        DAO<Dessin> daoD = FabriqueDAO.getFabriqueDAO(FabriqueDAO.TypeDAO.JDBC)
                .getDessinDAO();
        try {
            daoD.create(aSauv);
        } catch (CreationObjetException e) {
            throw new CommandeImpossibleException(e.getMessage());
        } catch (DoublonException e) {
            boolean ecrase = ui.demandeEcraseSauvegarde();
            if (ecrase) {
                try {
                    daoD.update(aSauv);
                } catch (InexistantException | MisAJourException e1) {
                    throw new CommandeImpossibleException(e1.getMessage());
                }
            } else {
                throw new CommandeImpossibleException(
                        "Sauvegarde déjà existante.");
            }
        }
    }
}
