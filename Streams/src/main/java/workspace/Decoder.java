package workspace;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Decoder {
  private static final Logger LOG = LoggerFactory.getLogger(Decoder.class);

  public boolean checkUrl(String url) {
    File f = new File(url);
    return f.exists();
  }

  public void caesarCipher(String inputFileUrl, String outputFileUrl, int key) {
    Map<Integer, Integer> mpToAlphabet = new HashMap<>();
    Map<Integer, Integer> mpToChar = new HashMap<>();
    for (int i = 1072; i < 1104; i++) {
      if (i > 1077) {
        mpToAlphabet.put(i, i - 1071);
        mpToChar.put(i - 1071, i);
      } else {
        mpToAlphabet.put(i, i - 1072);
        mpToChar.put(i - 1072, i);
      }
    }
    mpToAlphabet.put(1105, 6);
    mpToChar.put(6, 1105);

    List<Integer> text = new ArrayList<>();
    try {
      Reader reader = new FileReader(inputFileUrl, StandardCharsets.UTF_8);
      BufferedReader bReader = new BufferedReader(reader);
      int c;
      while ((c = bReader.read()) != -1) {
        text.add(c);
      }

      reader.close();
      bReader.close();
    } catch (IOException e) {
      LOG.warn(e.getMessage());
    }

    text = text.stream().map(x -> (mpToAlphabet.get(x) + key) % 33).map(mpToChar::get).toList();

    try {
      Writer writer = new FileWriter(outputFileUrl, StandardCharsets.UTF_8);
      
      for (Integer integer : text) {
        writer.write(integer);
      }

      writer.close();
    } catch (IOException e) {
      LOG.warn(e.getMessage());
    }
  }
}
