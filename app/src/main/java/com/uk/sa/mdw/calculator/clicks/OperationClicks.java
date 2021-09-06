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
package com.uk.sa.mdw.calculator.clicks;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.uk.sa.mdw.calculator.Init;

/**
 * {@code OperationClicks} class defines operational button click operations of the calculator
 * application.
 *
 * @version 0.3
 * @author Michael David Willis
 */
public class OperationClicks extends NumericClicks {
    /**
     * {@code makeDecimal} checks if {@code currentNumber} is empty, adding "0." if so or "."
     * if not to both {@code sumToCalculate} and {@code currentNumber} and updates the display.
     */
    public void makeDecimal() {
        // add digit to number
        if (currentNumber.length() == 0){
            sumToCalculate.append("0.");
            currentNumber += "0.";
            Log.d("CREATION", "length 0");
        }
        else {
            sumToCalculate.append(".");
            currentNumber += ".";
            Log.d("CREATION", "length > 0");
        }
        // display the sum so far in sumDisplay
        updateDisplay(Init.getClicks().binding.currentSum, sumToCalculate.toString());
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
    public void operationHandler(View view) {
        // save the number
        Init.getFun().addNumberToList();

        // clear currentNumber to save the next number
        currentNumber = "";
        // add the operator symbol to the display
        sumToCalculate.append(((Button) view).getText().toString());
        updateDisplay(Init.getClicks().binding.currentSum, sumToCalculate.toString());
        // turn off all operator buttons
        for (View view1 : Init.getClicks().clickableViewOperations) {
            view1.setClickable(false);
        }
        // add operator to the list of operators
        operators.add(((Button) view).getText().toString());
        // make decimal button clickable
        Init.getClicks().binding.buttonDecimal.setClickable(true);

    }
}
