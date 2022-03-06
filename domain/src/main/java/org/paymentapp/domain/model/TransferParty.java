package org.paymentapp.domain.model;

import org.springframework.util.StringUtils;

public class TransferParty {
    
    private String firstName;
    private String middleName;
    private String lastName;
    private String displayName;
    // required
    private AccountId idType;
    // required
    private String idValue;
    
    public TransferParty() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public AccountId getIdType() {
        return idType;
    }

    public void setIdType(AccountId idType) {
        this.idType = idType;
    }

    public String getIdValue() {
        return idValue;
    }

    public void setIdValue(String idValue) {
        this.idValue = idValue;
    }

    @Override
    public String toString() {
        return "TransferParty [idType=" + idType + ", idValue=" + idValue + "]";
    }

    public static void copyProperties(TransferParty transferParty, Party party, String idValue){
        transferParty.setFirstName(party.getFirstname());
        transferParty.setMiddleName(party.getMiddlename());
        transferParty.setLastName(party.getLastname());
        transferParty.setIdValue(idValue);   
        transferParty.setDisplayName(String.format("%s %s %s",
            party.getFirstname(),
            party.getMiddlename(),
            StringUtils.capitalize(party.getLastname().toLowerCase()))
            .trim().replaceAll("\\s{2,}", " "));
    }

    
}
