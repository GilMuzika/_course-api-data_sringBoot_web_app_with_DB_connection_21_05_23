package example.com._demo_project_with_h2_database.topic;
/* Repository is a class that being a bridge between the database and the app.

*/


import org.springframework.data.repository.CrudRepository;


public interface TopicRepository extends CrudRepository<Topic, String> {
// Thr first generic type of the CrudRepository is the type of the Entity class (annotated with @Entity), the second type is the type of the
// field of the entity class that represents the primary key column in the DB (annotated with @Id)
// All the methods are inherited from the parent class
}
