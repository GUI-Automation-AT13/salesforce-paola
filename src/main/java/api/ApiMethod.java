package api;

public enum ApiMethod {
    POST("POST"),
    DELETE("DELETE");

    private String name;

    ApiMethod(final String newName) {
        this.name = newName;
    }

    /**
     * Gets the api method.
     * @return the request method.
     */
    public String toName() {
        return name;
    }
}
