public class Cell {
    private final int row;
    private final int col;
    final int value;

    Cell(int row, int col, int value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public static Cell[] getInitialCells() {
        return new Cell[]{
                new Cell(0, 2, 6),
                new Cell(1, 1, 8),
                new Cell(1, 4, 5),
                new Cell(1, 5, 4),
                new Cell(1, 6, 2),
                new Cell(2, 1, 4),
                new Cell(2, 4, 9),
                new Cell(2, 7, 7),
                new Cell(3, 2, 7),
                new Cell(3, 3, 9),
                new Cell(3, 6, 3),
                new Cell(4, 4, 8),
                new Cell(4, 6, 4),
                new Cell(5, 0, 6),
                new Cell(5, 6, 1),
                new Cell(6, 0, 2),
                new Cell(6, 2, 3),
                new Cell(6, 8, 1),
                new Cell(7, 3, 5),
                new Cell(7, 7, 4),
                new Cell(8, 2, 8),
                new Cell(8, 3, 3),
                new Cell(8, 6, 5),
                new Cell(8, 8, 2)
        };
    }

    public int getRow() { return row; }
    public int getCol() { return col; }

    @Override
    public String toString() {
        return "Cell(" +
                row +
                ", " + col +
                ", " + value +
                ')';
    }
}
