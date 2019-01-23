public interface Deque<Generic>{
    public void addFirst(Generic T);

    public void addLast(Generic T);

    public boolean isEmpty();

    public int size();

    public void printDeque();

    public Generic removeFirst();

    public Generic removeLast();

    public Generic get(int index);

}