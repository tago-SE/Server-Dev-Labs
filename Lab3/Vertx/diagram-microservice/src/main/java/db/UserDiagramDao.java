package db;

import com.mongodb.*;
import com.mongodb.client.*;
import com.mongodb.client.MongoClient;
import models.UserDiagram;
import models.UserDiagramPoint;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.mongodb.client.model.Filters.eq;


/**
 * Controls access to the database, we only need insert, delete, and getByUsername
 * Currently no sanity checks of inserted data
 */
public class UserDiagramDao {

    private final MongoDatabase db;
    private final MongoCollection<Document> col;

    private static final String COL_X               = "x";
    private static final String COL_Y               = "y";
    private static final String COL_DATA_POINTS     = "points";
    private static final String COL_USERNAME        = "username";
    private static final String COL_NAME            = "name";
    private static final String COL_TYPE            = "type";
    private static final String COL_ID              = "_id";

    public UserDiagramDao(String hostAddress, String dbName) {
        MongoClient mongoClient = MongoClients.create("mongodb://" + hostAddress);
        db = mongoClient.getDatabase(dbName);
        col = db.getCollection("UserDiagram");
    }

    private Document toDocument(UserDiagram ud) {
        Document doc = new Document();
        doc.append(COL_USERNAME, ud.getUsername());
        doc.append(COL_NAME, ud.getName());
        doc.append(COL_TYPE, ud.getType());
        List<Document> dataPointsDoc = new ArrayList<>();
        List<UserDiagramPoint> dataPoints = ud.getDataPoints();
        if (dataPoints != null) for (UserDiagramPoint p : dataPoints) {
            dataPointsDoc.add(new Document().append(COL_X, p.getX()).append(COL_Y, p.getY()));
        }
        doc.append(COL_DATA_POINTS, dataPointsDoc);
        return doc;
    }

    private UserDiagram toModel(Document doc) {
        UserDiagram ud = new UserDiagram();
        ud.setName(doc.getString(COL_NAME));
        ud.setType(doc.getString(COL_TYPE));
        ud.setUsername(doc.getString(COL_USERNAME));
        ud.setId(doc.getObjectId(COL_ID).toString());
        List<UserDiagramPoint> points = new ArrayList<>();
        List<Document> docDataPoints = (List<Document>) doc.get(COL_DATA_POINTS);
        docDataPoints.forEach(d -> {
            points.add(new UserDiagramPoint(d.getInteger(COL_X), d.getInteger(COL_Y)));
        });
        ud.setDataPoints(points);
        return ud;
    }

    public void insert(UserDiagram ud) {
        Document doc = toDocument(ud);
        col.insertOne(doc);
    }

    public UserDiagram get(String id) {
        Document doc = col.find(eq(COL_ID, new ObjectId(id))).first();
        if (doc == null)
            return null;
        return toModel(doc);
    }

    public void delete(String id) {
        col.deleteOne(new Document(COL_ID, new ObjectId(id)));
    }

    public List<UserDiagram> getByUser(String username) {
        List<UserDiagram> resultList = new ArrayList<>();
        BasicDBObject query = new BasicDBObject();
        query.put(COL_USERNAME, username);
        MongoCursor<Document> cursor = null;
        try {
            cursor = col.find(query).iterator();
            while (cursor.hasNext()) {
                resultList.add(toModel(cursor.next()));
            }
        } finally {
            Objects.requireNonNull(cursor).close();
        }
        return resultList;
    }

    public void deleteByUser(String username) {
        BasicDBObject query = new BasicDBObject();
        query.put(COL_USERNAME, username);
        col.deleteMany(query);
    }

}
