package fr.uvsq.uvsq21602576.pglp_9_9;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Teste la classe Arret.
 * @author Flora
 */
public class ArretTest {

    /**
     * Teste sa construction.
     */
    @Test
    public void constructeurTest() {
        Arret arret = new Arret();
        assertFalse(arret.isArret());
    }

    /**
     * Teste la méthode setArret.
     */
    @Test
    public void setArretTest() {
        Arret arret = new Arret();
        arret.setArret();
        assertTrue(arret.isArret());
    }

}
