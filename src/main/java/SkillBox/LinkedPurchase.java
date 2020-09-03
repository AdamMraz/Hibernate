package SkillBox;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by AdamMraz on 9/1/2020.
 */

@Data
@Entity
@Table(name = "Linkedpurchaselist")
public class LinkedPurchase {

    @EmbeddedId
    private LinkedPurchaseKey id;

    @Column(name = "student_id", insertable = false, updatable = false)
    private int studentId;

    @Column(name = "course_id", insertable = false, updatable = false)
    private int courseId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", insertable = false, updatable = false)
    private Course course;

    public LinkedPurchase(Student student, Course course) {
        this.student = student;
        this.course = course;
        studentId = student.getId();
        courseId = course.getId();
        id = new LinkedPurchaseKey(studentId, courseId);
    }
}
