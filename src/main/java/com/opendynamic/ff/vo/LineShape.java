package com.opendynamic.ff.vo;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opendynamic.OdUtils;

/**
 * 连线形状。
 */
public class LineShape extends Shape implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    protected transient List<Point> pointList;// 折点列表。

    @Override
    public void init(Object owner) {
        super.init(owner);

        initPointList();
    }

    private void initPointList() {
        pointList = new ArrayList<>();

        NodeDef sourceNodeDef = ((FlowDef) owner).getSourceNodeDef();
        NodeDef targetNodeDef = ((FlowDef) owner).getTargetNodeDef();

        int sourceNodeLeft = sourceNodeDef.getShape().getLeft();
        int sourceNodeTop = sourceNodeDef.getShape().getTop();
        int sourceNodeWidth = sourceNodeDef.getShape().getWidth();
        int sourceNodeHeight = sourceNodeDef.getShape().getHeight();
        int targetNodeLeft = targetNodeDef.getShape().getLeft();
        int targetNodeTop = targetNodeDef.getShape().getTop();
        int targetNodeWidth = targetNodeDef.getShape().getWidth();
        int targetNodeHeight = targetNodeDef.getShape().getHeight();

        String startDirection;
        String endDirection;
        Point startPoint = null;
        Point startStubPoint = null;
        Point m1Point = null;
        Point m2Point = null;
        Point m3Point = null;
        Point m4Point = null;
        Point endStubPoint = null;
        Point endPoint = null;
        if (getLinePath().equals("D")) {// 直线
            if (Math.abs(sourceNodeLeft - targetNodeLeft) <= Math.abs(sourceNodeTop - targetNodeTop)) {
                if (sourceNodeTop < targetNodeTop) {
                    startPoint = new Point(sourceNodeLeft + sourceNodeWidth / 2, sourceNodeTop + sourceNodeHeight);
                    endPoint = new Point(targetNodeLeft + targetNodeWidth / 2, targetNodeTop);
                }
                else {
                    startPoint = new Point(sourceNodeLeft + sourceNodeWidth / 2, sourceNodeTop);
                    endPoint = new Point(targetNodeLeft + targetNodeWidth / 2, targetNodeTop + targetNodeHeight);
                }
            }
            else {
                if (sourceNodeLeft < targetNodeLeft) {
                    startPoint = new Point(sourceNodeLeft + sourceNodeWidth, sourceNodeTop + sourceNodeHeight / 2);
                    endPoint = new Point(targetNodeLeft, targetNodeTop + targetNodeHeight / 2);
                }
                else {
                    startPoint = new Point(sourceNodeLeft, sourceNodeTop + sourceNodeHeight / 2);
                    endPoint = new Point(targetNodeLeft + targetNodeWidth, targetNodeTop + targetNodeHeight / 2);
                }
            }

            pointList.add(startPoint);
            pointList.add(endPoint);
        }
        else {// 折线
            startDirection = getLinePath().substring(0, 1);
            endDirection = getLinePath().substring(getLinePath().length() - 1);

            if (startDirection.equals("N")) {// 计算起点，终点和各自的延长点
                startPoint = new Point(sourceNodeLeft + sourceNodeWidth / 2, sourceNodeTop);
                startStubPoint = new Point(sourceNodeLeft + sourceNodeWidth / 2, sourceNodeTop - getStub());
            }
            if (startDirection.equals("E")) {
                startPoint = new Point(sourceNodeLeft + sourceNodeWidth, sourceNodeTop + sourceNodeHeight / 2);
                startStubPoint = new Point(sourceNodeLeft + sourceNodeWidth + getStub(), sourceNodeTop + sourceNodeHeight / 2);
            }
            if (startDirection.equals("S")) {
                startPoint = new Point(sourceNodeLeft + sourceNodeWidth / 2, sourceNodeTop + sourceNodeHeight);
                startStubPoint = new Point(sourceNodeLeft + sourceNodeWidth / 2, sourceNodeTop + sourceNodeHeight + getStub());
            }
            if (startDirection.equals("W")) {
                startPoint = new Point(sourceNodeLeft, sourceNodeTop + sourceNodeHeight / 2);
                startStubPoint = new Point(sourceNodeLeft - getStub(), sourceNodeTop + sourceNodeHeight / 2);
            }
            if (endDirection.equals("N")) {
                endPoint = new Point(targetNodeLeft + targetNodeWidth / 2, targetNodeTop);
                endStubPoint = new Point(targetNodeLeft + targetNodeWidth / 2, targetNodeTop - getStub());
            }
            if (endDirection.equals("E")) {
                endPoint = new Point(targetNodeLeft + targetNodeWidth, targetNodeTop + targetNodeHeight / 2);
                endStubPoint = new Point(targetNodeLeft + targetNodeWidth + getStub(), targetNodeTop + targetNodeHeight / 2);
            }
            if (endDirection.equals("S")) {
                endPoint = new Point(targetNodeLeft + targetNodeWidth / 2, targetNodeTop + targetNodeHeight);
                endStubPoint = new Point(targetNodeLeft + targetNodeWidth / 2, targetNodeTop + targetNodeHeight + getStub());
            }
            if (endDirection.equals("W")) {
                endPoint = new Point(targetNodeLeft, targetNodeTop + targetNodeHeight / 2);
                endStubPoint = new Point(targetNodeLeft - getStub(), targetNodeTop + targetNodeHeight / 2);
            }

            // 计算中间折点
            if (isSameDirection(startStubPoint, endStubPoint, startDirection)) {// 与开始方向相同
                if (isSubjectToEndStub(startStubPoint, endStubPoint, startDirection, endDirection)) {// 以终点折点为准
                    if (startDirection.equals("N") || startDirection.equals("S")) {
                        m1Point = new Point(startStubPoint.x, endStubPoint.y);// 计算起点折点
                        if (!m1Point.equals(endStubPoint) && (endDirection.equals("N") || endDirection.equals("S"))) {// 计算终点折点
                            m4Point = endStubPoint;
                        }
                    }
                    else {
                        m1Point = new Point(endStubPoint.x, startStubPoint.y);// 计算起点折点
                        if (!m1Point.equals(endStubPoint) && (endDirection.equals("E") || endDirection.equals("W"))) {// 计算终点折点
                            m4Point = endStubPoint;
                        }
                    }
                }
                else {// 反向折线
                    if (startDirection.equals(reverseDirection(endDirection))) {// 中点双折
                        if (startDirection.equals("N") || startDirection.equals("S")) {
                            m1Point = new Point(startStubPoint.x, (startStubPoint.y + endStubPoint.y) / 2);
                            m4Point = new Point(endStubPoint.x, (startStubPoint.y + endStubPoint.y) / 2);
                        }
                        else {
                            m1Point = new Point((startStubPoint.x + endStubPoint.x) / 2, startStubPoint.y);
                            m4Point = new Point((startStubPoint.x + endStubPoint.x) / 2, endStubPoint.y);
                        }

                        if (m1Point.equals(m4Point)) {// 起终折点在一条直线上，取消折点
                            m1Point = null;
                            m4Point = null;
                        }
                    }
                    else {// 反向单折
                        if (!startStubPoint.equals(endStubPoint)) {// 起终折点不在一条直线上，添加折点
                            m1Point = startStubPoint;
                            m4Point = endStubPoint;
                        }

                        if (startDirection.equals("N") || startDirection.equals("S")) {// 计算单折折点
                            m2Point = new Point(endStubPoint.x, startStubPoint.y);
                        }
                        else {
                            m2Point = new Point(startStubPoint.x, endStubPoint.y);
                        }

                        if (m2Point.equals(m1Point) || m2Point.equals(m4Point)) {// 与折点重合取消
                            m2Point = null;
                        }
                    }
                }
            }
            else {// 与开始方向相反
                if (startDirection.equals(endDirection)) {// 同向单折
                    m1Point = startStubPoint;

                    if (startDirection.equals("N") || startDirection.equals("S")) {
                        m4Point = new Point(endStubPoint.x, startStubPoint.y);
                    }
                    else {
                        m4Point = new Point(startStubPoint.x, endStubPoint.y);
                    }

                    if (m4Point.equals(m1Point)) {// 与折点重合取消
                        m4Point = null;
                    }
                }
                else
                    if (startDirection.equals(reverseDirection(endDirection))) {// 反向中点双折
                        m1Point = startStubPoint;
                        m4Point = endStubPoint;

                        if (startDirection.equals("N") || startDirection.equals("S")) {
                            m2Point = new Point((startStubPoint.x + endStubPoint.x) / 2, startStubPoint.y);
                            m3Point = new Point((startStubPoint.x + endStubPoint.x) / 2, endStubPoint.y);
                        }
                        else {
                            m2Point = new Point(startStubPoint.x, (startStubPoint.y + endStubPoint.y) / 2);
                            m3Point = new Point(endStubPoint.x, (startStubPoint.y + endStubPoint.y) / 2);
                        }

                        // 与折点重合取消
                        if (m2Point.equals(m1Point)) {
                            m2Point = null;
                        }
                        if (m3Point.equals(m4Point)) {
                            m3Point = null;
                        }
                    }
                    else {// 单折
                        m1Point = startStubPoint;
                        m4Point = endStubPoint;

                        if (startDirection.equals("N") || startDirection.equals("S")) {
                            m2Point = new Point(endStubPoint.x, startStubPoint.y);
                        }
                        else {
                            m2Point = new Point(startStubPoint.x, endStubPoint.y);
                        }
                    }
            }

            pointList.add(startPoint);
            if (m1Point != null) {
                pointList.add(m1Point);
            }
            if (m2Point != null) {
                pointList.add(m2Point);
            }
            if (m3Point != null) {
                pointList.add(m3Point);
            }
            if (m4Point != null) {
                pointList.add(m4Point);
            }
            pointList.add(endPoint);
        }
    }

    private String reverseDirection(String direction) {// 反方向
        switch (direction) {
            case "N":
                return "S";
            case "E":
                return "W";
            case "S":
                return "N";
            case "W":
                return "E";
        }

        return null;
    }

    private boolean isSameDirection(Point startPoint, Point endPoint, String startDirection) {// 终点是否在起点方向的同向一侧
        if (startDirection.equals("N") && endPoint.y <= startPoint.y) {
            return true;
        }
        if (startDirection.equals("E") && endPoint.x >= startPoint.x) {
            return true;
        }
        if (startDirection.equals("S") && endPoint.y >= startPoint.y) {
            return true;
        }
        if (startDirection.equals("W") && endPoint.x <= startPoint.x) {
            return true;
        }

        return false;
    }

    private boolean isSubjectToEndStub(Point startStubPoint, Point endStubPoint, String startDirection, String endDirection) {// 以终点折点为准
        if (startDirection.equals("N")) {
            if ((endDirection.equals("N")) || (endDirection.equals("E") && endStubPoint.x <= startStubPoint.x) || (endDirection.equals("W") && endStubPoint.x >= startStubPoint.x)) {
                return true;
            }
        }
        if (startDirection.equals("E")) {
            if ((endDirection.equals("E")) || (endDirection.equals("N") && endStubPoint.y >= startStubPoint.y) || (endDirection.equals("S") && endStubPoint.y <= startStubPoint.y)) {
                return true;
            }
        }
        if (startDirection.equals("S")) {
            if ((endDirection.equals("S")) || (endDirection.equals("E") && endStubPoint.x <= startStubPoint.x) || (endDirection.equals("W") && endStubPoint.x >= startStubPoint.x)) {
                return true;
            }
        }
        if (startDirection.equals("W")) {
            if ((endDirection.equals("W")) || (endDirection.equals("N") && endStubPoint.y >= startStubPoint.y) || (endDirection.equals("S") && endStubPoint.y <= startStubPoint.y)) {
                return true;
            }
        }

        return false;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    @Override
    public int getMaxX() {
        int maxX = 0;
        for (Point point : pointList) {
            if (point.x > maxX) {
                maxX = point.x;
            }
        }

        return maxX;
    }

    @Override
    public int getMaxY() {
        int maxY = 0;
        for (Point point : pointList) {
            if (point.y > maxY) {
                maxY = point.y;
            }
        }

        return maxY;
    }

    @Override
    public void draw(Graphics2D g2d, String text) {
        g2d.setColor(getBorderColor());
        Point point;
        Point nextPoint;
        for (int i = 0; i < pointList.size() - 1; i++) {
            point = pointList.get(i);
            nextPoint = pointList.get(i + 1);
            if (i == pointList.size() - 2) {
                drawArrowLine((int) point.getX(), (int) point.getY(), (int) nextPoint.getX(), (int) nextPoint.getY(), g2d);
            }
            else {
                g2d.drawLine((int) point.getX(), (int) point.getY(), (int) nextPoint.getX(), (int) nextPoint.getY());
            }
        }

        if (StringUtils.isNotEmpty(text)) {
            Point startPoint = pointList.get(getTextLineIndex());
            Point endPoint = pointList.get(getTextLineIndex() + 1);
            int x = (int) (startPoint.getX() + endPoint.getX()) / 2 - getTextWidth() / 2 + getTextOffsetX();
            int y = (int) (startPoint.getY() + endPoint.getY()) / 2 - getTextHeight() / 2 + getTextOffsetY();
            g2d.setColor(getColor());// 绘制文字
            Font font = new Font(getFontFamily(), getFontWeight(), getFontSize());
            OdUtils.drawStringInCell(g2d, text, x, y, getTextWidth(), getTextHeight(), Shape.VERTICAL_ALIGN_MIDDLE, font);
        }
    }

    private void drawArrowLine(int sx, int sy, int ex, int ey, Graphics2D g2d) {
        double H = 10; // 箭头高度
        double L = 4; // 底边的一半
        int x3;
        int y3;
        int x4;
        int y4;
        double awrad = Math.atan(L / H); // 箭头角度
        double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长度
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        double x_3 = ex - arrXY_1[0]; // (x3,y3)是第一端点
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0]; // (x4,y4)是第二端点
        double y_4 = ey - arrXY_2[1];

        x3 = (int) x_3;
        y3 = (int) y_3;
        x4 = (int) x_4;
        y4 = (int) y_4;
        g2d.drawLine(sx, sy, ex, ey); // 画线

        GeneralPath triangle = new GeneralPath();
        triangle.moveTo(ex, ey);
        triangle.lineTo(x3, y3);
        triangle.lineTo(x4, y4);
        triangle.closePath();

        g2d.fill(triangle); // 实心箭头
    }

    private double[] rotateVec(int px, int py, double ang, boolean isChLen, double newLen) {
        double[] mathstr = new double[2];
        // 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度
        double vx = px * Math.cos(ang) - py * Math.sin(ang);
        double vy = px * Math.sin(ang) + py * Math.cos(ang);
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
    }

    @Override
    public void drawActive(Graphics2D g2d) {
    }

    @Override
    public void drawOptional(Graphics2D g2d, boolean isDefault) {
    }

    @Override
    public String getType() {
        return "line";
    }

    @Override
    public String getHtmlImgMapShape() {
        return "poly";
    }
}