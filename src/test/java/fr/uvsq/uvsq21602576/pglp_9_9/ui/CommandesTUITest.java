package fr.uvsq.uvsq21602576.pglp_9_9.ui;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.Commande;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeAfficheDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeAfficheTout;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeAjouteDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeCharger;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeCopie;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeCreationCarre;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeCreationCercle;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeCreationDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeCreationRectangle;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeCreationTriangle;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeDeplace;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeExit;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeRetireDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeSauvegarde;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeSortirDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeSupprime;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeSupprimeSauvegarde;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeUndo;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeVoirDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.MauvaiseUtilisationException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.NoCommandException;

/**
 * Classe de Test pour commandesTUI.
 * @author Flora
 */
public class CommandesTUITest {

    /** Etat du logiciel. */
    private final Etat etat = new Etat();

    /**
     * Testes les possibilités pour getCommande pour undo.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void undoGetCommandTests() throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        Commande c = CommandesTUI.UNDO.getCommande(null, true, obj.toArray(),
                etat, null);
        assertTrue(c instanceof CommandeUndo);
        try {
            CommandesTUI.UNDO.getCommande(null, false, obj.toArray(), etat,
                    null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.UNDO.getCommande("variable", true, obj.toArray(), etat,
                    null);
            fail("Variable : Expected "
                    + "MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.UNDO.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour exit.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void exitGetCommandTests() throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        Commande c = CommandesTUI.EXIT.getCommande(null, true, obj.toArray(),
                etat, null);
        assertTrue(c instanceof CommandeExit);
        try {
            CommandesTUI.EXIT.getCommande(null, false, obj.toArray(), etat,
                    null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.EXIT.getCommande("variable", true, obj.toArray(), etat,
                    null);
            fail("Variable : Expected "
                    + "MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.EXIT.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour Creation carré.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void creationCarreGetCommandTests()
            throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        obj.add(new Point(1, 1));
        obj.add(10);
        Commande c = CommandesTUI.CREATION_CARRE.getCommande("variable", true,
                obj.toArray(), etat, null);
        assertTrue(c instanceof CommandeCreationCarre);
        try {
            CommandesTUI.CREATION_CARRE.getCommande("variable", false,
                    obj.toArray(), etat, null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.CREATION_CARRE.getCommande(null, true, obj.toArray(),
                    etat, null);
            fail("Variable : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add(new Point(1, 1));
        obj.add("String");
        try {
            CommandesTUI.CREATION_CARRE.getCommande("variable", true,
                    obj.toArray(), etat, null);
            fail("Argument 2 int : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add("String");
        obj.add(10);
        try {
            CommandesTUI.CREATION_CARRE.getCommande("variable", true,
                    obj.toArray(), etat, null);
            fail("Argument 1 Point : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.CREATION_CARRE.getCommande("variable", true,
                    obj.toArray(), etat, null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour Creation cercle.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void creationCercleGetCommandTests()
            throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        obj.add(new Point(1, 1));
        obj.add(10);
        Commande c = CommandesTUI.CREATION_CERCLE.getCommande("variable", true,
                obj.toArray(), etat, null);
        assertTrue(c instanceof CommandeCreationCercle);
        try {
            CommandesTUI.CREATION_CERCLE.getCommande("variable", false,
                    obj.toArray(), etat, null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.CREATION_CERCLE.getCommande(null, true, obj.toArray(),
                    etat, null);
            fail("Variable : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add(new Point(1, 1));
        obj.add("String");
        try {
            CommandesTUI.CREATION_CERCLE.getCommande("variable", true,
                    obj.toArray(), etat, null);
            fail("Argument 2 int : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add("String");
        obj.add(10);
        try {
            CommandesTUI.CREATION_CERCLE.getCommande("variable", true,
                    obj.toArray(), etat, null);
            fail("Argument 1 Point : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.CREATION_CERCLE.getCommande("variable", true,
                    obj.toArray(), etat, null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour Creation rectangle.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void creationRectangleGetCommandTests()
            throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        obj.add(new Point(1, 1));
        obj.add(new Point(1, 1));
        Commande c = CommandesTUI.CREATION_RECTANGLE.getCommande("variable",
                true, obj.toArray(), etat, null);
        assertTrue(c instanceof CommandeCreationRectangle);
        try {
            CommandesTUI.CREATION_RECTANGLE.getCommande("variable", false,
                    obj.toArray(), etat, null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.CREATION_RECTANGLE.getCommande(null, true,
                    obj.toArray(), etat, null);
            fail("Variable : Expected "
                    + "MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add(new Point(1, 1));
        obj.add("String");
        try {
            CommandesTUI.CREATION_RECTANGLE.getCommande("variable", true,
                    obj.toArray(), etat, null);
            fail("Argument 2 point : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add("String");
        obj.add(new Point(1, 1));
        try {
            CommandesTUI.CREATION_RECTANGLE.getCommande("variable", true,
                    obj.toArray(), etat, null);
            fail("Argument 1 Point : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.CREATION_RECTANGLE.getCommande("variable", true,
                    obj.toArray(), etat, null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour Creation triangle.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void creationTriangleGetCommandTests()
            throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        obj.add(new Point(1, 1));
        obj.add(new Point(1, 1));
        obj.add(new Point(1, 1));
        Commande c = CommandesTUI.CREATION_TRIANGLE.getCommande("variable",
                true, obj.toArray(), etat, null);
        assertTrue(c instanceof CommandeCreationTriangle);
        try {
            CommandesTUI.CREATION_TRIANGLE.getCommande("variable", false,
                    obj.toArray(), etat, null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.CREATION_TRIANGLE.getCommande(null, true,
                    obj.toArray(), etat, null);
            fail("Variable : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add(new Point(1, 1));
        obj.add("String");
        obj.add(new Point(1, 1));
        try {
            CommandesTUI.CREATION_TRIANGLE.getCommande("variable", true,
                    obj.toArray(), etat, null);
            fail("Argument 2 point : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add("String");
        obj.add(new Point(1, 1));
        obj.add(new Point(1, 1));
        try {
            CommandesTUI.CREATION_TRIANGLE.getCommande("variable", true,
                    obj.toArray(), etat, null);
            fail("Argument 1 Point : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add(new Point(1, 1));
        obj.add(new Point(1, 1));
        obj.add("String");
        try {
            CommandesTUI.CREATION_TRIANGLE.getCommande("variable", true,
                    obj.toArray(), etat, null);
            fail("Argument 3 Point : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.CREATION_TRIANGLE.getCommande("variable", true,
                    obj.toArray(), etat, null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour Creation dessin.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void creationDessinGetCommandTests()
            throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        Commande c = CommandesTUI.CREATION_DESSIN.getCommande("variable", true,
                obj.toArray(), etat, null);
        assertTrue(c instanceof CommandeCreationDessin);
        try {
            CommandesTUI.CREATION_DESSIN.getCommande("variable", false,
                    obj.toArray(), etat, null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.CREATION_DESSIN.getCommande(null, true, obj.toArray(),
                    etat, null);
            fail("Variable : Expected "
                    + "MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.CREATION_DESSIN.getCommande("variable", true,
                    obj.toArray(), etat, null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour Copie.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void copieGetCommandTests() throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        obj.add("String");
        Commande c = CommandesTUI.COPIE.getCommande("variable", true,
                obj.toArray(), etat, null);
        assertTrue(c instanceof CommandeCopie);
        try {
            CommandesTUI.COPIE.getCommande("variable", false, obj.toArray(),
                    etat, null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.COPIE.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Variable : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add(10);
        try {
            CommandesTUI.COPIE.getCommande("variable", true, obj.toArray(),
                    etat, null);
            fail("Argument 1 String : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.COPIE.getCommande("variable", true, obj.toArray(),
                    etat, null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour deplace.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void deplaceGetCommandTests() throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        obj.add("String");
        obj.add(new Point(1, 1));
        Commande c = CommandesTUI.DEPLACE.getCommande(null, true, obj.toArray(),
                etat, null);
        assertTrue(c instanceof CommandeDeplace);
        try {
            CommandesTUI.DEPLACE.getCommande(null, false, obj.toArray(), etat,
                    null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.DEPLACE.getCommande("variable", true, obj.toArray(),
                    etat, null);
            fail("Variable : Expected "
                    + "MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add("String");
        obj.add("String");
        try {
            CommandesTUI.DEPLACE.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Argument 2 point : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add(new Point(1, 1));
        obj.add(new Point(1, 1));
        try {
            CommandesTUI.DEPLACE.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Argument 1 String : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.DEPLACE.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour affiche.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void afficheGetCommandTests() throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        Commande c = CommandesTUI.AFFICHE.getCommande(null, true, obj.toArray(),
                etat, null);
        assertTrue(c instanceof CommandeAfficheDessin);
        try {
            CommandesTUI.AFFICHE.getCommande(null, false, obj.toArray(), etat,
                    null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.AFFICHE.getCommande("variable", true, obj.toArray(),
                    etat, null);
            fail("Variable : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.AFFICHE.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour afficheTout.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void afficheToutGetCommandTests()
            throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        Commande c = CommandesTUI.AFFICHE_TOUT.getCommande(null, true,
                obj.toArray(), etat, null);
        assertTrue(c instanceof CommandeAfficheTout);
        try {
            CommandesTUI.AFFICHE_TOUT.getCommande(null, false, obj.toArray(),
                    etat, null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.AFFICHE_TOUT.getCommande("variable", true,
                    obj.toArray(), etat, null);
            fail("Variable : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.AFFICHE_TOUT.getCommande(null, true, obj.toArray(),
                    etat, null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour ajoute.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void ajouteGetCommandTests() throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        obj.add("String");
        obj.add("String");
        Commande c = CommandesTUI.AJOUTE.getCommande(null, true, obj.toArray(),
                etat, null);
        assertTrue(c instanceof CommandeAjouteDessin);
        try {
            CommandesTUI.AJOUTE.getCommande(null, false, obj.toArray(), etat,
                    null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.AJOUTE.getCommande("variable", true, obj.toArray(),
                    etat, null);
            fail("Variable : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add("String");
        obj.add(new Point(1, 1));
        try {
            CommandesTUI.AJOUTE.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Argument 2 String : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add(10);
        obj.add("String");
        try {
            CommandesTUI.AJOUTE.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Argument 1 String : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.AJOUTE.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour retire.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void retireGetCommandTests() throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        obj.add("String");
        obj.add("String");
        Commande c = CommandesTUI.RETIRE.getCommande(null, true, obj.toArray(),
                etat, null);
        assertTrue(c instanceof CommandeRetireDessin);
        try {
            CommandesTUI.RETIRE.getCommande(null, false, obj.toArray(), etat,
                    null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.RETIRE.getCommande("variable", true, obj.toArray(),
                    etat, null);
            fail("Variable : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add("String");
        obj.add(new Point(1, 1));
        try {
            CommandesTUI.RETIRE.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Argument 2 String : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add(10);
        obj.add("String");
        try {
            CommandesTUI.RETIRE.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Argument 1 String : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.RETIRE.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour supprime.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void supprimeGetCommandTests() throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        obj.add("String");
        Commande c = CommandesTUI.SUPPRIME.getCommande(null, true,
                obj.toArray(), etat, null);
        assertTrue(c instanceof CommandeSupprime);
        try {
            CommandesTUI.SUPPRIME.getCommande(null, false, obj.toArray(), etat,
                    null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.SUPPRIME.getCommande("variable", true, obj.toArray(),
                    etat, null);
            fail("Variable : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add(10);
        try {
            CommandesTUI.SUPPRIME.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Argument 1 String : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.SUPPRIME.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour voir.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void voirGetCommandTests() throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        obj.add("String");
        Commande c = CommandesTUI.VOIR.getCommande(null, true, obj.toArray(),
                etat, null);
        assertTrue(c instanceof CommandeVoirDessin);
        try {
            CommandesTUI.VOIR.getCommande(null, false, obj.toArray(), etat,
                    null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.VOIR.getCommande("variable", true, obj.toArray(), etat,
                    null);
            fail("Variable : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add(10);
        try {
            CommandesTUI.VOIR.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Argument 1 String : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.VOIR.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour sortir.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void sortirGetCommandTests() throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        Commande c = CommandesTUI.SORTIR.getCommande(null, true, obj.toArray(),
                etat, null);
        assertTrue(c instanceof CommandeSortirDessin);
        try {
            CommandesTUI.SORTIR.getCommande(null, false, obj.toArray(), etat,
                    null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.SORTIR.getCommande("variable", true, obj.toArray(),
                    etat, null);
            fail("Variable : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.SORTIR.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour sauvegarde.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void sauvegardeGetCommandTests()
            throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        Commande c = CommandesTUI.SAUVEGARDE.getCommande(null, true,
                obj.toArray(), etat, null);
        assertTrue(c instanceof CommandeSauvegarde);
        try {
            CommandesTUI.SAUVEGARDE.getCommande(null, false, obj.toArray(),
                    etat, null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.SAUVEGARDE.getCommande("variable", true, obj.toArray(),
                    etat, null);
            fail("Variable : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.SAUVEGARDE.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour charge.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void chargeGetCommandTests() throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        obj.add("String");
        Commande c = CommandesTUI.CHARGE.getCommande(null, true, obj.toArray(),
                etat, null);
        assertTrue(c instanceof CommandeCharger);
        try {
            CommandesTUI.CHARGE.getCommande(null, false, obj.toArray(), etat,
                    null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.CHARGE.getCommande("variable", true, obj.toArray(),
                    etat, null);
            fail("Variable : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add(10);
        try {
            CommandesTUI.CHARGE.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Argument 1 String : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.CHARGE.getCommande(null, true, obj.toArray(), etat,
                    null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Testes les possibilités pour getCommande pour supprime sauvegarde.
     * @throws MauvaiseUtilisationException En cas de mauvais utilisation de la
     *         commande
     */
    @Test
    public void supprimeSauvegardeGetCommandTests()
            throws MauvaiseUtilisationException {
        ArrayList<Object> obj = new ArrayList<>();
        obj.add("String");
        Commande c = CommandesTUI.SUPPR_SAUVEGARDE.getCommande(null, true,
                obj.toArray(), etat, null);
        assertTrue(c instanceof CommandeSupprimeSauvegarde);
        try {
            CommandesTUI.SUPPR_SAUVEGARDE.getCommande(null, false,
                    obj.toArray(), etat, null);
            fail("Parenthèse false : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        try {
            CommandesTUI.SUPPR_SAUVEGARDE.getCommande("variable", true,
                    obj.toArray(), etat, null);
            fail("Variable : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj = new ArrayList<>();
        obj.add(10);
        try {
            CommandesTUI.SUPPR_SAUVEGARDE.getCommande(null, true, obj.toArray(),
                    etat, null);
            fail("Argument 1 String : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
        obj.add("QQCH");
        try {
            CommandesTUI.SUPPR_SAUVEGARDE.getCommande(null, true, obj.toArray(),
                    etat, null);
            fail("Nb Arguments : "
                    + "Expected MauvaiseUtilisationException to be thrown.");
        } catch (MauvaiseUtilisationException e) {
        }
    }

    /**
     * Teste le getCommandesTUI ave undo.
     * @throws NoCommandException Si la commande n'est pas trouvée.
     */
    @Test
    public void getCommandesTUITest() throws NoCommandException {
        CommandesTUI c = CommandesTUI.getCommandesTUI("annule");
        assertTrue(c == CommandesTUI.UNDO);
    }

    /**
     * Teste le getCommandesTUI avec non exitant.
     * @throws NoCommandException Si la commande n'est pas trouvée.
     */
    @Test(expected = NoCommandException.class)
    public void getCommandesTUIExceptionTest() throws NoCommandException {
        CommandesTUI.getCommandesTUI("QQCH");
    }
}
