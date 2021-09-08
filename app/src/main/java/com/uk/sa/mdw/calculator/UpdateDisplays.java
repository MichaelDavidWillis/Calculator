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

import android.widget.TextView;

/**
 * {@code UpdateDisplays} is a simple interface with one default method, updateDisplay,
 * made to clearly show in code when something is being displayed.
 *
 * @author Michael David Willis
 * @version 0.4
 */
public interface UpdateDisplays{

    default void updateDisplay(TextView view, String toDisplay){
        view.setText(toDisplay);
    }
}
