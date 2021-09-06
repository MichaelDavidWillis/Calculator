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

import android.widget.Button;

import com.uk.sa.mdw.calculator.Init;
import com.uk.sa.mdw.calculator.UpdateDisplays;
import com.uk.sa.mdw.calculator.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code NumericClicks} class defines numerical button click operations of the calculator
 * application. As the base class of {@link SetClicks}, all variables are created here to
 * ensure they exist before any buttons are assigned.
 *
 * @version 0.3
 * @author Michael David Willis
 */
public class NumericClicks implements UpdateDisplays {

    // Controls the total sum displayed
    public StringBuilder sumToCalculate = new StringBuilder("");
    // Controls the current number being defined
    public String currentNumber = "";
    // List of user defined stored numbers
    public List<Number> values = new ArrayList<>();
    // List of user defined stored operators
    public List<String> operators = new ArrayList<>();
    // Defines which method to use in Equate
    public static boolean isDecimal;

    /**
     * {@code binding} defines a {@link ActivityMainBinding} object for storing all buttons for
     * quicker calling at run-time.
     */
    public ActivityMainBinding binding;

    /**
     * {@code concatToSum} concatenates the digit represented buttonPressed to both sumToCalculate
     * (variable that stores the full calculation to display) and currentNumber (variable used to
     * store the current number made from by user), then calls setOperations from
     * {@link SetClicks}.
     *
     * @param buttonPressed the numeric button pressed to call the command
     */
    public void concatToCurrentNumber(Button buttonPressed) {
        // add digit to number
        sumToCalculate.append(buttonPressed.getText().toString());
        currentNumber += buttonPressed.getText().toString();
        // display the sum so far
        updateDisplay(Init.getClicks().binding.currentSum, sumToCalculate.toString());
        // set listeners on the operational buttons
        Init.getClicks().setOperations();
    }
}
