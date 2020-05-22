package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.ComposantDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Commande pour supprimer un composant du dessin courant.
 * @author Flora
 */
public class CommandeSupprime implements CommandeUndoable {
    /** Etat actuel du logiciel. */
    private Etat etat;
    /** Nom du omposant à supprimer. */
    private String nomComposant;
    /** Composant supprimé. */
    private ComposantDessin composant;

    /**
     * Constructeur.
     * Crée la commande, avec le moteur, et le nom du composant à supprimer.
     * @param e Etat du logiciel
     * @param nom Nom du composant à suprimmer
     */
    public CommandeSupprime(final Etat e, final String nom) {
        this.etat = e;
        this.nomComposant = nom;
        this.composant = null;
    }

    /**
     * Execute la commande.
     * Supprimer le composant du dessin courant.
     */
    @Override
    public void execute() throws CommandeImpossibleException {
        this.composant = this.etat.getDessinCourant().retire(nomComposant);
        if (this.composant == null) {
            throw new CommandeImpossibleException(
                    "Aucun composant " + nomComposant + "à supprimer.");
        }
        this.etat.ajouteDansHistorique(this);
    }

    /**
     * Annule la commande.
     * Rajoute le composant dans le dessin courant.
     */
    @Override
    public void undo() throws UndoImpossibleException {
        try {
            this.etat.getDessinCourant().ajoute(composant);
        } catch (DejaExistantException e) {
            throw new UndoImpossibleException(e.getMessage());
        }
    }

}
