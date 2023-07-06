package model;

/**
 * The enum Category.
 */
public enum CATEGORY {
    /**
     * Breakfast category.
     */
    BREAKFAST("Breakfast"),
    /**
     * Lunch category.
     */
    LUNCH("Lunch"),
    /**
     * Dinner category.
     */
    DINNER("Dinner"),
    /**
     * The Afternoontea.
     */
    AFTERNOONTEA("Afternoon Tea"),
    /**
     * Vegetable category.
     */
    VEGETABLE("Vegetable"),
    /**
     * Snack category.
     */
    SNACK("Snack");


    CATEGORY(String _name){
        name = _name;
    }

    /**
     * Gets Category name.
     *
     * @return the Category name
     */
    public String getName() {
        return name;
    }

    private String name;
}
