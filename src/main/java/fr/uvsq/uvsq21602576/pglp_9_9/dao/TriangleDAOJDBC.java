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
import fr.uvsq.uvsq21602576.pglp_9_9.formes.ComposantDessin;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Point;
import fr.uvsq.uvsq21602576.pglp_9_9.formes.Triangle;

/**
 * DAO JDBC.
 * Pour Triangle.
 * @author Flora
 */
public class TriangleDAOJDBC extends DAO<Triangle> {

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
     * Crée la tables utile pour triangle.
     * C'est à dire : Triangle.
     * @param conn Connexion à la base de donnée
     * @throws CreationTableException En cas d'erreur lors de la creation des
     *         tables.
     */
    static void createTable(final Connection conn)
            throws CreationTableException {
        try {
            DessinDAOJDBC.createTable(conn);
            if (!tableExists("triangle", conn)) {
                try (Statement stmt = conn.createStatement()) {
                    stmt.execute("Create table triangle ("
                            + "nom varchar(30) not null, "
                            + "p1X int not null, " + "p1Y int not null,"
                            + "p2X int not null, " + "p2Y int not null,"
                            + "p3X int not null, " + "p3Y int not null,"
                            + "dessinID int not null,"
                            + "primary key (nom, dessinID), "
                            + "foreign key (dessinID) "
                            + "references dessin(dessinID))");
                }
            }
        } catch (MetaDataException | SQLException e) {
            throw new CreationTableException("triangle", e.getMessage());
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
     * Insert l'objet triangle dans la base de donnée.
     * Via la connexion conn.
     * @param obj Triangle à insérer
     * @param dessinID ID du dessin auquel la forme appartient
     * @param conn Connexion à la base de donnée
     * @throws InsertionException En cas d'erreur lors de l'insertion de données
     * @throws CreationTableException En cas d'erreur lors de la creation des
     *         tables
     * @throws DoublonException En cas de duplication de clé, si l'objet obj
     *         existe déjà
     */
    static void insert(final Triangle obj, final int dessinID,
            final Connection conn) throws InsertionException,
            CreationTableException, DoublonException {
        try {
            createTable(conn);
            try (PreparedStatement insertTriangle = conn.prepareStatement(
                    "INSERT INTO triangle values (?, ?, ?, ?, ?, ?, ?, ?)")) {
                remplitStatement(insertTriangle, obj.getNom(),
                        obj.getPointReference().getX(),
                        obj.getPointReference().getY(), obj.getPoint2().getX(),
                        obj.getPoint2().getY(), obj.getPoint3().getX(),
                        obj.getPoint3().getY(), dessinID);
                insertTriangle.execute();
            }
        } catch (DerbySQLIntegrityConstraintViolationException e) {
            throw new DoublonException(obj.getNom());
        } catch (SQLException | RemplissageStatementException e) {
            throw new InsertionException(obj.getNom(), e.getMessage());
        }
    }

    /**
     * Créer le triangle obj dans la base de donnée.
     * @param obj Triangle à créer
     * @return le triangle créé
     * @throws CreationObjetException En cas d'erreru à la création de l'objet.
     * @throws DoublonException En cas de duplication de clé, si l'objet obj
     *         existe déjà
     */
    @Override
    public Triangle create(final Triangle obj)
            throws CreationObjetException, DoublonException {
        Triangle result = null;
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
     * Lit tous les triangles dans la base de donnée.
     * Triangles appartenant au dessin dessinID
     * @param dessinID ID du dessin auquel les formes appartiennent
     * @param conn Connection à la base de donnée
     * @return Liste de Triangles lus
     * @throws LectureException En cas d'erreur lors de la lecture
     */
    static List<ComposantDessin> readAll(final int dessinID,
            final Connection conn) throws LectureException {
        ArrayList<ComposantDessin> listeTriangle = new ArrayList<>();
        try {
            if (!tableExists("triangle", conn)) {
                return listeTriangle;
            }
        } catch (MetaDataException e) {
        }
        try (PreparedStatement selectTriangle = conn.prepareStatement(
                "SELECT * FROM triangle " + "WHERE dessinID = ?")) {
            remplitStatement(selectTriangle, dessinID);
            try (ResultSet rs = selectTriangle.executeQuery()) {
                while (rs.next()) {
                    listeTriangle.add(new Triangle(rs.getString("nom"),
                            new Point(rs.getInt("p1X"), rs.getInt("p1Y")),
                            new Point(rs.getInt("p2X"), rs.getInt("p2Y")),
                            new Point(rs.getInt("p3X"), rs.getInt("p3Y"))));
                }
            }
        } catch (SQLException | RemplissageStatementException e) {
            throw new LectureException("triangles", e.getMessage());
        }
        return listeTriangle;
    }

    /**
     * Lit un triangle dans la base de donnée.
     * Triangle accessible par son nom id et l'id de son dessin.
     * @param id Nom du triangle à lire
     * @param dessinID ID du dessin auquel la forme appartient
     * @param conn Connection à la base de donnée
     * @return Triangle lu
     * @throws LectureException En cas d'erreur lors de la lecture
     * @throws InexistantException Si le triangle id n'existe pas
     */
    static Triangle read(final String id, final int dessinID,
            final Connection conn)
            throws LectureException, InexistantException {
        try {
            if (!tableExists("triangle", conn)) {
                throw new InexistantException(id);
            }
        } catch (MetaDataException e) {
        }
        try (PreparedStatement selectTriangle = conn.prepareStatement(
                "SELECT * FROM triangle WHERE nom = ? AND dessinID = ?")) {
            remplitStatement(selectTriangle, id, dessinID);
            try (ResultSet rs = selectTriangle.executeQuery()) {
                if (rs.next()) {
                    Triangle c = new Triangle(rs.getString("nom"),
                            new Point(rs.getInt("p1X"), rs.getInt("p1Y")),
                            new Point(rs.getInt("p2X"), rs.getInt("p2Y")),
                            new Point(rs.getInt("p3X"), rs.getInt("p3Y")));
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
     * Recherche un triangle dans la base de donnée.
     * Triangle accessible par son nom id.
     * @param id Nom du triangle recherché
     * @return Rectangle lu, null si erreur
     * @throws RechercheException En cas d'erreur lors de la recherche
     * @throws InexistantException Si le triangle id n'existe pas
     */
    @Override
    public Triangle find(final String id)
            throws InexistantException, RechercheException {
        Triangle result = null;
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
     * Modifie le triangle dans la base de donnée.
     * Lui assigne ces nouveaux paramètres.
     * Accessible via son nom et l'id de son dessin.
     * @param obj Rectangle à modifier
     * @param dessinID ID du dessin auquel la forme appartient
     * @param conn Connection à la base de donnée
     * @throws InexistantException Si le triangle obj n'existe pas dans la base
     *         de
     *         donnée
     * @throws ModificationException En cas d'erreur pendant la modification.
     */
    static void modify(final Triangle obj, final int dessinID,
            final Connection conn)
            throws InexistantException, ModificationException {
        try {
            if (!tableExists("triangle", conn)) {
                throw new InexistantException(obj.getNom());
            }
        } catch (MetaDataException e) {
        }
        try {
            try (PreparedStatement selectTriangle =
                    conn.prepareStatement("SELECT * FROM triangle "
                            + "WHERE nom = ? AND dessinID = ?")) {
                remplitStatement(selectTriangle, obj.getNom(), dessinID);
                try (ResultSet rs = selectTriangle.executeQuery()) {
                    if (!rs.next()) {
                        throw new InexistantException(obj.getNom());
                    }
                }
            }
            try (PreparedStatement updateTriangle = conn
                    .prepareStatement("UPDATE triangle SET p1X = ?, p1Y = ?, "
                            + "p2X = ?, p2Y = ?, p3X = ?, p3Y = ? "
                            + "WHERE nom = ? AND dessinID = ?")) {
                remplitStatement(updateTriangle, obj.getPointReference().getX(),
                        obj.getPointReference().getY(), obj.getPoint2().getX(),
                        obj.getPoint2().getY(), obj.getPoint3().getX(),
                        obj.getPoint3().getX(), obj.getNom(), dessinID);
                updateTriangle.execute();
            }
        } catch (SQLException | RemplissageStatementException e) {
            throw new ModificationException(obj.getNom(), e.getMessage());
        }
    }

    /**
     * Met à jour le triangle dans la base de donnée.
     * Lui assigne ces nouveaux paramètres.
     * Accessible via son nom.
     * @param obj Triangle à mettre à jour
     * @throws InexistantException Si le triangle obj n'existe pas dans la base
     *         de
     *         donnée
     * @throws MisAJourException En cas d'erreur pendant la mise à jour.
     */
    @Override
    public Triangle update(final Triangle obj)
            throws InexistantException, MisAJourException {
        Triangle result = null;
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
     * Supprime les triangles appartenant à dessinID de la base de données.
     * @param dessinID ID du dessin auquel les formes appartiennent
     * @param conn Connexion à la base de donnée
     * @throws SuppressionException En cas d'erreur lors de la suppression
     */
    static void suppressAll(final int dessinID, final Connection conn)
            throws SuppressionException {
        try {
            if (tableExists("triangle", conn)) {
                try (PreparedStatement deleteTriangle = conn.prepareStatement(
                        "DELETE FROM triangle " + "WHERE dessinID = ?")) {
                    remplitStatement(deleteTriangle, dessinID);
                    deleteTriangle.execute();
                }
            }
        } catch (SQLException | RemplissageStatementException
                | MetaDataException e) {
            throw new SuppressionException("triangles", e.getMessage());
        }
    }

    /**
     * Supprime les triangles appartenant à dessinID de la base de données.
     * Dont les noms ne sont pas dans noms.
     * @param noms Liste de noms de traingle non à supprimer
     * @param dessinID ID du dessin auquel les formes appartiennent
     * @param conn Connexion à la base de donnée
     * @throws SuppressionException En cas d'erreur lors de la suppression
     */
    static void suppressAllBut(final Object[] noms, final int dessinID,
            final Connection conn) throws SuppressionException {
        try {
            if (tableExists("triangle", conn)) {
                String sql = "DELETE FROM triangle " + "WHERE dessinID = ?";
                for (int i = 0; i < noms.length; i++) {
                    sql = sql.concat(" AND nom <> ?");
                }
                try (PreparedStatement deleteTriangle =
                        conn.prepareStatement(sql)) {
                    Object[] att = new Object[noms.length + 1];
                    att[0] = dessinID;
                    for (int i = 0; i < noms.length; i++) {
                        att[i + 1] = noms[i];
                    }
                    remplitStatement(deleteTriangle, att);
                    deleteTriangle.execute();
                }
            }
        } catch (SQLException | RemplissageStatementException
                | MetaDataException e) {
            throw new SuppressionException("triangles", e.getMessage());
        }
    }

    /**
     * Supprime le triangle obj de la base de donnée.
     * Accessible par son nom et l'id de son dessin.
     * @param obj Objet à supprimer
     * @param dessinID ID du dessin auquel la forme appartient
     * @param conn Connexion à la base de donnée
     * @throws SuppressionException En cas d'erreur lors de la suppression
     */
    static void suppress(final Triangle obj, final int dessinID,
            final Connection conn) throws SuppressionException {
        try {
            if (tableExists("triangle", conn)) {
                try (PreparedStatement deleteTriangle =
                        conn.prepareStatement("DELETE FROM triangle "
                                + "WHERE nom = ? AND dessinID = ?")) {
                    remplitStatement(deleteTriangle, obj.getNom(), dessinID);
                    deleteTriangle.execute();
                }
            }
        } catch (SQLException | RemplissageStatementException
                | MetaDataException e) {
            throw new SuppressionException(obj.getNom(), e.getMessage());
        }
    }

    /**
     * Efface le triangle obj de la base de donnée.
     * Accessible par son nom.
     * @param obj Objet à effacer
     * @throws DeletionException En cas d'erreur lors de l'effacement
     */
    @Override
    public void delete(final Triangle obj) throws DeletionException {
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
