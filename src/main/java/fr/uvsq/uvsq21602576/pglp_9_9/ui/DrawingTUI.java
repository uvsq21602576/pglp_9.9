package fr.uvsq.uvsq21602576.pglp_9_9.ui;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.uvsq.uvsq21602576.pglp_9_9.Arret;
import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.Commande;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.MauvaiseUtilisationException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.NoCommandException;

/**
 * Classe gérant.
 * @author Flora
 */
public class DrawingTUI extends DrawingUI {

    /** Scanner. Pour le TUI. */
    private Scanner scanner;

    /** String pattern pour un point. */
    private static final String SP_POINT =
            "\\(\\s*-?\\d+\\s*,\\s*-?\\d+\\s*\\)";
    /** String Pattern pour un entier. */
    private static final String SP_INT = "-?\\d+";
    /** String Pattern pour un nom de variable ou fonction. */
    private static final String SP_NOM = "[a-zA-Z][a-zA-Z0-9_]*";
    /** Pattern pour un entier. */
    private static final Pattern P_INT = Pattern.compile(SP_INT);
    /** Pattern pour un argument : Point, entier ou nom. */
    private static final Pattern P_ARG =
            Pattern.compile("(" + SP_POINT + "|" + SP_INT + "|" + SP_NOM + ")");
    /** Pattern pour une ligne de commande. */
    private static final Pattern P_COMMANDE = Pattern.compile(
            "^\\s*(([a-zA-Z][a-zA-Z0-9_]*)\\s*=\\s*)?([a-zA-Z][a-zA-Z0-9_]*)\\s*(\\(\\s*((([a-zA-Z][a-zA-Z0-9_]*|-?\\d*|\\(\\s*-?\\d+\\s*,\\s*-?\\d+\\s*\\))\\s*,\\s*)*\\s*([a-zA-Z][a-zA-Z0-9_]*|-?\\d+|\\(\\s*-?\\d+\\s*,\\s*-?\\d+\\s*\\)))?\\s*\\)\\s*)?$"); // )

    /** Dans le pattern commande, indice pour trouver le nom de la fonction. */
    private static final int INDICE_FONCTION = 3;
    /** Dans le pattern commande, indice pour trouver le nom de la variable. */
    private static final int INDICE_VARIABLE = 2;
    /** Dans le pattern commande, indice pour trouver la présence de "()". */
    private static final int INDICE_PARENTHESE = 4;
    /** Dans le pattern commande, indice pour trouver les arguments. */
    private static final int INDICE_ARGUMENTS = 5;

    /**
     * Constructeur.
     * Crée une interface utilisitateur textuelles à partir de l'état de
     * logiciel et l'instance d'arret.
     * @param e Etat du logiciel
     * @param a Instance d'Arret
     */
    public DrawingTUI(final Etat e, final Arret a) {
        super(e, a);
        this.scanner = new Scanner(System.in, "UTF-8");
    }

    /**
     * Interaction utilisateur.
     * Lis une ligne ecrite par l'utilisateur et renvoie la commande
     * correspondante.
     * @return Commande d'interaction
     * @throws NoCommandException Si la ligne ne correspond à aucune commande
     * @throws MauvaiseUtilisationException Si la ligne correspond à une
     *         commande, mais que celle-ci est mal utilisée
     * @throws LigneVideException Si la ligne saisie est vide
     */
    public Commande nextCommand() throws NoCommandException,
            MauvaiseUtilisationException, LigneVideException {
        System.out.print("> ");
        String line = this.scanner.nextLine();
        if (line.trim().isEmpty()) {
            throw new LigneVideException();
        }
        Matcher m = P_COMMANDE.matcher(line);
        if (m.find()) {
            String fonction = m.group(INDICE_FONCTION);
            CommandesTUI commandeTUI = CommandesTUI.getCommandesTUI(fonction);

            String variable = m.group(INDICE_VARIABLE);
            boolean parentheses =
                    (m.group(INDICE_PARENTHESE) == null) ? false : true;
            Object[] arguments =
                    entreParenthesesParser(m.group(INDICE_ARGUMENTS));

            return commandeTUI.getCommande(variable, parentheses, arguments,
                    super.getEtat(), this);
        } else {
            throw new NoCommandException(line);
        }
    }

    Object[] entreParenthesesParser(final String entreParentheses) {
        ArrayList<Object> result = new ArrayList<>();
        if (entreParentheses == null) {
            return result.toArray();
        }
        Matcher mArg = P_ARG.matcher(entreParentheses);
        String elt;
        while (mArg.find()) {
            elt = mArg.group(1);
            if (elt.matches(SP_POINT)) {
                Matcher mInt = P_INT.matcher(elt);
                mInt.find();
                int a = Integer.parseInt(mInt.group());
                mInt.find();
                int b = Integer.parseInt(mInt.group());
                result.add(new Point(a, b));
            } else if (elt.matches(SP_NOM)) {
                result.add(elt);
            } else if (elt.matches(SP_INT)) {
                result.add(Integer.parseInt(elt));
            }
        }
        return result.toArray();
    }

    /**
     * Demande dans la console, si l'utilisateur veut ecraser une sauvegarde.
     */
    @Override
    public boolean demandeEcraseSauvegarde() {
        String rep = "";
        while (!rep.toLowerCase().equals("o")
                && !rep.toLowerCase().equals("n")) {
            System.out.println("Un sauvegarde existe déjà. "
                    + "Souhaitez vous l'ecraser ? (O/N)");
            rep = scanner.nextLine();
        }
        return rep.toLowerCase().equals("o");
    }

    /**
     * Arrete le logiciel.
     */
    public void arrete() {
        System.out.println("Logiciel arrêté.");
        super.arrete();
    }

    /**
     * Affiche dans la console d'erreur le message lié à l'exception.
     */
    @Override
    public void afficheErreur(final Exception e) {
        System.err.println(e.getMessage());
    }

    /**
     * Affiche dans la console le dessin en cours.
     */
    @Override
    public void afficheDessin() {
        System.out
                .println(super.getEtat().getTotaliteDessinCourant().toString());
        System.out.print("Dessin courant : ");
        String s = "";
        for (Dessin d : super.getEtat().getParenteDessin()) {
            s = s.concat(" -> " + d.getNom());
        }
        s = s.concat(" -> " + super.getEtat().getDessinCourant().getNom());
        System.out.println(s);
    }

    /**
     * Affiche dans la console la totalité des dessins présents.
     */
    @Override
    public void afficheTout() {
        System.out.println(super.getEtat().getGlobal().toString());
    }
}
