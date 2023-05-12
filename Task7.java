import java.util.ArrayList;

class Heap 
{
    void heapify(ArrayList<Integer> hT, int i, int size) 
    {
        int smallest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < size && hT.get(l) < hT.get(smallest))
        {
            smallest = l;
        }
        if (r < size && hT.get(r) < hT.get(smallest))
        {
            smallest = r;
        }
        if (smallest != i) 
        {
            int temp = hT.get(smallest);
            hT.set(smallest, hT.get(i));
            hT.set(i, temp);
            heapify(hT, smallest, size);
        }
    }

    void buildHeap(ArrayList<Integer> hT, int size) 
    {
        for (int i = size / 2 - 1; i >= 0; i--) 
        {
            heapify(hT, i, size);
        }
    }

    void heapsort(ArrayList<Integer> hT, int size) 
    {
        buildHeap(hT, size);
        for (int i = size - 1; i > 0; i--) 
        {
            int temp = hT.get(0);
            hT.set(0, hT.get(i));
            hT.set(i, temp);
            heapify(hT, 0, i);
        }
    }

    void printArray(ArrayList<Integer> array, int size) 
    {
        for (Integer i : array) 
        {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) 
    {
        ArrayList<Integer> array = new ArrayList<Integer>();
        array.add(3);
        array.add(4);
        array.add(9);
        array.add(5);
        array.add(2);

        int size = array.size();
        Heap h = new Heap();
        System.out.println("Unsorted array:");
        h.printArray(array, size);
        h.heapsort(array, size);
        System.out.println("Sorted array:");
        h.printArray(array, size);
    }
}