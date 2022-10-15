import com.challenge.jokegenerator.Joke;
import com.challenge.jokegenerator.JokeGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class JokeGeneratorTest {

    private static final String SETUP = "Get ready...";
    private static final String PUNCHLINE = "For the punchline!";

    private final ByteArrayOutputStream printOut =
            new ByteArrayOutputStream();

    private final Joke joke = new Joke();

    @BeforeEach
    public void setUpEach() {

        System.setOut(new PrintStream(printOut));
    }

    @AfterEach
    public void cleanUpEach() {

        System.setOut(System.out);
    }

    @Test
    public void parseJoke_parseError() {

        JokeGenerator
                .generateJokeFromResponse("today");

        assertEquals("Must be out of jokes for now.\n",
                printOut.toString());
    }

    @Test
    public void tellJoke() throws InterruptedException {

        joke.setSetup(SETUP);
        joke.setPunchline(PUNCHLINE);

        JokeGenerator.tellJoke(joke);

        assertEquals("Get ready...\nFor the punchline!\n", printOut.toString());
    }
}
