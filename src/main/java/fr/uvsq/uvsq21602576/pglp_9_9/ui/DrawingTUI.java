package fr.uvsq.uvsq21602576.pglp_9_9.ui;

import java.util.Scanner;

import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.Commande;

/**
 * Classe gérant
 * @author Flora
 */
public class DrawingTUI {

    private Etat etat;
    private Arret arret;
    private Scanner scanner;

    public DrawingTUI(final Etat e, final Arret a) {
        this.etat = e;
        this.arret = a;
        this.scanner = new Scanner(System.in);
    }

    public Commande nextCommand() {
        if (this.scanner.hasNextLine()) {
            String line = this.scanner.nextLine();
            System.out.println(line);
        }
        return null;
    }
}
