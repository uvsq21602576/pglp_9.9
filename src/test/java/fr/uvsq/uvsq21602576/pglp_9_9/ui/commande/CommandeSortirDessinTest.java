package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DeplacementImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DessinGlobalException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Teste la commande de sortie de dessin.
 * @author Flora
 */
public class CommandeSortirDessinTest {

    /**
     * Teste l'execution, sans erreur.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     * @throws DessinGlobalException Si un dessin est nommé comme le global
     * @throws DejaExistantException Si un dessin du meme nom existe deja
     * @throws DeplacementImpossibleException Si un sous dessin est inexistant
     */
    @Test
    public void executeTest() throws CommandeImpossibleException,
            UndoImpossibleException, DessinGlobalException,
            DejaExistantException, DeplacementImpossibleException {
        Etat etat = new Etat();
        Dessin d = new Dessin("D");
        etat.getDessinCourant().ajoute(d);
        etat.voirSousDessin("D");
        Commande c = new CommandeSortirDessin(etat);
        c.execute();
        assertEquals(Dessin.GLOBAL, etat.getDessinCourant().getNom());
        assertTrue(etat.getParenteDessin().isEmpty());
    }

    /**
     * Teste l'annulation de la commande.
     * @throws CommandeImpossibleException En cas de problème lors de
     *         l'execution de la commande
     * @throws UndoImpossibleException En cas de problème lors de l'annulation
     *         de la commande
     * @throws DessinGlobalException Si un dessin est nommé comme le global
     * @throws DejaExistantException Si un dessin du meme nom existe deja
     * @throws DeplacementImpossibleException Si un sous dessin est inexistant
     */
    @Test
    public void undoTest() throws CommandeImpossibleException,
            UndoImpossibleException, DessinGlobalException,
            DeplacementImpossibleException, DejaExistantException {
        Etat etat = new Etat();
        Dessin d = new Dessin("D");
        etat.getDessinCourant().ajoute(d);
        etat.voirSousDessin("D");
        CommandeUndoable c = new CommandeSortirDessin(etat);
        c.execute();
        c.undo();
        assertEquals(d, etat.getDessinCourant());
        assertTrue(etat.getParenteDessin().isEmpty());
    }
}
