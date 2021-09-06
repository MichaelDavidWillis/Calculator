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

import com.uk.sa.mdw.calculator.calculate.Functions;
import com.uk.sa.mdw.calculator.Init;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code SetClicks} class sets all buttons to clickable for the calculator application and
 * their calls to the {@link SetClicks} class for handling and
 * {@link com.uk.sa.mdw.calculator.calculate.Equate} for calculations.
 *
 * @author Michael David Willis
 * @version 0.3
 */
public class SetClicks extends SpecialClicks {

    public List<View> clickableViewOperations = new ArrayList<>();

    /**
     * {@code setListeners} sets the OnClickListeners to the decimal button and all numeric
     * and special buttons.
     */
    public void setListeners() {

        // Numeric buttons - NumericClicks
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
            view.setOnClickListener(view1 -> Init.getClicks().concatToCurrentNumber((Button) view1));
        }

        // Decimal button - OperationClicks
        if (!Init.getClicks().currentNumber.contains("."))
        binding.buttonDecimal.setOnClickListener(view -> {
            Init.getClicks().makeDecimal();
            view.setClickable(false);
        });

        // AC button functionality - SpecialClicks
        binding.buttonAllCancel.setOnClickListener(view -> {
            setAC();
        });

        // Negative Button - SpecialClicks
        binding.buttonNegative.setOnClickListener(view -> {
            setNegative();
        });

    }

    /**
     * {@code setOperations} sets the OnClickListeners to currently implemented operational
     * buttons, except the decimal button, which is implemented in {@code setListeners}
     * <br>
     * Each is added to an ArrayList, clickableViewOperations, and then a for loop is used to
     * set the listeners to each in turn. Also sets the equals button if conditions are met.
     */
    public void setOperations() {

        // Operation buttons
        clickableViewOperations.add(binding.buttonPlus);
        clickableViewOperations.add(binding.buttonMinus);
        clickableViewOperations.add(binding.buttonDivide);
        clickableViewOperations.add(binding.buttonMultiply);

        for (View view : clickableViewOperations) {
            view.setOnClickListener(view1 -> Init.getClicks().operationHandler(view1));
        }
        // Equals button
        if (Init.getClicks().values.size() > 1
                || Init.getClicks().values.size() > 0 && Init.getClicks().currentNumber.length() > 0) {
            binding.buttonEquals.setOnClickListener(view -> Init.getEquate().totalAnswer());
        }
    }
}