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
    public void shouldRaiseExceptionCauseFirstNameIsNull() {
        Customer customer = new Customer(null, "Smith", "john.smith@foo.com", "john.recovery@foo.com", "+1 234 567 890", "1970-01-01");
        new CustomerService().createCustomer(customer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRaiseExceptionCauseEmailsAreInvalid() {
        Customer customer = new Customer("John", "Smith", "john.smith.foo.com", "john.recovery.foo.com", "+1 234 567 890", "1970-01-01");
        new CustomerService().createCustomer(customer);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldRaiseExceptionCauseDateOfBirthIsInTheFuture() {
        Customer customer = new Customer("John", "Smith", "john.smith@foo.com", "john.recovery@foo.com", "+1 234 567 890", "2022-01-01");
        new CustomerService().createCustomer(customer);
    }

}