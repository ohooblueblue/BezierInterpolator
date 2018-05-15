package me.zdd.bezier.mybezier;

import android.graphics.PointF;

import java.util.List;

/**
 * File Name:    Curve.java
 * ClassName:    Curve
 *
 * Description:
 *
 * @author ZDD
 * @date 2018年05月09日 15:06
 *
 */
public abstract class Curve
{
    protected PointF[] mControlPointFs;

    protected PointF[] mTempPointFs;

    public Curve(PointF... PointFs)
    {
        setControlPointFs(PointFs);
    }

    public Curve(List<PointF> PointFs)
    {
        setControlPointFs(PointFs);
    }

    public void setControlPointFs(PointF... PointFs)
    {
        mControlPointFs = PointFs;
    }

    public void setControlPointFs(List<PointF> PointFs)
    {
        mControlPointFs = new PointF[PointFs.size()];
        mTempPointFs = new PointF[PointFs.size()];
        PointFs.toArray(mControlPointFs);
    }

    public abstract PointF getPathPointF(float t);
}
