package example.com._demo_project_with_h2_database.course;

import example.com._demo_project_with_h2_database.topic.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class CourseController {




    @Autowired //@Autowired is a Spring annotation that marks a private field as one that needs a dependency to be injected
    private CourseService courseService; //private variable intended to bear injected service instance


    /* If return type of response-creating method (annotated with one of Request Mapping annotations)
    is a list of objects, it's automatically converted to JSON
    */
    @RequestMapping("/{topicId}/courses")
    public List<Course> getAllCourses(@PathVariable String topicId) {
        return courseService.getAllCourses(topicId);
    }

    @GetMapping(value = "/{topicId}/course/{id}") // @GetMapping("/{courseId}") is also valid
    public Course getCourse(@PathVariable("courseId") String id) {
        //@PathVariable annotation says Spring to treat the substring in the curly parentheses as a parameter.
        //When this parameter has the same name as the parameter of the method, "@PathVariable" doesn't need any parameters.
        //If the name is different, the annotation neds a string parameter  that is the name of the parameter in the path
        return courseService.getCourse(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{topicId}/courses") //alternatively @PostMapping
    public void addCourse(@RequestBody Course course, @PathVariable String topicId) {
        //@RequestBody annotation tells that the payload of this POST request contains a body that needs to be converted to a Topic instance
        course.setTopic(new Topic(topicId, "", ""));
        courseService.addCourse(course);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{tpicId}/courses/{id}")
    public void updateCourse(@PathVariable String id, @PathVariable String topicId, @RequestBody Course course) {
        course.setTopic(new Topic(topicId, "", ""));
        courseService.updateCourse(course);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{tpicId}/courses/{id}")
    public void deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
    }
}
