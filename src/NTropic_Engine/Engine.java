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

import java.awt.Insets;
import java.awt.event.KeyEvent;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * Game engine for 2D game
 *
 * @author Ryan Castelli
 */
public class Engine extends JFrame {

    public boolean isRunning;

    public BufferedImage backBuffer;

    public InputHandler input;

    public Insets insets;

    public int fps;
    public int posX;
    public int posY;
    public int wHeight;
    public int wWidth;
    
    public String gameName;

    /**
     * Constructor to execute game
     */
    public Engine() {
    }

    /**
     * Starts and loops game
     */
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        initialize();
        while (isRunning) {
            long time = System.currentTimeMillis();

            update();
            draw();

            time = (1000 / fps) - (System.currentTimeMillis() - time); //frame delay

            if (time > 0) {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException ie) {
                    System.err.println(ie);
                }
            }

        }
        setVisible(false); //insert game over here
    }

    /**
     * Initial start-up
     */
    public void initialize() {
        setTitle(gameName);
        setSize(wWidth,wHeight);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        insets = getInsets();
        setSize(insets.left + wWidth + insets.right, insets.top +wHeight + insets.bottom);

        backBuffer = new BufferedImage(wWidth,wHeight, BufferedImage.TYPE_INT_RGB);

        input = new InputHandler(this);

        posX = 0;
        posY = 0;
    }

    /**
     * Checks for inputs and such
     */
    public void update() {
        if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
            posX++;
        }
        if (input.isKeyDown(KeyEvent.VK_LEFT)) {
            posX--;
        }
        if (input.isKeyDown(KeyEvent.VK_UP)) {
            posY--;
        }
        if (input.isKeyDown(KeyEvent.VK_DOWN)) {
            posY++;
        }
        if (input.isKeyDown(KeyEvent.VK_D)) {
            posX++;
        }
        if (input.isKeyDown(KeyEvent.VK_A)) {
            posX--;
        }
        if (input.isKeyDown(KeyEvent.VK_W)) {
            posY--;
        }
        if (input.isKeyDown(KeyEvent.VK_S)) {
            posY++;
        }
    }
    
    public void draw() {
        //implement per game
    }
}
