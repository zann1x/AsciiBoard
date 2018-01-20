package com.zann1x.asciiboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MainInputService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private LinearLayout mainBoard;
    private ScrollView scrollView;
    private RecyclerView asciiBoardView;

    private AsciiFaceData asciiFaceData = new AsciiFaceData();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public View onCreateInputView() {
        mainBoard = (LinearLayout) getLayoutInflater().inflate(R.layout.mainboard, null);
        scrollView = mainBoard.findViewById(R.id.scrollview);

        asciiBoardView = (RecyclerView) getLayoutInflater().inflate(R.layout.recyclerview, null);
        asciiBoardView.setHasFixedSize(true);
        asciiBoardView.setLayoutManager(new LinearLayoutManager(this));
        asciiBoardView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        asciiBoardView.setAdapter(new AsciiFaceAdapter(this, asciiFaceData.asciiFaces));

        scrollView.addView(asciiBoardView);

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
        if (0 <= primaryCode && primaryCode <= asciiFaceData.asciiFaces.size())
            text = asciiFaceData.asciiFaces.get(primaryCode);

        switch(primaryCode) {
            case Keyboard.KEYCODE_DONE:
                inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case Keyboard.KEYCODE_DELETE:
                inputConnection.deleteSurroundingText(1, 0);
                break;
            case 42: // magical button in the last row of the asciiboard that does nothing
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
