package org.young.base.enumeration;

/**
 * User: Young
 * Date: 2018/7/9 0009
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public enum ChatType {

    single("single"),
    group("group");

    private String value;

    private ChatType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return this.name();
    }
}
