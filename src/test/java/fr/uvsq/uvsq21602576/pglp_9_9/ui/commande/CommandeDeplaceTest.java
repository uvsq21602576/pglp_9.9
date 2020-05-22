package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Cercle;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.ComposantDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Tests pour la commande deplace.
 * @author Flora
 */
public class CommandeDeplaceTest {

    /**
     * Teste l'execution, sans problème.
     * @throws DejaExistantException Si une forme du même nom que celle ajoutée
     *         existe déjà.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     */
    @Test
    public void executeTest() throws DejaExistantException,
            CommandeImpossibleException, UndoImpossibleException {
        Etat etat = new Etat();
        etat.getDessinCourant().ajoute(new Cercle("c", new Point(1, 1), 10));
        CommandeUndoable c = new CommandeDeplace(etat, "c", new Point(2, -2));
        c.execute();
        ArrayList<ComposantDessin> expected = new ArrayList<>();
        expected.add(new Cercle("c", new Point(3, -1), 10));
        assertEquals(expected,
                new ArrayList<>(etat.getDessinCourant().getComposantsFils()));
    }

    /**
     * Teste l'execution, sans forme présente à déplacer.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     */
    @Test(expected = CommandeImpossibleException.class)
    public void executeExceptionTest()
            throws CommandeImpossibleException, UndoImpossibleException {
        Etat etat = new Etat();
        CommandeUndoable c = new CommandeDeplace(etat, "c", new Point(2, -2));
        c.execute();
    }

    /**
     * Teste l'annulation.
     * @throws DejaExistantException Si une forme du même nom que celle ajoutée
     *         existe déjà.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     */
    @Test
    public void undoTest() throws DejaExistantException,
            CommandeImpossibleException, UndoImpossibleException {
        Etat etat = new Etat();
        etat.getDessinCourant().ajoute(new Cercle("c", new Point(1, 1), 10));
        CommandeUndoable c = new CommandeDeplace(etat, "c", new Point(2, -2));
        c.execute();
        c.undo();
        ArrayList<ComposantDessin> expected = new ArrayList<>();
        expected.add(new Cercle("c", new Point(1, 1), 10));
        assertEquals(expected,
                new ArrayList<>(etat.getDessinCourant().getComposantsFils()));
    }
}
