package speech;

import javax.speech.* ;
import javax.speech.synthesis.Synthesizer;

public class HelloWorld {
    public static void main ( String [ ] args ) {
        try {
            Synthesizer synth = Central
                    .createSynthesizer ( null ) ; 
            if ( synth != null ) { 
                synth.allocate () ;
                synth.resume () ;
                synth.speakPlainText ( "Hello, World", null ) ;
                synth.waitEngineState ( Synthesizer.QUEUE_EMPTY ) ;
            } else {
                System.err.println ( "Error!" ) ;
            }
        } catch ( Exception e ) {
            e.printStackTrace () ;
        }
    }
}