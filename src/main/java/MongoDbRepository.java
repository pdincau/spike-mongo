import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

public class MongoDbRepository {

    public static final String VIEWS = "views";
    public static final String DATABASE_NAME = "spike";

    MongoDatabase database;

    public MongoDbRepository() {
        MongoClient client = new MongoClient();
        database = client.getDatabase(DATABASE_NAME);
    }

    public void insert(String json) {
        database.getCollection(VIEWS).insertOne(Document.parse(json));
    }

    public void findBy(EqualityCriteria ... criterias) {
        List<Bson> bsons = criteriaToBsons(criterias);
        FindIterable<Document> views = database.getCollection(VIEWS).find(and(bsons));
        System.out.print(views.first());
    }

    public void drop() {
        database.getCollection(VIEWS).drop();
        System.out.println(database.getCollection(VIEWS).count());
    }

    private List<Bson> criteriaToBsons(EqualityCriteria[] criterias) {
        return stream(criterias).map(c -> eq(c.getField(), c.getValue())).collect(toList());
    }
}
