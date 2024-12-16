package ru.project.congratSystem.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "friends")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private long id;

    @NotNull
    @Size(min = 2, max = 15, message = "твоего друга очень странно зовут")
    @Column(name = "friend_name")
    private String friendName;

    @NotNull
    @Size(min = 2, max = 15, message = "у твоего друга очень странная фамилия")
    @Column(name = "friend_surname")
    private String friendSurname;

    @NotNull
    @Pattern(regexp = "\\d{2}\\.\\d{2}\\.\\d{4}",
            message = "Дата рождения должна быть в формате дд.мм.гггг")
    @Column(name = "friend_date_of_birth")
    private String friendDateOfBirth;

    @NotNull
    @Email
    @Column(name = "friend_email")
    private String friendEmail;

    @ManyToOne
    @JoinColumn(name = "owner_of_friend",referencedColumnName = "username")
    @JsonBackReference
    private User owner;

    @OneToMany(mappedBy = "whoCongr")
    private List<Congratulation> friendCongrats;


    public Friend(long id, String friendName, String friendSurname, String friendDateOfBirth, String friendEmail) {
        this.id = id;
        this.friendName = friendName;
        this.friendSurname = friendSurname;
        this.friendDateOfBirth = friendDateOfBirth;
        this.friendEmail = friendEmail;
    }

    public Friend() {
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", friendName='" + friendName + '\'' +
                ", friendSurname='" + friendSurname + '\'' +
                ", friendDateOfBirth='" + friendDateOfBirth + '\'' +
                ", friendEmail='" + friendEmail + '\'' +
                '}';
    }
}
