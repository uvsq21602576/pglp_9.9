package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.ui.Arret;

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
        CommandeExit exit = new CommandeExit(arret);
        exit.execute();
        assertTrue(arret.isArret());
    }
}
