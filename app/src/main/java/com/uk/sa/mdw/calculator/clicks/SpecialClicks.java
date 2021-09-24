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

import androidx.annotation.RequiresApi;

import com.uk.sa.mdw.calculator.state.Init;
import com.uk.sa.mdw.calculator.state.PercentageHolder;

/**
 * {@code SpecialClicks} class defines special button click operations of the calculator
 * application.
 *
 * @version 0.5
 * @author Michael David Willis
 */
public class SpecialClicks extends OperationClicks {

    /**
     * {@code setNegative} changes currentNumber to a negative or positive, depending on it's
     * current state.
     */
    void setNegative(){
        // find last index of currentNumber
        int startOfCurrentNumber = sumToCalculate.lastIndexOf(holder.currentNumber);
        // check if sumToCalculate is empty or already a negative
        if (holder.currentNumber.length() == 0
                || holder.currentNumber.charAt(0) != '-') {
            // add "-" to both sumToCalculate & currentNumber
            sumToCalculate.insert(startOfCurrentNumber, "-");
            holder.currentNumber = "-" + holder.currentNumber;
        } else {
            // take off "-" from both sumToCalculate & currentNumber
            sumToCalculate.delete(startOfCurrentNumber, startOfCurrentNumber + 1);
            holder.currentNumber = holder.currentNumber.substring(1);
        }
        // update displays
        updateDisplay(Init.getClicks().binding.currentSum, sumToCalculate.toString());
    }

    /**
     * {@code setAC} clears the calculators current state.
     */
    void setAC(){
        // clear the lists and variables
        Init.getFun().clearLists();
        // update the displays
        updateDisplay(binding.currentSum, sumToCalculate.toString());
        updateDisplay(binding.calculatorDisplay, "");
    }

    /**
     * {@code setSquare} multiplies the currentNumber by itself.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    void setSquare(){
        // Add ² to sumToCalculate and display
        sumToCalculate.append("²");
        updateDisplay(binding.currentSum, sumToCalculate.toString());
        // add currentNumber to values
        Init.getFun().addNumberToList(holder);
        // add "×" to operators
        holder.operators.add("×");
    }

    /**
     * {@code setRoot} finds the root of the {@link com.uk.sa.mdw.calculator.state.Holder}'s
     * {@code currentNumber}
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    void setRoot(){
        int sumLength = sumToCalculate.length();
        sumToCalculate.delete( sumLength - holder.currentNumber.length(), sumLength);
        // find square root of currentNumber
        double rt = Math.sqrt(Double.parseDouble(holder.currentNumber));
        holder.currentNumber = Double.toString(rt);
        sumToCalculate.append(holder.currentNumber);
        Init.getFun().addNumberToList(holder);
        updateDisplay(binding.currentSum, sumToCalculate.toString());
    }

    /**
     * {@code setPercentage} finds the percent given of the next number entered by the user.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setPercentage(){
        // change to a new holder
        PercentageHolder newHolder = new PercentageHolder();
        newHolder.values.add(0, Double.parseDouble(holder.currentNumber));
        newHolder.operators.add("×");
        holder.currentNumber = "";
        holders.addLast(holder);
        Log.d("HOLDER CREATION", "" + holders.size());
        holder = newHolder;
        // sumToDisplay becomes "currentNumber% of "
        sumToCalculate.append(holder.currentNumber).append("% of ");
        updateDisplay(binding.currentSum, sumToCalculate.toString());
    }

    /**
     * {@code setMemoryStore} saves the {@link com.uk.sa.mdw.calculator.state.Holder}'s current
     * number to the {@link ClicksBase}'s variable {@code storedNumber} to recall at any time.
     */
    void setMemoryStore(){
        // save currentNumber to memory
        storedNumber = holder.currentNumber;
    }

    /**
     * {@code setMemoryRecall} recalls the storedNumber from the {@link ClicksBase} object and
     * sets it to the current {@link com.uk.sa.mdw.calculator.state.Holder}'s
     * {@code currentNumber}.
     */
    void setMemoryRecall(){
        // make currentNumber equal to the number saved in memory
        holder.currentNumber += storedNumber;
        sumToCalculate.append(storedNumber);
        // update the displays
        updateDisplay(binding.currentSum, sumToCalculate.toString());
    }
}

