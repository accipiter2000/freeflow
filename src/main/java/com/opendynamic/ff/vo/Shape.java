package com.opendynamic.ff.vo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 形状。
 */
public abstract class Shape implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String TEXT_ALIGN_LEFT = "left";// 文本横向对齐-左。
    public static final String TEXT_ALIGN_CENTER = "center";// 文本横向对齐-中间。
    public static final String TEXT_ALIGN_RIGHT = "right";// 文本横向对齐-右。
    public static final String VERTICAL_ALIGN_TOP = "top";// 文本纵向对齐-顶部。
    public static final String VERTICAL_ALIGN_MIDDLE = "middle";// 文本纵向对齐-中间。
    public static final String VERTICAL_ALIGN_BOTTOM = "bottom";// 文本纵向对齐-底部。
    public static final String FONT_WEIGHT_NORMAL = "normal";// 字体粗细-一般。
    public static final String FONT_WEIGHT_BOLD = "bold";// 字体粗细-加粗。
    public static final String COLOR_TRANSPARENT = "transparent";// 颜色-透明。

    private String type;// 类型。
    private String linePath = "NS";// 折线路径。
    private int stub = 25;// 连线两端截线的最小长度。
    private int textLineIndex = 0;// 文本所在折线段下标。
    private int textWidth = 100;// 文本最大宽度。
    private int textHeight = 20;// 文本最大高度。
    private int textOffsetX = 0;// 文本横向偏移。
    private int textOffsetY = 0;// 文本纵向偏移。
    private String style;// 样式。

    private int left;// 左边缘x坐标。
    private int top;// 上边缘y坐标。
    private int width;// 宽度。
    private int height;// 高度。
    private String fontFamily = "Microsoft YaHei";// 字体。
    private int fontWeight = Font.BOLD;// 字体粗细。
    private int fontSize = 13;// 字体大小。
    private String borderStyle = "solid";// 边框样式。
    private int borderWidth = 1;// 边框宽度。
    private int borderRadius = 15;// 边框圆角。
    @JsonProperty
    private String borderColor = "#000000";// 边框颜色。
    @JsonProperty
    private String color = "#000000";// 文字颜色。
    @JsonProperty
    private String backgroundColor = "#ffffc8";// 背景颜色。
    private String textAlign = TEXT_ALIGN_CENTER;// 文字横向对齐。
    private String verticalAlign = VERTICAL_ALIGN_MIDDLE;// 文字纵向对齐。

    private String coords;// HTML可点击区域定义。

    @JsonIgnore
    protected transient Object owner;// 形状所属对象。

    /**
     * 初始化。
     * 
     * @param owner
     *        该形状所属对象。
     */
    public void init(Object owner) {
        this.owner = owner;

        if (StringUtils.isNotEmpty(style)) {
            String[] rules = style.split(";");
            String[] rule;
            for (int i = 0; i < rules.length; i++) {
                if (rules[i].trim().isEmpty()) {
                    continue;
                }

                rule = rules[i].split(":");
                setRule(rule[0].trim(), rule[1].trim());
            }
        }

        coords = left + "," + top + "," + (left + width) + "," + (top + height);
    }

    private void setRule(String property, String value) {
        if (StringUtils.isEmpty(property) || StringUtils.isEmpty(value)) {
            return;
        }

        if (property.equals("left")) {
            if (value.endsWith("px")) {
                value = value.substring(0, value.length() - 2);
            }
            this.left = Integer.parseInt(value);
        }
        if (property.equals("top")) {
            if (value.endsWith("px")) {
                value = value.substring(0, value.length() - 2);
            }
            this.top = Integer.parseInt(value);
        }
        if (property.equals("width")) {
            if (value.endsWith("px")) {
                value = value.substring(0, value.length() - 2);
            }
            this.width = Integer.parseInt(value);
        }
        if (property.equals("height")) {
            if (value.endsWith("px")) {
                value = value.substring(0, value.length() - 2);
            }
            this.height = Integer.parseInt(value);
        }
        if (property.equals("font-family")) {
            this.fontFamily = value;
        }
        if (property.equals("font-weight")) {
            if (FONT_WEIGHT_NORMAL.equals(value)) {
                this.fontWeight = Font.PLAIN;
            }
            if (FONT_WEIGHT_BOLD.equals(value)) {
                this.fontWeight = Font.BOLD;
            }
        }
        if (property.equals("font-size")) {
            if (value.endsWith("px")) {
                value = value.substring(0, value.length() - 2);
            }
            this.fontSize = Integer.parseInt(value);
        }
        if (property.equals("border-style")) {
            this.borderStyle = value;
        }
        if (property.equals("border-width")) {
            if (value.endsWith("px")) {
                value = value.substring(0, value.length() - 2);
            }
            this.borderWidth = Integer.parseInt(value);
        }
        if (property.equals("border-radius")) {
            if (value.endsWith("px")) {
                value = value.substring(0, value.length() - 2);
            }
            this.borderRadius = Integer.parseInt(value);
        }
        if (property.equals("border-color")) {
            this.borderColor = value;
        }
        if (property.equals("color")) {
            this.color = value;
        }
        if (property.equals("background-color")) {
            this.backgroundColor = value;
        }
        if (property.equals("text-align")) {
            this.textAlign = value;
        }
        if (property.equals("vertical-align")) {
            this.verticalAlign = value;
        }
    }

    /**
     * 获取类型。
     * 
     * @return 类型。
     */
    public String getType() {
        return type;
    }

    /**
     * 获取折线路径。
     * 
     * @return 折线路径。
     */
    public String getLinePath() {
        return linePath;
    }

    /**
     * 获取连线两端截线的最小长度。
     * 
     * @return 连线两端截线的最小长度。
     */
    public int getStub() {
        return stub;
    }

    /**
     * 获取文本所在折线段下标。
     * 
     * @return 文本所在折线段下标。
     */
    public int getTextLineIndex() {
        return textLineIndex;
    }

    /**
     * 获取文本最大宽度。
     * 
     * @return 文本最大宽度。
     */
    public int getTextWidth() {
        return textWidth;
    }

    /**
     * 获取文本最大高度。
     * 
     * @return 文本最大高度。
     */
    public int getTextHeight() {
        return textHeight;
    }

    /**
     * 获取文本横向偏移。
     * 
     * @return 文本横向偏移。
     */
    public int getTextOffsetX() {
        return textOffsetX;
    }

    /**
     * 获取文本纵向偏移。
     * 
     * @return 文本纵向偏移。
     */
    public int getTextOffsetY() {
        return textOffsetY;
    }

    /**
     * 获取样式。
     * 
     * @return 样式。
     */
    public String getStyle() {
        return style;
    }

    /**
     * 获取左边缘x坐标。
     * 
     * @return 左边缘x坐标。
     */
    public int getLeft() {
        return left;
    }

    /**
     * 获取上边缘y坐标。
     * 
     * @return 上边缘y坐标。
     */
    public int getTop() {
        return top;
    }

    /**
     * 获取宽度。
     * 
     * @return 宽度。
     */
    public int getWidth() {
        return width;
    }

    /**
     * 获取高度。
     * 
     * @return 高度。
     */
    public int getHeight() {
        return height;
    }

    /**
     * 获取字体。
     * 
     * @return 字体。
     */
    public String getFontFamily() {
        return fontFamily;
    }

    /**
     * 获取字体粗细。
     * 
     * @return 字体粗细。
     */
    public int getFontWeight() {
        return fontWeight;
    }

    /**
     * 获取字体大小。
     * 
     * @return 字体大小。
     */
    public int getFontSize() {
        return fontSize;
    }

    /**
     * 获取边框样式。
     * 
     * @return 边框样式。
     */
    public String getBorderStyle() {
        return borderStyle;
    }

    /**
     * 获取边框宽度。
     * 
     * @return 边框宽度。
     */
    public int getBorderWidth() {
        return borderWidth;
    }

    /**
     * 获取边框圆角。
     * 
     * @return 边框圆角。
     */
    public int getBorderRadius() {
        return borderRadius;
    }

    /**
     * 获取边框颜色。
     * 
     * @return 边框颜色。
     */
    @JsonIgnore
    public Color getBorderColor() {
        if (COLOR_TRANSPARENT.equals(borderColor)) {
            return new Color(255, 255, 255, 0);
        }

        if (borderColor.startsWith("#")) {
            String[] colors = new String[3];
            colors[0] = borderColor.substring(1, 3);
            colors[1] = borderColor.substring(3, 5);
            colors[2] = borderColor.substring(5, 7);
            return new Color(Integer.valueOf(colors[0], 16), Integer.valueOf(colors[1], 16), Integer.valueOf(colors[2], 16));
        }
        else {
            String[] colors = borderColor.split(",");
            return new Color(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2]));
        }
    }

    /**
     * 获取文字颜色。
     * 
     * @return 文字颜色。
     */
    @JsonIgnore
    public Color getColor() {
        if (COLOR_TRANSPARENT.equals(color)) {
            return new Color(255, 255, 255, 0);
        }

        if (color.startsWith("#")) {
            String[] colors = new String[3];
            colors[0] = color.substring(1, 3);
            colors[1] = color.substring(3, 5);
            colors[2] = color.substring(5, 7);
            return new Color(Integer.valueOf(colors[0], 16), Integer.valueOf(colors[1], 16), Integer.valueOf(colors[2], 16));
        }
        else {
            String[] colors = color.split(",");
            return new Color(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2]));
        }
    }

    /**
     * 获取背景颜色。
     * 
     * @return 背景颜色。
     */
    @JsonIgnore
    public Color getBackgroundColor() {
        if (COLOR_TRANSPARENT.equals(backgroundColor)) {
            return new Color(255, 255, 255, 0);
        }

        if (backgroundColor.startsWith("#")) {
            String[] colors = new String[3];
            colors[0] = backgroundColor.substring(1, 3);
            colors[1] = backgroundColor.substring(3, 5);
            colors[2] = backgroundColor.substring(5, 7);
            return new Color(Integer.valueOf(colors[0], 16), Integer.valueOf(colors[1], 16), Integer.valueOf(colors[2], 16));
        }
        else {
            String[] colors = backgroundColor.split(",");
            return new Color(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2]));
        }
    }

    /**
     * 获取文字横向对齐。
     * 
     * @return 文字横向对齐。
     */
    public String getTextAlign() {
        return textAlign;
    }

    /**
     * 获取文字纵向对齐。
     * 
     * @return 文字纵向对齐。
     */
    public String getVerticalAlign() {
        return verticalAlign;
    }

    /**
     * 获取HTML可点击区域定义。
     * 
     * @return HTML可点击区域定义。
     */
    public String getCoords() {// HTML IMG MAP标签用
        return coords;
    }

    /**
     * 获取形状所属对象。
     * 
     * @return 形状所属对象。
     */
    public Object getOwner() {
        return owner;
    }

    /**
     * 获取X轴最大值。
     * 
     * @return X轴最大值。
     */
    public abstract int getMaxX();

    /**
     * 获取Y轴最大值。
     * 
     * @return Y轴最大值。
     */
    public abstract int getMaxY();

    /**
     * 绘制节点。
     * 
     * @param g2d
     *        Graphics2D。
     * @param text
     *        文本。
     */
    public abstract void draw(Graphics2D g2d, String text);

    /**
     * 绘制为活动节点。活动节点的颜色为红色。
     * 
     * @param g2d
     *        Graphics2D。
     */
    public abstract void drawActive(Graphics2D g2d);

    /**
     * 绘制为可选跳转节点。可选跳转节点的颜色为绿色。缺省可选跳转节点为实线，非缺省为虚线。
     * 
     * @param g2d
     *        Graphics2D。
     * @param isDefault
     *        是否为缺省可选跳转节点。
     */
    public abstract void drawOptional(Graphics2D g2d, boolean isDefault);

    /**
     * 获取HTML IMG MAP标签用类型。
     * 
     * @return HTML IMG MAP标签用类型。
     */
    public abstract String getHtmlImgMapShape();
}