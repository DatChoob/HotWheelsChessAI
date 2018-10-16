package com.josh.util;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum BoardPosition {
    A1(0, 0),
    A2(0, 1),
    A3(0, 2),
    A4(0, 3),
    A5(0, 4),
    A6(0, 5),
    A7(0, 6),
    A8(0, 7),

    B1(1, 0),
    B2(1, 1),
    B3(1, 2),
    B4(1, 3),
    B5(1, 4),
    B6(1, 5),
    B7(1, 6),
    B8(1, 7),

    C1(2, 0),
    C2(2, 1),
    C3(2, 2),
    C4(2, 3),
    C5(2, 4),
    C6(2, 5),
    C7(2, 6),
    C8(2, 7),

    D1(3, 0),
    D2(3, 1),
    D3(3, 2),
    D4(3, 3),
    D5(3, 4),
    D6(3, 5),
    D7(3, 6),
    D8(3, 7),

    E1(4, 0),
    E2(4, 1),
    E3(4, 2),
    E4(4, 3),
    E5(4, 4),
    E6(4, 5),
    E7(4, 6),
    E8(4, 7),

    F1(5, 0),
    F2(5, 1),
    F3(5, 2),
    F4(5, 3),
    F5(5, 4),
    F6(5, 5),
    F7(5, 6),
    F8(5, 7),

    G1(6, 0),
    G2(6, 1),
    G3(6, 2),
    G4(6, 3),
    G5(6, 4),
    G6(6, 5),
    G7(6, 6),
    G8(6, 7);

    private int row, column;

    BoardPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public static BoardPosition getBoardPositionZeroIndex(int row, int column) {
        BoardPosition ret = A1;
        switch (column) {
            case 0:
                //A
                switch (row) {
                    case 0:
                        ret = A1;
                        break;
                    case 1:
                        ret = A2;
                        break;
                    case 2:
                        ret = A3;
                        break;
                    case 3:
                        ret = A4;
                        break;
                    case 4:
                        ret = A5;
                        break;
                    case 5:
                        ret = A6;
                        break;
                    case 6:
                        ret = A7;
                        break;
                    case 7:
                        ret = A8;
                        break;

                }
                break;
            case 1:
                //B
                switch (row) {
                    case 0:
                        ret = B1;
                        break;
                    case 1:
                        ret = B2;
                        break;
                    case 2:
                        ret = B3;
                        break;
                    case 3:
                        ret = B4;
                        break;
                    case 4:
                        ret = B5;
                        break;
                    case 5:
                        ret = B6;
                        break;
                    case 6:
                        ret = B7;
                        break;
                    case 7:
                        ret = B8;
                        break;
                }
                break;
            case 2:
                //C
                switch (row) {
                    case 0:
                        ret = C1;
                        break;
                    case 1:
                        ret = C2;
                        break;
                    case 2:
                        ret = C3;
                        break;
                    case 3:
                        ret = C4;
                        break;
                    case 4:
                        ret = C5;
                        break;
                    case 5:
                        ret = C6;
                        break;
                    case 6:
                        ret = C7;
                        break;
                    case 7:
                        ret = C8;
                        break;
                }
                break;
            case 3:
                //D
                switch (row) {
                    case 0:
                        ret = D1;
                        break;
                    case 1:
                        ret = D2;
                        break;
                    case 2:
                        ret = D3;
                        break;
                    case 3:
                        ret = D4;
                        break;
                    case 4:
                        ret = D5;
                        break;
                    case 5:
                        ret = D6;
                        break;
                    case 6:
                        ret = D7;
                        break;
                    case 7:
                        ret = D8;
                        break;
                }
                break;
            case 4:
                //E
                switch (row) {
                    case 0:
                        ret = E1;
                        break;
                    case 1:
                        ret = E2;
                        break;
                    case 2:
                        ret = E3;
                        break;
                    case 3:
                        ret = E4;
                        break;
                    case 4:
                        ret = E5;
                        break;
                    case 5:
                        ret = E6;
                        break;
                    case 6:
                        ret = E7;
                        break;
                    case 7:
                        ret = E8;
                        break;
                }
                break;
            case 5:
                //F
                switch (row) {
                    case 0:
                        ret = F1;
                        break;
                    case 1:
                        ret = F2;
                        break;
                    case 2:
                        ret = F3;
                        break;
                    case 3:
                        ret = F4;
                        break;
                    case 4:
                        ret = F5;
                        break;
                    case 5:
                        ret = F6;
                        break;
                    case 6:
                        ret = F7;
                        break;
                    case 7:
                        ret = F8;
                        break;
                }
                break;
            case 6:
                //G
                switch (row) {
                    case 0:
                        ret = G1;
                        break;
                    case 1:
                        ret = G2;
                        break;
                    case 2:
                        ret = G3;
                        break;
                    case 3:
                        ret = G4;
                        break;
                    case 4:
                        ret = G5;
                        break;
                    case 5:
                        ret = G6;
                        break;
                    case 6:
                        ret = G7;
                        break;
                    case 7:
                        ret = G8;
                        break;
                }
                break;
            default:
                ret = A1;
        }
        return ret;
    }

    public static int columnCharToIndex(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            default:
                throw new InvalidParameterException("not a valid column");
        }

    }

    public static String indexToColumnChar(int index) {
        switch (index) {
            case 1:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
            case 4:
                return "D";
            case 5:
                return "E";
            case 6:
                return "F";
            case 7:
                return "G";
            default:
                throw new InvalidParameterException("not a valid index");
        }

    }

    public static String generateOpponentEquivalentMove(String myMove) {
        char[] moveComponents = myMove.toCharArray();
        List<?> newVales = Arrays.asList(moveComponents[0], switchMyRowToOpponentRowNotation(moveComponents[1]), moveComponents[2], switchMyRowToOpponentRowNotation((moveComponents[3])));
        return newVales.stream().map(String::valueOf).collect(Collectors.joining());

    }

    public static char switchMyRowToOpponentRowNotation(char row) {
        char ret = '1';
        switch (row) {
            case '1':
                ret = '8';
                break;
            case '2':
                ret = '7';
                break;
            case '3':
                ret = '6';
                break;
            case '4':
                ret = '5';
                break;
            case '5':
                ret = '4';
                break;
            case '6':
                ret = '3';
                break;
            case '7':
                ret = '2';
                break;
            case '8':
                ret = '1';
                break;
            default:
                ret = 1;
                break;

        }
        return ret;
    }

}
