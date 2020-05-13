package fr.uvsq.uvsq21602576.pglp_9_9.formes;

/**
 * Composante d'un dessin.
 * @author Flora
 */
public interface ComposantDessin extends Deplacable {

    /**
     * Renvoie une représentation textuelle.
     * @param profondeur Profondeur dans l'arbre de composant
     * @return Représentation textuelle
     */
    String toString(int profondeur);
}
