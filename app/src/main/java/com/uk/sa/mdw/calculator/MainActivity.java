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

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * {@code MainActivity} class represents a calculator application's main class.
 *
 * @version 0.2
 * @author Michael David Willis
 */
public class MainActivity extends AppCompatActivity {

    /**
     * {@code onCreate} creates the app.
     * <br>
     * {@code onCreate} creates an {@code Init} object to initiate the calculator app, passing
     * {@code this} {@link android.app.Activity} for the binding object.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Init initiate = new Init();
        initiate.init(this);
    }
}