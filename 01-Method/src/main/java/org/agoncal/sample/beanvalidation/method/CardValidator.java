package org.agoncal.sample.beanvalidation.method;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */
public class CardValidator {

    // ======================================
    // =           Public Methods           =
    // ======================================

    @AssertTrue
    public Boolean validate(@NotNull @Valid CreditCard creditCard) {

        Character lastDigit = creditCard.getNumber().charAt(creditCard.getNumber().length() - 1);

        if (Integer.parseInt(lastDigit.toString()) % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}