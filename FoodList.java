package sample;

public class FoodList {
    private String categories;
    private String foodnames;
    private String calories;
    private String servingsize;

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getFoodnames() {
        return foodnames;
    }

    public void setFoodnames(String foodnames) {
        this.foodnames = foodnames;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getServingsize() {
        return servingsize;
    }

    public void setServingsize(String servingsize) {
        this.servingsize = servingsize;
    }

    public FoodList(String categories, String foodnames, String calories, String servingsize) {
        this.categories = categories;
        this.foodnames = foodnames;
        this.calories = calories;
        this.servingsize = servingsize;
    }
}
