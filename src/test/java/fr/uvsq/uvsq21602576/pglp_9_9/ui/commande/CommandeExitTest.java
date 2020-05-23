package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.Arret;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.DrawingTUI;

/**
 * Teste la commande Exit.
 * @author Flora
 */
public class CommandeExitTest {

    /**
     * Teste son execution.
     */
    @Test
    public void executeTest() {
        Arret arret = new Arret();
        DrawingTUI ui = new DrawingTUI(null, arret);
        CommandeExit exit = new CommandeExit(ui);
        exit.execute();
        assertTrue(arret.isArret());
    }
}
