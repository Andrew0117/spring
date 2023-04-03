package org.service.user.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "osoba")
public class Osoba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name; // imię

    @Column(name = "surname", nullable = false, length = 100)
    private String surname; // nazwisko

    @Column(name = "home_phone", nullable = false, length = 100)
    private String homePhone; // numer telefonu domowego

    @Column(name = "office_phone", nullable = false, length = 100)
    private String officePhone; // numer telefonu służbowego

    @Column(name = "e_mail", nullable = false, length = 100)
    private String eMail; // adres e-mail

    @Column(name = "photo_of_a_person", nullable = true, columnDefinition = "LONGBLOB")
    @Lob()
    private byte[] photoOfAPerson; // zdjęcie osoby (opcjonalnie)

    public Osoba() {
    }
}
