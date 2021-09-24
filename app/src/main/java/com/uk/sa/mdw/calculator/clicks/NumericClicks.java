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
import android.util.Log;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.uk.sa.mdw.calculator.state.Holder;
import com.uk.sa.mdw.calculator.state.Init;

/**
 * {@code NumericClicks} class defines numerical button click operations of the calculator
 * application.
 *
 * @version 0.5
 * @author Michael David Willis
 */
public class NumericClicks extends ClicksBase{
    /**
     * {@code concatToSum} concatenates the digit represented buttonPressed to both sumToCalculate
     * (variable that stores the full calculation to display) and currentNumber (variable used to
     * store the current number made from by user), then calls setOperations from
     * {@link SetClicks}.
     *
     * @param buttonPressed the numeric button pressed to call the command
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void concatToCurrentNumber(Button buttonPressed) {
        // add digit to number
        sumToCalculate.append(buttonPressed.getText().toString());
        holder.currentNumber += buttonPressed.getText().toString();
        // display the sum so far
        updateDisplay(Init.getClicks().binding.currentSum, sumToCalculate.toString());
        // decide to allow parentheses button to be clickable
        Init.getClicks().decideParenthesesButton();
        Init.getClicks().setSpecials();
        // set listeners on the operational buttons
        Init.getClicks().setOperations();
    }

    /**
     * {@code makeDecimal} checks if {@code currentNumber} is empty, adding "0." if so or "."
     * if not to both {@code sumToCalculate} and {@code currentNumber} and updates the display.
     */
    public void makeDecimal() {
        // add digit to number
        if (holder.currentNumber.length() == 0){
            sumToCalculate.append("0.");
            holder.currentNumber += "0.";
            Log.d("CREATION", "length 0");
        }
        else {
            sumToCalculate.append(".");
            holder.currentNumber += ".";
            Log.d("CREATION", "length > 0");
        }
        // display the sum so far in sumDisplay
        updateDisplay(Init.getClicks().binding.currentSum, sumToCalculate.toString());

        // make parentheses unclickable
        binding.buttonParentheses.setClickable(false);
    }

    /**
     * {@code parentheses} decides which method to call when parentheses button is pressed,
     * {@code openParentheses} to open a new parentheses or {@code closeParentheses} to close
     * the current parentheses.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void parentheses(){
        if (parenthesesOpen == 0                                    // counter is empty or
                || holder.values.size() == holder.operators.size()  // equal values and operators
                && holder.currentNumber.length() == 0){             // and currentNumber is empty
            openParentheses();
            binding.buttonEquals.setClickable(false);
            Log.d("PARENTHESES", "Open");
        } else {
            // Add currentNumber to the Holder ArrayList values
            Init.getFun().addNumberToList(holder);
            // Total Answer
            Init.getEquate().totalAnswer();
            Log.d("PARENTHESES", "Closed");
        }
    }

    /**
     * {@code openParentheses} handles opening parentheses, making and plugging in a new
     * {@link Holder} object for the new calculation. Each previous {@link Holder} is saved
     * in the ArrayList {@code holders}.
     */
    public void openParentheses() {
        // Add open parentheses to display
        sumToCalculate.append("(");
        // Send current Holder to be stored in ArrayList holders
        holders.addLast(holder);
        // make a new Holder
        holder = new Holder();
        // Increment parentheses counter
        parenthesesOpen++;
        // Update display
        updateDisplay(Init.getClicks().binding.currentSum, sumToCalculate.toString());
    }

    /**
     * {@code closeParentheses} handles the closing of parentheses, equating the answer of
     * the parenthesized calculation and adding it to the correct {@link Holder} from the
     * ArrayList {@code holders}.
     */
    public void closeParentheses(String answer) {
        // Get rid of = symbol added by totalAnswer
        sumToCalculate.replace(sumToCalculate.length() - 1, sumToCalculate.length(), ")");
        // Decrement parentheses counter
        parenthesesOpen--;
        // get previous Holder to be stored in ArrayList holders
        holder = holders.getLast();
        // remove that Holder from the ArrayList holders
        holders.removeLast();
        // make Holder's currentNumber equal the answer of previous Holder
        holder.currentNumber = answer;
        // Update display
        updateDisplay(Init.getClicks().binding.currentSum, sumToCalculate.toString());
        // check to set buttons
        if(parenthesesOpen == 0){
            binding.buttonEquals.setClickable(true);
        }
        binding.buttonParentheses.setClickable(parenthesesOpen > 0);
    }
}
