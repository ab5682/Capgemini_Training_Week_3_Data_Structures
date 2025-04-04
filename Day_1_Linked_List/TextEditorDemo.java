class TextState {
    String text;
    TextState prev;
    TextState next;

    public TextState(String text) {
        this.text = text;
    }
}

class TextEditor {
    private TextState head;
    private TextState tail;
    private TextState current;
    private int size = 0;
    private final int MAX_HISTORY = 10;

    public void addState(String newText) {
        TextState newState = new TextState(newText);

        if (current != null && current.next != null) {
            current.next.prev = null;
            current.next = null;
        }

        if (tail != null) {
            tail.next = newState;
            newState.prev = tail;
        } else {
            head = newState;
        }

        tail = newState;
        current = newState;
        size++;

        if (size > MAX_HISTORY) {
            head = head.next;
            head.prev = null;
            size--;
        }
    }

    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Undo done.");
        } else {
            System.out.println("No more undo available.");
        }
    }

    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Redo done.");
        } else {
            System.out.println("No more redo available.");
        }
    }

    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current Text: " + current.text);
        } else {
            System.out.println("No content.");
        }
    }
}

public class TextEditorDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.addState("Hello");
        editor.addState("Hello World");
        editor.addState("Hello World!");
        editor.displayCurrentState();  

        editor.undo();
        editor.displayCurrentState();  

        editor.undo();
        editor.displayCurrentState(); 

        editor.redo();
        editor.displayCurrentState();  

        editor.addState("Hello Again");
        editor.displayCurrentState(); 

        editor.redo();  
    }
}
