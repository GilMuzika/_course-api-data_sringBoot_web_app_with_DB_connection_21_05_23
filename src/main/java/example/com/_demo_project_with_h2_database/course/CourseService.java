package example.com._demo_project_with_h2_database.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/* Spring business service
In Spring, business devises are typically initialized just once when application starts, and this instance
being kept in memory all the time while the application runs.
Other classes, like controllers or other services, which are dependent on it, declare this dependency,
telling by this to Spring that this instance needs to be injected into them.
*/
@Service //@Service annotation mark a class as a Spring business service
public class CourseService {

    //@PersistenceContext
    //private EntityManager em;

    //Injecting repository into service the same way the service is injected into controller
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses(String topicId) {
        ArrayList<Course> courses = new ArrayList<>();
        Iterable<Course> iterable = courseRepository.findByTopicId(topicId);
        iterable.forEach(courses::add); // just as iterable.forEach(x -> Courses.add(x));
        return courses;
    }


    public Course getCourse(String id) {
        return courseRepository.findById(id).get();
    }

    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourse(Course course){
        /* The method "save()" used both to add a new entity and to update the existing entity.
        It just checks the entity's @Id, if a row with this id already exists in the table, this row is updated with the data from the entity,
        if there isn't a row with this id, so a new row is added to the end of the table and filled with the data from the entity,
        including the id
        So, passing "id" parameter is unnecessary
        */
        courseRepository.save(course);
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

}
