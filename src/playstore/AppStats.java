package playstore;

public class AppStats {
    
    private int count = 0;
    private float totalRating = 0;
    private String highestApp;
    private float highestAppRating = 0;
    private String lowestApp;
    private float lowestAppRating = 5;
    private String category;

    public String getCategory() {
        return category;
    }

    // getters and setters
    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public float getTotalRating() { return totalRating; }
    public void setTotalRating(float totalRating) { this.totalRating = totalRating; }

    public String getHighestApp() { return highestApp; }
    public void setHighestApp(String highestApp) { this.highestApp = highestApp; }
    
    public float getHighestAppRating() { return highestAppRating; }
    public void setHighestAppRating(float highestAppRating) { this.highestAppRating = highestAppRating; }

    public String getLowestApp() { return lowestApp; }
    public void setLowestApp(String lowestApp) { this.lowestApp = lowestApp; }

    public float getLowestAppRating() { return lowestAppRating; }
    public void setLowestAppRating(float lowestAppRating) { this.lowestAppRating = lowestAppRating; }

    public void setCategory(String category) { this.category = category; }
    
    public AppStats(String category) {
        this.category = category; 
    }
    
    // methods
    public void evaluate (String appName, float rating) {
        count++;
        this.totalRating += rating;
        if (rating > this.highestAppRating) {
            this.highestAppRating = rating;
            this.highestApp = appName;
        } 
        if (rating < this.lowestAppRating) {
            this.lowestAppRating = rating;
            this.lowestApp = appName;
        }
    }
}
