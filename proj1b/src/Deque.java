public interface Deque<Item>{
    public void addFirst(Item T);

    public void addLast(Item T);

    public boolean isEmpty();

    public int size();

    public void printDeque();

    public Item removeFirst();

    public Item removeLast();

    public Item get(int index);

}