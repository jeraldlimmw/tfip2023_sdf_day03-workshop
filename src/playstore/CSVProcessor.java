package playstore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CSVProcessor {
    
    public static void main(String[] args) throws IOException {

        Path p = Paths.get(args[0]);
        File f = p.toFile();
        
        Map<String, String[]> appInfo =  new HashMap<>();

        // Open file as input stream
        InputStream is = new FileInputStream(f);
        // Convert the input stream to a reader byte
        InputStreamReader isr = new InputStreamReader(is);
        // Write whole lines 
        BufferedReader br = new BufferedReader(isr);
        
        String line;

        while ((line = br.readLine()) != null) {
            System.out.printf("> %s\n", line.toUpperCase());
            String[] app = line.split(",");
            int index = 0
            while (!app[index].contains())
            if (!appInfo.containsKey(app[0])) {
                String[] temp = {app[0], app[2]};
                appInfo.put(app[0],temp);
            }
        }

        Map<String, float[]> totalAppRating = new HashMap<>();
        float[] rating = new float[2];
        String category;

        for (String a : appInfo.keySet()) {
            if (appInfo.get(a)[1].contains("NaN")) {
                continue;
            }
            category = appInfo.get(a)[0];
            if (!totalAppRating.containsKey(category)) { 
                rating[0] = Float.parseFloat(appInfo.get(a)[1]);
                rating[1] = 1f;
                totalAppRating.put(category, rating);
            } else {
                rating[0] = totalAppRating.get(category)[0] + Float.parseFloat(appInfo.get(a)[1]);
                rating[1] = totalAppRating.get(category)[1]++;
                totalAppRating.put(category, rating);
            }
        }

        for (String c : totalAppRating.keySet()) {
            float avg = totalAppRating.get(c)[0] / totalAppRating.get(c)[1]; 
            System.out.printf("Category: %s, Average Rating: %f/n", c, avg);
        }

        br.close();
        isr.close();
        is.close();
        // closed in reverse order of how it was opened

    }
    
}
