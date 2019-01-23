package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.Core.RandomUtils;
import java.util.Random;
import org.junit.Assert;

public class MapGen{
    private static int Width = 80;
    private static int Height = 40;
    public static TETile[][] world = new TETile[Width][Height];
    public static Random random = new Random(357);
    int[] bob = new int[2];


    public MapGen() {
        //initialize all tiles to nothing
        for (int x = 0; x < Width; x += 1) {
            for (int y = 0; y < Height; y += 1) {
                world[x][y] = Tileset.NOTHING;
        }
     }
    }
    public class Position{
       public int[] upperRcorner = new int[2];
        public int[] lowerLcorner = new int[2];
    }


    public Position squareRoom(int size) {
        int x_start = RandomUtils.uniform(random, Width/2);
        int y_start = RandomUtils.uniform(random, Height/2);
        if ((x_start + size < Width) && (y_start + size < Height)) {
            for (int x = x_start; x < (x_start+size + 1); x += 1) {
                for (int y = y_start; y < (y_start+size + 1); y += 1) {
                    if (y == y_start | y == (y_start+size)) {
                        world[x][y] = Tileset.WALL;
                    } else if (x == x_start | x == (x_start+size)) {
                        world[x][y] = Tileset.WALL;
                    } else {
                        world[x][y] = Tileset.FLOOR;

                    }
                }
            }
       }
        else {
            System.out.println("Occlusion!");
        }
        Position pos = new Position();
        pos.lowerLcorner[0] = x_start;
        pos.lowerLcorner[1] = y_start;
        pos.upperRcorner[0] =x_start+size;
        pos.upperRcorner[1] =y_start+size;
        return pos;
   }

//    public static void selectRoom(int seed) {
//        Random Ransom = new Random(seed);
//        int tileNum = Ransom.nextInt(6);
//        Position[] choice;
//        switch (tileNum) {
//            case 0:
//                makefirstRoom(0);
//            case 1:
//                 makefirstRoom(1);
//            case 2:
//                 makefirstRoom(2);
//            case 3:
//                 makefirstRoom(3);
//            case 4:
//                 makefirstRoom(4);
//            case 5:
//                 makefirstRoom(5);
//            default:
//                 makefirstRoom(3);
//
//        }

    public static void main(String[] args){
        TERenderer border = new TERenderer();
        border.initialize(Width, Height);
        MapGen canvas = new MapGen();
        canvas.squareRoom(RandomUtils.uniform(random, 3, 6));
        border.renderFrame(world);
    }
}



