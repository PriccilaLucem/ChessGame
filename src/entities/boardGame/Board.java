package src.entities.boardGame;

import src.entities.exceptions.BoardExcecption;

public class Board {
    private Integer rows;
    private Integer columns;
    private Piece[][] pieces;

    public Board(Integer rows, Integer columns) {
        if (rows < 1 || columns < 1) {
            throw new BoardExcecption("Error: Collumns and rows must be at least 1");
        }
        this.columns = columns;
        this.rows = rows;
        pieces = new Piece[rows][columns];
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public Piece piece(int row, int column) {
        verifyIfPositionExists(row, column);
        return pieces[row][column];
    }

    public Piece piece(Position position) {

        return pieces[position.getRow()][position.getColumn()];
    }

    public void placePiece(Piece piece, Position position) {
        verifyIfPositionExists(position);
        if (thereIsAPiece(position)) {
            throw new BoardExcecption("Error: There is a piece in this position");
        }
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position;
    }

    private boolean positionExists(int row, int column) {
        if (row >= 0 && row < 8 && column >= 0 && column < 8) {
            return true;
        }
        return false;
    }

    public boolean positionExists(Position position) {
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean thereIsAPiece(Position position) {
        verifyIfPositionExists(position);
        return piece(position) != null;
    }

    private void verifyIfPositionExists(Position position) {
        if (!positionExists(position)) {
            throw new BoardExcecption("Error: Position does not exist");

        }
    }

    private void verifyIfPositionExists(int raw, int column) {
        if (!positionExists(raw, column)) {
            throw new BoardExcecption("Error: Position does not exist");

        }
    }

    public Piece removePiece(Position position) {
        verifyIfPositionExists(position);
        if (piece(position) != null) {
            Piece piece = piece(position);
            piece.position = null;
            pieces[position.getRow()][position.getColumn()] = null;
            return piece;

        }
        return null;
    }
}
