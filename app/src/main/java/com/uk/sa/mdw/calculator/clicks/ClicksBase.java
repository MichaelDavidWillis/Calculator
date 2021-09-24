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

import com.uk.sa.mdw.calculator.state.Holder;
import com.uk.sa.mdw.calculator.state.UpdateDisplays;
import com.uk.sa.mdw.calculator.databinding.ActivityMainBinding;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * {@code ClicksBase} object holds the variables needed for displaying and storing data
 * needed for calculator functions.
 *
 * @version 0.5
 * @author Michael David Willis
 */
public class ClicksBase implements UpdateDisplays {

    /**
     * {@code binding} defines a {@link ActivityMainBinding} object for storing all buttons for
     * quicker calling at run-time.
     */
    public ActivityMainBinding binding;

    // Controls the total sum displayed
    public StringBuilder sumToCalculate = new StringBuilder("");

    // For tracking number of parentheses currently open
    public int parenthesesOpen = 0;

    // For storing unplugged Holders
    public Deque<Holder> holders = new ArrayDeque<>();

    // Set starter Holder to holder
    public Holder holder = new Holder();

    public String storedNumber;


}
