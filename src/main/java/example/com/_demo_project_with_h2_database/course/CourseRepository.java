package example.com._demo_project_with_h2_database.course;
/* Repository is a class that being a bridge between the database and the app.

*/


import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CourseRepository extends CrudRepository<Course, String> {
// Thr first generic type of the CrudRepository is the type of the Entity class (annotated with @Entity), the second type is the type of the
// field of the entity class that represents the primary key column in the DB (annotated with @Id)
// All the methods are inherited from the parent class

   /* The repository is an interface that only declares methods, however,
   Spring Data JDBC api can figure out the implementation basing on particular naming patterns
   of hte methods:
   1. all the methods that return an entity or group of entities are start with the word "find"
   2. If the method returns a group of entities filtered by a particular condition, the next portion of the name must be the word "By"
   3. The next portion of the name must be a name of a field of thr entity/column name in the table, in Camel case
   4. If the field is entity Object, next portion of hte name must be specified, and it must be a name of a field in this daughter entity/of a column in a joined table, in Camel case

   */
   public List<Course> findByTopicId(String topicId); //find all filtered by topicId: find -> By -> Topic -> Id

}
