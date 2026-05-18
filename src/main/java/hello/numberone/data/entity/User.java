package hello.numberone.data.entity;

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
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;

    //orphanRemoval = true : 부모가 가진 리스트에서 자식을 제거 -> DB에서 자식 데이터 삭제
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ToDoList> toDoLists = new ArrayList<>();

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void update(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
