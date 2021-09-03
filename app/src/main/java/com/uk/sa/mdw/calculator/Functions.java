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

import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code Functions} class handles the functionality of the calculator application.
 *
 * @version 0.1
 * @author Michael David Willis
 */

public class Functions implements UpdateDisplays {

    String sumToCalculate = "";
    String currentNumber = "";
    // List of Numbers in order to implement Doubles later
    List<Number> values = new ArrayList<>();
    private final List<String> operators = new ArrayList<>();

    /**
     * {@code concatToSum} concatenates the digit represented buttonPressed to both sumToCalculate
     * (variable that stores the full calculation to display) and currentNumber (variable used to
     * store the current number made from by user), then calls setOperations from
     * {@link com.uk.sa.mdw.calculator.SetClicks}.
     *
     * @param buttonPressed the numeric button pressed to call the command
     */
    void concatToCurrentNumber(Button buttonPressed) {
        // add digit to number
        sumToCalculate += buttonPressed.getText().toString();
        currentNumber += buttonPressed.getText().toString();
        // display the sum so far in sumDisplay
        updateDisplay(Init.getClicks().binding.currentSum, sumToCalculate);
        // set listeners on the operational buttons
        Init.getClicks().setOperations();
    }

    /**
     * {@code operationHandler} adds
     * <br>
     * Once a new number has been set and stored by pressing a operational button,
     * {@code operationHandler} is called.
     *
     *
     * @param view the operation button pressed to call the command
     */
    void operationHandler(View view) {
        // save the number
        values.add(Integer.parseInt(currentNumber));
        // clear currentNumber to save the next number
        currentNumber = "";
        // add the operator symbol to the display
        sumToCalculate += ((Button) view).getText().toString();
        updateDisplay(Init.getClicks().binding.currentSum, sumToCalculate);
        // turn off all operator buttons
        for (View view1 : Init.getClicks().clickableViewOperations) {
            view1.setClickable(false);
        }
        operators.add(((Button) view).getText().toString());

    }

    /**
     * {@code totalAnswer} handles the call from the equals button, computing the calculation
     * and displaying the result.
     * <br>
     * {@code totalAnswer} calls the numbers from the ArrayList {@code values} and the operators
     * from the ArrayList {@code operations}, using a switch statement to determine the operation.
     *
     * This currently operates left to right of the sum, although I do want to implement the
     * correct mathematical order of operations later.
     */
    void totalAnswer() {
        // save the number
        values.add(Integer.parseInt(currentNumber));
        // complete to sum and display
        sumToCalculate += "=";
        updateDisplay(Init.getClicks().binding.currentSum, sumToCalculate);

        // make current answer the first value in values list
        int currentAnswer = (Integer) values.get(0);
        // iterate through the operators list
        for (int i = 0; i < operators.size(); i++) {

            // get the next number in the list to operate on
            Integer b = (Integer) values.get(i + 1);

            // Determine the operator and sum accordingly
            switch (operators.get(i)) {
                case "+":
                    currentAnswer += b;
                    break;
                case "-":
                    currentAnswer -= b;
                    break;
                case "÷":
                    currentAnswer /= b;
                    break;
                case "×":
                    currentAnswer *= b;
                    break;
            }
        }
        // get the currentAnswer as a String to display
        String toReturn = String.valueOf(currentAnswer);
        updateDisplay(Init.getClicks().binding.calculatorDisplay, toReturn);
        // make equals button un-clickable until required arguments are met
        Init.getClicks().binding.buttonEquals.setClickable(false);
        clearLists();
        // make both variables equal to the answer
        sumToCalculate = currentNumber = toReturn;
    }

    /**
     * {@code clearLists} clears the list and variables stored.
     */
    void clearLists() {
        values.clear();
        operators.clear();
        currentNumber = "";
        sumToCalculate = "";
    }

}
