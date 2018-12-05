package ssu.rubicom.tetris;

public class CTetris extends Tetris {

    public CTetris(int cy, int cx) throws Exception {
        super(cy, cx);
    }

    public void printScreen() {
        int dy = oScreen.get_dy();
        int dx = oScreen.get_dx();
        int dw = iScreenDw;
        int[][] array = oScreen.get_array();

        for (int y = 0; y < dy - dw + 1; y++) {
            for (int x = dw - 1; x < dx - dw + 1; x++) {
                if (array[y][x] == 0)
                    System.out.print("□ ");
                else if (array[y][x] == 10)
                    System.out.print("◈ ");
                else if (array[y][x] == 20)
                    System.out.print("★ ");
                else if (array[y][x] == 30)
                    System.out.print("● ");
                else if (array[y][x] == 40)
                    System.out.print("◆ ");
                else if (array[y][x] == 50)
                    System.out.print("▲ ");
                else if (array[y][x] == 60)
                    System.out.print("♣ ");
                else if (array[y][x] == 70)
                    System.out.print("♥ ");
                else
                    System.out.print("■ ");
            }
            System.out.println();
        }
    }
    private Matrix int2bool(Matrix m) throws Exception {
        Matrix b = new Matrix(m);
        int cy = b.get_dy();
        int cx = b.get_dx();
        int[][] array = b.get_array();
        for (int y = 0; y < cy; y++) {
            for (int x = 0; x < cx; x++)
                array[y][x] = (array[y][x] != 0 ? 1 : 0);
        }
        return b;
    }

    protected Matrix deleteFullLines(Matrix screen, Matrix blk, int top, int dy, int dx, int dw) throws Exception {
        Matrix line, line2, zero, temp;
        if (blk == null) return screen; // called right after the game starts!!
        int cy, y, nDeleted = 0,nScanned = blk.get_dy();
        if (top + blk.get_dy() - 1 >= dy)
            nScanned -= (top + blk.get_dy() - dy);
        zero = new Matrix(1, dx - 2*dw);
        for (y = nScanned - 1; y >= 0 ; y--) {
            cy = top + y + nDeleted;
            line = screen.clip(cy, 0, cy + 1, screen.get_dx());
            line2 = int2bool(line); // convert color-valued matrix to binary matrix
            if (line2.sum() == screen.get_dx()) { // use binary matrix
                temp = screen.clip(0, 0, cy, screen.get_dx());
                screen.paste(temp, 1, 0);
                screen.paste(zero, 0, dw);
                nDeleted++;
            }
        }
        return screen;
    }
    public TetrisState accept(char key) throws Exception {
        Matrix tempBlk, tempBlk2, currBlk2;
        if (state == TetrisState.NewBlock) {
            oScreen = deleteFullLines(oScreen, currBlk, top, iScreenDy, iScreenDx, iScreenDw);
            iScreen.paste(oScreen, 0, 0);
            state = TetrisState.Running;
            idxBlockType = key - '0'; // copied from key
            idxBlockDegree = 0;
            currBlk = setOfBlockObjects[idxBlockType][idxBlockDegree];
            top = 0;
            left = iScreenDw + iScreenDx / 2 - (currBlk.get_dx()+1) / 2;
            tempBlk = iScreen.clip(top, left, top+currBlk.get_dy(), left+currBlk.get_dx());
            tempBlk2 = int2bool(tempBlk); // convert color-valued matrix to binary matrix
            currBlk2 = int2bool(currBlk); // convert color-valued matrix to binary matrix
            tempBlk2 = tempBlk2.add(currBlk2); // use binary matrix
            tempBlk = tempBlk.add(currBlk);
            oScreen.paste(iScreen, 0, 0);
            oScreen.paste(tempBlk, top, left); System.out.println();
            if (tempBlk2.anyGreaterThan(1)) { // use binary matrix
                state = TetrisState.Finished;	// System.out.println("Game Over!");
                return state;	// System.exit(0);
            }
            return state;		// should require a key input
        }
        // while ((key = getKey()) != 'q') {
        switch(key) {
            case 'a': left--; break; // move left
            case 'd': left++; break; // move right
            case 's': top++; break; // move down
            case 'w': // rotateCW
                idxBlockDegree = (idxBlockDegree+1)%nBlockDegrees;
                currBlk = setOfBlockObjects[idxBlockType][idxBlockDegree];
                break;
            case ' ': // drop the block
                do {
                    top++;
                    tempBlk = iScreen.clip(top, left, top+currBlk.get_dy(), left+currBlk.get_dx());
                    tempBlk2 = int2bool(tempBlk); // convert color-valued matrix to binary matrix
                    currBlk2 = int2bool(currBlk); // convert color-valued matrix to binary matrix
                    tempBlk2 = tempBlk2.add(currBlk2); // use binary matrix
                } while (tempBlk2.anyGreaterThan(1) == false); // use binary matrix
                break;
            default: System.out.println("unknown key!");
        }
        tempBlk = iScreen.clip(top, left, top+currBlk.get_dy(), left+currBlk.get_dx());
        tempBlk2 = int2bool(tempBlk); // convert color-valued matrix to binary matrix
        currBlk2 = int2bool(currBlk); // convert color-valued matrix to binary matrix
        tempBlk2 = tempBlk2.add(currBlk2); // use binary matrix
        tempBlk = tempBlk.add(currBlk);
        if (tempBlk2.anyGreaterThan(1)) { // use binary matrix
            switch(key) {
                case 'a': left++; break; // undo: move right
                case 'd': left--; break; // undo: move left
                case 's': top--; state = TetrisState.NewBlock; break; // undo: move up
                case 'w': // undo: rotateCCW
                    idxBlockDegree = (idxBlockDegree+nBlockTypes-1)%nBlockDegrees;
                    currBlk = setOfBlockObjects[idxBlockType][idxBlockDegree];
                    break;
                case ' ': top--; state = TetrisState.NewBlock; break; // undo: move up
            }
            tempBlk = iScreen.clip(top, left, top+currBlk.get_dy(), left+currBlk.get_dx());
            tempBlk = tempBlk.add(currBlk);
        }
        oScreen.paste(iScreen, 0, 0);
        oScreen.paste(tempBlk, top, left);
        // printScreen(oScreen); System.out.println();
        return state;
        // if (newBlockNeeded) { ... }
        // } end of while
    }
}



