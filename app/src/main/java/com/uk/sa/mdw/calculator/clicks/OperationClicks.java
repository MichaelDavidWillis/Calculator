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

import android.view.View;
import android.widget.Button;

import com.uk.sa.mdw.calculator.Init;

/**
 * {@code OperationClicks} class defines operational button click operations of the calculator
 * application.
 *
 * @version 0.4
 * @author Michael David Willis
 */
public class OperationClicks extends NumericClicks {

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
        Init.getFun().addNumberToList(holder);

        // clear currentNumber to save the next number
        holder.currentNumber = "";
        // add the operator symbol to the display
        sumToCalculate.append(((Button) view).getText().toString());
        updateDisplay(Init.getClicks().binding.currentSum, sumToCalculate.toString());
        // turn off all operator buttons
        for (View view1 : Init.getClicks().clickableViewOperations) {
            view1.setClickable(false);
        }
        // add operator to the list of operators
        holder.operators.add(((Button) view).getText().toString());
        // make decimal button clickable
        Init.getClicks().binding.buttonDecimal.setClickable(true);
        Init.getClicks().binding.buttonParentheses.setClickable(true);

    }
}
