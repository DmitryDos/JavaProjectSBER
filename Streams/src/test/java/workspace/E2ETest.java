package workspace;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class E2ETest {

  private static final Logger LOG = LoggerFactory.getLogger(Decoder.class);
  private final Decoder decoder = new Decoder();
  File fInput;
  File fOutput;
  String rightAnswer;

  @Before
  public void createTempFiles() {
    try {
      fInput = File.createTempFile("input", ".tmp");
      fOutput = File.createTempFile("output", ".tmp");
      Writer writerIn = new FileWriter(fInput.getAbsolutePath(), StandardCharsets.UTF_8);
      writerIn.write("лоремипсумдолорситамет");

      rightAnswer = "рухйснфцшсиурухцнчесйч";

      writerIn.close();

    } catch (IOException e) {
      LOG.warn(e.getMessage());
      throw new RuntimeException(e);
    }
  }

  @Test
  public void testDecoder() {
    String urlIn = fInput.getAbsolutePath();
    String urlOut = fOutput.getAbsolutePath();

    LOG.debug(urlIn);
    LOG.debug(urlOut);

    assertTrue(decoder.checkUrl(urlIn));
    assertTrue(decoder.checkUrl(urlOut));
    LOG.debug("Files have correct URLs");

    decoder.caesarCipher(urlIn, urlOut, 5);

    StringBuilder result = new StringBuilder();
    Reader reader;
    try {
      reader = new FileReader(urlOut, StandardCharsets.UTF_8);
      BufferedReader bReader = new BufferedReader(reader);
      int c;
      while ((c = bReader.read()) != -1) {
        result.append((char) c);
      }
      reader.close();
      bReader.close();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertEquals(rightAnswer, result.toString());

    fInput.deleteOnExit();
    fOutput.deleteOnExit();
  }
}