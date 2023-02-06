package playstore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException{
        
        Map<String, AppStats> stats = new HashMap<>();
        AppStats st;

        Path p = Paths.get(args[0]);
        File f = p.toFile();

        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        // discard the header line
        br.readLine();

        String line;
        int discard = 0;
        while ((line = br.readLine()) != null) {
            String[] cols = line.split(",");
            String appName = cols[0];
            String category = cols[1];
            String col3 = cols[2];
            float rating = 0f;

            try {
                if (col3.toLowerCase().equals("nan")) {
                    discard++;
                    continue;
                }
                rating = Float.parseFloat(col3);
            } catch (NumberFormatException ex) {
                discard++;
                continue;
            }

            if (stats.containsKey(category)) {
                st = stats.get(category);
            } else {
                st = new AppStats(category);
                stats.put(category, st);
            }
            st.evaluate(appName, rating);
        }

        for (String c : stats.keySet()) {
            st = stats.get(c);
            System.out.printf("Category: %s\n", c);
            System.out.printf("\tAverage rating: %f\n", (st.getTotalRating()/st.getCount()));
            System.out.printf("\tHighest rating: %s, %f\n", st.getHighestApp(), st.getHighestAppRating());
            System.out.printf("\tLowest rating: %s, %f\n", st.getLowestApp(), st.getLowestAppRating());
        }

        System.out.printf("Samples discarded: %d\n", discard);

        br.close();
        fr.close();
    }
}