package com.opendynamic.ff.vo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Shape implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String TEXT_ALIGN_LEFT = "left";
    public static final String TEXT_ALIGN_CENTER = "center";
    public static final String TEXT_ALIGN_RIGHT = "right";
    public static final String VERTICAL_ALIGN_TOP = "top";
    public static final String VERTICAL_ALIGN_MIDDLE = "middle";
    public static final String VERTICAL_ALIGN_BOTTOM = "bottom";
    public static final String FONT_WEIGHT_NORMAL = "normal";
    public static final String FONT_WEIGHT_BOLD = "bold";
    public static final String COLOR_TRANSPARENT = "transparent";

    private String type;// 类型
    private String linePath = "NS";// 折线路径
    private int stub = 25;// 连线两端截线的最小长度
    private int textLineIndex = 0;// 文本所在折线段下标
    private int textWidth = 100;// 文本最大宽度
    private int textHeight = 20;// 文本最大高度
    private int textOffsetX = 0;// 文本横向偏移
    private int textOffsetY = 0;// 文本纵向偏移
    private String style;

    private int left;// 左边缘x坐标
    private int top;// 上边缘y坐标
    private int width;// 宽度
    private int height;// 高度
    private String fontFamily = "Microsoft YaHei";// 字体
    private int fontWeight = Font.BOLD;// 字体粗细
    private int fontSize = 13;// 字体大小
    private String borderStyle = "solid";// 边框类型
    private int borderWidth = 1;// 边框宽度
    private int borderRadius = 15;// 边框圆角
    @JsonProperty
    private String borderColor = "#000000";// 边框颜色
    @JsonProperty
    private String color = "#000000";// 文字颜色
    @JsonProperty
    private String backgroundColor = "#ffffc8";// 背景颜色
    private String textAlign = TEXT_ALIGN_CENTER;// 文字横向对齐
    private String verticalAlign = VERTICAL_ALIGN_MIDDLE;// 文字纵向对齐

    private String coords;// html可点击区域定义

    @JsonIgnore
    protected transient Object owner;

    /**
     * 初始化。
     * 
     * @param owner
     */
    public void init(Object owner) {
        this.owner = owner;

        if (StringUtils.isNotEmpty(style)) {
            String[] rules = style.split(";");
            String[] rule;
            for (int i = 0; i < rules.length; i++) {
                if (rules[i].trim().equals("")) {
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

    public String getType() {
        return type;
    }

    public String getLinePath() {
        return linePath;
    }

    public int getStub() {
        return stub;
    }

    public int getTextLineIndex() {
        return textLineIndex;
    }

    public int getTextWidth() {
        return textWidth;
    }

    public int getTextHeight() {
        return textHeight;
    }

    public int getTextOffsetX() {
        return textOffsetX;
    }

    public int getTextOffsetY() {
        return textOffsetY;
    }

    public String getStyle() {
        return style;
    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getCoords() {// HTML IMG MAP标签用
        return coords;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public int getFontWeight() {
        return fontWeight;
    }

    public String getBorderStyle() {
        return borderStyle;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public int getBorderRadius() {
        return borderRadius;
    }

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

    public int getFontSize() {
        return fontSize;
    }

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

    public String getTextAlign() {
        return textAlign;
    }

    public String getVerticalAlign() {
        return verticalAlign;
    }

    public Object getOwner() {
        return owner;
    }

    /**
     * 获取X轴最大值。
     * 
     * @return
     */
    public abstract int getMaxX();

    /**
     * 获取Y轴最大值。
     * 
     * @return
     */
    public abstract int getMaxY();

    /**
     * 绘制节点。
     * 
     * @param g2d
     * @param text
     */
    public abstract void draw(Graphics2D g2d, String text);

    /**
     * 绘制为活动节点。
     * 
     * @param g2d
     */
    public abstract void drawActive(Graphics2D g2d);

    /**
     * 绘制为可选跳转节点。
     * 
     * @param g2d
     */
    public abstract void drawOptional(Graphics2D g2d, boolean isDefault);

    /**
     * 获取HTML IMG MAP标签用类型。
     * 
     * @return
     */
    public abstract String getHtmlImgMapShape();
}