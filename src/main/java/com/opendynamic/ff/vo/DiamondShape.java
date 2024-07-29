package com.opendynamic.ff.vo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opendynamic.OdUtils;

/**
 * 菱形形状。
 */
public class DiamondShape extends Shape implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private int[] xPoints;// 所有点的横坐标。
    @JsonIgnore
    private int[] yPoints;// 所有点的纵坐标。

    @Override
    public void init(Object owner) {
        super.init(owner);

        xPoints = new int[] { getLeft(), getLeft() + getWidth() / 2, getLeft() + getWidth(), getLeft() + getWidth() / 2 };
        yPoints = new int[] { getTop() + getHeight() / 2, getTop(), getTop() + getHeight() / 2, getTop() + getHeight() };
    }

    @Override
    public int getMaxX() {
        return getLeft() + getWidth();
    }

    @Override
    public int getMaxY() {
        return getTop() + getHeight();
    }

    @Override
    public void draw(Graphics2D g2d, String text) {
        if (getBackgroundColor().getAlpha() > 0) {
            g2d.setPaint(new GradientPaint(getLeft() + getWidth() / 2, getTop(), Color.WHITE, getLeft() + getWidth() / 2, getTop() + getHeight(), getBackgroundColor()));
            g2d.fillPolygon(xPoints, yPoints, 4);// 绘制图形
        }
        g2d.setColor(getBorderColor());
        g2d.drawPolygon(xPoints, yPoints, 4);// 绘制边界

        g2d.setColor(getColor());// 绘制文字
        Font font = new Font(getFontFamily(), getFontWeight(), getFontSize());
        OdUtils.drawStringInCell(g2d, text, getLeft() + getTextOffsetX(), getTop() + getTextOffsetY(), getWidth(), getHeight(), getVerticalAlign(), font);
    }

    @Override
    public void drawActive(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawPolygon(xPoints, yPoints, 4);// 绘制边界
    }

    @Override
    public void drawOptional(Graphics2D g2d, boolean isDefault) {
        g2d.setColor(Color.GREEN);
        if (isDefault) {
            g2d.setStroke(new BasicStroke(3));
        }
        else {
            g2d.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 3.5f, new float[] { 15, 10, }, 0f));
        }
        g2d.drawPolygon(xPoints, yPoints, 4);// 绘制边界
    }

    @Override
    public String getType() {
        return "diamond";
    }

    @Override
    public String getHtmlImgMapShape() {
        return "diamond";
    }
}