package common;

/**
 *
 * @author frederico
 */
public enum ActionResult {
    
    SUCCESS("1"),
    ERROR("2");
    
    private String value;
    
    ActionResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    
}
