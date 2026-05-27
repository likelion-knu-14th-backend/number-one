package hello.numberone.user.data.entity;

import hello.numberone.product.data.entity.Profile;
import hello.numberone.user.data.enums.Role;
import hello.numberone.product.data.entity.ToDoList;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    //orphanRemoval = true : 부모가 가진 리스트에서 자식을 제거 -> DB에서 자식 데이터 삭제
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToDoList> toDoLists = new ArrayList<>();

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
