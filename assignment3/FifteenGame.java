import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FifteenGame {
    private final int size;
    private final int[] tiles;
    private int emptyTileIndex;
    private final Random random = new Random();
    private static final int SHUFFLE_STEPS = 200;

    public FifteenGame(int size){
        this.size = size;
        this.tiles = new int[size * size];
        reset();
    }

    public int getSize() {
        return size;
    }

    public int[] getTiles(){
        return tiles;
    }

    // Återställer spelet till ursprungsläget
    // Används när man startar upp ett nytt spel eller innan blandning
    public void reset() {
        for (int i = 0; i < tiles.length; i++) tiles[i] = (i + 1) % tiles.length;
        emptyTileIndex = tiles.length - 1;
    }

    // Kontrollerar om en vald bricka ligger brevid den tomma rutan
    // Returnerar true om brickan kan flyttas, annars false
    public boolean isNextToEmpty(int tileIndex){
        int row = tileIndex / size, column = tileIndex % size;
        int emptyRow = emptyTileIndex / size, emptyColumn = emptyTileIndex % size;
        return (row == emptyRow && Math.abs(column - emptyColumn) == 1 || (column == emptyColumn && Math.abs(row- emptyRow) == 1));
    }

    /**
     *    Försöker flytta en bricka
     *    Om den ligger brevid den tomma rutan byter de plats
     *    Returnerar true om flytten lyckades, annars false
     */
    public boolean move(int tileIndex){
        if(!isNextToEmpty(tileIndex)) return false;
        swap(tileIndex, emptyTileIndex);
        emptyTileIndex = tileIndex;
        return true;
    }

    // Startar ett nytt spel genom att blanda brickorna slumpmässigt (200 gånger)
    public void newGame() {
        shuffleTiles(SHUFFLE_STEPS);
    }

    // Kontrollerar om spelaren har vunnit (alla brickor i nummerordning
    // Returnerar true om spelet är löst
    public boolean gameSolved() {
        for (int i = 0; i < tiles.length - 1; i++){
            if (tiles[i] != i + 1) return false;
        }
        return tiles[tiles.length - 1] == 0;
    }

    // Hjälpmedtod som byter plats på två brickor
    private void swap(int firstTileIndex, int secondTileIndex) {
        int temp = tiles[firstTileIndex];
        tiles[firstTileIndex] = tiles[secondTileIndex];
        tiles[secondTileIndex] = temp;
    }

    // Blandar brickorna genom att göra ett antal
    private void shuffleTiles(int steps){
        reset();
        int previousEmptyTileIndex = -1;
        for (int step = 0; step < steps; step++){
            List<Integer> validNeighbors = new ArrayList<>();
            int emptyRow = emptyTileIndex / size, emptyColumn = emptyTileIndex % size;
            addIfValid(validNeighbors, emptyRow - 1, emptyColumn);
            addIfValid(validNeighbors, emptyRow + 1, emptyColumn);
            addIfValid(validNeighbors, emptyRow, emptyColumn - 1);
            addIfValid(validNeighbors, emptyRow, emptyColumn + 1);

            if (previousEmptyTileIndex != -1) validNeighbors.remove(Integer.valueOf(previousEmptyTileIndex));
            int selectedNeighborIndex = validNeighbors.get(random.nextInt(validNeighbors.size()));
            swap(selectedNeighborIndex, emptyTileIndex);
            previousEmptyTileIndex = emptyTileIndex;
            emptyTileIndex = selectedNeighborIndex;
        }
    }

    private void addIfValid(List<Integer> validNeighbors, int row, int column){
        if (row >= 0 && row < size && column >=0 && column < size) validNeighbors.add(row * size + column);
    }
}
