package ru.project.congratSystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.ComponentScan;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    @NotEmpty
    @Size(min = 3, max = 10, message = "некорректное значение!")
    private String name;

    @Column(name = "surname")
    @NotEmpty
    @Size(min = 3, max = 10, message = "некорректное значение!")
    private String surname;

    @Column(name = "username")
    @NotEmpty
    @Size(min = 3, max = 20, message = "некорректное значение!")
    private String username;

    @NotEmpty
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private String dateOfBirth;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    @NotEmpty
    @Email
    private String email;

    @OneToMany(mappedBy = "owner")
    @JsonManagedReference
    private List<Friend> friends;

    @OneToMany(mappedBy = "ownerOfCongrat" )
    @JsonManagedReference
    private List<Congratulation> congrats;

    public User(long id, String name, String surname, String username,
                String dateOfBirth, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.password = password;
    }

    public User() {

    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", password='" + password +
                '}';
    }
}
