package example.com._demo_project_with_h2_database.topic;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
// @Entity tells to JPA that this class is an entity that servs as a model to DB
// Because this class marked with @Entity, JPA knows to create a table named "Topic" wih three columns, "_id", "_name", "_description"
@Entity
public class Topic {
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
