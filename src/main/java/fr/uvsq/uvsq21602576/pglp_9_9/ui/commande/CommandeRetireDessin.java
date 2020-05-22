package fr.uvsq.uvsq21602576.pglp_9_9.ui.commande;

import fr.uvsq.uvsq21602576.pglp_9_9.Etat;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.DejaExistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.exceptions.NoFilsException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.ComposantDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Dessin;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.CommandeImpossibleException;
import fr.uvsq.uvsq21602576.pglp_9_9.ui.commande.exceptions.UndoImpossibleException;

/**
 * Commande pour retirer un composant d'un sous-dessin.
 * @author Flora
 */
public class CommandeRetireDessin implements CommandeUndoable {
    /** Etat actuel du logiciel. */
    private Etat etat;
    /** Nom du composant à retirer. */
    private String nomComposant;
    /** Nom du dessin où retirer. */
    private String nomDessin;

    /**
     * Constructeur.
     * Crée la commande, avec le moteur, le nom du composant à retirer et le nom
     * du dessin.
     * @param e Etat du logiciel
     * @param nom Nom du composant à retirer
     * @param dessin Nom du dessin où retirer
     */
    public CommandeRetireDessin(final Etat e, final String nom,
            final String dessin) {
        this.etat = e;
        this.nomComposant = nom;
        this.nomDessin = dessin;
    }

    /**
     * Execute la commande.
     * Deplace le composant du sous dessin indiqué au dessin courant.
     * @throws CommandeImpossibleException Si le dessin ou le composant n'existe
     *         pas, ou si un composant du même nom se trouve déjà dans dessin.
     */
    @Override
    public void execute() throws CommandeImpossibleException {
        ComposantDessin d;
        try {
            d = this.etat.getDessinCourant().getComposant(nomDessin);
        } catch (NoFilsException e) {
            throw new CommandeImpossibleException(
                    "Aucun dessin " + nomDessin + ".");
        }
        if (!(d instanceof Dessin)) {
            throw new CommandeImpossibleException(
                    "Le composant " + nomDessin + " n'est pas un dessin.");
        }
        ComposantDessin c;
        try {
            c = ((Dessin) d).getComposant(nomComposant);
        } catch (NoFilsException e1) {
            throw new CommandeImpossibleException("Aucun composant "
                    + nomComposant + " dans le dessin " + nomDessin + ".");
        }
        try {
            this.etat.getDessinCourant().ajoute(c);
        } catch (DejaExistantException e) {
            throw new CommandeImpossibleException("Un composant " + nomComposant
                    + " existe déjà dans le dessin.");
        }
        ((Dessin) d).retire(c);
        this.etat.ajouteDansHistorique(this);
    }

    /**
     * Annule la commande.
     * Redeplace le composant du sous dessin vers le courant
     */
    @Override
    public void undo() throws UndoImpossibleException {
        ComposantDessin d;
        try {
            d = this.etat.getDessinCourant().getComposant(nomDessin);
        } catch (NoFilsException e) {
            throw new UndoImpossibleException(
                    "Aucun dessin " + nomDessin + ".");
        }
        if (!(d instanceof Dessin)) {
            throw new UndoImpossibleException(
                    "Le composant " + nomDessin + " n'est pas un dessin.");
        }
        ComposantDessin c;
        try {
            c = this.etat.getDessinCourant().getComposant(nomComposant);
        } catch (NoFilsException e1) {
            throw new UndoImpossibleException(
                    "Aucun composant " + nomComposant + ".");
        }
        try {
            ((Dessin) d).ajoute(c);
        } catch (DejaExistantException e) {
            throw new UndoImpossibleException("Un composant " + nomComposant
                    + " existe déjà dans le dessin " + nomDessin + ".");
        }
        this.etat.getDessinCourant().retire(c);
    }

}
