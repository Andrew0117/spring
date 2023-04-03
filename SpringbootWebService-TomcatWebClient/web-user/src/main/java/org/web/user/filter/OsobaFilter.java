package org.web.user.filter;

import lombok.Data;
import org.web.user.vm.OsobaVM;

@Data
public class OsobaFilter {

    private String name;

    private String surname;

    private String homePhone;

    private String officePhone;

    private String eMail;

    public OsobaVM getOsobaVM() {
        OsobaVM osobaVM = new OsobaVM();
        osobaVM.setName(this.name);
        osobaVM.setSurname(this.surname);
        osobaVM.setHomePhone(this.homePhone);
        osobaVM.setOfficePhone(this.officePhone);
        osobaVM.setEMail(this.eMail);

        return osobaVM;
    }
}
