package ru.project.congratSystem.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;

@Getter
@Setter
@ToString
@Entity
@Table(name = "congrats")
public class Congratulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "congrat_id")
    private long id;


    @Size(min = 1, max = 30, message = "Невалидный заголовок")
    @Column(name = "title")
    private String title;

    @NotNull
    @Size(min = 1, max = 300, message = "Невалидный текст сообщения")
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "owner_of_congrat",referencedColumnName = "username")
    @JsonBackReference
    private User ownerOfCongrat;

    @ManyToOne
    @JoinColumn(name = "who_congrat",referencedColumnName = "friend_id")
    @JsonBackReference
    private Friend whoCongr;

//    @Email
    @Column(name = "mail_adress")
    private String mailAdress;


    public Congratulation() {

    }

    public Congratulation(long id, String title, String text, File attachment, User ownerOfCongrat, Friend whoCongr, String mailAdress) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.ownerOfCongrat = ownerOfCongrat;
        this.whoCongr = whoCongr;
        this.mailAdress = mailAdress;
    }
}
