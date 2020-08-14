package SkillBox;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by AdamMraz on 8/8/2020.
 */

@Data
@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int salary;
    private int age;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Courses",
            joinColumns = {@JoinColumn(name = "id")},
            inverseJoinColumns = {@JoinColumn(name = "teacher_id")})
    private List<Course> courses;
}
