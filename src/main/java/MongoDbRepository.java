import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

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

    public void find() {
        FindIterable<Document> views = database.getCollection(VIEWS).find(and(eq("foo", "1"), eq("bar.baz", "2")));
        System.out.print(views.first());
    }

    public void drop() {
        database.getCollection(VIEWS).drop();
        System.out.println(database.getCollection(VIEWS).count());
    }
}
