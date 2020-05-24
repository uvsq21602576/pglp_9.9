package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Cercle;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.ComposantDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Teste la commande copie.
 * @author Flora
 */
public class CommandeCopieTest {

    /**
     * Teste l'execution.
     * @throws DejaExistantException Si un même objet existait déjà dans le
     *         dessin.
     * @throws CommandeImpossibleException En cas d'erreur lors de l'execution
     *         de la commande.
     */
    @Test
    public void executeTest()
            throws DejaExistantException, CommandeImpossibleException {
        Etat etat = new Etat();
        Cercle c = new Cercle("c1", new Point(1, 2), 30);
        etat.getDessinCourant().ajoute(c);
        CommandeUndoable com = new CommandeCopie(etat, "c1bis", "c1");
        com.execute();
        ArrayList<ComposantDessin> expected = new ArrayList<>();
        expected.add(new Cercle("c1bis", new Point(1, 2), 30));
        expected.add(c);
        assertEquals(expected,
                new ArrayList<>(etat.getDessinCourant().getComposantsFils()));
    }

    /**
     * Teste l'annulation.
     * @throws DejaExistantException Si un même objet existait déjà dans le
     *         dessin.
     * @throws CommandeImpossibleException En cas d'erreur lors de l'execution
     *         de la commande.
     * @throws UndoImpossibleException En cas d'erreur lors de l'annulation.
     */
    @Test
    public void undoTest() throws DejaExistantException,
            CommandeImpossibleException, UndoImpossibleException {
        Etat etat = new Etat();
        Cercle c = new Cercle("c1", new Point(1, 2), 30);
        etat.getDessinCourant().ajoute(c);
        CommandeUndoable com = new CommandeCopie(etat, "c1bis", "c1");
        com.execute();
        com.undo();
        ArrayList<ComposantDessin> expected = new ArrayList<>();
        expected.add(c);
        assertEquals(expected,
                new ArrayList<>(etat.getDessinCourant().getComposantsFils()));
    }

}
