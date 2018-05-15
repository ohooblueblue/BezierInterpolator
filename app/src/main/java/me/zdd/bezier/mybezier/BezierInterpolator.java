package me.zdd.bezier.mybezier;

import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

/**
 * File Name:    BezierInterpolator.java
 * ClassName:    BezierInterpolator
 *
 * Description: 贝塞尔曲线插值器
 *
 * @author ZDD
 * @date 2018年05月09日 15:06
 *
 */
public class BezierInterpolator extends CurveInterpolator
{
    /**
     * 构造贝塞尔插值器，传入两个控制点，此种方式会增加(0,0)和(1,1)两个控制点
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     */
    public BezierInterpolator(float x1, float y1, float x2, float y2)
    {
        List<PointF> controlPointFs = new ArrayList<PointF>();
        controlPointFs.add(new PointF(0f, 0f));
        controlPointFs.add(new PointF(x1, y1));
        controlPointFs.add(new PointF(x2, y2));
        controlPointFs.add(new PointF(1f, 1f));
        buildSampler(controlPointFs);
    }

    /**
     * 构造贝塞尔插值器，传入多个控制点
     * @param PointFs
     */
    public BezierInterpolator(List<PointF> PointFs)
    {
        buildSampler(PointFs);
    }

    private void buildSampler(List<PointF> controlPointFs)
    {
        BezierCurve bezierCurve = new BezierCurve(controlPointFs);
        setCurveType(bezierCurve);
        DisplacementSampler samplers = new DisplacementSampler();
        setSamplerUsage(samplers);
        build();
    }
}
