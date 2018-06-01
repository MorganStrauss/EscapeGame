/*
 * Copyright (C) 2018 Ryan Castelli
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
package NTropic_Engine;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

/**
 * Handles input from user
 *
 * @author Ryan Castelli
 */
public class InputHandler implements KeyListener {
    
    private final HashSet<Integer> pressed = new HashSet<>();

    /**
     * Assigns object to a Component
     *
     * @param c Component passing input
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public InputHandler(Component c) {
        c.addKeyListener(this);
    }

    /**
     * Checks for specific key press
     *
     * @param keyCode Key to check
     * @return Whether key is held down
     */
    public boolean isKeyDown (int keyCode) {
        return pressed.contains(keyCode);
    }

    /**
     * Called when key is pressed
     *
     * @param e KeyPressed sent by component
     */
    @Override
    public void keyPressed(KeyEvent e) {
        pressed.add(e.getKeyCode());
    }

    /**
     * Called when key is released
     *
     * @param e KeyEvent sent by component
     */
    @Override
    public void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
    }

    /**
     * Unused
     * @param e N/A
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }
}
