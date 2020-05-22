package fr.uvsq.uvsq21602576.pglp_9_9.formes;

/**
 * Composante d'un dessin.
 * @author Flora
 */
public interface ComposantDessin extends Deplacable {

    /**
     * Renvoie le nom du composant.
     * @return Nom du composant
     */
    String getNom();

    /**
     * Change le nom du composant pour celui en paramètre.
     * @param nom Nouveau du composant
     */
    void setNom(String nom);

    /**
     * Renvoie la copie exacte du composant.
     * @return Copie exacte du composant
     */
    ComposantDessin copie();

    /**
     * Renvoie une représentation textuelle.
     * @param profondeur Profondeur dans l'arbre de composant
     * @return Représentation textuelle
     */
    String toString(int profondeur);
}
