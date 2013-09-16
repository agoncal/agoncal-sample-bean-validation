package org.agoncal.sample.beanvalidation.with;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class Customer {

    // ======================================
    // =             Attributes             =
    // ======================================

    @NotNull
    @Size(min = 4, max = 50, message = "Firstname should be between {min} and {max}")
    private String firstName;
    private String lastName;
    @NotNull
    @Email(message = "Primary email is not a valid email address")
    private String primaryEmail;
    @Email(message = "Recovery email is not a valid email address")
    private String recoveryEmail;
    private String phoneNumber;
    @Past(message = "Date of birth should be in the past")
    private Date dateOfBirth;

    // ======================================
    // =            Constructors            =
    // ======================================

    public Customer() {
    }

    public Customer(String firstName, String lastName, String primaryEmail, String recoveryEmail, String phoneNumber, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.primaryEmail = primaryEmail;
        this.recoveryEmail = recoveryEmail;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = convert(dateOfBirth);
    }

    private Date convert(String dateOfBirth) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateOfBirth);
        } catch (ParseException e) {
            return null;
        }
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getRecoveryEmail() {
        return recoveryEmail;
    }

    public void setRecoveryEmail(String recoveryEmail) {
        this.recoveryEmail = recoveryEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}