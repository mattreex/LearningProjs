package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static int HEIGHT = 30;
    private static int WIDTH = 70;



    public static int[] addHexagon(TETile[][] world, int size, int start_x, int start_y, TETile til){
        //start from bottom
        int[] lengths = new int[size];
        int y_offset = 0;
        int c = 0;
        //build up
        while(findWidth(size, y_offset) > 0) {
            int i = findWidth(size, y_offset);
            for (int x = start_x; x < start_x + i; x++){
                world[x][start_y+y_offset] = til;
            }
            y_offset++;
            start_x = start_x -1;
            lengths[c] = i;
            c++;
        }
        //start from top
        start_x = start_x + size;
        y_offset = 2*size-1;
        // build down
        for (int j : lengths) {
            for (int x = start_x; x < start_x + j; x++){
                world[x][start_y+y_offset] = til;
            }

            y_offset--;
            start_x = start_x -1;
        }
        int[] position = new int[4];
        position[0] = start_x + (2*size + (size-2));
        position[1] = start_y +y_offset +2;
        position[2] = start_x - (size-2);
        position[3] = start_y + y_offset +2;
        return position;
    }

    private static int findWidth(int size, int y_offset) {
        if (y_offset == 0) {
            return size;
        } else if (y_offset > 0 && y_offset < size) {
            return y_offset * 2 + size;
        } else {
            return 0;
        }
    }

    @Test
    public void testfindWidth(){
        assertEquals(3, findWidth(3, 0));
        assertEquals(findWidth(1, 0), 1);
        assertEquals(58, findWidth(20, 19));
    }
    private static TETile randomTile(Random random) {
        int tileNum = random.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.SAND;
            default: return Tileset.MOUNTAIN;
        }
    }

    public static void tesselateHexagons(TETile[][] world, int number, int size){
        Random random = new Random(number);
        TETile til = randomTile(random);
        int i = 1;
        int [] position = addHexagon(world, size, WIDTH/2, 0, Tileset.NOTHING);
        int [] position1;
        int [] position2;
        TETile res = randomTile(random);
        position1= addHexagon(world, size, position[0], position[1], Tileset.FLOWER);
        TETile se = randomTile(random);
        position2 = addHexagon(world, size, position[2], position[3], Tileset.WALL);
        while (i < number){
            addHexagon(world, size, position1[0], position1[1], Tileset.FLOWER);
            position1= addHexagon(world, size, position1[2], position1[3], Tileset.FLOWER);
            addHexagon(world, size, position2[0], position2[1], Tileset.WALL);
            position2 = addHexagon(world, size, position2[2], position2[3], Tileset.WALL);
            i++;
        }

    }

    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.GRASS;
            }
        }
        tesselateHexagons(world,3, 5);
        // draws the world to the screen
        ter.renderFrame(world);
    }
}