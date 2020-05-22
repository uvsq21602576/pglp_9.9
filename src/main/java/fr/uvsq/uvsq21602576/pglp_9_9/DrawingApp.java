package fr.uvsq.uvsq21602576.pglp_9_9;

import fr.uvsq.uvsq21602576.pglp_9_9.ui.Arret;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.DrawingTUI;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.Commande;

public enum DrawingApp {
    DRAWING_APP;

    public void run() {
        Arret arret = new Arret();
        Etat etat = new Etat();
        DrawingTUI tui = new DrawingTUI(etat, arret);
        Commande c;
        while (!arret.isArret()) {
            c = tui.nextCommand();
            /*
             * c.execute();
             * tui.affiche();
             */
        }
    }

    public static void main(String args[]) {
        DRAWING_APP.run();
    }
}
