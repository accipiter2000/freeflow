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

public class LineShape extends Shape implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    protected transient List<Point> pointList;

    @Override
    public void init(Object owner) {
        super.init(owner);

        initPointList();
    }

    private void initPointList() {
        pointList = new ArrayList<>();

        NodeDef sourceNode = ((FlowDef) owner).getSourceNodeDef();
        NodeDef targetNode = ((FlowDef) owner).getTargetNodeDef();

        int sourceNodeLeft = sourceNode.getShape().getLeft();
        int sourceNodeTop = sourceNode.getShape().getTop();
        int sourceNodeWidth = sourceNode.getShape().getWidth();
        int sourceNodeHeight = sourceNode.getShape().getHeight();
        int targetNodeLeft = targetNode.getShape().getLeft();
        int targetNodeTop = targetNode.getShape().getTop();
        int targetNodeWidth = targetNode.getShape().getWidth();
        int targetNodeHeight = targetNode.getShape().getHeight();

        String startDirection = null;
        String endDirection = null;
        Point startPoint = null;
        Point startStretchPoint = null;
        Point m1Point = null;
        Point m2Point = null;
        Point endStretchPoint = null;
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
            endDirection = getLinePath().substring(getLinePath().length() - 1, getLinePath().length());

            if (startDirection.equals("N")) {// 计算起点，终点和各自的延长点
                startPoint = new Point(sourceNodeLeft + sourceNodeWidth / 2, sourceNodeTop);
                startStretchPoint = new Point(sourceNodeLeft + sourceNodeWidth / 2, sourceNodeTop - getStub());
            }
            if (startDirection.equals("E")) {
                startPoint = new Point(sourceNodeLeft + sourceNodeWidth, sourceNodeTop + sourceNodeHeight / 2);
                startStretchPoint = new Point(sourceNodeLeft + sourceNodeWidth + getStub(), sourceNodeTop + sourceNodeHeight / 2);
            }
            if (startDirection.equals("S")) {
                startPoint = new Point(sourceNodeLeft + sourceNodeWidth / 2, sourceNodeTop + sourceNodeHeight);
                startStretchPoint = new Point(sourceNodeLeft + sourceNodeWidth / 2, sourceNodeTop + sourceNodeHeight + getStub());
            }
            if (startDirection.equals("W")) {
                startPoint = new Point(sourceNodeLeft, sourceNodeTop + sourceNodeHeight / 2);
                startStretchPoint = new Point(sourceNodeLeft - getStub(), sourceNodeTop + sourceNodeHeight / 2);
            }
            if (endDirection.equals("N")) {
                endPoint = new Point(targetNodeLeft + targetNodeWidth / 2, targetNodeTop);
                endStretchPoint = new Point(targetNodeLeft + targetNodeWidth / 2, targetNodeTop - getStub());
            }
            if (endDirection.equals("E")) {
                endPoint = new Point(targetNodeLeft + targetNodeWidth, targetNodeTop + targetNodeHeight / 2);
                endStretchPoint = new Point(targetNodeLeft + targetNodeWidth + getStub(), targetNodeTop + targetNodeHeight / 2);
            }
            if (endDirection.equals("S")) {
                endPoint = new Point(targetNodeLeft + targetNodeWidth / 2, targetNodeTop + targetNodeHeight);
                endStretchPoint = new Point(targetNodeLeft + targetNodeWidth / 2, targetNodeTop + targetNodeHeight + getStub());
            }
            if (endDirection.equals("W")) {
                endPoint = new Point(targetNodeLeft, targetNodeTop + targetNodeHeight / 2);
                endStretchPoint = new Point(targetNodeLeft - getStub(), targetNodeTop + targetNodeHeight / 2);
            }

            // 计算中间折点
            if (isSameDirection(startStretchPoint, endStretchPoint, startDirection)) {// 与开始方向相同
                if (isSameRotation(startPoint, endPoint, startDirection, endDirection)) {// 同向折线
                    if (startDirection.equals("N") || startDirection.equals("S")) {
                        m1Point = new Point(startStretchPoint.x, endStretchPoint.y);
                    }
                    else {
                        m1Point = new Point(endStretchPoint.x, startStretchPoint.y);
                    }

                    if (startDirection.equals(endDirection)) {
                        m2Point = endStretchPoint;
                    }
                }
                else {// 反向折线
                    if (startDirection.equals(reverseDirection(endDirection))) {// 中点双折
                        if (startDirection.equals("N") || startDirection.equals("S")) {
                            m1Point = new Point(startStretchPoint.x, (startStretchPoint.y + endStretchPoint.y) / 2);
                            m2Point = new Point(endStretchPoint.x, (startStretchPoint.y + endStretchPoint.y) / 2);
                        }
                        else {
                            m1Point = new Point((startStretchPoint.x + endStretchPoint.x) / 2, startStretchPoint.y);
                            m2Point = new Point((startStretchPoint.x + endStretchPoint.x) / 2, endStretchPoint.y);
                        }
                    }
                    else {// 反向单折
                        if (startDirection.equals("N") || startDirection.equals("S")) {
                            m1Point = new Point(endStretchPoint.x, startStretchPoint.y);
                        }
                        else {
                            m1Point = new Point(startStretchPoint.x, endStretchPoint.y);
                        }
                    }
                }
            }
            else {// 与开始方向相反
                if (startDirection.equals(reverseDirection(endDirection))) {// 中点双折
                    if (startDirection.equals("N") || startDirection.equals("S")) {
                        m1Point = new Point((startStretchPoint.x + endStretchPoint.x) / 2, startStretchPoint.y);
                        m2Point = new Point((startStretchPoint.x + endStretchPoint.x) / 2, endStretchPoint.y);
                    }
                    else {
                        m1Point = new Point(startStretchPoint.x, (startStretchPoint.y + endStretchPoint.y) / 2);
                        m2Point = new Point(endStretchPoint.x, (startStretchPoint.y + endStretchPoint.y) / 2);
                    }
                }
                else {// 单折
                    if (startDirection.equals("N") || startDirection.equals("S")) {
                        m1Point = new Point(endStretchPoint.x, startStretchPoint.y);
                    }
                    else {
                        m1Point = new Point(startStretchPoint.x, endStretchPoint.y);
                    }
                }
            }

            pointList.add(startPoint);
            if (m1Point != null) {
                if (startPoint.x != m1Point.x && startPoint.y != m1Point.y) {
                    pointList.add(startStretchPoint);
                }
                if (!((startPoint.x == m1Point.x && m1Point.x == endPoint.x) || (startPoint.y == m1Point.y && m1Point.y == endPoint.y))) {
                    pointList.add(m1Point);
                }
            }
            if (m2Point != null) {
                if (!((startPoint.x == m2Point.x && m2Point.x == endPoint.x) || (startPoint.y == m2Point.y && m2Point.y == endPoint.y))) {
                    pointList.add(m2Point);
                }
                if (endPoint.x != m2Point.x && endPoint.y != m2Point.y) {
                    pointList.add(endStretchPoint);
                }
            }
            pointList.add(endPoint);
        }
    }

    private String reverseDirection(String direction) {// 反方向
        if (direction.equals("N")) {
            return "S";
        }
        if (direction.equals("E")) {
            return "W";
        }
        if (direction.equals("S")) {
            return "N";
        }
        if (direction.equals("W")) {
            return "E";
        }

        return null;
    }

    private boolean isSameDirection(Point startPoint, Point endPoint, String startDirection) {// 终点是否在起点方向的同向一侧
        if (startDirection.equals("N") && endPoint.y < startPoint.y) {
            return true;
        }
        if (startDirection.equals("E") && endPoint.x > startPoint.x) {
            return true;
        }
        if (startDirection.equals("S") && endPoint.y > startPoint.y) {
            return true;
        }
        if (startDirection.equals("W") && endPoint.x < startPoint.x) {
            return true;
        }

        return false;
    }

    private boolean isSameRotation(Point startPoint, Point endPoint, String startDirection, String endDirection) {// 折线是否为同一旋转方向
        if (startDirection.equals("N")) {
            if ((endDirection.equals("N") && endPoint.y < startPoint.y) || (endDirection.equals("E") && endPoint.x < startPoint.x) || (endDirection.equals("W") && endPoint.x > startPoint.x)) {
                return true;
            }
        }
        if (startDirection.equals("E")) {
            if ((endDirection.equals("E") && endPoint.x > startPoint.x) || (endDirection.equals("N") && endPoint.y > startPoint.y) || (endDirection.equals("S") && endPoint.y < startPoint.y)) {
                return true;
            }
        }
        if (startDirection.equals("S")) {
            if ((endDirection.equals("S") && endPoint.y > startPoint.y) || (endDirection.equals("E") && endPoint.x < startPoint.x) || (endDirection.equals("W") && endPoint.x > startPoint.x)) {
                return true;
            }
        }
        if (startDirection.equals("W")) {
            if ((endDirection.equals("W") && endPoint.x < startPoint.x) || (endDirection.equals("N") && endPoint.y > startPoint.y) || (endDirection.equals("S") && endPoint.y < startPoint.y)) {
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
            Font font = new Font(getFontFamily(), getFontWeight(), getFontSize());
            OdUtils.drawStringInCell(g2d, text, x, y, getTextWidth(), getTextHeight(), Shape.VERTICAL_ALIGN_MIDDLE, font);
        }
    }

    private void drawArrowLine(int sx, int sy, int ex, int ey, Graphics2D g2d) {
        double H = 10; // 箭头高度
        double L = 4; // 底边的一半
        int x3 = 0;
        int y3 = 0;
        int x4 = 0;
        int y4 = 0;
        double awrad = Math.atan(L / H); // 箭头角度
        double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长度
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        double x_3 = ex - arrXY_1[0]; // (x3,y3)是第一端点
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0]; // (x4,y4)是第二端点
        double y_4 = ey - arrXY_2[1];

        Double X3 = new Double(x_3);
        x3 = X3.intValue();
        Double Y3 = new Double(y_3);
        y3 = Y3.intValue();
        Double X4 = new Double(x_4);
        x4 = X4.intValue();
        Double Y4 = new Double(y_4);
        y4 = Y4.intValue();
        g2d.drawLine(sx, sy, ex, ey); // 画线

        GeneralPath triangle = new GeneralPath();
        triangle.moveTo(ex, ey);
        triangle.lineTo(x3, y3);
        triangle.lineTo(x4, y4);
        triangle.closePath();

        g2d.fill(triangle); // 实心箭头
    }

    private double[] rotateVec(int px, int py, double ang, boolean isChLen, double newLen) {
        double mathstr[] = new double[2];
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