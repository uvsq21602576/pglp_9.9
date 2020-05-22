package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.ui.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

public class CommandeAfficheTout implements Commande {

    private Etat etat;

    public CommandeAfficheTout(final Etat e) {
        this.etat = e;
    }

    @Override
    public void execute()
            throws CommandeImpossibleException, UndoImpossibleException {
        System.out.println(this.etat.getGlobal().toString());
    }

}
