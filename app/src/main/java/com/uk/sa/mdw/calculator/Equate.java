/*
 * Copyright (C) 2021
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.uk.sa.mdw.calculator;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * {@code Equate} class handles calculations of the calculator application.
 *
 * @version 0.2
 * @author Michael David Willis
 */
public class Equate {

    /**
     * {@code getNumber} is a simple method made to make code more readable.
     * <p>
     * Returns a Number from the ArrayList {@code values} from {@link Functions}
     */
    private Number getNumber(int position){
        return Init.getFun().values.get(position);
    }

    /**
     * {@code totalAnswer} adds the currentNumber to the {@code ArrayList} values and decides
     * whether to use {@code equateDecimal} or {@code equateInteger} based on whether a decimal
     * number has been entered into the List {@code values}.
     * <br>
     * It also makes a {@link Log} of which was used for debugging purposes.
     */
    void totalAnswer() {
        // save the number
        Init.getFun().addNumberToList();
        // complete to sum and display
        Init.getFun().sumToCalculate += "=";
        Init.getFun().updateDisplay(Init.getClicks().binding.currentSum, Init.getFun().sumToCalculate);

        // make current answer the first value in values list
        // Need way to make currentNumber a Integer or a Double

        Number answer;
        if (Functions.isDecimal || Init.getFun().operators.contains("÷")) {
            Log.d("CREATION", "Double");
            answer = equateDecimal();
        } else {
            Log.d("CREATION", "Integer");
            answer = equateInteger();
        }
        finishSum(answer);
    }

    /**
     * {@code equateInteger} is the method called to calculate integer values.
     * <br>
     * {@code equateInteger} is included in the code to minimize the overkill of using
     * {@code BigDecimal} when not needed.
     * <br>
     * calls the numbers from the ArrayList {@code values} and the operators
     * from the ArrayList {@code operations}, using a switch statement to determine the operation.
     * <br>
     * This currently operates left to right of the sum, although I do want to implement the
     * correct mathematical order of operations later.
     */
    Number equateInteger(){

        Integer currentIntAnswer = (Integer) getNumber(0);
        // iterate through the operators list
        for (int i = 0; i < Init.getFun().operators.size(); i++) {

            // get the next number in the list to operate on
            Integer b = (Integer) getNumber(i + 1);

            // Determine the operator and sum accordingly
            switch (Init.getFun().operators.get(i)) {
                case "+":
                    currentIntAnswer += b;
                    break;
                case "-":
                    currentIntAnswer -= b;
                    break;
                case "÷":
                    currentIntAnswer /= b;
                    break;
                case "×":
                    currentIntAnswer *= b;
                    break;
            }
        }
        return currentIntAnswer;
    }

    /**
     * {@code equateDecimal} is the method called to calculate decimal values.
     * <br>
     * {@link BigDecimal} is used to represent the values and it's methods used to calculate
     * the answer.
     * <br>
     * Calls the numbers from the ArrayList {@code values} and the operators
     * from the ArrayList {@code operations}, using a switch statement to determine the operation.
     * <br>
     * This currently operates left to right of the sum, although I do want to implement the
     * correct mathematical order of operations later.
     */
    Number equateDecimal() {

        BigDecimal currentDecimalAnswer = BigDecimal.valueOf(Double.parseDouble(getNumber(0).toString()));
        // iterate through the operators list
        for (int i = 0; i < Init.getFun().operators.size(); i++) {

            // get the next number in the list to operate on
            BigDecimal b = BigDecimal.valueOf(Double.parseDouble(getNumber(i + 1).toString()));

            // Determine the operator and sum accordingly
            switch (Init.getFun().operators.get(i)) {
                case "+":
                    currentDecimalAnswer = currentDecimalAnswer.add(b);
                    break;
                case "-":
                    currentDecimalAnswer = currentDecimalAnswer.subtract(b);
                    break;
                case "÷":
                    currentDecimalAnswer = currentDecimalAnswer.divide(b, 7, RoundingMode.HALF_UP);
                    break;
                case "×":
                    currentDecimalAnswer = currentDecimalAnswer.multiply(b);
                    break;
            }
        }
       return currentDecimalAnswer.stripTrailingZeros();
    }

    /**
     * {@code finishSum} is a method made to avoid repeated code.
     * <br>
     * Updates the display to show answer, make equals unclickable and clears stored lists
     * and variables, adds the answer to sumToCalculate & currentNumber and checks if to make
     * decimalButton clickable (only if answer is not already a decimal number).
     */
    private void finishSum(Number answer){
        // get the currentAnswer as a String to display
        String toReturn = String.valueOf(answer);
        Init.getFun().updateDisplay(Init.getClicks().binding.calculatorDisplay, toReturn);

        // make equals button un-clickable until required arguments are met
        Init.getClicks().binding.buttonEquals.setClickable(false);
        Init.getFun().clearLists();

        // make both variables equal to the answer
        Init.getFun().sumToCalculate = Init.getFun().currentNumber = toReturn;

        // Only make decimalButton clickable if answer is not already a decimal number
        if (!Init.getFun().currentNumber.contains(".")) {
            Init.getClicks().binding.buttonDecimal.setClickable(true);
        } else {
            Init.getClicks().binding.buttonDecimal.setClickable(false);
        }
    }

}
