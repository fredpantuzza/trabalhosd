/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import common.ActionResult;

/**
 *
 * @author frederico
 */
public class ReturnDTO implements DTO {
    
    private ActionResult result;
    
    private String message;

    @Override
    public String getId() {
        return result.name();
    }

    public ReturnDTO(ActionResult result) {
        this.result = result;
    }

    public ReturnDTO(ActionResult result, String message) {
        this.result = result;
        this.message = message;
    }

    public ActionResult getResult() {
        return result;
    }

    public void setResult(ActionResult result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
