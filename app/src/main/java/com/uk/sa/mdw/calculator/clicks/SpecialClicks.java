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

import com.uk.sa.mdw.calculator.Init;

/**
 * {@code SpecialClicks} class defines special button click operations of the calculator
 * application.
 *
 * @version 0.3
 * @author Michael David Willis
 */
public class SpecialClicks extends OperationClicks {

    /**
     * {@code setNegative} changes currentNumber to a negative or positive, depending on it's
     * current state.
     */
    void setNegative(){
        // find last index of currentNumber
        int startOfCurrentNumber = Init.getClicks().sumToCalculate.lastIndexOf(Init.getClicks().currentNumber);
        // check if sumToCalculate is empty or already a negative
        if (Init.getClicks().currentNumber.length() == 0
                || Init.getClicks().currentNumber.charAt(0) != '-') {
            // add "-" to both sumToCalculate & currentNumber
            Init.getClicks().sumToCalculate.insert(startOfCurrentNumber, "-");
            Init.getClicks().currentNumber = "-" + Init.getClicks().currentNumber;
        } else {
            // take off "-" from both sumToCalculate & currentNumber
            Init.getClicks().sumToCalculate.delete(startOfCurrentNumber, startOfCurrentNumber + 1);
            Init.getClicks().currentNumber = Init.getClicks().currentNumber.substring(1);
        }
        // update displays
        updateDisplay(Init.getClicks().binding.currentSum, Init.getClicks().sumToCalculate.toString());
    }

    /**
     * {@code setAC} clears the calculators current state.
     */
    void setAC(){
        // clear the lists and variables
        Init.getFun().clearLists();
        // update the displays
        updateDisplay(binding.currentSum, Init.getClicks().sumToCalculate.toString());
        updateDisplay(binding.calculatorDisplay, "");
    }
}

