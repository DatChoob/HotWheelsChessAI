package com.josh.util;

import org.apache.commons.lang3.StringUtils;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public enum BoardPosition {
    A1(0, 0),
    A2(1, 0),
    A3(2, 0),
    A4(3, 0),
    A5(4, 0),
    A6(5, 0),
    A7(6, 0),
    A8(7, 0),

    B1(0, 1),
    B2(1, 1),
    B3(2, 1),
    B4(3, 1),
    B5(4, 1),
    B6(5, 1),
    B7(6, 1),
    B8(7, 1),

    C1(0, 2),
    C2(1, 2),
    C3(2, 2),
    C4(3, 2),
    C5(4, 2),
    C6(5, 2),
    C7(6, 2),
    C8(7, 2),

    D1(0, 3),
    D2(1, 3),
    D3(2, 3),
    D4(3, 3),
    D5(4, 3),
    D6(5, 3),
    D7(6, 3),
    D8(7, 3),

    E1(0, 4),
    E2(1, 4),
    E3(2, 4),
    E4(3, 4),
    E5(4, 4),
    E6(5, 4),
    E7(6, 4),
    E8(7, 4),

    F1(0, 5),
    F2(1, 5),
    F3(2, 5),
    F4(3, 5),
    F5(4, 5),
    F6(5, 5),
    F7(6, 5),
    F8(7, 5),

    G1(0, 6),
    G2(1, 6),
    G3(2, 6),
    G4(3, 6),
    G5(4, 6),
    G6(5, 6),
    G7(6, 6),
    G8(7, 6);

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

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                String input = scan.nextLine();
                if (StringUtils.isNotBlank(input))
                    System.out.println(input.toUpperCase() + "->" + BoardPosition.generateOpponentEquivalentMove(input.toUpperCase()));
            } catch (Exception e) {

                System.out.println("err" + e.getMessage());
                e.printStackTrace();
                break;
            }
        }
    }
}
