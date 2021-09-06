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

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testing for each of the implemented operations in both integer & decimal calculations.
 *
 * @version 0.3
 * @author Michael David Willis
 */
public class CalculationTests {

    private final int a = 5, b = 2;
    private final double c = 3.3, d = 1.6;

    @Test
    public void integerAdditionIsCorrect() {

        Init.getClicks().values.add(a);
        Init.getClicks().values.add(b);
        Init.getClicks().operators.add("+");

        assertEquals("Addition Integer",7, Init.getEquate().equateInteger());
        Init.getClicks().operators.clear();
        Init.getClicks().values.clear();
    }
    @Test
    public void integerSubtractionIsCorrect() {

        Init.getClicks().values.add(a);
        Init.getClicks().values.add(b);
        Init.getClicks().operators.add("-");

        assertEquals("Subtraction Integer",3, Init.getEquate().equateInteger());
        Init.getClicks().operators.clear();
        Init.getClicks().values.clear();
    }
    @Test
    public void integerDivideIsCorrect() {

        Init.getClicks().values.add(a);
        Init.getClicks().values.add(b);
        Init.getClicks().operators.add("÷");

        assertEquals(2.5, Double.parseDouble(Init.getEquate().equateDecimal().toString()), 7);
        Init.getClicks().operators.clear();
        Init.getClicks().values.clear();
    }
    @Test
    public void integerMultiplyIsCorrect() {

        Init.getClicks().values.add(a);
        Init.getClicks().values.add(b);
        Init.getClicks().operators.add("×");

        assertEquals("Multiply Integer",10, Init.getEquate().equateInteger());
        Init.getClicks().operators.clear();
        Init.getClicks().values.clear();
    }

    @Test
    public void decimalAdditionIsCorrect() {

        Init.getClicks().values.add(c);
        assertEquals(Init.getClicks().values.get(0), c);
        Init.getClicks().values.add(d);
        assertEquals(Init.getClicks().values.get(1), d);
        Init.getClicks().operators.add("+");

        assertEquals(4.9, Double.parseDouble(Init.getEquate().equateDecimal().toString()), 7);
        Init.getClicks().operators.clear();
        Init.getClicks().values.clear();
    }

    @Test
    public void decimalSubtractionIsCorrect() {

        Init.getClicks().values.add(c);
        assertEquals(Init.getClicks().values.get(0), c);
        Init.getClicks().values.add(d);
        assertEquals(Init.getClicks().values.get(1), d);
        Init.getClicks().operators.add("-");

        assertEquals(1.7, Double.parseDouble(Init.getEquate().equateDecimal().toString()), 7);
        Init.getClicks().operators.clear();
        Init.getClicks().values.clear();
    }

    @Test
    public void decimalDivideIsCorrect() {

        Init.getClicks().values.add(c);
        assertEquals(Init.getClicks().values.get(0), c);
        Init.getClicks().values.add(d);
        assertEquals(Init.getClicks().values.get(1), d);
        Init.getClicks().operators.add("÷");

        assertEquals(2.0625, Double.parseDouble(Init.getEquate().equateDecimal().toString()), 7);
        Init.getClicks().operators.clear();
        Init.getClicks().values.clear();
    }

    @Test
    public void decimalMultiplyIsCorrect() {

        Init.getClicks().values.add(c);
        assertEquals(Init.getClicks().values.get(0), c);
        Init.getClicks().values.add(d);
        assertEquals(Init.getClicks().values.get(1), d);
        Init.getClicks().operators.add("×");

        assertEquals(5.28, Double.parseDouble(Init.getEquate().equateDecimal().toString()), 7);
        Init.getClicks().operators.clear();
        Init.getClicks().values.clear();
    }
}