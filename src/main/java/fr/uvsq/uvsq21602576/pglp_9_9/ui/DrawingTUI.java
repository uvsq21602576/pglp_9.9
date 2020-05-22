package fr.uvsq.uvsq21602576.pglp_9_9.ui;

import java.util.Scanner;

import fr.uvsq.uvsq21602576.pglp_9_9.Arret;
import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.Commande;

/**
 * Classe gérant
 * @author Flora
 */
public class DrawingTUI extends DrawingUI {

    /** Scanner. Pour le TUI. */
    private Scanner scanner;

    public DrawingTUI(final Etat e, final Arret a) {
        super(e, a);
        this.scanner = new Scanner(System.in);
    }

    public Commande nextCommand() {
        if (this.scanner.hasNextLine()) {
            String line = this.scanner.nextLine();
            System.out.println(line);
        }
        return null;
    }

    public boolean demandeEcraseSauvegarde() {
        String rep = "";
        while (!rep.toLowerCase().equals("o")
                && !rep.toLowerCase().equals("n")) {
            System.out.println(
                    "Un sauvegarde existe déjà. Souhaitez vous l'ecraser ? (O/N)");
            rep = scanner.nextLine();
        }
        if (rep.toLowerCase().equals("o")) {
            return true;
        } else {
            return false;
        }
    }

    public void afficheErreur(Exception e) {
        System.err.println(e.getMessage());
    }

    @Override
    public void afficheDessin() {
        System.out
                .println(super.getEtat().getTotaliteDessinCourant().toString());
        System.out.print("Dessin courrant : ");
        String s = "";
        for (Dessin d : super.getEtat().getParenteDessin()) {
            s = s.concat("-> " + d.getNom());
        }
        s = s.concat("-> " + super.getEtat().getDessinCourant().getNom());
        System.out.println(s);
    }

    @Override
    public void afficheTout() {
        System.out.println(super.getEtat().getGlobal().toString());
    }
}
