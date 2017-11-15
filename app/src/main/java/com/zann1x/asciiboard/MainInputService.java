package com.zann1x.asciiboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

public class MainInputService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private InputMethodManager inputMethodManager;

    private AsciiBoard asciiBoard;
    private AsciiBoardView asciiBoardView;

    @Override
    public void onCreate() {
        super.onCreate();
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.showInputMethodPicker();
    }

    @Override
    public void onInitializeInterface() {
        /*if (asciiBoard == null) {
            asciiBoard = new AsciiBoard(this, R.xml.ascii);
        }*/
    }

    @Override
    public View onCreateInputView() {
        asciiBoard = new AsciiBoard(this, R.xml.ascii);

        asciiBoardView = (AsciiBoardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        asciiBoardView.setOnKeyboardActionListener(this);
        asciiBoardView.setKeyboard(asciiBoard);

        return asciiBoardView;
    }

    // Implementation of KeyboardView.OnKeyboardActionListener

    @Override
    public void onPress(int primaryCode) {

    }

    @Override
    public void onRelease(int primaryCode) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection inputConnection = getCurrentInputConnection();
        switch(primaryCode) {
            default:
                char code = (char) primaryCode;
                inputConnection.commitText(String.valueOf(code), 1);
        }
    }

    @Override
    public void onText(CharSequence text) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
