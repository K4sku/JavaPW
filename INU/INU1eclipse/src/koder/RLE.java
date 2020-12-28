package koder;
import java.util.HashMap;

public class RLE {
    public String encode(String plainText){
        HashMap<String, Integer> counter = new HashMap<>();
        String output = "";

        for (int i = 0; i < plainText.length(); i++) {
            String c = String.valueOf(plainText.charAt(i));
                counter.merge(c, 1, Integer::sum);
        }

        for (String i : counter.keySet()) {
            String codePart;
            codePart = i + counter.get(i) + ",";
            output = output.concat(codePart);
        }
        return output;
    }

    public String decode(String encodedText){
        String output = "";
        String[] encodeChunks = encodedText.split(",");
        for(String chunk : encodeChunks) {
            String s = chunk.substring(0,1);
            try {
                int i = Integer.parseInt(chunk.substring(1));
                output = output.concat(s.repeat(i));
            } catch (Exception e) {
                output = "Błąd formatowania";
            }
        }
        return output;
    }
}
