package com.ten.wsn.coverage.frame;

import com.ten.wsn.coverage.calculate.CalculationThread;
import com.ten.wsn.coverage.config.FrameSize;

import java.awt.*;

/**
 * 绘制连通率的图
 */
public class DataCanves extends Canvas implements FrameSize {

    private static double[] fugailv;

    public DataCanves() {
        super();
        fugailv = CalculationThread.getFugailv();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

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
        g.drawString("通信半径变化", Y_AXIS_X + 200, Y_AXIS_Y + 40);
        for (int i = 0; i <= 10; i++) {
            g.drawLine(Y_AXIS_X + i * 50, Y_AXIS_Y, Y_AXIS_X + i * 50, Y_AXIS_Y - 10);
            g.drawString(String.format("%.1f", (double) i * 0.1), Y_AXIS_X + i * 50, Y_AXIS_Y + 10);
        }
        //绘制Y坐标
        g.drawString("覆", Y_AXIS_X - 40, Y_AXIS_Y - 300);
        g.drawString("盖", Y_AXIS_X - 40, Y_AXIS_Y - 280);
        g.drawString("率", Y_AXIS_X - 40, Y_AXIS_Y - 260);

        for (int i = 0; i <= 10; i++) {
            g.drawString((double) i / 10 + "", Y_AXIS_X - 20, Y_AXIS_Y - i * 50);
        }

        g2D.setStroke(new BasicStroke(Float.parseFloat("1.0F")));// 轴线粗度
        g.setColor(Color.BLUE);

        for (int i = 0; i < fugailv.length; i++) {
            if (i == 0) {
                g.drawLine(100, 600, 100 + 25 * (i + 1), 600 - (int) (fugailv[0] * 500));
                g.fillRect(100, 600, 4, 4);
            } else {
                g.drawLine(75 + i * 50, 600 - (int) (fugailv[i - 1] * 500), 75 + 50 * (i + 1), 600 - (int) (fugailv[i] * 500));
                g.fillRect(75 + i * 50, 600 - (int) (fugailv[i - 1] * 500), 4, 4);
            }
        }


    }
}
