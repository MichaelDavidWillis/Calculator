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

import com.uk.sa.mdw.calculator.Init;
import com.uk.sa.mdw.calculator.UpdateDisplays;
import com.uk.sa.mdw.calculator.clicks.NumericClicks;

/**
 * {@code Functions} class handles common functionalities of the calculator application.
 *
 * @version 0.3
 * @author Michael David Willis
 */

public class Functions implements UpdateDisplays {

    /**
     * {@code clearLists} clears the list and variables stored, sets isDecimal to false and makes
     * buttonDecimal clickable
     */
    public void clearLists() {

        Init.getClicks().values.clear();
        Init.getClicks().operators.clear();
        Init.getClicks().currentNumber = "";
        Init.getClicks().sumToCalculate = new StringBuilder();
        NumericClicks.isDecimal = false;

        Init.getClicks().binding.buttonDecimal.setClickable(true);
    }

    /**
     * {@code addNumberToList} checks if {@code currentNumber} contains a decimal point, if
     * so it adds the number as a {@link Double} and sets the boolean {@code isDecimal} to true
     * (this is the value that controls which method to use in {@link Equate}).
     * <br>
     * It also makes a {@link Log} to show which was used for debugging purposes.
     */
    public void addNumberToList(){
        if (Init.getClicks().currentNumber.contains(".")){
            Init.getClicks().values.add(Double.parseDouble(Init.getClicks().currentNumber));
            NumericClicks.isDecimal = true;
            Log.d("CREATION", "decimal " + Init.getClicks().currentNumber);
        } else {
            Init.getClicks().values.add(Integer.parseInt(Init.getClicks().currentNumber));
            Log.d("CREATION", "non-decimal");
        }
    }

}
