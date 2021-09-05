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
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code Functions} class handles the functionality of the calculator application.
 *
 * @version 0.2
 * @author Michael David Willis
 */

public class Functions implements UpdateDisplays {

    String sumToCalculate = "";
    String currentNumber = "";
    List<Number> values = new ArrayList<>();
    List<String> operators = new ArrayList<>();
    static boolean isDecimal;

    /**
     * {@code concatToSum} concatenates the digit represented buttonPressed to both sumToCalculate
     * (variable that stores the full calculation to display) and currentNumber (variable used to
     * store the current number made from by user), then calls setOperations from
     * {@link SetClicks}.
     *
     * @param buttonPressed the numeric button pressed to call the command
     */
    void concatToCurrentNumber(Button buttonPressed) {
        // add digit to number
        sumToCalculate += buttonPressed.getText().toString();
        currentNumber += buttonPressed.getText().toString();
        // display the sum so far
        updateDisplay(Init.getClicks().binding.currentSum, sumToCalculate);
        // set listeners on the operational buttons
        Init.getClicks().setOperations();
    }

    /**
     * {@code makeDouble} checks if {@code currentNumber} is empty, adding "0." if so or "."
     * if not to both {@code sumToCalculate} and {@code currentNumber} and updates the display.
     */
    void makeDecimal() {
        // add digit to number
        if (currentNumber.length() == 0){
            sumToCalculate += "0.";
            currentNumber += "0.";
            Log.d("CREATION", "length 0");
        }
        else {
            sumToCalculate += ".";
            currentNumber += ".";
            Log.d("CREATION", "length > 0");
        }
        // display the sum so far in sumDisplay
        updateDisplay(Init.getClicks().binding.currentSum, sumToCalculate);
    }

    /**
     * Once a new number has been set and stored by pressing a operational button,
     * {@code operationHandler} is called.
     * <br>
     * Calls {@code addNumberToList}, clears {@code currentNumber}, set operator buttons
     * to unclickable, adds the operator pressed to sumToCalculate, updates the display,
     * adds the operator to the ArrayList {@code operators} and makes buttonDecimal clickable.
     *
     * @param view the operation button pressed to call the command
     */
    void operationHandler(View view) {
        // save the number
        addNumberToList();

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
        Init.getClicks().binding.buttonDecimal.setClickable(true);

    }



    /**
     * {@code clearLists} clears the list and variables stored, sets isDecimal to false and makes
     * buttonDecimal clickable
     */
    void clearLists() {

        values.clear();
        operators.clear();
        currentNumber = "";
        sumToCalculate = "";
        Functions.isDecimal = false;

        Init.getClicks().binding.buttonDecimal.setClickable(true);
    }

    /**
     * {@code addNumberToList} checks if {@code currentNumber} contains a decimal point, if
     * so it adds the number as a {@link Double} and sets the boolean {@code isDecimal} to true
     * (this is the value that controls which method to use in {@link Equate}).
     * <br>
     * It also makes a {@link Log} to show which was used for debugging purposes.
     */
    void addNumberToList(){
        if (currentNumber.contains(".")){
            values.add(Double.parseDouble(currentNumber));
            isDecimal = true;
            Log.d("CREATION", "decimal " + currentNumber);
        } else {
            values.add(Integer.parseInt(currentNumber));
            Log.d("CREATION", "non-decimal");
        }
    }

}
