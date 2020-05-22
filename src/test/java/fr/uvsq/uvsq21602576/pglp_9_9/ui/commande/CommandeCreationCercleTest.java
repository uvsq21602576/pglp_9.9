package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.formes.Cercle;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.ComposantDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Teste la commande de creation de cercle.
 * @author Flora
 */
public class CommandeCreationCercleTest {

    /**
     * Teste l'execution, sans erreur.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     */
    @Test
    public void executeTest()
            throws CommandeImpossibleException, UndoImpossibleException {
        Etat etat = new Etat();
        Commande c = new CommandeCreationCercle(etat, "C", new Point(1, 2), 10);
        c.execute();
        ArrayList<ComposantDessin> expected = new ArrayList<>();
        expected.add(new Cercle("C", new Point(1, 2), 10));
        assertEquals(expected,
                new ArrayList<>(etat.getDessinCourant().getComposantsFils()));
    }

    /**
     * Teste l'execution, avec un entier au lieu d'un point dans les arguments.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     */
    @Test(expected = CommandeImpossibleException.class)
    public void executeMauvaisArgumentTest()
            throws CommandeImpossibleException, UndoImpossibleException {
        Etat etat = new Etat();
        Commande c = new CommandeCreationCercle(etat, "C", 10, 10);
        c.execute();
    }

    /**
     * Teste l'annulation de la commande.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     */
    @Test
    public void undoTest()
            throws CommandeImpossibleException, UndoImpossibleException {
        Etat etat = new Etat();
        CommandeUndoable c =
                new CommandeCreationCercle(etat, "C", new Point(1, 2), 10);
        c.execute();
        c.undo();
        assertTrue(etat.getDessinCourant().getComposantsFils().isEmpty());
    }
}
