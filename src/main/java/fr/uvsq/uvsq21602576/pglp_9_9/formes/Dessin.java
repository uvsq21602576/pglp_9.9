package fr.uvsq.uvsq21602576.pglp_9_9.formes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Représente un Dessin.
 * Un dessin est un groupe de forme.
 * @author Flora
 */
public class Dessin implements ComposantDessin {
    /** Nom du dessin. */
    private String nom;
    /** Liste de composant de dessin. **/
    private ArrayList<ComposantDessin> composantsFils;

    /**
     * Constructeur.
     * Crée un dessin vide avec un nom.
     * @param n Nom du dessin
     */
    public Dessin(final String n) {
        this.nom = n;
        this.composantsFils = new ArrayList<>();
    }

    /**
     * Ajoute un composant au dessin.
     * @param c Composant à ajouter
     */
    public void ajoute(final ComposantDessin c) {
        composantsFils.add(c);
    }

    /**
     * Retire un composant du dessin.
     * @param c Composant à retirer
     */
    public void retire(final ComposantDessin c) {
        this.composantsFils.remove(c);
    }

    /**
     * Deplace tous les composantFils.
     * @param dP vecteur de déplacement
     */
    @Override
    public void deplace(final Point dP) {
        for (ComposantDessin c : composantsFils) {
            c.deplace(dP);
        }
    }

    /**
     * Retourne le haché correspondant.
     * @return Haché
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((composantsFils == null) ? 0 : composantsFils.hashCode());
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        return result;
    }

    /**
     * Teste si les deux Dessins sont égaux.
     * Deux Dessins sont égaux s'ils possèdent les mêmes composants fils et le
     * même nom.
     * @param obj Dessin à comparer
     * @return true si égaux, false sinon
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Dessin)) {
            return false;
        }
        Dessin other = (Dessin) obj;
        if (composantsFils == null) {
            if (other.composantsFils != null) {
                return false;
            }
        } else if (!composantsFils.equals(other.composantsFils)) {
            return false;
        }
        if (nom == null) {
            if (other.nom != null) {
                return false;
            }
        } else if (!nom.equals(other.nom)) {
            return false;
        }
        return true;
    }

    /**
     * Retourne la liste de Composant, non modifiable.
     * @return liste non modifibale de composants
     */
    List<ComposantDessin> getComposantsFils() {
        return Collections.unmodifiableList(this.composantsFils);
    }

    /**
     * Retourne une représentation textuelle du dessin.
     * C'est-à-dire son nom et son contenu.
     * @return Représentation textuelle du dessin
     */
    public String toString() {
        return this.toString(0);
    }

    /**
     * Retourne une représentation textuelle du dessin.
     * Avec des tabulations pour la profondeur.
     * C'est-à-dire son nom et son contenu.
     * @param profondeur Profondeur dans l'arbre de Composant
     * @return Représentation textuelle du dessin
     */
    @Override
    public String toString(final int profondeur) {
        String s = "";
        for (int i = 0; i < profondeur; i++) {
            s = s.concat("    ");
        }
        s = s.concat("|- " + this.nom + " :\n");
        for (ComposantDessin c : composantsFils) {
            s = s.concat(c.toString(profondeur + 1));
            s = s.concat("\n");
        }
        return s;
    }
}
