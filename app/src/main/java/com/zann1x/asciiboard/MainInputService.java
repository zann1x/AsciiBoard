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
import android.view.Window;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.zann1x.asciiboard.asciiface.AsciiFace;
import com.zann1x.asciiboard.asciiface.AsciiFaceAdapter;
import com.zann1x.asciiboard.category.Category;
import com.zann1x.asciiboard.category.CategoryAdapter;

import java.util.List;

public class MainInputService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private InputMethodManager inputMethodManager;

    private AsciiFaceAdapter asciiFaceAdapter;
    private AsciiFace asciiFace;
    private RecyclerView asciiBoardView;
    private Category currentCategory;

    @Override
    public void onCreate() {
        super.onCreate();
        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        asciiFace = new AsciiFace(getApplicationContext());
        currentCategory = asciiFace.asciiFaceMap.keySet().iterator().next();
        ;
    }

    @Override
    public View onCreateInputView() {
        LinearLayout mainBoard = (LinearLayout) getLayoutInflater().inflate(R.layout.mainboard, null);

        CategoryAdapter categoryAdapter = new CategoryAdapter(this, asciiFace.asciiFaceMap.keySet());

        RecyclerView categoryView = mainBoard.findViewById(R.id.recycler_category_view);
        categoryView.setAdapter(categoryAdapter);

        asciiFaceAdapter = new AsciiFaceAdapter(this, asciiFace.asciiFaceMap.get(currentCategory));

        asciiBoardView = mainBoard.findViewById(R.id.asciiboard_view);
        asciiBoardView.setHasFixedSize(true);
        asciiBoardView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        asciiBoardView.setAdapter(asciiFaceAdapter);

        KeyboardView keyboardView = mainBoard.findViewById(R.id.keyboard_view);
        keyboardView.setKeyboard(new Keyboard(this, R.xml.keyboard));
        keyboardView.setOnKeyboardActionListener(this);
        keyboardView.setPreviewEnabled(false);

        return mainBoard;
    }

    @Override
    public View onCreateCandidatesView() {
        return super.onCreateCandidatesView();
    }

    public void switchCategory(Category newCategory) {
        currentCategory = newCategory;
        asciiFaceAdapter = new AsciiFaceAdapter(this, asciiFace.asciiFaceMap.get(currentCategory));
        asciiBoardView.setAdapter(asciiFaceAdapter);
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
        List<String> asciiFaces = asciiFace.asciiFaceMap.get(currentCategory);
        if (0 <= primaryCode && primaryCode < asciiFaces.size())
            text = asciiFaces.get(primaryCode);

        switch (primaryCode) {
            case Keyboard.KEYCODE_DONE:
                inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case Keyboard.KEYCODE_DELETE:
                CharSequence selectedText = inputConnection.getSelectedText(0);
                if (TextUtils.isEmpty(selectedText))
                    inputConnection.deleteSurroundingText(1, 0);
                else
                    inputConnection.commitText("", 1);
                break;
            case -32: // space
                inputConnection.commitText(" ", 1);
                break;
            case -42: // switch to the next input method
                Window window = getWindow().getWindow();
                if (window != null) {
                    IBinder imeToken = window.getAttributes().token;
                    inputMethodManager.switchToNextInputMethod(imeToken, false);
                }
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
