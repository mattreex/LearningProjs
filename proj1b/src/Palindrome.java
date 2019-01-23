public class Palindrome<Item> extends LinkedListDeque<Item> {

    public static Deque<Character> wordToDeque(String word) {
        Deque<Character> deq = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deq.addLast(word.charAt(i));
        }
        return deq;
    }


    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else if (word.length() % 2 == 0) {
            Deque<Character> Deq1 = wordToDeque(word.substring(0, word.length() / 2));
            Deque<Character> Deq2 = wordToDeque(word.substring(word.length() / 2));
            for (int i = 0; i < word.length() / 2; i++) {
                if (Deq1.removeFirst() != Deq2.removeLast()) {
                    return false;
                }
            }

        } else if (word.length() % 2 != 0) {
            Deque<Character> Deq1 = wordToDeque(word.substring(0, word.length() / 2));
            Deque<Character> Deq2 = wordToDeque(word.substring(word.length() / 2 + 1));
            int i = 0;
            while (i < (word.length()/2)) {
                 if (Deq1.removeFirst() != Deq2.removeLast()) {
                    return false;
                }
                 i++;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else if (word.length() % 2 == 0) {
            Deque<Character> Deq1 = wordToDeque(word.substring(0, word.length() / 2));
            Deque<Character> Deq2 = wordToDeque(word.substring(word.length() / 2));
            for (int i = 0; i < word.length() / 2; i++) {
                if (!cc.equalChars(Deq1.removeFirst(), Deq2.removeLast())) {
                    return false;
                }
            }

        } else if (word.length() % 2 != 0) {
            Deque<Character> Deq1 = wordToDeque(word.substring(0, word.length() / 2));
            Deque<Character> Deq2 = wordToDeque(word.substring(word.length() / 2 + 1));
            int i = 0;
            while (i < (word.length()/2)) {
                if (!cc.equalChars(Deq1.removeFirst(), Deq2.removeLast())) {
                    return false;
                }
                i++;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Palindrome ok = new Palindrome();
        System.out.println(ok.isPalindrome("m"));
        System.out.println(ok.isPalindrome("neven"));
        System.out.println(ok.isPalindrome(""));
        System.out.println(ok.isPalindrome("mlaskjnkey"));
        Deque<Character> tro = wordToDeque("freeze");
        System.out.println(tro.get(3));
        CharacterComparator me = new OffByN(3);
        System.out.println(ok.isPalindrome("ad", me));

    }

}
