package mypackage;

import java.util.LinkedList;
import java.util.List;

import mypackage.Board.Tile;

public class Move {
	enum Moves {
		UP, DOWN, LEFT, RIGHT;
	}

	

	private int grid[][];
	private int score;
	public int gridUP[][];
	private int scoreUP=0;
	public int gridDOWN[][];
	private int scoreDOWN=0;
	public int gridLEFT[][];
	private int scoreLEFT=0;
	public int gridRIGHT[][];
	private int scoreRIGHT=0;

	Move(Tile[] tTiles, int score) {
		this.score = score;
		normalize(tTiles);
		createMoveUP();
		createMoveDOWN();
		createMoveLEFT();
		createMoveRIGHT();
	}

	public void normalize(Tile[] tTiles) {
		grid = new int[4][4];
		
		gridUP=new int[4][4];
		scoreUP=0;
		gridDOWN=new int[4][4];;
		scoreDOWN=0;
		gridLEFT=new int[4][4];;
		scoreLEFT=0;
		gridRIGHT=new int[4][4];;
		scoreRIGHT=0;
		
		int index = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				grid[i][j] = tTiles[index++].getValue();
			}
		}
	}

	public void createMoveUP() {
		scoreUP = score;

		for (int i = 0; i < 4; i++) {
			int[] line = getCol(grid, i, Moves.UP);
			int[] merged = mergeLine(moveLine(line), scoreUP);
			setLine(Moves.UP, i, merged);
		}
	}

	public void createMoveDOWN() {
		scoreDOWN = score;

		for (int i = 0; i < 4; i++) {
			int[] line = getCol(grid, i, Moves.DOWN);

			int[] merged = mergeLine(moveLine(line), scoreDOWN);
			setLine(Moves.DOWN, i, merged);
		}
	}

	public void createMoveLEFT() {
		scoreLEFT = score;

		for (int i = 0; i < 4; i++) {
			int[] line = getRow(grid, i, Moves.LEFT);

			int[] merged = mergeLine(moveLine(line), scoreLEFT);
			setLine(Moves.LEFT, i, merged);
		}
	}

	public void createMoveRIGHT() {
		scoreRIGHT = score;

		for (int i = 0; i < 4; i++) {
			int[] line = getRow(grid, i, Moves.RIGHT);

			int[] merged = mergeLine(moveLine(line), scoreRIGHT);
			setLine(Moves.RIGHT, i, merged);
		}
	}

	private int[] getRow(int[][] source, int index, Moves m) {

		int[] row = new int[4];

		if (m == Moves.LEFT) {
			row = source[index];
		} else if (m == Moves.RIGHT) {
			int count = 0;
			for (int i = 3; i <= 0; i--)
				row[count++] = source[index][i];
		}
		return row;

	}

	private int[] getCol(int[][] source, int index, Moves m) {
		int[] col = new int[4];

		if (m == Moves.UP) {
			for (int i = 0; i < 4; i++)
				col[i] = source[i][index];
		} else if (m == Moves.DOWN) {
			int count = 0;
			for (int i = 3; i <= 0; i--)
				col[count++] = source[i][index];
		}
		return col;
	}

	private int[] moveLine(int[] oldLine) {
		System.out.println("moveLine dim oldLine->"+oldLine.length);
		
		LinkedList<Integer> l = new LinkedList<Integer>();
		for (int i = 0; i < 4; i++) {
			if (!(oldLine[i] == 0))
				l.addLast(oldLine[i]);
		}
		if (l.size() == 0) {
			return oldLine;
		}else{
			int[] newLine = new int[4];
			for (int i = 0; i < l.size(); i++) {
				newLine[i] = l.removeFirst().intValue();
			}
			return newLine;
		}
	}

	private int[] mergeLine(int[] oldLine, int score) {
		LinkedList<Integer> list = new LinkedList<Integer>();

		for (int i = 0; i < 4 && !(oldLine[i] == 0); i++) {
			int num = oldLine[i];
			if (i < 3 && oldLine[i] == oldLine[i + 1]) {
				num *= 2;
				score += num;
			}
			i++;
			list.add(new Integer(num));
		}
		if (list.size() == 0) {
			return oldLine;
		} else {
			return toArrayInt(list);
		}
	}

	private void setLine(Moves m, int index, int[] re) {
		System.out.println("setLine---> "+ index +" dim re--->"+re.length);
		int count = 0;

		switch (m) {
		case UP:
			for (int i=0;i<4;i++)
			{
				System.out.println("SetLine->caseUp-> i="+i+" index="+index+" re[0]="+re[0]+" re[1]="+re[1]+" re[2]="+re[2]+" re[3]="+re[3]);
				gridUP[i][index] = re[i];
				
			}
			break;
		case DOWN:
			count = 0;
			for (int i = 3; i <= 0; i--)
				gridDOWN[count++][index] = re[i];
			break;
		case LEFT:
			for (int i = 0; i < 4; i++)
				gridLEFT[index][i] = re[i];
			break;
		case RIGHT:
			count = 0;
			for (int i = 3; i <= 0; i--)
				gridRIGHT[index][count++] = re[i];
			break;
		}

	}

	public int[] toArrayInt(List<Integer> list) {
		int[] ret = new int[4];
		int i = 0;
		for (Integer e : list)
			ret[i++] = e;
		return ret;
	}

	public int[][] getGridUP() {
		return gridUP;
	}

	public void setGridUP(int[][] gridUP) {
		this.gridUP = gridUP;
	}

	public int getScoreUP() {
		return scoreUP;
	}

	public void setScoreUP(int scoreUP) {
		this.scoreUP = scoreUP;
	}

	public int[][] getGridDOWN() {
		return gridDOWN;
	}

	public void setGridDOWN(int[][] gridDOWN) {
		this.gridDOWN = gridDOWN;
	}

	public int getScoreDOWN() {
		return scoreDOWN;
	}

	public void setScoreDOWN(int scoreDOWN) {
		this.scoreDOWN = scoreDOWN;
	}

	public int[][] getGridLEFT() {
		return gridLEFT;
	}

	public void setGridLEFT(int[][] gridLEFT) {
		this.gridLEFT = gridLEFT;
	}

	public int getScoreLEFT() {
		return scoreLEFT;
	}

	public void setScoreLEFT(int scoreLEFT) {
		this.scoreLEFT = scoreLEFT;
	}

	public int[][] getGridRIGHT() {
		return gridRIGHT;
	}

	public void setGridRIGHT(int[][] gridRIGHT) {
		this.gridRIGHT = gridRIGHT;
	}

	public int getScoreRIGHT() {
		return scoreRIGHT;
	}

	public void setScoreRIGHT(int scoreRIGHT) {
		this.scoreRIGHT = scoreRIGHT;
	}
	
	
}