package example.com._demo_project_with_h2_database;

import example.com._demo_project_with_h2_database.course.Course;
import example.com._demo_project_with_h2_database.course.CourseRepository;
import example.com._demo_project_with_h2_database.topic.Topic;
import example.com._demo_project_with_h2_database.topic.TopicRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;



	/*
    Spring Boot QuickStart Demo Project
    The project that is created with the teacher in this course:
    https://www.youtube.com/watch?v=msXL2oDexqw&list=PLqq-6Pq4lTTbx8p2oCgcAQGQyqN8XeA1x&index=2



    Common Application Properties:
    https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html
*/


//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootApplication
public class DemoProjectWithH2DatabaseApplication {
/* The way to plug in a code into SpringBoot is by creating custom classes and annotating them
    with the appropriate to the intent annotation.
    For example, when creating a business service, we create a class and annotate it with @Service annotation.
    When creating a controller, we create a class and annotate it with  @Controller annotation

    */

	public static void main(String[] args) {

		/*
        "application context" is a container for SpringBoot application (just like Docker) that provides a server environment
        to start the application. "SpringApplication.run()" scans the classpath of the application and looks for classes that have
        special annotations. If found, those classes treated accordingly to their annotations.
        It also starts the Tomcat server to provide server environment to web app

        The default address for a locally run web  application is http://localhost:8080/
        */
		ConfigurableApplicationContext context =  SpringApplication.run(DemoProjectWithH2DatabaseApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx, TopicRepository topRep, CourseRepository courRep) {
		return args -> {

			Topic topic1 = new Topic("javascript", "JavaScript", "JavaScript description");
			Topic topic2 = new Topic("java", "Java", "Java description");
			Topic topic3 = new Topic("csharp", "C#", "C# description");
			topRep.save(topic1);
			System.out.printf("The topic %s was saved \n", topic1);
			topRep.save(topic2);
			System.out.printf("The topic %s was saved \n", topic2);
			topRep.save(topic3);
			System.out.printf("The topic %s was saved \n", topic3);

			Course course1 = new Course("csharointo", "C# Introducrtion", "C# Introducrtion description", "csharp");
			courRep.save(course1);
			System.out.printf("The course %s was saved \n", course1);

			System.out.println("Congratulations!");
		};
	}

}
