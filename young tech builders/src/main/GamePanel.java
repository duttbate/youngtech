package main;

import entity.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import object.SuperObject;
import tile.tileManager;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
    final int originalTileSize = 20; // 48x48 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxScreenRow;

    // FPS
    int FPS = 60;

    // System
    tileManager tileM = new tileManager(this);
    Keyhandler keyH = new Keyhandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this); // Use CollisionChecker for both tile and object collisions
    public AssetSetter aSetter = new AssetSetter(this);
    // public UI ui = new UI(this);
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        // Initialize cChecker
        cChecker = new CollisionChecker(this); // Pass the GamePanel instance
    }

    public void setupGame() {
        aSetter.setObject(); // Populate the obj array
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
		super.paintComponent(g);
	
		Graphics2D g2 = (Graphics2D) g;
	
		// Tile
		tileM.draw(g2);
	
		// Object
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				System.out.println("Drawing object " + i + " at (" + obj[i].worldX + ", " + obj[i].worldY + ")");
				obj[i].draw(g2, this);
			}
		}
	
		// Player
		player.draw(g2);

        // UI
        // ui.draw(g2);
	
		g2.dispose();
	}
}