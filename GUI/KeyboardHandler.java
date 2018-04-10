package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyboardHandler implements EventHandler<KeyEvent> {
	private MainViewFX mainView;

	public KeyboardHandler(MainViewFX mv) {
		this.mainView = mv;
	}


	@Override
    public void handle(KeyEvent event) {
        // Gets the name of the key, aka the unicode character
        String keyName = event.getCode().getName();

        mainView.keyboardInput(keyName);
    }
}