package mypackage;

import java.util.LinkedList;
import java.util.List;

import mypackage.Board.Moves;
import mypackage.Board.Tile;

public class Move {
	
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
	private Moves previous=null;

	Move(Tile[] tTiles, int score) {
		this.score = score;
		normalize(tTiles);
		
		createMoveUP();
		if(java.util.Arrays.deepEquals(gridUP,grid))
			System.out.println("gridUP e Sorgente sono uguali");
		
		createMoveDOWN();
		if(java.util.Arrays.deepEquals(gridDOWN,grid))
			System.out.println("gridDOWN e Sorgente sono uguali");
		
		createMoveLEFT();
		if(java.util.Arrays.deepEquals(gridLEFT,grid))
			System.out.println("gridLEFT e Sorgente sono uguali");
		
		createMoveRIGHT();
		if(java.util.Arrays.deepEquals(gridRIGHT,grid))
			System.out.println("gridRIGHT e Sorgente sono uguali");
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
		System.out.println("Griglia Sorgente");
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				grid[i][j] = tTiles[index++].getValue();
				System.out.print(grid[i][j]+" ");
			}
			System.out.println();
		}
	}

	public void createMoveUP() {
		scoreUP = score;
		System.out.println("CreateMoveUP");
		for (int i = 0; i < 4; i++) {
			int[] line = getCol(grid, i, Moves.UP);
			int[] merged = mergeLine(moveLine(line), scoreUP);
			setLine(Moves.UP, i, merged);
		}
		System.out.println("GrigliaUP Generata:");
		for(int i=0;i<4;i++)
		{
			System.out.println();
			for(int j=0;j<4;j++)
				System.out.print(gridUP[i][j]+" ");
		}
		System.out.println();

	}

	public void createMoveDOWN() {
		System.out.println("CreateMoveDown");
		scoreDOWN = score;

		for (int i = 0; i < 4; i++) {
			int[] line = getCol(grid, i, Moves.DOWN);

			int[] merged = mergeLine(moveLine(line), scoreDOWN);
			setLine(Moves.DOWN, i, merged);
		}
		
		System.out.println("GrigliaDOWN Generata:");
		for(int i=0;i<4;i++)
		{
			System.out.println();
			for(int j=0;j<4;j++)
				System.out.print(gridDOWN[i][j]+" ");
		}
		System.out.println();

		
	}

	public void createMoveLEFT() {
		scoreLEFT = score;
		System.out.println("CreateMoveLEFT");

		for (int i = 0; i < 4; i++) {
			int[] line = getRow(grid, i, Moves.LEFT);

			int[] merged = mergeLine(moveLine(line), scoreLEFT);
			setLine(Moves.LEFT, i, merged);
		}
		
		System.out.println("GrigliaLEFT Generata:");
		for(int i=0;i<4;i++)
		{
			System.out.println();
			for(int j=0;j<4;j++)
				System.out.print(gridLEFT[i][j]+" ");
		}
		System.out.println();

	}

	public void createMoveRIGHT() {
		scoreRIGHT = score;
		System.out.println("CreateMoveRIGHT");

		for (int i = 0; i < 4; i++) {
			int[] line = getRow(grid, i, Moves.RIGHT);

			int[] merged = mergeLine(moveLine(line), scoreRIGHT);
			setLine(Moves.RIGHT, i, merged);
		}
		
		System.out.println("GrigliaRIGHT Generata:");
		for(int i=0;i<4;i++)
		{
			System.out.println();
			for(int j=0;j<4;j++)
				System.out.print(gridRIGHT[i][j]+" ");
		}
		System.out.println();

	}

	private int[] getRow(int[][] source, int index, Moves m) {
		int[] row = new int[4];

		if (m == Moves.LEFT) {
			row = source[index];
		} else if (m == Moves.RIGHT) {
			int count = 0;
			for (int i = 3; i >= 0; i--){
				row[count] = source[index][i];
				count++;
		}
			}
		
		System.out.print("GetRow "+m+" Result: ");
		for(int i=0;i<4;i++)
			System.out.print(row[i]+" ");
		System.out.println();
		
		return row;

	}

	private int[] getCol(int[][] source, int index, Moves m) {
		
		int[] col = new int[4];

		if (m == Moves.UP) {
			for (int i = 0; i < 4; i++)
				col[i] = source[i][index];
		} else if (m == Moves.DOWN) {
			int count = 0;
			for (int i = 3; i >= 0; i--){
				col[count] = source[i][index];
				count++;
			}
		}
		System.out.print("GetCol "+m+" Result: ");
		for(int i=0;i<4;i++)
			System.out.print(col[i]+" ");
		System.out.println();
		
		return col;
	}

	private int[] moveLine(int[] oldLine) {
		
		System.out.print("MoveLine Input: ");
		for(int i=0;i<4;i++)
			System.out.print(oldLine[i]+" ");
		System.out.println();
		
		LinkedList<Integer> l = new LinkedList<Integer>();
		for (int i = 0; i < 4; i++) {
			if (!(oldLine[i] == 0))
			{
				l.add(oldLine[i]);
			}
		}
		if (l.size() == 0) {
			return oldLine;
		}else{
			int[] newLine = new int[4];
			
	
			for (int i = 0; i < l.size(); i++) {
					newLine[i] = l.get(i).intValue();
			}
			System.out.print("MoveLine Result: ");
			for(int i=0;i<4;i++)
				System.out.print(newLine[i]+" ");
			System.out.println();
			
			return newLine;
		}
	}

	private int[] mergeLine(int[] oldLine, int score) {
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		System.out.println("MergeLine Input: ");
		for(int i=0;i<4;i++)
			System.out.print(oldLine[i]+" ");
		System.out.println();

		for (int i = 0; i < 4 && !(oldLine[i]==0); i++) {
			int num = oldLine[i];
			if (i < 3 && oldLine[i] == oldLine[i + 1]) {
				System.out.println("Trovato confronto"+oldLine[i]+"  "+oldLine[i+1]);
				num *= 2;
				score += num;
				i++;
			}

			list.add(new Integer(num));
		}
		if (list.size() == 0) {
			System.out.println("Ritorno OLD");
			return oldLine;
		} else {
			System.out.print("MergeLine Result: ");
			for(Integer n : list)
				System.out.print(n+" ");
			System.out.println();
			
			
			System.out.print("MergeLine Converted Result: ");
			int temp[] = toArrayInt(list);
			for(int i=0;i<4;i++)
				System.out.print(temp[i]+" ");
			System.out.println();
			
			return toArrayInt(list);
		}
	}

	private void setLine(Moves m, int index, int[] re) {
		int count = 0;

		switch (m) {
		case UP:
			for (int i=0;i<4;i++)
				gridUP[i][index] = re[i];
			break;
		case DOWN:
			count = 0;
			for (int i = 3; i >= 0; i--)
				gridDOWN[count++][index] = re[i];
			break;
		case LEFT:
			for (int i = 0; i < 4; i++)
				gridLEFT[index][i] = re[i];
			break;
		case RIGHT:
			count = 0;
			for (int i = 3; i >= 0; i--)
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

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Moves getPrevious() {
		return previous;
	}

	public void setPrevious(Moves moves) {
		this.previous = moves;
	}
	
	
}