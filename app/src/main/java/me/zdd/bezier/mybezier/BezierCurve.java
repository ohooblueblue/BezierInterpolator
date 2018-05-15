package me.zdd.bezier.mybezier;

import android.graphics.PointF;

import java.util.List;

/**
 * File Name:    BezierCurve.java
 * ClassName:    BezierCurve
 *
 * Description: 贝塞尔曲线实体
 *
 * @author ZDD
 * @date 2018年05月09日 15:06
 *
 */
public class BezierCurve extends Curve
{
    public BezierCurve(PointF... PointFs)
    {
        super(PointFs);
    }

    public BezierCurve(List<PointF> PointFs)
    {
        super(PointFs);
    }

    @Override
    public PointF getPathPointF(float t)
    {
        final int size = mControlPointFs.length;
        if (size < 2)
        {
            return new PointF(0, 0);
        }

        for (int i = 0; i < size; i++)
        {
            if (mTempPointFs[i] == null)
            {
                mTempPointFs[i] = new PointF();
            }
            mTempPointFs[i].set(mControlPointFs[i]);
        }
        return deCasteljau(mTempPointFs, t);
    }

    public static PointF deCasteljau(PointF[] PointFs, float t)
    {
        final int n = PointFs.length;
        for (int i = 1; i <= n; i++)
        {
            for (int j = 0; j < n - i; j++)
            {
                PointFs[j].x = (1 - t) * PointFs[j].x + t * PointFs[j + 1].x;
                PointFs[j].y = (1 - t) * PointFs[j].y + t * PointFs[j + 1].y;
            }
        }
        return PointFs[0];
    }
}
