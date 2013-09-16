package org.agoncal.sample.beanvalidation.without;

import org.junit.Test;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class CustomerServiceTest {

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldValidateCustomer() {
        Customer customer = new Customer("John", "Smith", "john.smith@foo.com", "john.recovery@foo.com", "+1 234 567 890", "1970-01-01");
        new CustomerService().createCustomer(customer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRaiseConstraintViolationCauseFirstNameIsNull() {
        Customer customer = new Customer(null, "Smith", "john.smith@foo.com", "john.recovery@foo.com", "+1 234 567 890", "1970-01-01");
        new CustomerService().createCustomer(customer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRaiseConstraintViolationCauseEmailsAreInvalid() {
        Customer customer = new Customer("John", "Smith", "john.smith.foo.com", "john.recovery.foo.com", "+1 234 567 890", "1970-01-01");
        new CustomerService().createCustomer(customer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRaiseConstraintViolationCauseDateOfBirthIsInTheFuture() {
        Customer customer = new Customer("John", "Smith", "john.smith@foo.com", "john.recovery@foo.com", "+1 234 567 890", "2022-01-01");
        new CustomerService().createCustomer(customer);
    }

    @Test
    public void shouldValidateCustomerFirstName() {
        Customer customer = new Customer();
        CustomerService customerService = new CustomerService();

        // Firstname is null
        try {
            customerService.createCustomer(customer);
        } catch (IllegalArgumentException e) {
        }

        // Firstname is < 4
        try {
            customer.setFirstName("Bob");
            customerService.createCustomer(customer);
        } catch (IllegalArgumentException e) {
        }

        // Firstname is > 50
        try {
            customer.setFirstName("BobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBobBob");
            customerService.createCustomer(customer);
        } catch (IllegalArgumentException e) {
        }

        // Firstname is Ok
        customer.setFirstName("Roger");

        // Primary email is null
        try {
            customerService.createCustomer(customer);
        } catch (IllegalArgumentException e) {
        }

        // Primary email is invalid
        try {
            customer.setPrimaryEmail("invalidemail");
            customerService.createCustomer(customer);
        } catch (IllegalArgumentException e) {
        }

        // Primary email is Ok
        customer.setPrimaryEmail("valid@email.com");

        // Recovery email is invalid
        try {
            customer.setRecoveryEmail("invalid.email");
            customerService.createCustomer(customer);
        } catch (IllegalArgumentException e) {
        }

        // Recovery email is Ok
        customer.setRecoveryEmail("valid@email.com");

        // Date of birth is in the future
        try {
            customer.setDateOfBirth("2022-01-01");
            customerService.createCustomer(customer);
        } catch (IllegalArgumentException e) {
        }

        // Date of birth is in the past
        customer.setDateOfBirth("2012-01-01");
        customerService.createCustomer(customer);
    }
}