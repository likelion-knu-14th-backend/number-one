package hello.numberone.domain.student.entity;

import hello.numberone.domain.auth.enums.Role;
import hello.numberone.domain.grade.entity.Profile;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String studentNumber;

    private Integer age;

    private String major;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Student(String name, String studentNumber, Integer age, String major) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.age = age;
        this.major = major;
    }

    public void update(String name, String studentNumber, Integer age, String major) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.age = age;
        this.major = major;
    }

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Profile profile;

}

