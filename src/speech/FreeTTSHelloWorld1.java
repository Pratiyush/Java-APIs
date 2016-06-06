package speech;

import java.io.FileNotFoundException;

import com.sun.speech.freetts.FreeTTS;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSHelloWorld1 {

	public static void listAllVoices() {
		System.out.println();
		System.out.println("All voices available:");
		VoiceManager voiceManager = VoiceManager.getInstance();
		Voice[] voices = voiceManager.getVoices();
		for (int i = 0; i < voices.length; i++) {
			System.out.println("    " + voices[i].getName() + " ("
					+ voices[i].getDomain() + " domain)");
		}
	}

	public static void main(String[] args) throws InstantiationException,
			FileNotFoundException {

		String message = "Hello world! This is a test program";

		listAllVoices();

		String voiceName = (args.length > 0) ? args[0] : "kevin16";

		System.out.println();
		System.out.println("Using voice: " + voiceName);

		VoiceManager voiceManager = VoiceManager.getInstance();
		Voice helloVoice = voiceManager.getVoice(voiceName);

		if (helloVoice == null) {
			System.err.println("Cannot find a voice named " + voiceName
					+ ".  Please specify a different voice.");
			System.exit(1);
		}

		FreeTTS freeTTS = new FreeTTS(helloVoice);
		freeTTS.setAudioFile("C:\\eig\\test.wav");

		freeTTS.startup();

		helloVoice.allocate();

		helloVoice.speak(message);

		helloVoice.deallocate();

		freeTTS.shutdown();
		System.exit(0);
	}
}