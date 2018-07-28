package org.young.base.enumeration;

/**
 * User: Young
 * Date: 2018/7/9 0009
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public enum MsgType {

    writtenWords("writtenWords"), //文字
    emoji("emoji"),//emoji
    audio("audio"),//音频
    redEnvelopes("redEnvelopes"),//红包
    file("file"),//文件
    position("position"),//位置
    picture("picture");//图片

    private String value;

    private MsgType(String value) {
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
