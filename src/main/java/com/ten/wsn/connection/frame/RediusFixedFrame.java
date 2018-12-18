package com.ten.wsn.connection.frame;

import com.ten.wsn.connection.calculate.NRMValue;
import com.ten.wsn.connection.config.FrameSize;

import java.awt.*;
import java.util.List;

import static com.ten.wsn.connection.config.FrameSize.*;
import static com.ten.wsn.connection.config.FrameSize.X_AXIS_X;
import static com.ten.wsn.connection.config.FrameSize.X_AXIS_Y;

public class RediusFixedFrame extends BaseLineFrame {

    public RediusFixedFrame() {
        this.setLocation(1000, 100);
        this.setSize(FrameSize.WIDTH + 2 * FrameSize.ORIGIN_X, FrameSize.HEIGHT + 2 * FrameSize.ORIGIN_Y);
        setLineCanvas();
    }

    @Override
    void setLineCanvas() {
        lineCanvas = new RediusFixedCanvas();
        this.add(lineCanvas);
    }

    class RediusFixedCanvas extends BaseLineCanvas {

        @Override
        public void paint(Graphics g) {

            Graphics2D g2D = (Graphics2D) g;
            Color c = new Color(200, 70, 0);
            g.setColor(c);
            //绘图提示-消除锯齿
            g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            // 画坐标轴
            g2D.setStroke(new BasicStroke(Float.parseFloat("2.0F")));// 轴线粗度

            // 上边
            g.drawLine(ORIGIN_X, ORIGIN_Y, X_AXIS_X, X_AXIS_Y);
            // 下边
            g.drawLine(Y_AXIS_X, Y_AXIS_Y, X_AXIS_X, Y_AXIS_Y);
            // 左边
            g.drawLine(ORIGIN_X, ORIGIN_Y, Y_AXIS_X, Y_AXIS_Y);
            // 右边
            g.drawLine(X_AXIS_X, X_AXIS_Y, X_AXIS_X, Y_AXIS_Y);
            //绘制X坐标
            g.drawString("节点数目变化", Y_AXIS_X + 200, Y_AXIS_Y + 40);
            for (int i = 0; i <= 10; i++) {
                g.drawLine(Y_AXIS_X + i * 50, Y_AXIS_Y, Y_AXIS_X + i * 50, Y_AXIS_Y - 10);
                g.drawString(i * 10 + "", Y_AXIS_X + i * 50, Y_AXIS_Y + 10);
            }
            //绘制Y坐标
            g.drawString("连", Y_AXIS_X - 40, Y_AXIS_Y - 300);
            g.drawString("通", Y_AXIS_X - 40, Y_AXIS_Y - 280);
            g.drawString("率", Y_AXIS_X - 40, Y_AXIS_Y - 260);
            for (int i = 0; i <= 10; i++) {
                g.drawString((double) i / 10 + "", Y_AXIS_X - 20, Y_AXIS_Y - i * 50);
            }

            g2D.setStroke(new BasicStroke(Float.parseFloat("1.0F")));// 轴线粗度
            g.setColor(Color.BLUE);

            int num = 0;

            for (List<NRMValue> line : lines) {
                //绘制连通率的节点图
                for (int i = 0; i < line.size(); i++) {
                    NRMValue value = line.get(i);
                    //绘制指示线
                    if (i == 0) {
                        g.setColor(colors[num++ % 5]);
                        g.drawLine(X_AXIS_X - 50, X_AXIS_Y + (num + 1) * 50, X_AXIS_X, X_AXIS_Y + (num + 1) * 50);
                        g.drawString("r:" + value.getR(), X_AXIS_X - 100, X_AXIS_Y + (num + 1) * 50);
                    }

                    g.fillRect(value.getX(), value.getY(), 5, 5);

                    if (i < line.size() - 1) {
                        g.drawLine(value.getX(), value.getY(), line.get(i + 1).getX(), line.get(i + 1).getY());
                    }
                }
            }
        }

    }

}