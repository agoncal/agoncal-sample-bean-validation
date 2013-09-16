package org.agoncal.sample.beanvalidation.with;

import javax.validation.*;
import java.util.Set;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class CustomerService {

    // ======================================
    // =             Attributes             =
    // ======================================

    private ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    private Validator validator = vf.getValidator();
    private Set<ConstraintViolation<Customer>> violations;

    // ======================================
    // =           Public Methods           =
    // ======================================

    public void createCustomer(Customer customer) {

        violations = validator.validate(customer);
        if (violations.size() > 0) throw new ConstraintViolationException(violations);

        // Customer is valid, store it into the database
    }

    public void updateCustomer(Customer customer) {

        violations = validator.validate(customer);
        if (violations.size() > 0) throw new ConstraintViolationException(violations);

        // Customer is valid, update it into the database
    }
}