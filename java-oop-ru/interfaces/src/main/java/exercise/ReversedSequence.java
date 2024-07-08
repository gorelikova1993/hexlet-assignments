package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private String string;

    public ReversedSequence(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            result = str.charAt(i) + result;
        }
        this.string = result;
    }

    @Override
    public int length() {
        return string.length();
    }

    @Override
    public char charAt(int index) {
        char[] arr = string.toCharArray();
        return arr[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        char[] arr = string.toCharArray();
        String result = "";
        for (int i = start; i < end; i++) {
            result += arr[i];
        }
        return result;
    }
}
// END
