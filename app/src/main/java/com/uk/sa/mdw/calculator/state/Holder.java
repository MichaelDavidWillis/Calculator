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
package com.uk.sa.mdw.calculator.state;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code Holder} is a simple class to make objects that hold variables that define the
 * calculation. As a separate class, {@code Holder} objects can be exchanged (or plugged and
 * unplugged) at anytime to equate calculations inside parentheses.
 *
 * @version 0.4
 * @author Michael David Willis
 */
public class Holder {

    // Controls the current number being defined
    public String currentNumber = "";
    // List of user defined stored numbers
    public List<Number> values = new ArrayList<>();
    // List of user defined stored operators
    public List<String> operators = new ArrayList<>();
    // Defines which method to use in Equate
    public boolean isDecimal;
}
