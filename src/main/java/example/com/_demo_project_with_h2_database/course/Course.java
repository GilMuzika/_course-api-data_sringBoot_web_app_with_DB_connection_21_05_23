package example.com._demo_project_with_h2_database.course;

import example.com._demo_project_with_h2_database.topic.Topic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Field;

@Getter
@Setter
@NoArgsConstructor
// @Entity tells to JPA that this class is an entity that servs as a model to DB
// Because this class marked with @Entity, JPA knows to create a table named "Topic" wih three columns, "_id", "_name", "_description"
@Entity
public class Course {
    @Id //@Id annotation tells JPA that a column created from a field annotated with @Id is the primary key
    private String id;
    private String name;
    private String description;

    /* Field names of an entity will be processed by the data JDBC driver, not only those names will be used as a column names in
    the  table (alongside with the class name as the table name), but they are also used to contextual creating of methods
    in repository, where user-define method can be defined as an interface abstractions (the repository is an interface),
    so the name must be composed properly in order that the system will be able to derive the method implementation from the name.
    Those method definitions must include entity filed named in proper format,
    so the field names themselves must begin only with a lower case letter, no special symbols like underscore allowed
    */

    @ManyToOne /* @ManyToOne annotation tells that the relationship between courses and topics is many-to-one,
    which is many courses can have the same topic
    Regarding that there is no access to the DB itself, including a reference to one entity into another,
    and marking it with proper relationship annotation, is critical, because this is the only instrument
    to control databases relationships
    */
    private Topic topic;

    public Course(String id, String name, String _description, String topicId) {
        this.id = id;
        this.name = name;
        this.description = _description;
        this.topic = new Topic(topicId, "", "");
    }

    @Override
    public String toString() {
        Field[] fields  = this.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder("[");
        String comma = ", ";
        int count = 0;
        for(Field f : fields) {
            if(count == fields.length - 1)
                comma = "";
            try {
                sb.append(String.format("%s: %s%s", f.getName(), f.get(this), comma));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            count++;
        }
        sb.append("]");
        return  sb.toString();
    }
}
