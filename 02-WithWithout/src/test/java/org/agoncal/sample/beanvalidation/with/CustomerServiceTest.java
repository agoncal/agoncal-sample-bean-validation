package org.agoncal.sample.beanvalidation.with;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.*;
import java.util.Set;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class CustomerServiceTest {

    // ======================================
    // =             Attributes             =
    // ======================================

    protected static ValidatorFactory vf;
    protected static Validator validator;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void init() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    // ======================================
    // =              Methods               =
    // ======================================

    @Test
    public void shouldValidateCustomer() {
        Customer customer = new Customer("John", "Smith", "john.smith@foo.com", "john.recovery@foo.com", "+1 234 567 890", "1970-01-01");
        new CustomerService().createCustomer(customer);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldRaiseExceptionCauseFirstNameIsNull() {
        Customer customer = new Customer(null, "Smith", "john.smith@foo.com", "john.recovery@foo.com", "+1 234 567 890", "1970-01-01");
        new CustomerService().createCustomer(customer);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldRaiseExceptionCauseEmailsAreInvalid() {
        Customer customer = new Customer("John", "Smith", "john.smith.foo.com", "john.recovery.foo.com", "+1 234 567 890", "1970-01-01");
        new CustomerService().createCustomer(customer);
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldRaiseExceptionCauseDateOfBirthIsInTheFuture() {
        Customer customer = new Customer("John", "Smith", "john.smith@foo.com", "john.recovery@foo.com", "+1 234 567 890", "2022-01-01");
        new CustomerService().createCustomer(customer);
    }
}