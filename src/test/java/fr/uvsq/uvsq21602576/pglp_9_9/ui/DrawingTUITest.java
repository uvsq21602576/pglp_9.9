package fr.uvsq.uvsq21602576.pglp_9_9.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;

/**
 * Classe de Test pour Drawing TUI.
 * @author Flora
 */
public class DrawingTUITest {

    /**
     * Teste le parser des arguments entre parenthèse.
     * Pour une chaine nulle.
     */
    @Test
    public void entreParentheseParserNullTest() {
        DrawingTUI d = new DrawingTUI(null, null);
        Object[] actual = d.entreParenthesesParser("");
        assertEquals(0, actual.length);
    }

    /**
     * Teste le parser des arguments entre parenthèse.
     * Pour une chaine avec deux entiers.
     */
    @Test
    public void entreParentheseParserIntTest() {
        DrawingTUI d = new DrawingTUI(null, null);
        Object[] actual = d.entreParenthesesParser("-100, 42  ");
        assertEquals(2, actual.length);
        assertEquals(-100, actual[0]);
        assertEquals(42, actual[1]);
    }

    /**
     * Teste le parser des arguments entre parenthèse.
     * Pour une chaine avec deux points.
     */
    @Test
    public void entreParentheseParserPointTest() {
        DrawingTUI d = new DrawingTUI(null, null);
        Object[] actual = d.entreParenthesesParser("  ( 0,9), (-19, 045 )");
        assertEquals(2, actual.length);
        assertEquals(new Point(0, 9), actual[0]);
        assertEquals(new Point(-19, 45), actual[1]);
    }

    /**
     * Teste le parser des arguments entre parenthèse.
     * Pour une chaine avec deux mots.
     */
    @Test
    public void entreParentheseParserMotTest() {
        DrawingTUI d = new DrawingTUI(null, null);
        Object[] actual = d.entreParenthesesParser("cercle1, deSSin25_DE  ");
        assertEquals(2, actual.length);
        assertEquals("cercle1", actual[0]);
        assertEquals("deSSin25_DE", actual[1]);
    }
}
