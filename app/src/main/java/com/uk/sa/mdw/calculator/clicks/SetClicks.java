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

import android.os.Build;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.uk.sa.mdw.calculator.state.Init;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code SetClicks} class sets all buttons to clickable for the calculator application and
 * their calls to the {@link SetClicks} class for handling and
 * {@link com.uk.sa.mdw.calculator.calculate.Equate} for calculations.
 *
 * @author Michael David Willis
 * @version 0.5
 */
public class SetClicks extends SpecialClicks {

    public List<View> clickableViewOperations = new ArrayList<>();

    /**
     * {@code setListeners} sets the OnClickListeners to the decimal button and all numeric
     * and special buttons.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setNumerics() {

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

        // Decimal button - NumericClicks
        if (!holder.currentNumber.contains(".")) {   // check if currentNumber is already a decimal
            binding.buttonDecimal.setOnClickListener(view -> {
                Init.getClicks().makeDecimal();
                view.setClickable(false);
            });
        }

        // Parentheses Button - NumericClicks
        binding.buttonParentheses.setOnClickListener(view -> parentheses());
    }

    /**
     * {@code setOperations} sets the OnClickListeners to currently implemented operational
     * buttons, except the decimal button, which is implemented in {@code setListeners}
     * <br>
     * Each is added to an ArrayList, clickableViewOperations, and then a for loop is used to
     * set the listeners to each in turn. Also sets the equals button if conditions are met.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
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
        if (holder.values.size() > 1                    // 2 or more numbers in values
                || holder.values.size() > 0             // or 1 number in values and
                && holder.currentNumber.length() > 0) { // there is a number in currentNumber
            binding.buttonEquals.setOnClickListener(view -> {
                Init.getFun().addNumberToList(holder);
                Init.getEquate().totalAnswer();
            });
        }

        // Percentage button
        if (holder.currentNumber.length() > 0) {
            binding.buttonPercent.setOnClickListener(view -> setPercentage());
        }

        // Square Root button
        if (holder.currentNumber.length() > 0) {
            binding.buttonRoot.setOnClickListener(view -> setRoot());
        }
    }

    /**
     * {@code decideParenthesesButton} is a method, called from {@link NumericClicks}
     * {@code concatToNumber} which decides whether or not to make the parentheses button
     * available to the user based on the user's input so far.
     */
    public void decideParenthesesButton(){
        // make parentheses unclickable if
        // - a parentheses is opened and an operator has yet to be entered
        // - if the last character in sumToCalculate is a number
        // - if there is no parentheses open and the last character in sumToCalculate is a number
        if (parenthesesOpen > 0                                     // parentheses is open
                && holder.operators.size() == 0                     // and no operators and
                && holder.operators.size() == holder.values.size()  // operators & values same size
                || sumToCalculate.charAt(sumToCalculate.length() - 1) > 48 // or last char in sum
                && sumToCalculate.charAt(sumToCalculate.length() - 1) < 57 // is between 0 & 9 and
                && holder.operators.size() < holder.values.size()   // more values than operators
                || parenthesesOpen == 0                                    // or parentheses closed
                && sumToCalculate.charAt(sumToCalculate.length() - 1) > 48 // and last char in sum
                && sumToCalculate.charAt(sumToCalculate.length() - 1) < 57){ // is between 0 & 9
            binding.buttonParentheses.setClickable(false);
        } else {
            binding.buttonParentheses.setClickable(true);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setSpecials(){
        // AC button functionality - SpecialClicks
        binding.buttonAllCancel.setOnClickListener(view -> setAC());

        // Square button - SpecialClicks
        binding.buttonSquare.setOnClickListener(view -> {
            setSquare();
            setOperations();
        });

        // Negative Button - SpecialClicks
        binding.buttonNegative.setOnClickListener(view -> setNegative());

        // Memory Store Button - SpecialClicks
        binding.buttonMemoryStore.setOnClickListener(view -> setMemoryStore());

        // Memory Recall Button - SpecialClicks
        binding.buttonMemoryRecall.setOnClickListener(view -> {
            setMemoryRecall();
            setOperations();
        });
    }
}