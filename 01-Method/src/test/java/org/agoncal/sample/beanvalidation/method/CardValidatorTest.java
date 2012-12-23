package org.agoncal.sample.beanvalidation.method;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import javax.validation.*;
import java.lang.reflect.Method;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class CardValidatorTest {

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
    public void shouldRaiseNoConstraintViolationCauseCreditCardIsEven() throws NoSuchMethodException {

        CreditCard creditCard = new CreditCard("12341234", "10/10", 1234, "VISA");
        CardValidator cardValidator = new CardValidator();

        MethodValidator methodValidator = validator.forMethods();
        Method method = CardValidator.class.getMethod("validate", CreditCard.class);
        Set<ConstraintViolation<CardValidator>> constraints = methodValidator.validateParameters(cardValidator, method, new Object[]{creditCard});
        assertEquals(0, constraints.size());

        constraints = methodValidator.validateReturnValue(cardValidator, method, cardValidator.validate(creditCard));
        assertEquals(0, constraints.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseCreditCardIsOdd() throws NoSuchMethodException {

        CreditCard creditCard = new CreditCard("1234123", "10/10", 1234, "VISA");
        CardValidator cardValidator = new CardValidator();

        MethodValidator methodValidator = validator.forMethods();
        Method method = CardValidator.class.getMethod("validate", CreditCard.class);
        Set<ConstraintViolation<CardValidator>> constraints = methodValidator.validateParameters(cardValidator, method, new Object[]{creditCard});
        assertEquals(0, constraints.size());

        constraints = methodValidator.validateReturnValue(cardValidator, method, cardValidator.validate(creditCard));
        assertEquals(1, constraints.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseCreditCardIsNull() throws NoSuchMethodException {

        CardValidator cardValidator = new CardValidator();

        MethodValidator methodValidator = validator.forMethods();
        Method method = CardValidator.class.getMethod("validate", CreditCard.class);
        Set<ConstraintViolation<CardValidator>> constraints = methodValidator.validateParameters(cardValidator, method, new Object[]{null});
        displayContraintViolations(constraints);
        assertEquals(1, constraints.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseCreditCardParametersAreNull() throws NoSuchMethodException {

        CreditCard creditCard = new CreditCard(null, null, null, null);
        CardValidator cardValidator = new CardValidator();

        MethodValidator methodValidator = validator.forMethods();
        Method method = CardValidator.class.getMethod("validate", CreditCard.class);
        Set<ConstraintViolation<CardValidator>> constraints = methodValidator.validateParameters(cardValidator, method, new Object[]{creditCard});
        displayContraintViolations(constraints);
        assertEquals(3, constraints.size());
    }

    private void displayContraintViolations(Set<ConstraintViolation<CardValidator>> constraintViolations) {
        for (ConstraintViolation constraintViolation : constraintViolations) {
            System.out.println("### " + constraintViolation.getRootBeanClass().getSimpleName() +
                    "." + constraintViolation.getPropertyPath() + " - Invalid Value = " + constraintViolation.getInvalidValue() + " - Error Msg = " + constraintViolation.getMessage());

        }
    }
}