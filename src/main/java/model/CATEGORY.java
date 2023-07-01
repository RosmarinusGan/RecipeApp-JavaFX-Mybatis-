package model;

public enum CATEGORY {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    AFTERNOONTEA("Afternoon Tea"),
    VEGETABLE("Vegetable"),
    SNACK("Snack");


    CATEGORY(String _name){
        name = _name;
    }

    public String getName() {
        return name;
    }

    private String name;
}
