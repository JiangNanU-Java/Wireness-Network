package com.ten.wsn.connection;

import java.awt.*;
import java.util.ArrayList;

/**
 * 绘制连通率的图
 */
public class DataCanves extends Canvas implements FrameSize{

    private ArrayList<ConnectionLine> lines;
    private Color[] colors;

    public DataCanves() {
        super();
        lines = ConnectionLine.getR_list();
        colors=new Color[5];
        colors[0]=Color.GREEN;
        colors[1]=Color.BLUE;
        colors[2]=Color.orange;
        colors[3]=Color.RED;
        colors[4]=Color.MAGENTA;
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
        g.drawLine(Origin_X, Origin_Y, XAxis_X, XAxis_Y);
        // 下边
        g.drawLine(YAxis_X, YAxis_Y, XAxis_X, YAxis_Y);
        // 左边
        g.drawLine(Origin_X, Origin_Y, YAxis_X, YAxis_Y);
        // 右边
        g.drawLine(XAxis_X, XAxis_Y, XAxis_X, YAxis_Y);
        //绘制X坐标
        g.drawString("节点数目变化",YAxis_X+200,YAxis_Y+40);
        for (int i=0;i<=10;i++){
            g.drawLine(YAxis_X+i*50,YAxis_Y,YAxis_X+i*50,YAxis_Y-10);
            g.drawString(i*10+"",YAxis_X+i*50,YAxis_Y+10);
        }
        //绘制Y坐标
        g.drawString("连",YAxis_X-40,YAxis_Y-300);
        g.drawString("通",YAxis_X-40,YAxis_Y-280);
        g.drawString("率",YAxis_X-40,YAxis_Y-260);
        for (int i = 0; i <= 10; i++) {
            g.drawString((double)i/10+"",YAxis_X-20,YAxis_Y-i*50);
        }

        g2D.setStroke(new BasicStroke(Float.parseFloat("1.0F")));// 轴线粗度
        g.setColor(Color.BLUE);

        int num=0;

        for (ArrayList<NRMValue> line : lines) {
            //绘制连通率的节点图
            for (int i = 0; i < line.size(); i++) {
                NRMValue value = line.get(i);
                //绘制指示线
                if (i==0){
                    g.setColor(colors[num++]);
                    g.drawLine(XAxis_X-50,XAxis_Y+(num+1)*50,XAxis_X,XAxis_Y+(num+1)*50);
                    g.drawString("r:"+value.getR(),XAxis_X-100,XAxis_Y+(num+1)*50);
                }

                g.fillRect(value.getX(), value.getY(), 5, 5);

                if (i < line.size() - 1) {
                    g.drawLine(value.getX(), value.getY(), line.get(i + 1).getX(), line.get(i + 1).getY());
                }
            }
        }


    }
}
