package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.NoFilsException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.ComposantDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Commande.
 * Pour faire une copie d'un composant.
 * @author Flora
 */
public class CommandeCopie implements CommandeUndoable {
    /** Etat actuel du logiciel. */
    private Etat etat;
    /** Nom du composant à copier. */
    private String aCopie;
    /** Nom du composant copié. */
    private String copie;
    /** Composant créé après copie. */
    private ComposantDessin composantCopie;

    /**
     * Constructeur.
     * Crée la commande, avec le moteur et le nom du composant à copier.
     * @param e Etat du logiciel
     * @param c Nom de la copie
     * @param aC Nom du composant à copier
     */
    public CommandeCopie(final Etat e, final String c, final String aC) {
        this.etat = e;
        this.copie = c;
        this.aCopie = aC;
        this.composantCopie = null;
    }

    /**
     * Execute la commande.
     * Copie un composant du dessin et l'ajoute dans celui-ci.
     * @throws CommandeImpossibleException Si un element du meme nom que la
     *         copie existe déjà, où si le composant que l'on veut copier
     *         n'existe pas.
     */
    @Override
    public void execute() throws CommandeImpossibleException {
        try {
            this.composantCopie = etat.getDessinCourant().copier(aCopie, copie);
        } catch (DejaExistantException | NoFilsException e) {
            throw new CommandeImpossibleException(e.getMessage());
        }
        this.etat.ajouteDansHistorique(this);
    }

    /**
     * Annule la commande.
     * Retire le cercle précédemment créé du dessin.
     */
    @Override
    public void undo() throws UndoImpossibleException {
        this.etat.getDessinCourant().retire(composantCopie);
    }

}
