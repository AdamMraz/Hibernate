package SkillBox;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by AdamMraz on 9/1/2020.
 */

@Data
@Entity
@Table(name = "Linkedpurchase")
public class LinkedPurchase {

    @EmbeddedId
    private LinkedPurchaseKey id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    public LinkedPurchase(Student student, Course course) {
        this.student = student;
        this.course = course;
        id = new LinkedPurchaseKey(student.getId(), course.getId());
    }
}
