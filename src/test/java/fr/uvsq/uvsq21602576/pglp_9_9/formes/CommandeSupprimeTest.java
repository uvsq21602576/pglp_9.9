package fr.uvsq.uvsq21602576.pglp_9_9.formes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.Commande;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeSupprime;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeUndoable;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Classe de test pour la commande supprimer.
 * @author Flor
 */
public class CommandeSupprimeTest {

    /**
     * Teste l'execution, sans problème.
     * @throws DejaExistantException Si le composant ajouté existe déjà.
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
        Commande c = new CommandeSupprime(etat, "c");
        c.execute();
        assertTrue(etat.getDessinCourant().getComposantsFils().isEmpty());
    }

    /**
     * Teste l'execution, sans objet à supprimer.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     */
    @Test(expected = CommandeImpossibleException.class)
    public void executeExceptionTest()
            throws CommandeImpossibleException, UndoImpossibleException {
        Etat etat = new Etat();
        Commande c = new CommandeSupprime(etat, "c");
        c.execute();
    }

    /**
     * Teste l'annulation.
     * @throws DejaExistantException Si le composant ajouté existe déjà.
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
        CommandeUndoable c = new CommandeSupprime(etat, "c");
        c.execute();
        c.undo();
        ArrayList<ComposantDessin> expected = new ArrayList<>();
        expected.add(new Cercle("c", new Point(1, 1), 10));
        assertEquals(expected,
                new ArrayList<>(etat.getDessinCourant().getComposantsFils()));
    }
}
