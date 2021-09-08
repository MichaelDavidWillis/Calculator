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
package com.uk.sa.mdw.calculator.calculate;

import android.util.Log;

import com.uk.sa.mdw.calculator.Holder;
import com.uk.sa.mdw.calculator.Init;
import com.uk.sa.mdw.calculator.UpdateDisplays;
import com.uk.sa.mdw.calculator.clicks.NumericClicks;

/**
 * {@code Functions} class handles common functionalities of the calculator application.
 *
 * @version 0.4
 * @author Michael David Willis
 */

public class Functions implements UpdateDisplays {

    /**
     * {@code clearLists} clears the list and variables stored, sets isDecimal to false and makes
     * buttonDecimal and buttonParentheses clickable
     */
    public void clearLists() {

        // Clear Holders
        Init.getClicks().holders.clear();
        Init.getClicks().holder = new Holder();
        // Clear parentheses counter
        Init.getClicks().parenthesesOpen = 0;
        // Clear display
        Init.getClicks().sumToCalculate = new StringBuilder();

        // Reset Decimal Button
        Init.getClicks().binding.buttonDecimal.setClickable(true);
        // Reset Parentheses Button
        Init.getClicks().binding.buttonParentheses.setClickable(true);
    }

    /**
     * {@code addNumberToList} checks if {@code currentNumber} contains a decimal point, if
     * so it adds the number as a {@link Double} and sets the boolean {@code isDecimal} to true
     * (this is the value that controls which method to use in {@link Equate}).
     * <br>
     * It also makes a {@link Log} to show which was used for debugging purposes.
     */
    public void addNumberToList(Holder holder){
        if (holder.currentNumber.contains(".")){
            holder.values.add(Double.parseDouble(holder.currentNumber));
            holder.isDecimal = true;
            Log.d("CREATION", "decimal " + holder.currentNumber);
        } else {
            holder.values.add(Integer.parseInt(holder.currentNumber));
            Log.d("CREATION", "non-decimal");
        }
    }

}
