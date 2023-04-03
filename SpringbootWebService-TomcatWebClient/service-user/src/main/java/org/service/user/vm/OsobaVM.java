package org.service.user.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.service.user.entity.Osoba;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class OsobaVM {

    private Long id;

    @NotEmpty(message = "name must not be empty")
    @Size(max = 100)
    private String name;

    @NotEmpty(message = "surname must not be empty")
    @Size(max = 100)
    private String surname;

    @NotEmpty(message = "homePhone must not be empty")
    @Size(max = 100)
    @Pattern(
            message = "format +xx (xxx) xxx-xxxx, +xxxxxxxxxxxx, +xx xxx xxx-xxxx",
            regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$"
    )
    private String homePhone;

    @NotEmpty(message = "officePhone must not be empty")
    @Size(max = 100)
    @Pattern(
            message = "format +xx (xxx) xxx-xxxx, +xxxxxxxxxxxx, +xx xxx xxx-xxxx",
            regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$"
    )
    private String officePhone;

    @NotEmpty(message = "email must not be empty")
    @Size(max = 100)
    @Email(
            message = "email should be a valid email",
            regexp = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"
    )
    private String eMail;

    private byte[] photoOfAPerson;

    public OsobaVM() {
    }

    public OsobaVM(Osoba osoba) {
        this.id = osoba.getId();
        this.name = osoba.getName();
        this.surname = osoba.getSurname();
        this.homePhone = osoba.getHomePhone();
        this.officePhone = osoba.getOfficePhone();
        this.eMail = osoba.getEMail();
        this.photoOfAPerson = osoba.getPhotoOfAPerson();
    }

    @JsonIgnore
    public Osoba getOsoba() {
        Osoba osoba = new Osoba();
        osoba.setId(this.id);
        osoba.setName(this.name);
        osoba.setSurname(this.surname);
        osoba.setHomePhone(this.homePhone);
        osoba.setOfficePhone(this.officePhone);
        osoba.setEMail(this.eMail);
        osoba.setPhotoOfAPerson(this.photoOfAPerson);

        return osoba;
    }

}
