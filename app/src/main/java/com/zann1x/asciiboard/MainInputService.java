package com.zann1x.asciiboard;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.IBinder;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public View onCreateInputView() {
        mainBoard = (LinearLayout) getLayoutInflater().inflate(R.layout.mainboard, null);

        asciiBoardView = mainBoard.findViewById(R.id.asciiboardview);
        asciiBoardView.setHasFixedSize(true);
        asciiBoardView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        asciiBoardView.setAdapter(new AsciiFaceAdapter(this, AsciiFaceData.asciiFaces));

        scrollView = mainBoard.findViewById(R.id.scrollview);
        scrollView.recomputeViewAttributes(asciiBoardView);

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

        switch(primaryCode) {
            case Keyboard.KEYCODE_DONE:
                inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case Keyboard.KEYCODE_DELETE:
                CharSequence selectedText = inputConnection.getSelectedText(0);
                if(TextUtils.isEmpty(selectedText))
                    inputConnection.deleteSurroundingText(1, 0);
                else
                    inputConnection.commitText("", 1);
                break;
            case -32: // space
                inputConnection.commitText(" " , 1);
                break;
            case -42: // switch to the next input method
                IBinder imeToken = getWindow().getWindow().getAttributes().token;
                inputMethodManager.switchToNextInputMethod(imeToken, false);
                break;
            default:
                inputConnection.commitText(String.valueOf((text == null) ? (char) primaryCode : text), 1);
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
