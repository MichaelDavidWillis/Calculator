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

import android.app.Activity;

import androidx.databinding.DataBindingUtil;

/**
 * {@code Init} class initiates the calculator application, creates the objects for passing to
 * other classes. These are made static to ensure only one object of each is used.
 *
 * @version 0.2
 * @author Michael David Willis
 */
public class Init {

    static SetClicks clicks = new SetClicks();
    static Functions fun = new Functions();
    static Equate equate = new Equate();

    /**
     * {@code init} sets the binding Object to the buttons of the {@link MainActivity} and calls
     * {@link SetClicks} {@code setListeners} to set the buttons to be clickable.
     */
    void init(Activity activity){

        clicks.binding = DataBindingUtil.setContentView(activity, R.layout.activity_main);
        clicks.setListeners();
    }

    /**
     * {@code getClicks} returns the {@link SetClicks} object.
     */
    static SetClicks getClicks(){
        return clicks;
    }

    /**
     * {@code getFun} returns the {@link Functions} object.
     */
    static Functions getFun(){
        return fun;
    }

    /**
     * {@code getEquate} returns the {@link Equate} object.
     */
    static Equate getEquate() { return equate;}

}
