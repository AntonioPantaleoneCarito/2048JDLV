package mypackage;

public class ThreadAI extends Thread {

    protected boolean isRunning;
    protected boolean isFinished;
    protected Board board;
    
    
    public ThreadAI(Board board) {
        this.board = board;
        this.isRunning = true;
        this.isFinished = false;
    }
    
    public void run() {
        while (isRunning && (!board.isWin() || !board.isLose())) {
           board.oneHint();
        }
        this.isFinished = true;
    }
    
    public void stopRunning() {
        this.isRunning = false;
        while (!isFinished) {
            try {
                Thread.sleep(10L);
            } catch (InterruptedException e) {
            }
        }
    }

}
