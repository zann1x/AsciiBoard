package com.zann1x.asciiboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

public class MainInputService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private InputMethodManager inputMethodManager;

    private AsciiBoard asciiBoard;
    private AsciiBoardView asciiBoardView;

    // https://textfac.es/
    // https://www.npmjs.com/package/cool-ascii-faces
    private String asciiFaces[] = {
            "<empty_string>",
            "¯\\_(ツ)_/¯",
            "ಠ_ಠ",
            "( ͡° ͜ʖ ͡°)",
            "ʕ•ᴥ•ʔ",
            "(ᵔᴥᵔ)",
            "ʅʕ•ᴥ•ʔʃ",
            "༼ つ ◕_◕ ༽つ",
            "♥‿♥"
    };

    @Override
    public void onCreate() {
        super.onCreate();
        /*
        inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.showInputMethodPicker();
        */
    }

    @Override
    public void onInitializeInterface() {
        if (asciiBoard == null) {
            asciiBoard = new AsciiBoard(this, R.xml.asciiface);
        }
    }

    @Override
    public View onCreateInputView() {
        asciiBoard = new AsciiBoard(this, R.xml.asciiface);

        asciiBoardView = (AsciiBoardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        asciiBoardView.setKeyboard(asciiBoard);
        asciiBoardView.setOnKeyboardActionListener(this);

        return asciiBoardView;
    }

    @Override
    public View onCreateCandidatesView() {
        return super.onCreateCandidatesView();
    }

    /*
    private void playClick(int keyCode) {
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        switch(keyCode) {
            case 32:
                audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_SPACEBAR);
                break;
            case Keyboard.KEYCODE_DONE:
            case 10:
                audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_RETURN);
                break;
            case Keyboard.KEYCODE_DELETE:
                audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_DELETE);
                break;
            default:
                audioManager.playSoundEffect(AudioManager.FX_KEYPRESS_STANDARD);
        }
    }
    */

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
        if (0 <= primaryCode && primaryCode <= asciiFaces.length)
            text = asciiFaces[primaryCode];

        switch(primaryCode) {
            case Keyboard.KEYCODE_DONE:
                inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));
                break;
            case Keyboard.KEYCODE_DELETE:
                inputConnection.deleteSurroundingText(1, 0);
                break;
            case 42: // magical button in the last row of the keyboard that does nothing
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
