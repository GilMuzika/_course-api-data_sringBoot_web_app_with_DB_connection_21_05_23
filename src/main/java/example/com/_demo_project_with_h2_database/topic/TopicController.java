package example.com._demo_project_with_h2_database.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {




    @Autowired //@Autowired is a Spring annotation that marks a private field as one that needs a dependency to be injected
    private TopicService topicService; //private variable intended to bear injected service instance


    /* If return type of response-creating method (annotated with one of Request Mapping annotations)
    is a list of objects, it's automatically converted to JSON
    */
    @RequestMapping("/")
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    @GetMapping(value = "/{topicId}") // @GetMapping("/{topicId}") is also valid
    public Topic getTopic(@PathVariable("topicId") String id) {
        //@PathVariable annotation says Spring to treat the substring in the curly parentheses as a parameter.
        //When this parameter has the same name as the parameter of the method, "@PathVariable" doesn't need any parameters.
        //If the name is different, the annotation neds a string parameter  that is the name of the parameter in the path
        return topicService.getTopic(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/") //alternatively @PostMapping
    public void addTopic(@RequestBody Topic topic) {
        //@RequestBody annotation tells that the payload of this POST request contains a body that needs to be converted to a Topic instance
        topicService.addTopic(topic);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public void updateTopic(@PathVariable String id, @RequestBody Topic topic) {
        topicService.updateTopic(id, topic);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void deleteTopic(@PathVariable String id) {
        topicService.deleteTopic(id);
    }
}
