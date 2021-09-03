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

import com.uk.sa.mdw.calculator.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code SetClicks} class sets all buttons to clickable for the calculator application and
 * their calls to the {@link Functions} class for handling.
 *
 * @author Michael David Willis
 * @version 0.1
 */
public class SetClicks implements UpdateDisplays {

    /**
     * {@code binding} defines a {@link ActivityMainBinding} object for storing all buttons for
     * quicker calling at run-time.
     */
    public ActivityMainBinding binding;

    List<View> clickableViewOperations = new ArrayList<>();

    /**
     * {@code setListeners} sets the OnClickListeners to all numerical buttons.
     * <br>
     * Each numeric is added to an ArrayList, clickableViewsConcat, and then a for loop is used to
     * set the listeners to each in turn.
     * The All-Clear button (AC) is also set to clear stored variables and reset the displays.
     */
    void setListeners() {

        List<View> clickableViewsConcat = new ArrayList<>();
        clickableViewsConcat.add(binding.buttonZero);
        clickableViewsConcat.add(binding.buttonOne);
        clickableViewsConcat.add(binding.buttonTwo);
        clickableViewsConcat.add(binding.buttonThree);
        clickableViewsConcat.add(binding.buttonFour);
        clickableViewsConcat.add(binding.buttonFive);
        clickableViewsConcat.add(binding.buttonSix);
        clickableViewsConcat.add(binding.buttonSeven);
        clickableViewsConcat.add(binding.buttonEight);
        clickableViewsConcat.add(binding.buttonNine);
        clickableViewsConcat.add(binding.buttonDecimal);

        for (View view : clickableViewsConcat) {
            // on click run concatToSum method
            view.setOnClickListener(view1 -> Init.getFun().concatToCurrentNumber((Button) view1));
        }

        // AC button functionality
        binding.buttonAllCancel.setOnClickListener(view -> {
            // clear the lists and variables
            Init.getFun().clearLists();
            // update the displays
            updateDisplay(binding.currentSum, Init.getFun().sumToCalculate);
            updateDisplay(binding.calculatorDisplay, "");
        });

    }

    /**
     * {@code setOperations} sets the OnClickListeners to currently implemented operational
     * buttons.
     * <p>
     * Each is added to an ArrayList, clickableViewOperations, and then a for loop is used to
     * set the listeners to each in turn. Also sets the equals button if conditions are met.
     */
    void setOperations() {

        // 2. Set a operation
        // Set listeners
        clickableViewOperations.add(binding.buttonPlus);
        clickableViewOperations.add(binding.buttonMinus);
        clickableViewOperations.add(binding.buttonDivide);
        clickableViewOperations.add(binding.buttonMultiply);

        for (View view : clickableViewOperations) {
            // on click run operation handler
            view.setOnClickListener(view1 -> Init.getFun().operationHandler(view1));
        }
        // only make equals button available if there's sufficient arguments
        if (Init.getFun().values.size() > 1
                || Init.getFun().values.size() > 0 && Init.getFun().currentNumber.length() > 0) {
            binding.buttonEquals.setOnClickListener(view -> Init.getFun().totalAnswer());
        }
    }
}