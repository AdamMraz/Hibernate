import SkillBox.Course;
import SkillBox.LinkedPurchase;
import SkillBox.Student;
import SkillBox.Subscription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AdamMraz on 7/28/2020.
 */
public class Source {
    public static void main(String[] args) throws IOException {
        List<Course> courses = new ArrayList<Course>();
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();

        boolean flag = true;
        int index = 1;


        while (flag) {
            try {
                courses.add(session.get(Course.class, index));
                index++;
            }
            catch (Exception e) {
                flag = false;
            }
        }

        for (Course course : courses) {
            for (Subscription subscription : course.getSubscriptions()) {
                LinkedPurchase temp = new LinkedPurchase(subscription.getStudent(), course);
                course.getLinkedPurchases().add(temp);
            }
        }

        for (Course course : courses) {
            for(LinkedPurchase purchase : course.getLinkedPurchases()) {
                System.out.println(purchase.getId());
            }
        }

        sessionFactory.close();
    }
}
