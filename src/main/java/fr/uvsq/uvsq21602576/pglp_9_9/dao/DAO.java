package fr.uvsq.uvsq21602576.pglp_9_9.dao;

import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.CreationObjetException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.DeletionException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.DoublonException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.InexistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.MisAJourException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.RechercheException;

/**
 * Classe abstraite du DAO.
 * @author Flora
 * @param <T> Classe des objets manipulés
 */
public abstract class DAO<T> {

    /**
     * Pour la création.
     * @param obj objet à créer
     * @return object créé
     * @throws CreationObjetException En cas d'erreur lors de la creation
     * @throws DoublonException En cas de duplication de clé
     */
    public abstract T create(T obj)
            throws CreationObjetException, DoublonException;

    /**
     * Pour la recherche.
     * @param id Identifiant de l'objet à trouvée
     * @return object trouvé
     * @throws InexistantException En cas d'objet inexistant
     * @throws RechercheException En cas d'erreur lors de l'exception
     */
    public abstract T find(String id)
            throws InexistantException, RechercheException;

    /**
     * Pour la modification.
     * @param obj Objet modifié à réécrire
     * @return object modifié
     * @throws InexistantException En cas d'objet inexistant
     * @throws MisAJourException En cas d'erreurs lors de la mise à jour
     */
    public abstract T update(T obj)
            throws InexistantException, MisAJourException;

    /**
     * Pour la suppression.
     * @param id Nom de l'objet à supprimer
     * @throws DeletionException En cas d'erreur lors de la suppression
     */
    public abstract void delete(String id) throws DeletionException;

}
