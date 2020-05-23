package fr.uvsq.uvsq21602576.pglp_9_9;

import fr.uvsq.uvsq21602576.pglp_9_9.ui.DrawingTUI;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.LigneVideException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.Commande;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.MauvaiseUtilisationException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.NoCommandException;

/**
 * Application duu logiciel de dessin.
 * @author Flora
 */
public enum DrawingApp {
    /** Pour lancer le logiciel de dessin. */
    DRAWING_APP;

    /** Traitement du logiciel. */
    public void run() {
        Arret arret = new Arret();
        Etat etat = new Etat();
        DrawingTUI tui = new DrawingTUI(etat, arret);
        Commande c;
        while (!arret.isArret()) {
            try {
                c = tui.nextCommand();
                c.execute();
                tui.afficheDessin();
            } catch (LigneVideException e) {

            } catch (CommandeImpossibleException | NoCommandException
                    | MauvaiseUtilisationException e) {
                tui.afficheErreur(e);
            }
        }
    }

    /**
     * Fonction Main.
     * @param args Arguments de la ligne de commande.
     */
    public static void main(final String[] args) {
        DRAWING_APP.run();
    }
}
