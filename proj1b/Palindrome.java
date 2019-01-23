
public class Palindrome<Generic> extends ArrayDeque<Generic>{
    Deque<Generic> deq;

    public Palindrome(){
        deq = new ArrayDeque<>();
    }
    public Deque<Character> wordToDeque(String word){

    for (int i = 0; i<"word".length(); i++){
        addLast(Character.toString(word.charAt(i)));
    }
    return deq;
    }

}