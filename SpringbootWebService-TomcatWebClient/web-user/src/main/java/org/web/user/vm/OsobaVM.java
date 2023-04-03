package org.web.user.vm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Data
public class OsobaVM {

    private Long id;

    private String name;

    private String surname;

    private String homePhone;

    private String officePhone;

    private String eMail;

    @JsonIgnore
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private MultipartFile file;

    private byte[] photoOfAPerson;

    public OsobaVM() {

    }

    public void setFile(MultipartFile file) throws IOException {
        this.file = file;
        this.photoOfAPerson = file != null ? file.getBytes() : null;
    }

    @JsonIgnore
    public String getPhotoOfAPersonString() {
        return this.photoOfAPerson != null ? Base64.getEncoder().encodeToString(this.photoOfAPerson) : null;
    }

}
