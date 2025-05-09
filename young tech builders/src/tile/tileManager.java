package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;

public final class tileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public tileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[20]; // Adjust size as needed
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {
            for (int i = 0; i < tile.length; i++) {
                tile[i] = new Tile();
            }

            tile[0].image = ImageIO.read(new File("src/res/tiles/001.png"));
            tile[0].collision = true;
            tile[1].image = ImageIO.read(new File("src/res/tiles/002.png"));
            tile[1].collision = true;
            tile[2].image = ImageIO.read(new File("src/res/tiles/003.png"));
            tile[2].collision = true;
            tile[3].image = ImageIO.read(new File("src/res/tiles/004.png"));
            tile[3].collision = true;
            tile[4].image = ImageIO.read(new File("src/res/tiles/005.png"));
            tile[4].collision = true;
            tile[5].image = ImageIO.read(new File("src/res/tiles/006.png"));
            tile[5].collision = true;
            tile[6].image = ImageIO.read(new File("src/res/tiles/007.png"));
            tile[7].image = ImageIO.read(new File("src/res/tiles/008.png"));
            tile[7].collision = true;
            tile[8].image = ImageIO.read(new File("src/res/tiles/009.png"));
            tile[8].collision = true;
            tile[9].image = ImageIO.read(new File("src/res/tiles/010.png"));
            tile[9].collision = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/res/maps/ForestMap.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;
            while (row < gp.maxWorldRow) { // FIXED: Use maxWorldRow instead of maxScreenRow
                String line = br.readLine();
                if (line == null) break;

                String[] numbers = line.split(" ");
                for (int col = 0; col < gp.maxWorldCol; col++) { // FIXED: Use maxWorldCol
                    if (col < numbers.length) {
                        try {
                            int num = Integer.parseInt(numbers[col]);
                            mapTileNum[col][row] = num;
                        } catch (NumberFormatException e) {
                            System.err.println("Error parsing tile number at row: " + row + ", col: " + col + ". Setting to 0.");
                            mapTileNum[col][row] = 0;
                        }
                    } else {
                        mapTileNum[col][row] = 0;
                    }
                }
                row++;
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
