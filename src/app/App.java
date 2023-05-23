package src.app;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import src.entities.chess.ChessMatch;
import src.entities.chess.ChessPiece;
import src.entities.chess.ChessPosition;
import src.entities.exceptions.ChessException;

public class App {
    public static void main(String[] args) {
        ChessMatch chessMatch = new ChessMatch();

        Scanner sc = new Scanner(System.in);
        List<ChessPiece> capturedlList = new ArrayList<>();

        while (true) {
            try {

                UI.clearScreen();
                UI.printMatch(chessMatch, capturedlList);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);

                UI.clearScreen();
                UI.clearScreen();

                UI.printBoard(chessMatch.getPieces(), possibleMoves);
                System.out.println();
                System.out.println("Target");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.perfomChessMove(source, target);

                if (capturedPiece != null) {
                    capturedlList.add(capturedPiece);
                }
            } catch (ChessException e) {
                System.err.println(e.getMessage());
                System.out.println("Press Enter to continue");
                sc.nextLine();
            } catch (InputMismatchException e) {
                System.err.println(e.getMessage());
                System.out.println("Press Enter to continue");
                sc.nextLine();
            }

        }
    }
}
