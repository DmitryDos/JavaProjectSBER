package workspace;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  private static final Logger LOG = LoggerFactory.getLogger(Decoder.class);

  public static void main(String[] args) {

    Decoder decoder = new Decoder();

    String fileInputUrl = args[0];
    String fileOutputUrl = args[1];
    int key = Integer.parseInt(args[2]);

    if (decoder.checkUrl(fileInputUrl)) {
      decoder.caesarCipher(fileInputUrl, fileOutputUrl, key);
    } else {
      LOG.warn("Files don't exist");
    }
  }
}
