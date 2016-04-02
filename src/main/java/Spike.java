import java.util.Date;

public class Spike {

    public static void main(String[] args) {
        MongoDbRepository repository = new MongoDbRepository();
        seed(repository);
        System.out.println(new Date());
        repository.findBy(searchCriterias());
        System.out.println(new Date());
    }

    private static EqualityCriteria[] searchCriterias() {
        return new EqualityCriteria[]{new EqualityCriteria("foo", "1"), new EqualityCriteria("bar.baz", "2")};
    }

    private static void seed(MongoDbRepository repository) {
        repository.drop();
        System.out.println(new Date());
        for (int i = 0; i < 1000000; i++) {
            String json = jsonFor(i);
            repository.insert(json);
        }
    }

    private static String jsonFor(int i) {
        return "{\"foo\":\"1\",\"bar\":{\"baz\":\"" + i + "\"}}";
    }
}
