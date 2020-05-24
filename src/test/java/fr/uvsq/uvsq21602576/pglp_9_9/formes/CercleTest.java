package fr.uvsq.uvsq21602576.pglp_9_9.formes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Classe de tests pour Cercle.
 * @author Flora
 */
public class CercleTest {

    /**
     * Teste la copie.
     */
    @Test
    public void copieTest() {
        Cercle c = new Cercle("c1", new Point(3, 4), 10);
        Cercle copie = (Cercle) c.copie();
        assertFalse(c == copie);
        assertEquals(c, copie);
    }

    /**
     * Teste le déplacement.
     */
    @Test
    public void deplaceTest() {
        Cercle c = new Cercle("c1", new Point(3, 1), 10);
        c.deplace(new Point(1, -1));
        Cercle expected = new Cercle("c1", new Point(4, 0), 10);
        assertEquals(expected, c);
    }

    /**
     * Teste l'égalité correcte.
     */
    @Test
    public void equalsTest() {
        Cercle c = new Cercle("c1", new Point(3, 1), 10);
        Cercle d = new Cercle("c1", new Point(3, 1), 10);
        assertEquals(c, d);
    }

    /**
     * Teste l'égalité incorrecte, par le centre.
     */
    @Test
    public void equalsCentreNotTest() {
        Cercle c = new Cercle("c1", new Point(3, 1), 10);
        Cercle d = new Cercle("c1", new Point(4, 1), 10);
        assertNotEquals(c, d);
    }

    /**
     * Teste l'égalité incorrecte par le rayon.
     */
    @Test
    public void equalsRayonNotTest() {
        Cercle c = new Cercle("c1", new Point(3, 1), 13);
        Cercle d = new Cercle("c1", new Point(3, 1), 10);
        assertNotEquals(c, d);
    }

    /**
     * Teste l'égalité incorrecte avec le nom.
     */
    @Test
    public void equalsNomNotTest() {
        Cercle c = new Cercle("c2", new Point(3, 1), 10);
        Cercle d = new Cercle("c1", new Point(3, 1), 10);
        assertNotEquals(c, d);
    }
}
