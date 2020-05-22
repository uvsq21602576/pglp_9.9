package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.ui.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Commande.
 * Affiche le dessin en cours.
 * @author Flora
 */
public class CommandeAfficheDessin implements Commande {

    private Etat etat;

    public CommandeAfficheDessin(final Etat e) {
        this.etat = e;
    }

    @Override
    public void execute()
            throws CommandeImpossibleException, UndoImpossibleException {
        System.out.println(this.etat.getDessinCourant().toString());
    }

}
