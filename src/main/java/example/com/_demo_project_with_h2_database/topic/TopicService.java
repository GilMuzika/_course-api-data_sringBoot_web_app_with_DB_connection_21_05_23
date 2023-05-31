package example.com._demo_project_with_h2_database.topic;

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
public class TopicService {

    //@PersistenceContext
    //private EntityManager em;

    //Injecting repository into service the same way the service is injected into controller
    @Autowired
    private TopicRepository topicRepository;

    public List<Topic> getAllTopics() {
        ArrayList<Topic> topics = new ArrayList<>();
        Iterable<Topic> iterable = topicRepository.findAll();
        iterable.forEach(topics::add); // just as iterable.forEach(x -> topics.add(x));
        return topics;
    }


    public Topic getTopic(String id) {
        return topicRepository.findById(id).get();
    }

    public void addTopic(Topic topic) {
        topicRepository.save(topic);
    }

    public void updateTopic(String id, Topic topic){
        /* The method "save()" used both to add a new entity and to update the existing entity.
        It just checks the entity's @Id, if a row with this id already exists in the table, this row is updated with the data from the entity,
        if there isn't a row with this id, so a new row is added to the end of the table and filled with the data from the entity,
        including the id
        So, passing "id" parameter is unnecessary
        */
        topicRepository.save(topic);
    }

    public void deleteTopic(String id) {
        topicRepository.deleteById(id);
    }

}
