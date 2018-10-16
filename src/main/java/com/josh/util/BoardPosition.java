package com.josh.util;

import java.security.InvalidParameterException;

public enum BoardPosition {
    A1,
    A2,
    A3,
    A4,
    A5,
    A6,
    A7,
    A8,

    B1,
    B2,
    B3,
    B4,
    B5,
    B6,
    B7,
    B8,

    C1,
    C2,
    C3,
    C4,
    C5,
    C6,
    C7,
    C8,

    D1,
    D2,
    D3,
    D4,
    D5,
    D6,
    D7,
    D8,

    E1,
    E2,
    E3,
    E4,
    E5,
    E6,
    E7,
    E8,

    F1,
    F2,
    F3,
    F4,
    F5,
    F6,
    F7,
    F8,

    G1,
    G2,
    G3,
    G4,
    G5,
    G6,
    G7,
    G8;

    public static BoardPosition getBoardPosition1Index(int row, int column) {
        BoardPosition ret =  A1;
        switch(column){
            case 1:
                //A
                switch(row){
                    case 1: ret = A1; break;
                    case 2: ret = A2; break;
                    case 3: ret = A3; break;
                    case 4: ret = A4; break;
                    case 5: ret = A5; break;
                    case 6: ret = A6; break;
                    case 7: ret = A7; break;
                    case 8: ret = A8; break;

                }
                break;
            case 2:
                //B
                switch(row){
                    case 1: ret = B1; break;
                    case 2: ret = B2; break;
                    case 3: ret = B3; break;
                    case 4: ret = B4; break;
                    case 5: ret = B5; break;
                    case 6: ret = B6; break;
                    case 7: ret = B7; break;
                    case 8: ret = B8; break;
                }
                break;
            case 3:
                //C
                switch(row){
                    case 1: ret = C1; break;
                    case 2: ret = C2; break;
                    case 3: ret = C3; break;
                    case 4: ret = C4; break;
                    case 5: ret = C5; break;
                    case 6: ret = C6; break;
                    case 7: ret = C7; break;
                    case 8: ret = C8; break;
                }
                break;
            case 4:
                //D
                switch(row){
                    case 1: ret = D1; break;
                    case 2: ret = D2; break;
                    case 3: ret = D3; break;
                    case 4: ret = D4; break;
                    case 5: ret = D5; break;
                    case 6: ret = D6; break;
                    case 7: ret = D7; break;
                    case 8: ret = D8; break;
                }
                break;
            case 5:
                //E
                switch(row){
                    case 1: ret = E1; break;
                    case 2: ret = E2; break;
                    case 3: ret = E3; break;
                    case 4: ret = E4; break;
                    case 5: ret = E5; break;
                    case 6: ret = E6; break;
                    case 7: ret = E7; break;
                    case 8: ret = E8; break;
                }
                break;
            case 6:
                //F
                switch(row){
                    case 1: ret = F1; break;
                    case 2: ret = F2; break;
                    case 3: ret = F3; break;
                    case 4: ret = F4; break;
                    case 5: ret = F5; break;
                    case 6: ret = F6; break;
                    case 7: ret = F7; break;
                    case 8: ret = F8; break;
                }
                break;
            case 7:
                //G
                switch(row){
                    case 1: ret = G1; break;
                    case 2: ret = G2; break;
                    case 3: ret = G3; break;
                    case 4: ret = G4; break;
                    case 5: ret = G5; break;
                    case 6: ret = G6; break;
                    case 7: ret = G7; break;
                    case 8: ret = G8; break;
                }
                break;
            default:
                ret= A1;
        }
        return ret;
    }



    public static BoardPosition getBoardPositionZeroIndex(int row, int column) {
        BoardPosition ret =  A1;
        switch(column){
            case 0:
                //A
                switch(row){
                    case 0: ret = A1; break;
                    case 1: ret = A2; break;
                    case 2: ret = A3; break;
                    case 3: ret = A4; break;
                    case 4: ret = A5; break;
                    case 5: ret = A6; break;
                    case 6: ret = A7; break;
                    case 7: ret = A8; break;

                }
                break;
            case 1:
                //B
                switch(row){
                    case 0: ret = B1; break;
                    case 1: ret = B2; break;
                    case 2: ret = B3; break;
                    case 3: ret = B4; break;
                    case 4: ret = B5; break;
                    case 5: ret = B6; break;
                    case 6: ret = B7; break;
                    case 7: ret = B8; break;
                }
                break;
            case 2:
                //C
                switch(row){
                    case 0: ret = C1; break;
                    case 1: ret = C2; break;
                    case 2: ret = C3; break;
                    case 3: ret = C4; break;
                    case 4: ret = C5; break;
                    case 5: ret = C6; break;
                    case 6: ret = C7; break;
                    case 7: ret = C8; break;
                }
                break;
            case 3:
                //D
                switch(row){
                    case 0: ret = D1; break;
                    case 1: ret = D2; break;
                    case 2: ret = D3; break;
                    case 3: ret = D4; break;
                    case 4: ret = D5; break;
                    case 5: ret = D6; break;
                    case 6: ret = D7; break;
                    case 7: ret = D8; break;
                }
                break;
            case 4:
                //E
                switch(row){
                    case 0: ret = E1; break;
                    case 1: ret = E2; break;
                    case 2: ret = E3; break;
                    case 3: ret = E4; break;
                    case 4: ret = E5; break;
                    case 5: ret = E6; break;
                    case 6: ret = E7; break;
                    case 7: ret = E8; break;
                }
                break;
            case 5:
                //F
                switch(row){
                    case 0: ret = F1; break;
                    case 1: ret = F2; break;
                    case 2: ret = F3; break;
                    case 3: ret = F4; break;
                    case 4: ret = F5; break;
                    case 5: ret = F6; break;
                    case 6: ret = F7; break;
                    case 7: ret = F8; break;
                }
                break;
            case 6:
                //G
                switch(row){
                    case 0: ret = G1; break;
                    case 1: ret = G2; break;
                    case 2: ret = G3; break;
                    case 3: ret = G4; break;
                    case 4: ret = G5; break;
                    case 5: ret = G6; break;
                    case 6: ret = G7; break;
                    case 7: ret = G8; break;
                }
                break;
            default:
                ret= A1;
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

    public static String indexToColumnChar(int index){
        switch(index){
            case 1: return "A";
            case 2: return "B";
            case 3: return "C";
            case 4: return "D";
            case 5: return "E";
            case 6: return "F";
            case 7: return "G";
            default: throw new InvalidParameterException("not a valid index");
        }

    }

}
