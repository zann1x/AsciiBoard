package com.zann1x.asciiboard;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.IBinder;
import android.renderscript.ScriptGroup;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainInputService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private InputMethodManager inputMethodManager;

    private LinearLayout mainBoard;
    private ScrollView scrollView;
    private RecyclerView asciiBoardView;
    private KeyboardView keyboardView;

    @Override
    public void onCreate() {
        super.onCreate();
        inputMethodManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public View onCreateInputView() {
        mainBoard = (LinearLayout) getLayoutInflater().inflate(R.layout.mainboard, null);

        asciiBoardView = (RecyclerView) getLayoutInflater().inflate(R.layout.recyclerview, null);
        asciiBoardView.setHasFixedSize(true);
        asciiBoardView.setLayoutManager(new LinearLayoutManager(this));
        asciiBoardView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        asciiBoardView.setAdapter(new AsciiFaceAdapter(this, AsciiFaceData.asciiFaces));

        scrollView = mainBoard.findViewById(R.id.scrollview);
        scrollView.addView(asciiBoardView);

        keyboardView = mainBoard.findViewById(R.id.keyboardview);
        keyboardView.setKeyboard(new Keyboard(this, R.xml.keyboard));
        keyboardView.setOnKeyboardActionListener(this);
        keyboardView.setPreviewEnabled(false);

        return mainBoard;
    }

    @Override
    public View onCreateCandidatesView() {
        return super.onCreateCandidatesView();
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

        String text = null;
        if (0 <= primaryCode && primaryCode < AsciiFaceData.asciiFaces.size())
            text = AsciiFaceData.asciiFaces.get(primaryCode);

        /*
        Bug: when AsciiFaceData.asciiFaces gets bigger in the future, the key codes of the
                space and the keyboard switch key interfere with the values that are tried
                to retrieved from the asciiFaces list
                 --> keyboard switch instead of ascii face
                 --> ascii face instead of space
         */
        switch(primaryCode) {
            case Keyboard.KEYCODE_DONE:
                inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case Keyboard.KEYCODE_DELETE:
                inputConnection.deleteSurroundingText(1, 0);
                break;
            case 42: // switch to the next input method
                inputMethodManager.switchToNextInputMethod(getWindow().getWindow().getAttributes().token, false);
                break;
            default:
                char code = (char) primaryCode;
                inputConnection.commitText(String.valueOf(text == null ? code : text), 1);
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
