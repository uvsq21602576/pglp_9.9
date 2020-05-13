package fr.uvsq.uvsq21602576.pglp_9_9.formes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Classe de Test pour Rectangle.
 * @author Flora
 */
public class RectangleTest {

    /**
     * Teste le déplacement.
     */
    @Test
    public void deplaceTest() {
        Rectangle r = new Rectangle("r1", new Point(0, 0), new Point(3, 4));
        r.deplace(new Point(1, -1));
        Rectangle expected =
                new Rectangle("r1", new Point(1, -1), new Point(4, 3));
        assertEquals(expected, r);
    }
}
