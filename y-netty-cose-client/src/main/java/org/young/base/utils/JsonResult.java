package org.young.base.utils;

/**
 * User: Young
 * Date: 2018/7/26 0026
 * Time: 11:42
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class JsonResult {

    private String status = null;

    private Object result = null;

    // Getter Setter

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
