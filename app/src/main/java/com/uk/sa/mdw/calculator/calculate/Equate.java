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

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.uk.sa.mdw.calculator.state.Holder;
import com.uk.sa.mdw.calculator.state.Init;
import com.uk.sa.mdw.calculator.state.PercentageHolder;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * {@code Equate} class handles the calculations of the calculator application.
 *
 * @version 0.5
 * @author Michael David Willis
 */
public class Equate {

    public Holder holder;

    /**
     * {@code getNumber} is a simple method made to make code more readable.
     * <p>
     * Returns a Number from the ArrayList {@code values} from {@link Functions}
     */
    private Number getNumber(int position){
        // Ensure this holder is pointing at the current holder in the Clicks object
        holder = Init.getClicks().holder;
        return holder.values.get(position);
    }

    /**
     * {@code totalAnswer} adds the currentNumber to the ArrayList {@code values} and decides
     * whether to use {@code equateDecimal} or {@code equateInteger} based on whether a decimal
     * number has been entered into the List {@code values} or it is a divide calculation and
     * how to handle closing parentheses
     * <br>
     * It also makes a {@link Log} of which was used for debugging purposes.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void totalAnswer() {
        // Ensure this holder is pointing at the current holder in the Clicks object
        holder = Init.getClicks().holder;
        // complete to sum and display
        Init.getClicks().sumToCalculate.append("=");
        Init.getFun().updateDisplay(Init.getClicks().binding.currentSum, Init.getClicks().sumToCalculate.toString());

        // Number to store either integer or double value
        Number answer;
        if (holder.isDecimal || holder.operators.contains("÷")) {
            Log.d("EQUATION", "Decimal");
            answer = equateDecimal();
        } else {
            Log.d("EQUATION", "Integer");
            answer = equateInteger();
        }

        if (answer.toString().contains("E") || answer.toString().endsWith(".0")){
            answer = answer.intValue();
        }

        // How to handle holders of parentheses calculations
        if (Init.getClicks().parenthesesOpen == 0) {
            finishSum(answer);
            Init.getClicks().binding.buttonParentheses.setClickable(false);
        } else {
            Log.d("EQUATION", answer.toString());
            Init.getClicks().closeParentheses(answer.toString());
        }
    }

    /**
     * {@code equateInteger} is the method called to calculate integer values.
     * <br>
     * {@code equateInteger} is included in the code to minimize the overkill of using
     * {@code BigDecimal} when not needed.
     * <br>
     * Calls the numbers from the ArrayList {@code values} and the operators
     * from the ArrayList {@code operations}, using a switch statement to determine the operation.
     * <br>
     * This currently operates left to right of the sum, although I do want to implement the
     * correct mathematical order of operations later.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Number equateInteger(){
        // Ensure this holder is pointing at the current holder in the Clicks object
        holder = Init.getClicks().holder;

        Integer currentIntAnswer = (Integer) getNumber(0);
        // iterate through the operators list
        for (int i = 0; i < holder.operators.size(); i++) {

            // get the next number in the list to operate on
            Integer b = (Integer) getNumber(i + 1);

            // Determine the operator and sum accordingly
            switch (holder.operators.get(i)) {
                case "+":
                    try {
                        Math.addExact(currentIntAnswer, b);
                        currentIntAnswer += b;
                    } catch (ArithmeticException e){
                        String eMessage = "Exceeds limit";
                        Init.getClicks().updateDisplay(Init.getClicks().binding.calculatorDisplay,
                                eMessage);
                    }
                    break;
                case "-":
                    try {
                        Math.subtractExact(currentIntAnswer, b);
                        currentIntAnswer -= b;
                    } catch (ArithmeticException e){
                        String eMessage = "Exceeds limit";
                        Init.getClicks().updateDisplay(Init.getClicks().binding.calculatorDisplay,
                                eMessage);
                    }
                    break;
                case "÷":
                    try {
                        currentIntAnswer /= b;
                    } catch (ArithmeticException e){
                        String eMessage = "Cannot divide by 0";
                        Init.getClicks().updateDisplay(Init.getClicks().binding.calculatorDisplay,
                                eMessage);
                    }
                    break;
                case "×":
                    try {
                        Math.multiplyExact(currentIntAnswer, b);
                        currentIntAnswer *= b;
                    } catch (ArithmeticException e){
                        String eMessage = "Exceeds limit";
                        Init.getClicks().updateDisplay(Init.getClicks().binding.calculatorDisplay,
                                eMessage);
                    }
                    break;
            }
        }
        return currentIntAnswer;
    }

    /**
     * {@code equateDecimal} is the method called to calculate decimal values.
     * <br>
     * {@link BigDecimal} is used to represent the values and it's methods used to calculate
     * the answer.
     * <br>
     * Calls the numbers from the ArrayList {@code values} and the operators
     * from the ArrayList {@code operations}, using a switch statement to determine the operation.
     * <br>
     * This currently operates left to right of the sum, although I do want to implement the
     * correct mathematical order of operations later.
     */
    public Number equateDecimal() {
        // Ensure this holder is pointing at the current holder in the Clicks object
        holder = Init.getClicks().holder;

        BigDecimal currentDecimalAnswer = BigDecimal.valueOf(Double.parseDouble(getNumber(0).toString()));
        // iterate through the operators list
        for (int i = 0; i < holder.operators.size(); i++) {

            // get the next number in the list to operate on
            BigDecimal b = BigDecimal.valueOf(Double.parseDouble(getNumber(i + 1).toString()));

            // Determine the operator and sum accordingly
            switch (holder.operators.get(i)) {
                case "+":
                    currentDecimalAnswer = currentDecimalAnswer.add(b);
                    break;
                case "-":
                    currentDecimalAnswer = currentDecimalAnswer.subtract(b);
                    break;
                case "÷":
                    currentDecimalAnswer = currentDecimalAnswer.divide(b, 7, RoundingMode.HALF_UP);
                    break;
                case "×":
                    currentDecimalAnswer = currentDecimalAnswer.multiply(b);
                    break;
            }
        }
        return BigDecimal.valueOf(currentDecimalAnswer.stripTrailingZeros().doubleValue());
    }

    /**
     * {@code finishSum} is a method made to avoid repeated code.
     * <br>
     * Updates the display to show answer, make equals unclickable and clears stored lists
     * and variables, adds the answer to sumToCalculate & currentNumber and checks if to make
     * decimalButton clickable (only if answer is not already a decimal number).
     */
    private void finishSum(Number answer){
        // get the currentAnswer as a String to display
        String toReturn = String.valueOf(answer);
        Init.getFun().updateDisplay(Init.getClicks().binding.calculatorDisplay, toReturn);

        // make equals button un-clickable until required arguments are met
        Init.getClicks().binding.buttonEquals.setClickable(false);
        if (!(holder instanceof PercentageHolder)){
            Init.getFun().clearLists();
        }

        // make both variables equal to the answer
        Init.getClicks().sumToCalculate = new StringBuilder(toReturn);

        Log.d("HOLDER CREATION", "" + Init.getClicks().holders.size());

        if (holder instanceof PercentageHolder){
            holder = Init.getClicks().holders.getLast();
            Init.getClicks().holder.currentNumber = toReturn;
        } else {
            Init.getClicks().holder.currentNumber = toReturn;
        }

        // Only make decimalButton clickable if answer is not already a decimal number
        Init.getClicks().binding.buttonDecimal.setClickable(!holder.currentNumber.contains("."));
    }

}
