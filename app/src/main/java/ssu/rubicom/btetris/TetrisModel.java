package ssu.rubicom.btetris;

import ssu.rubicom.tetris.CTetris;
import ssu.rubicom.tetris.Matrix;
import ssu.rubicom.tetris.Tetris;

public class TetrisModel { // derived from TestMain.java in Lecture 5
    public CTetris board;
    public TetrisModel(int dy, int dx) throws Exception {
        CTetris.init(setOfBlockArrays);
        board = new CTetris(dy, dx);
    }
    public CTetris.TetrisState accept(char ch) throws Exception { return board.accept(ch); }
    public Matrix getBlock(char type) { return board.setOfBlockObjects[type - '0'][0]; }
    private int[][][][] setOfBlockArrays = { // [7][4][?][?]
        /* 4차원 배열을 둘 공간이 마땅치 않아서 테트리스 모델을 만들었음.
           테트리스 보드를 둘러싸고 있는 껍데기 객체에 불과함
           */
            {
                    {
                            {10, 10},
                            {10, 10}
                    },
                    {
                            {10, 10},
                            {10, 10}
                    },
                    {
                            {10, 10},
                            {10, 10}
                    },
                    {
                            {10, 10},
                            {10, 10}
                    }
            },
            {
                    {
                            {0, 20, 0},
                            {20, 20, 20},
                            {0, 0, 0},
                    },
                    {
                            {0, 20, 0},
                            {0, 20, 20},
                            {0, 20, 0},
                    },
                    {
                            {0, 0, 0},
                            {20, 20, 20},
                            {0, 20, 0},
                    },
                    {
                            {0, 20, 0},
                            {20, 20, 0},
                            {0, 20, 0},
                    },
            },
            {
                    {
                            {30, 0, 0},
                            {30, 30, 30},
                            {0, 0, 0},
                    },
                    {
                            {0, 30, 30},
                            {0, 30, 0},
                            {0, 30, 0},
                    },
                    {
                            {0, 0, 0},
                            {30, 30, 30},
                            {0, 0, 30},
                    },
                    {
                            {0, 30, 0},
                            {0, 30, 0},
                            {30, 30, 0},
                    },
            },
            {
                    {
                            {0, 0, 40},
                            {40, 40, 40},
                            {0, 0, 0},
                    },
                    {
                            {0, 40, 0},
                            {0, 40, 0},
                            {0, 40, 40},
                    },
                    {
                            {0, 0, 0},
                            {40, 40, 40},
                            {40, 0, 0},
                    },
                    {
                            {40, 40, 0},
                            {0, 40, 0},
                            {0, 40, 0},
                    },
            },
            {
                    {
                            {0, 50, 0},
                            {50, 50, 0},
                            {50, 0, 0},
                    },
                    {
                            {50, 50, 0},
                            {0, 50, 50},
                            {0, 0, 0},
                    },
                    {
                            {0, 50, 0},
                            {50, 50, 0},
                            {50, 0, 0},
                    },
                    {
                            {50, 50, 0},
                            {0, 50, 50},
                            {0, 0, 0},
                    },
            },
            {
                    {
                            {0, 60, 0},
                            {0, 60, 60},
                            {0, 0, 60},
                    },
                    {
                            {0, 0, 0},
                            {0, 60, 60},
                            {60, 60, 0},
                    },
                    {
                            {0, 60, 0},
                            {0, 60, 60},
                            {0, 0, 60},
                    },
                    {
                            {0, 0, 0},
                            {0, 60, 60},
                            {60, 60, 0},
                    },
            },
            {
                    {
                            {0, 0, 0, 0},
                            {70, 70, 70, 70},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                    },
                    {
                            {0, 70, 0, 0},
                            {0, 70, 0, 0},
                            {0, 70, 0, 0},
                            {0, 70, 0, 0},
                    },
                    {
                            {0, 0, 0, 0},
                            {70, 70, 70, 70},
                            {0, 0, 0, 0},
                            {0, 0, 0, 0},
                    },
                    {
                            {0, 70, 0, 0},
                            {0, 70, 0, 0},
                            {0, 70, 0, 0},
                            {0, 70, 0, 0},
                    },
            },
    }; // end of setOfBlockArrays
}
