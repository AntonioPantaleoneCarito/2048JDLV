package mypackage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Board extends JPanel implements KeyListener {
	private static final int TILE_SIZE = 64;
	private static final int TILES_MARGIN = 16;
	private static final Color BG_COLOR = new Color(0xbbada0);
	private static final String FONT_NAME = "Arial";
	private static Solver AI;
	private boolean auto=false;
	
	private Tile[] tiles;

	private boolean win, lose;
	private int score;
	private int scoreAI;

	public Board() {
		setPreferredSize(new Dimension(340, 400));
		setFocusable(true);
		addKeyListener(this);
		resetGame();
	}

	public void resetGame() {
		score = 0;
		win = false;
		lose = false;
		tiles = new Tile[4 * 4];
		AI = new Solver();
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = new Tile();
		}
		init(1);
	}

	private void init(int code) {
		if (code == 1) {
			addTile();
			addTile();
		}
	}

	private void addTile() {
		List<Tile> list = availableSpace();
		if (!availableSpace().isEmpty()) {
			int index = (int) (Math.random() * list.size()) % list.size();
			Tile emptyTime = list.get(index);
			emptyTime.value = Math.random() < 0.9 ? 2 : 4; // 90% Uscita 2
		}
	}

	private List<Tile> availableSpace() {
		final List<Tile> list = new ArrayList<Tile>(16);
		for (Tile t : tiles) {
			if (t.isEmpty()) {
				list.add(t);
			}
		}
		return list;
	}

	private boolean isFull() {
		return availableSpace().size() == 0;
	}

	boolean canMove() {
		if (!isFull()) {
			return true;
		}
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				Tile t = tileAt(x, y);
				if ((x < 3 && t.value == tileAt(x + 1, y).value)
						|| ((y < 3) && t.value == tileAt(x, y + 1).value)) {
					return true;
				}
			}
		}
		return false;
	}

	private Tile tileAt(int x, int y) {
		return tiles[x + y * 4];
	}
	private Tile tileAtAI(Tile[] AItiles, int x, int y)
	{
		return AItiles[x + y * 4];
	}
	
	private boolean compare(Tile[] line1, Tile[] line2) {
		if (line1 == line2) {
			return true;
		} else if (line1.length != line2.length) {
			return false;
		}

		for (int i = 0; i < line1.length; i++) {
			if (line1[i].value != line2[i].value) {
				return false;
			}
		}
		return true;
	}

	private Tile[] mergeLine(Tile[] oldLine) {
		LinkedList<Tile> list = new LinkedList<Tile>();
		for (int i = 0; i < 4 && !oldLine[i].isEmpty(); i++) 
		{
			int num = oldLine[i].value;
			if (i < 3 && oldLine[i].value == oldLine[i + 1].value) {
				num *= 2;
				score += num;
				int ourTarget = 2048;
				if (num == ourTarget) {
					win = true;
				}
				i++;
			}
			list.add(new Tile(num));
		}
		if (list.size() == 0) {
			return oldLine;
		} else {
			ensureSize(list, 4);
			return list.toArray(new Tile[4]);
		}
	}

	private static void ensureSize(java.util.List<Tile> l, int s) {
		while (l.size() != s) {
			l.add(new Tile());
		}
	}

	private Tile[] getLine(int index) {
		Tile[] result = new Tile[4];
		for (int i = 0; i < 4; i++) {
			result[i] = tileAt(i, index);
		}
		return result;
	}
	private Tile[] getLineAI(Tile[] AItiles,int index) {
		Tile[] result = new Tile[4];
		for (int i = 0; i < 4; i++) {
			result[i] = tileAtAI(AItiles,i, index);
		}
		return result;
	}

	private void setLine(int index, Tile[] re) {
		System.arraycopy(re, 0, tiles, index * 4, 4);
	}

	private Tile[] rotate(int angle) {
		Tile[] newTiles = new Tile[4 * 4];
		int offsetX = 3, offsetY = 3;
		if (angle == 90) {
			offsetY = 0;
		} else if (angle == 270) {
			offsetX = 0;
		}

		double rad = Math.toRadians(angle);
		int cos = (int) Math.cos(rad);
		int sin = (int) Math.sin(rad);
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				int newX = (x * cos) - (y * sin) + offsetX;
				int newY = (x * sin) + (y * cos) + offsetY;
				newTiles[(newX) + (newY) * 4] = tileAt(x, y);
			}
		}
		return newTiles;
	}

	private Tile[] moveLine(Tile[] oldLine) {
		LinkedList<Tile> l = new LinkedList<Tile>();
		for (int i = 0; i < 4; i++) {
			if (!oldLine[i].isEmpty())
				l.addLast(oldLine[i]);
		}
		if (l.size() == 0) {
			return oldLine;
		} else {
			Tile[] newLine = new Tile[4];
			ensureSize(l, 4);
			for (int i = 0; i < 4; i++) {
				newLine[i] = l.removeFirst();
			}
			return newLine;
		}
	}

	public Tile[] leftAI(){
		Tile[] AItiles = tiles;
		for(int i=0;i<4;i++)
		{
			Tile [] line = getLineAI(AItiles,i);
		//	Tile[] merged = mergeLineAI(moveLine(line));
			
		}
		
		
		
		
		return AItiles;
		
	}
	
	public void left() {
		boolean needAddTile = false;
		for (int i = 0; i < 4; i++) {
			Tile[] line = getLine(i);
			Tile[] merged = mergeLine(moveLine(line));
			setLine(i, merged);
			if (!needAddTile && !compare(line, merged)) {
				needAddTile = true;
			}
		}

		if (needAddTile) {
			addTile();
		}
	}

	public void right() {
		tiles = rotate(180);
		left();
		tiles = rotate(180);
	}

	public void up() {
		tiles = rotate(270);
		left();
		tiles = rotate(90);
	}

	public void down() {
		tiles = rotate(90);
		left();
		tiles = rotate(270);
	}

	static class Tile {

		int value;

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public Tile() {
			this(0);
		}

		public Tile(int num) {
			value = num;
		}

		public boolean isEmpty() {
			return value == 0;
		}

		public Color getForeground() {
			return value < 16 ? new Color(0x776e65) : new Color(0xf9f6f2);
		}

		public Color getBackground() { // In base al valore setta il colore
										// della cella
			switch (value) {
			case 2:
				return new Color(0xeee4da);
			case 4:
				return new Color(0xede0c8);
			case 8:
				return new Color(0xf2b179);
			case 16:
				return new Color(0xf59563);
			case 32:
				return new Color(0xf67c5f);
			case 64:
				return new Color(0xf65e3b);
			case 128:
				return new Color(0xedcf72);
			case 256:
				return new Color(0xedcc61);
			case 512:
				return new Color(0xedc850);
			case 1024:
				return new Color(0xedc53f);
			case 2048:
				return new Color(0xedc22e);
			}
			return new Color(0xcdc1b4);
		}

	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			resetGame();
		}
		if (!canMove()) {
			lose = true;
		}

		if (!win && !lose) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				left();
				break;
			case KeyEvent.VK_RIGHT:
				right();
				break;
			case KeyEvent.VK_DOWN:
				down();
				break;
			case KeyEvent.VK_UP:
				up();
				break;
			}
		}

		if (!win && !canMove()) {
			lose = true;
		}

		repaint();
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(BG_COLOR);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				drawTile(g, tiles[x + y * 4], x, y);
			}
		}
		
	}

	private void drawTile(Graphics g2, Tile tile, int x, int y) {
		Graphics2D g = ((Graphics2D) g2);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
				RenderingHints.VALUE_STROKE_NORMALIZE);
		int value = tile.value;
		int xOffset = offsetCoors(x);
		int yOffset = offsetCoors(y);
		g.setColor(tile.getBackground());
		g.fillRoundRect(xOffset, yOffset, TILE_SIZE, TILE_SIZE, 14, 14);
		g.setColor(tile.getForeground());
		final int size = value < 100 ? 36 : value < 1000 ? 32 : 24;
		final Font font = new Font(FONT_NAME, Font.BOLD, size);
		g.setFont(font);

		String s = String.valueOf(value);
		final FontMetrics fm = getFontMetrics(font);

		final int w = fm.stringWidth(s);
		final int h = -(int) fm.getLineMetrics(s, g).getBaselineOffsets()[2];

		if (value != 0)
			g.drawString(s, xOffset + (TILE_SIZE - w) / 2, yOffset + TILE_SIZE
					- (TILE_SIZE - h) / 2 - 2);

		if (win || lose) {
			g.setColor(new Color(255, 255, 255, 30));
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(new Color(78, 139, 202));
			g.setFont(new Font(FONT_NAME, Font.BOLD, 48));
			if (win) {
				g.drawString("You won!", 68, 150);
			}
			if (lose) {
				g.drawString("Game over!", 50, 130);
				g.drawString("You lose!", 64, 200);
			}
			if (win || lose) {
				g.setFont(new Font(FONT_NAME, Font.PLAIN, 16));
				g.setColor(new Color(128, 128, 128, 128));
				g.drawString("Press ESC to play again", 80, getHeight() - 40);
			}
		}
		g.setFont(new Font(FONT_NAME, Font.PLAIN, 18));
		g.drawString("Score: " + score, 200, 365);

	}

	private static int offsetCoors(int arg) {
		return arg * (TILES_MARGIN + TILE_SIZE) + TILES_MARGIN;
	}
	
	public void oneHint()
	{
		Move m = new Move(tiles, score);
		AI = new Solver(m);
		System.out.println(AI.res.get(0));
	
	}
	
	public void startAI()
	{
		while(auto && (!win || !lose))
		{
			oneHint();
		}
	}
	


	static class buttonPanel extends JPanel implements ActionListener
	{
		JButton hint=new JButton("Suggerimento");
	    JButton startAI=new JButton("Start Bot");
	    JButton stopAI= new JButton("Stop Bot");
	    Board b;
	    
	    public buttonPanel(Board b) {
	    	this.b = b;
	    	hint.addActionListener(this);
	    	startAI.addActionListener(this);
	    	stopAI.addActionListener(this);
	    	
			this.add(hint);
			this.add(startAI);
			this.add(stopAI);
		}

		public void actionPerformed(ActionEvent e) {
			 Object command = e.getActionCommand();
			 System.out.println(command);
		        if (command.equals("Suggerimento")){
		           b.auto = false;
		           b.oneHint();
		        }
		        else if (command.equals("Start Bot"))
		        {
		        	b.auto=true;
		        	b.startAI();
		        }
		        else if (command.equals("Stop Bot"))
		        {
		        	b.auto=false;
		        }
		        
			
		}
		
	}
	
	
	public static void main(String[] args) {
	    JFrame game = new JFrame();
	    Board board = new Board();
	    buttonPanel btPanel = new buttonPanel(board);
	    game.setTitle("2048 Game with IA");
	    game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    game.setSize(340, 400);
	    game.setResizable(false);
	    game.setLayout(new BorderLayout());

	    game.add(board,BorderLayout.CENTER);
	    game.add(btPanel,BorderLayout.SOUTH);
	   
		
	    game.setLocationRelativeTo(null);
	    game.setVisible(true);

	}


}


