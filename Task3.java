public abstract class TwoColorDoubleStackADT<E> 
{
    private int redTop;
    private int blueTop;
    private int capacity;
    private E[] array;

    public TwoColorDoubleStackADT(int capacity) 
    {
        this.capacity = capacity;
        array = (E[]) new Object[capacity];
        redTop = -1;
        blueTop = capacity;
    }

    public void redPush(E item) 
    {
        if (redTop + 1 >= blueTop) 
        {
            throw new IllegalStateException("Stack overflowed!");
        }
        redTop++;
        array[redTop] = item;
    }

    public E redPop() 
    {
        if (redTop == -1) 
        {
            throw new NoSuchElementException("Stack underflowed!");
        }
        E item = array[redTop];
        array[redTop] = null;
        redTop--;
        return item;
    }

    public boolean isRedEmpty() 
    {
        return redTop == -1;
    }

    public void bluePush(E item) 
    {
        if (blueTop - 1 <= redTop) 
        {
            throw new IllegalStateException("Stack overflowed!");
        }
        blueTop--;
        array[blueTop] = item;
    }

    public E bluePop() 
    {
        if (blueTop == capacity) 
        {
            throw new NoSuchElementException("Stack underflowed!");
        }
        E item = array[blueTop];
        array[blueTop] = null;
        blueTop++;
        return item;
    }

    public boolean isBlueEmpty() 
    {
        return blueTop == capacity;
    }
}