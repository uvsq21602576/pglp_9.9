package fr.uvsq.uvsq21602576.pglp_9_9;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.CommandeUndoable;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Teste pour Historique.
 * @author Flora
 */
public class HistoriqueTest {

    /**
     * Teste la méthode add.
     */
    @Test
    public void addTest() {
        Historique h = new Historique();
        CommandeUndoable c = new CommandeUndoable() {
            @Override
            public void execute() throws CommandeImpossibleException {
            }

            @Override
            public void undo() throws UndoImpossibleException {
            }
        };
        h.ajoute(c);
        ArrayList<CommandeUndoable> expected = new ArrayList<>();
        expected.add(c);
        assertEquals(expected, h.getHistorique());
    }

    /**
     * Teste le retour Arriere.
     * Dans le cas où tout marche bien.
     * @throws UndoImpossibleException Si l'annulation échouerait.
     */
    @Test
    public void retourArriereTest() throws UndoImpossibleException {
        Historique h = new Historique();
        CommandeUndoable c = new CommandeUndoable() {
            @Override
            public void execute() throws CommandeImpossibleException {
            }

            @Override
            public void undo() throws UndoImpossibleException {
            }
        };
        h.ajoute(c);
        h.retourArriere();
        assertTrue(h.getHistorique().isEmpty());
    }

    /**
     * Teste le retour Arriere.
     * En cas d'historique vide.
     * @throws UndoImpossibleException Si l'annulation échoue.
     */
    @Test(expected = UndoImpossibleException.class)
    public void retourArriereExceptionVideTest()
            throws UndoImpossibleException {
        Historique h = new Historique();
        h.retourArriere();
    }

    /**
     * Teste le retour Arriere.
     * En cas d'annulation impossible.
     */
    @Test
    public void retourArriereExceptionImpossibleTest() {
        Historique h = new Historique();
        CommandeUndoable c = new CommandeUndoable() {
            @Override
            public void execute() throws CommandeImpossibleException {
            }

            @Override
            public void undo() throws UndoImpossibleException {
                throw new UndoImpossibleException("test");
            }
        };
        h.ajoute(c);

        try {
            h.retourArriere();
            fail("Expected UndoImpossibleException to be thrown");
        } catch (UndoImpossibleException e) {
            ArrayList<CommandeUndoable> expected = new ArrayList<>();
            expected.add(c);
            assertEquals(expected, h.getHistorique());
        }
    }
}
