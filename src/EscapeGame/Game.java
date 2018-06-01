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
package EscapeGame;

import NTropic_Engine.Engine;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/**
 * 2D Escape Game
 * @author NTropy
 */
public class Game extends Engine {

    public static Game mainGame = new Game();

    public Desk desk1 = new Desk(200, 300, 20, 50);

    public Desk desk2 = new Desk(500, 700, 20, 50);
    
    public Desk[] desks;

    private Game() {
        super();
    }

    public static void main(String args[]) {
        mainGame.fps = 60;
        mainGame.gameName = "Test";
        mainGame.wHeight = 900;
        mainGame.wWidth = 1800;
        mainGame.isRunning = true;
        mainGame.run();
        System.exit(0);
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        super.initialize();
        int deskNum = 2;
        desks = new Desk[deskNum];
        desks[0] = desk1;
        desks[1] = desk2;
        while (isRunning) {
            long time = System.currentTimeMillis();

            update();
            this.draw();

            time = (1000 / fps) - (System.currentTimeMillis() - time); //frame delay

            if (time > 0) {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException ie) {
                    System.err.println(ie);
                }
            }

        }
        mainGame.setVisible(false); //insert game over here
    }

    /**
     * Checks for inputs and such
     */
    @Override
    public void update() {
        if (!(mainGame.posX + 11 > desk1.posX && mainGame.posX < desk1.posX + desk1.length && mainGame.posY + 10 > desk1.posY && mainGame.posY + 31 < desk1.posY + desk1.width)) {

            if (input.isKeyDown(KeyEvent.VK_RIGHT)) {
                posX++;
            }
        }
        if (!(mainGame.posX - 11 < desk1.posX + (2 * desk1.length) && mainGame.posX + 9 > desk1.posX && mainGame.posY + 10 > desk1.posY && mainGame.posY + 31 < desk1.posY + desk1.width)) {

            if (input.isKeyDown(KeyEvent.VK_LEFT)) {
                posX--;
            }
        }

        if (!(mainGame.posY > desk1.posY && mainGame.posY < desk1.posY + (desk1.width - 29) && mainGame.posX + 9 > desk1.posX && mainGame.posX < desk1.posX + (2 * desk1.length) + 10)) {

            if (input.isKeyDown(KeyEvent.VK_UP)) {
                posY--;
            }
        }

        if (!(mainGame.posY + 11 > desk1.posY && mainGame.posY < desk1.posY + (desk1.width - 30) && mainGame.posX + 9 > desk1.posX && mainGame.posX < desk1.posX + (2 * desk1.length) + 10)) {

            if (input.isKeyDown(KeyEvent.VK_DOWN)) {
                posY++;
            }
        }

        if (!(mainGame.posX + 11 > desk1.posX && mainGame.posX < desk1.posX + desk1.length && mainGame.posY + 10 > desk1.posY && mainGame.posY + 31 < desk1.posY + desk1.width)) {

            if (input.isKeyDown(KeyEvent.VK_D)) {
                posX++;
            }
        }

        if (!(mainGame.posX - 11 < desk1.posX + (2 * desk1.length) && mainGame.posX + 9 > desk1.posX && mainGame.posY + 10 > desk1.posY && mainGame.posY + 31 < desk1.posY + desk1.width)) {

            if (input.isKeyDown(KeyEvent.VK_A)) {
                posX--;
            }
        }

        if (!(mainGame.posY > desk1.posY && mainGame.posY < desk1.posY + (desk1.width - 29) && mainGame.posX + 9 > desk1.posX && mainGame.posX < desk1.posX + (2 * desk1.length) + 10)) {

            if (input.isKeyDown(KeyEvent.VK_W)) {
                posY--;
            }
        }

        if (!(mainGame.posY + 11 > desk1.posY && mainGame.posY < desk1.posY + (desk1.width - 30) && mainGame.posX + 9 > desk1.posX && mainGame.posX < desk1.posX + (2 * desk1.length) + 10)) {

            if (input.isKeyDown(KeyEvent.VK_S)) {
                posY++;
            }
        }
    }

    @Override
    public void draw() {
        Graphics g = getGraphics();

        Graphics bbg = mainGame.backBuffer.getGraphics();

        bbg.setColor(Color.BLACK); //background
        bbg.fillRect(0, 0, mainGame.wWidth, mainGame.wHeight);

        Color transYellow = new Color(204, 204, 0, 137);
        bbg.setColor(transYellow);
        bbg.fillOval(mainGame.posX - 10, mainGame.posY - 10, 30, 30);

        bbg.setColor(Color.RED);
        bbg.fillOval(mainGame.posX, mainGame.posY, 10, 10);

        bbg.setColor(Color.RED);

        for (int j = desk1.posX - 15; j < desk1.posX + (2 * desk1.length) - 5; j++) {
            for (int i = desk1.posY - 15; i < desk1.posY + desk1.width - 45; i++) {
                if ((int) Math.sqrt(Math.pow((j - mainGame.posX + 10), 2) + Math.pow((i - mainGame.posY + 10), 2)) < 15) {
                    bbg.drawLine(j + 15, i + 15, j + 15, i + 15);
                }
            }
        }

        g.drawImage(mainGame.backBuffer, mainGame.insets.left, mainGame.insets.top, this);
    }
}
