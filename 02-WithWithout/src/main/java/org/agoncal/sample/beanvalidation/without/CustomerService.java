package org.agoncal.sample.beanvalidation.without;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class CustomerService {

    // ======================================
    // =           Public Methods           =
    // ======================================

    public void createCustomer(Customer customer) {

        if (customer.getFirstName() == null || customer.getFirstName().length() < 4 || customer.getFirstName().length() > 50)
            throw new IllegalArgumentException("Firstname should be between 4 and 40");
        if (customer.getPrimaryEmail() == null || !validEmail(customer.getPrimaryEmail()))
            throw new IllegalArgumentException("Primary email is not a valid email address");
        if (customer.getRecoveryEmail() != null && !validEmail(customer.getRecoveryEmail()))
            throw new IllegalArgumentException("Recovery email is not a valid email address");
        if (customer.getDateOfBirth() != null && customer.getDateOfBirth().getTime() > new Date().getTime())
            throw new IllegalArgumentException("Date of birth should be in the past");

        // Customer is valid, store it into the database
    }

    public void updateCustomer(Customer customer) {

        if (customer.getFirstName() == null || customer.getFirstName().length() < 4 || customer.getFirstName().length() > 50)
            throw new IllegalArgumentException("Firstname should be between 4 and 40");
        if (customer.getPrimaryEmail() == null || !validEmail(customer.getPrimaryEmail()))
            throw new IllegalArgumentException("Primary email is not a valid email address");
        if (customer.getRecoveryEmail() != null && !validEmail(customer.getRecoveryEmail()))
            throw new IllegalArgumentException("Recovery email is not a valid email address");
        if (customer.getDateOfBirth() != null && customer.getDateOfBirth().getTime() > new Date().getTime())
            throw new IllegalArgumentException("Date of birth should be in the past");

        // Customer is valid, update it into the database
    }

    // ======================================
    // =          Private Methods           =
    // ======================================

    private boolean validEmail(String email) {
        final String EMAIL_PATTERN = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
                + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*"
                + "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}