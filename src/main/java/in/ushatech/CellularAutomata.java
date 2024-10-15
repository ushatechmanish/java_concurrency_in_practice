package in.ushatech;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CellularAutomata
{
    private final Board mainBoard; // 2D board
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board mainBoard)
    {
        this.mainBoard = mainBoard;
        int count = Runtime.getRuntime().availableProcessors();

        this.barrier = new CyclicBarrier(count,
                () ->
                {
                    mainBoard.commitNewValue();
//                    barrier.reset(); barrier is reset automatically after all the worker threads complete their work

                });
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++)
        {
            workers[i] = new Worker(mainBoard.getSubBoard(count, i)); // divide the work in equal parts
        }
    }

    public void start()
    {
        for (int i = 0; i < workers.length; i++)
        {
            new Thread(workers[i]).start();
        }
        mainBoard.waitForConvergence(); // Moved outside the loop to wait for all workers to converge
    }

    private int computeValue(int row, int col)
    {
        // Compute value based on row, col (to be implemented)
        return 0; // Placeholder logic
    }

    private class Worker implements Runnable
    {
        private final Board board;

        public Worker(Board board)
        {
            this.board = board;
        }

        @Override
        public void run()
        {
            while (!board.hasConverged())
            {
                for (int row = 0; row < board.getMaxX(); row++)
                {
                    for (int col = 0; col < board.getMaxY(); col++)
                    {  // Corrected `board.getMaxY` syntax
                        board.setValue(row, col, computeValue(row, col)); // Assuming computeValue is defined elsewhere
                    }
                }
                // After calculation
                try
                {
                    barrier.await(); // All worker threads will wait here till all workers are done
                } catch (InterruptedException | BrokenBarrierException e)
                {
                    return; // Exit on exception
                }
            }
        }
    }
}

class Board
{
    private final int[][] values;
    private final int maxX;
    private final int maxY;
    private boolean converged = false;

    public Board(int maxX, int maxY)
    {
        this.maxX = maxX;
        this.maxY = maxY;
        this.values = new int[maxX][maxY];
    }

    public int getMaxX()
    {
        return maxX;
    }

    public int getMaxY()
    {
        return maxY;
    }

    public synchronized void setValue(int x, int y, int value)
    {
        values[x][y] = value;
    }

    public synchronized int getValue(int x, int y)
    {
        return values[x][y];
    }

    // Split the main board into sub-boards for workers
    public Board getSubBoard(int numWorkers, int workerIndex)
    {
        int subWidth = maxX / numWorkers;
        int startX = workerIndex * subWidth;
        int endX = (workerIndex == numWorkers - 1) ? maxX : startX + subWidth;

        Board subBoard = new Board(endX - startX, maxY);
        for (int x = startX; x < endX; x++)
        {
            for (int y = 0; y < maxY; y++)
            {
                subBoard.setValue(x - startX, y, this.getValue(x, y));
            }
        }
        return subBoard;
    }

    // Commit new values, indicating a change in state
    public synchronized void commitNewValue()
    {
        // Logic to merge sub-boards back to the main board (placeholder)
        // Mark the board as converged for simplicity
        converged = true;
    }

    // Check if the board has reached a stable state
    public boolean hasConverged()
    {
        return converged;
    }

    // This is called after the worker threads have finished processing
    public void waitForConvergence()
    {
        while (!hasConverged())
        {
            try
            {
                Thread.sleep(100); // Polling until convergence (placeholder logic)
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }
}
