package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.ui.Historique;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Teste la commande undo.
 * @author Flora
 */
public class CommandeUndoTest {

    /**
     * Teste l'execution.
     * Quand tout marche bien.
     * @throws UndoImpossibleException En cas d'undo impossible.
     */
    @Test
    public void executeTest() throws UndoImpossibleException {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Historique h = new Historique();
        CommandeUndoable c = new CommandeUndoable() {
            @Override
            public void execute() throws CommandeImpossibleException,
                    UndoImpossibleException {
            }

            @Override
            public void undo() throws UndoImpossibleException {
            }
        };
        h.ajoute(c);

        CommandeUndo undo = new CommandeUndo(h);
        undo.execute();

        String observed = outContent.toString().trim();
        System.setOut(System.out);

        assertTrue(h.getHistorique().isEmpty());
        assertEquals("undo effectué", observed);
    }

    /**
     * Teste l'execution.
     * En cas d'historique vide.
     */
    @Test
    public void executeExceptionTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Historique h = new Historique();

        CommandeUndo undo = new CommandeUndo(h);
        try {
            undo.execute();
            fail("Expected UndoImpossibleException to be thrown");
        } catch (UndoImpossibleException e) {
            String observed = outContent.toString().trim();
            assertTrue(observed.isEmpty());
        } finally {
            System.setOut(System.out);
            System.setErr(System.err);
        }
    }

}
