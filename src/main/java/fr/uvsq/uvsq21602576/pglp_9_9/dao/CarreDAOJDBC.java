package fr.uvsq.uvsq21602576.pglp_9_9.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.derby.shared.common.error.DerbySQLIntegrityConstraintViolationException;

import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.ConnectionException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.CreationObjetException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.CreationTableException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.DeletionException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.DoublonException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.InexistantException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.InsertionException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.JDBCException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.LectureException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.MetaDataException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.MisAJourException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.ModificationException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.RechercheException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.RemplissageStatementException;
import fr.uvsq.uvsq21602576.pglp_9_9.dao.exceptions.SuppressionException;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Carre;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.ComposantDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;

/**
 * DAO JDBC.
 * Pour Carré.
 * @author Flora
 */
public class CarreDAOJDBC extends DAO<Carre> {

    /**
     * URL pour la base de donnée.
     */
    private static final String DB_URL =
            "jdbc:derby:donneesPourDB\\jdbcDB;create=true";

    /**
     * Retourne la connexion à la base de donnée.
     * @return Connexion à la base de donnée
     * @throws ConnectionException En cas d'erreur à la connexion
     */
    private Connection getConnection() throws ConnectionException {
        try {
            return DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage());
        }
    }

    /**
     * Teste si la table name existe.
     * Dans la base de donnée connectée à conn.
     * @param name Nom de la table
     * @param conn Connexion avec la base de donnée.
     * @return true si elle existe, false sinon
     * @throws MetaDataException En cas d'erreur lors de la lecture des méta
     *         données de la base de donnée.
     */
    private static boolean tableExists(final String name, final Connection conn)
            throws MetaDataException {
        DatabaseMetaData dbmd;
        try {
            dbmd = conn.getMetaData();
            try (ResultSet rs =
                    dbmd.getTables(null, null, name.toUpperCase(), null)) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new MetaDataException(e.getMessage());
        }
        return false;
    }

    /**
     * Crée la tables utile pour Carré.
     * C'est à dire : Carré.
     * @param conn Connexion à la base de donnée
     * @throws CreationTableException En cas d'erreur lors de la creation des
     *         tables.
     */
    static void createTable(final Connection conn)
            throws CreationTableException {
        try {
            DessinDAOJDBC.createTable(conn);
            if (!tableExists("carre", conn)) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute("Create table carre ("
                            + "nom varchar(30) not null, "
                            + "hgX int not null, " + "hgY int not null,"
                            + "longueur int not null, "
                            + "dessinID int not null,"
                            + "primary key (nom, dessinID), "
                            + "foreign key (dessinID) "
                            + "references dessin(dessinID))");
                }
            }
        } catch (MetaDataException | SQLException e) {
            throw new CreationTableException("carre", e.getMessage());
        }
    }

    /**
     * Remplit les différentes valeurs du statement préparé.
     * Avec les objects values mis en paramêtre.
     * @param statement PreparedStatement à remplir
     * @param values Valeurs du preparedStatement
     * @throws RemplissageStatementException En cas d'erreur lors du
     *         remplissage.
     */
    public static void remplitStatement(final PreparedStatement statement,
            final Object... values) throws RemplissageStatementException {
        try {
            for (int i = 0; i < values.length; i++) {
                statement.setObject(i + 1, values[i]);
            }
        } catch (SQLException e) {
            throw new RemplissageStatementException(e.getMessage());
        }
    }

    /**
     * Insert l'objet carré dans la base de donnée.
     * Via la connexion conn.
     * @param obj Carré à insérer
     * @param dessinID ID du dessin auquel la forme appartient
     * @param conn Connexion à la base de donnée
     * @throws InsertionException En cas d'erreur lors de l'insertion de données
     * @throws CreationTableException En cas d'erreur lors de la creation des
     *         tables
     * @throws DoublonException En cas de duplication de clé, si l'objet obj
     *         existe déjà
     */
    static void insert(final Carre obj, final int dessinID,
            final Connection conn) throws InsertionException,
            CreationTableException, DoublonException {
        try {
            createTable(conn);
            try (PreparedStatement insertCarre = conn.prepareStatement(
                    "INSERT INTO carre values (?, ?, ?, ?, ?)")) {
                remplitStatement(insertCarre, obj.getNom(),
                        obj.getPointReference().getX(),
                        obj.getPointReference().getY(), obj.getLongueur(),
                        dessinID);
                insertCarre.execute();
            }
        } catch (DerbySQLIntegrityConstraintViolationException e) {
            throw new DoublonException(obj.getNom());
        } catch (SQLException | RemplissageStatementException e) {
            throw new InsertionException(obj.getNom(), e.getMessage());
        }
    }

    /**
     * Créer le carré obj dans la base de donnée.
     * @param obj Carré à créer
     * @return le carré créer
     * @throws CreationObjetException En cas d'erreru à la création de l'objet.
     * @throws DoublonException En cas de duplication de clé, si l'objet obj
     *         existe déjà
     */
    @Override
    public Carre create(final Carre obj)
            throws CreationObjetException, DoublonException {
        Carre result = null;
        try (Connection conn = getConnection()) {
            try {
                conn.setAutoCommit(false);
                insert(obj, -1, conn);
                conn.commit();
                result = obj;
            } catch (SQLException | JDBCException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (DoublonException e) {
            throw e;
        } catch (JDBCException | SQLException e) {
            throw new CreationObjetException(obj.getNom(), e.getMessage());
        }
        return result;
    }

    /**
     * Lit tous les carrés dans la base de donnée.
     * Carré apparetenant au dessin dessinID
     * @param dessinID ID du dessin auquel les formes appartiennent
     * @param conn Connection à la base de donnée
     * @return Liste de Carrés lus
     * @throws LectureException En cas d'erreur lors de la lecture
     */
    static List<ComposantDessin> readAll(final int dessinID,
            final Connection conn) throws LectureException {
        ArrayList<ComposantDessin> listeCarre = new ArrayList<>();
        try {
            if (!tableExists("carre", conn)) {
                return listeCarre;
            }
        } catch (MetaDataException e) {
        }
        try (PreparedStatement selectCarre = conn.prepareStatement(
                "SELECT * FROM carre " + "WHERE dessinID = ?")) {
            remplitStatement(selectCarre, dessinID);
            try (ResultSet rs = selectCarre.executeQuery()) {
                while (rs.next()) {
                    listeCarre.add(new Carre(rs.getString("nom"),
                            new Point(rs.getInt("hgX"), rs.getInt("hgY")),
                            rs.getInt("longueur")));
                }
            }
        } catch (SQLException | RemplissageStatementException e) {
            throw new LectureException("carrés", e.getMessage());
        }
        return listeCarre;
    }

    /**
     * Lit un carré dans la base de donnée.
     * Carré accessible par son nom id et l'id de son dessin.
     * @param id Nom du carré à lire
     * @param dessinID ID du dessin auquel la forme appartient
     * @param conn Connection à la base de donnée
     * @return Carré lu
     * @throws LectureException En cas d'erreur lors de la lecture
     * @throws InexistantException Si le carré id n'existe pas
     */
    static Carre read(final String id, final int dessinID,
            final Connection conn)
            throws LectureException, InexistantException {
        try {
            if (!tableExists("carre", conn)) {
                throw new InexistantException(id);
            }
        } catch (MetaDataException e) {
        }
        try (PreparedStatement selectCarre = conn.prepareStatement(
                "SELECT * FROM carre " + "WHERE nom = ? AND dessinID = ?")) {
            remplitStatement(selectCarre, id, dessinID);
            try (ResultSet rs = selectCarre.executeQuery()) {
                if (rs.next()) {
                    Carre c = new Carre(rs.getString("nom"),
                            new Point(rs.getInt("hgX"), rs.getInt("hgY")),
                            rs.getInt("longueur"));
                    return c;
                } else {
                    throw new InexistantException(id);
                }
            }
        } catch (SQLException | RemplissageStatementException e) {
            throw new LectureException(id, e.getMessage());
        }
    }

    /**
     * Recherche un carré dans la base de donnée.
     * Carré accessible par son nom id.
     * @param id Nom du carré recherché
     * @return Carré lu, null si erreur
     * @throws RechercheException En cas d'erreur lors de la recherche
     * @throws InexistantException Si le carré id n'existe pas
     */
    @Override
    public Carre find(final String id)
            throws InexistantException, RechercheException {
        Carre result = null;
        try (Connection conn = getConnection()) {
            try {
                conn.setAutoCommit(false);
                result = read(id, -1, conn);
                conn.commit();
            } catch (JDBCException | SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (InexistantException e) {
            throw e;
        } catch (JDBCException | SQLException e) {
            throw new RechercheException(id, e.getMessage());
        }
        return result;
    }

    /**
     * Modifie le carré dans la base de donnée.
     * Lui assigne ces nouveau paramêtre.
     * Accessible via son nom et l'id de son dessin.
     * @param obj Carré à modifié
     * @param dessinID ID du dessin auquel la forme appartient
     * @param conn Connection à la base de donnée
     * @throws InexistantException Si le carré obj n'existe pas dans la base de
     *         donnée
     * @throws ModificationException En cas d'erreur pendant la modification.
     */
    static void modify(final Carre obj, final int dessinID,
            final Connection conn)
            throws InexistantException, ModificationException {
        try {
            if (!tableExists("carre", conn)) {
                throw new InexistantException(obj.getNom());
            }
        } catch (MetaDataException e) {
        }
        try {
            try (PreparedStatement selectCarre =
                    conn.prepareStatement("SELECT * FROM carre "
                            + "WHERE nom = ? AND dessinID = ?")) {
                remplitStatement(selectCarre, obj.getNom(), dessinID);
                try (ResultSet rs = selectCarre.executeQuery()) {
                    if (!rs.next()) {
                        throw new InexistantException(obj.getNom());
                    }
                }
            }
            try (PreparedStatement updateCarre = conn.prepareStatement(
                    "UPDATE carre SET hgX = ?, hgY = ?, longueur = ? "
                            + "WHERE nom = ? AND dessinID = ?")) {
                remplitStatement(updateCarre, obj.getPointReference().getX(),
                        obj.getPointReference().getY(), obj.getLongueur(),
                        obj.getNom(), dessinID);
                updateCarre.execute();
            }
        } catch (SQLException | RemplissageStatementException e) {
            throw new ModificationException(obj.getNom(), e.getMessage());
        }
    }

    /**
     * Met à jour le carré dans la base de donnée.
     * Lui assigne ces nouveaux paramètres.
     * Accessible via son nom.
     * @param obj Carré à mettre à jour
     * @throws InexistantException Si le carré obj n'existe pas dans la base de
     *         donnée
     * @throws MisAJourException En cas d'erreur pendant la mise à jour.
     */
    @Override
    public Carre update(final Carre obj)
            throws InexistantException, MisAJourException {
        Carre result = null;
        try (Connection conn = getConnection()) {
            try {
                conn.setAutoCommit(false);
                modify(obj, -1, conn);
                conn.commit();
                result = obj;
            } catch (JDBCException | SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (InexistantException e) {
            throw e;
        } catch (JDBCException | SQLException e) {
            throw new MisAJourException(obj.getNom(), e.getMessage());
        }
        return result;
    }

    /**
     * Supprime les carrés appartenant à dessinID de la base de données.
     * @param dessinID ID du dessin auquel les formes appartiennent
     * @param conn Connexion à la base de donnée
     * @throws SuppressionException En cas d'erreur lors de la suppression
     */
    static void suppressAll(final int dessinID, final Connection conn)
            throws SuppressionException {
        try {
            if (tableExists("carre", conn)) {
                try (PreparedStatement deleteCarre = conn.prepareStatement(
                        "DELETE FROM carre " + "WHERE dessinID = ?")) {
                    remplitStatement(deleteCarre, dessinID);
                    deleteCarre.execute();
                }
            }
        } catch (SQLException | RemplissageStatementException
                | MetaDataException e) {
            throw new SuppressionException("carrés", e.getMessage());
        }
    }

    /**
     * Supprime les carrés appartenant à dessinID de la base de données.
     * Dont les noms ne sont pas dans noms.
     * @param noms Liste de noms de carré non à supprimer
     * @param dessinID ID du dessin auquel les formes appartiennent
     * @param conn Connexion à la base de donnée
     * @throws SuppressionException En cas d'erreur lors de la suppression
     */
    static void suppressAllBut(final Object[] noms, final int dessinID,
            final Connection conn) throws SuppressionException {
        try {
            if (tableExists("carre", conn)) {
                String sql = "DELETE FROM carre " + "WHERE dessinID = ?";
                for (int i = 0; i < noms.length; i++) {
                    sql = sql.concat(" AND nom <> ?");
                }
                try (PreparedStatement deleteCarre =
                        conn.prepareStatement(sql)) {
                    Object[] att = new Object[noms.length + 1];
                    att[0] = dessinID;
                    for (int i = 0; i < noms.length; i++) {
                        att[i + 1] = noms[i];
                    }
                    remplitStatement(deleteCarre, att);
                    deleteCarre.execute();
                }
            }
        } catch (SQLException | RemplissageStatementException
                | MetaDataException e) {
            throw new SuppressionException("carrés", e.getMessage());
        }
    }

    /**
     * Supprime le carré obj de la base de donnée.
     * Accessible par son nom et l'id de son dessin.
     * @param obj Objet à supprimer
     * @param dessinID ID du dessin auquel la forme appartient
     * @param conn Connexion à la base de donnée
     * @throws SuppressionException En cas d'erreur lors de la suppression
     */
    static void suppress(final Carre obj, final int dessinID,
            final Connection conn) throws SuppressionException {
        try {
            if (tableExists("carre", conn)) {
                try (PreparedStatement deleteCarre =
                        conn.prepareStatement("DELETE FROM carre "
                                + "WHERE nom = ? AND dessinID = ?")) {
                    remplitStatement(deleteCarre, obj.getNom(), dessinID);
                    deleteCarre.execute();
                }
            }
        } catch (SQLException | RemplissageStatementException
                | MetaDataException e) {
            throw new SuppressionException(obj.getNom(), e.getMessage());
        }
    }

    /**
     * Efface le carré obj de la base de donnée.
     * Accessible par son nom.
     * @param obj Objet à effacer
     * @throws DeletionException En cas d'erreur lors de l'effacement
     */
    @Override
    public void delete(final Carre obj) throws DeletionException {
        try (Connection conn = getConnection()) {
            try {
                conn.setAutoCommit(false);
                suppress(obj, -1, conn);
                conn.commit();
            } catch (JDBCException | SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (JDBCException | SQLException e) {
            throw new DeletionException(obj.getNom(), e.getMessage());
        }
    }

}
