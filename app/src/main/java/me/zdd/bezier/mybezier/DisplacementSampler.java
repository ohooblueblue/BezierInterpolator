package me.zdd.bezier.mybezier;

import android.graphics.PointF;

/**
 * File Name:    DisplacementSampler.java
 * ClassName:    DisplacementSampler
 *
 * Description:
 *
 * @author ZDD
 * @date 2018年05月09日 15:06
 *
 */
public class DisplacementSampler extends CurveSampler
{

    @Override
    protected float[] buildSamplers(Curve curve, int size)
    {
        float[] displacementSamplers = new float[size];

        int superSamplerSize = 4 * size;

        PointF[] superSamplers = new PointF[superSamplerSize];
        for (int i = 0; i < superSamplerSize; i++)
        {
            PointF PointF = curve.getPathPointF(i / (float) (superSamplerSize - 1));
            superSamplers[i] = new PointF(PointF.x, PointF.y);
        }

        int prevFloorIndex = 0;
        for (int i = 0; i < size; i++)
        {
            float t = i / (float) (size - 1);

            int floorIndex = searchNearestFloor(superSamplers, prevFloorIndex, t);
            int ceilIndex = Math.min(floorIndex + 1, superSamplerSize - 1);

            float t0 = superSamplers[floorIndex].x;
            float t1 = superSamplers[ceilIndex].x;

            float d0 = superSamplers[floorIndex].y;
            float d1 = superSamplers[ceilIndex].y;

            if (floorIndex != ceilIndex && t0 != t1)
            {
                displacementSamplers[i] = linearInterpolate(t, t0, d0, t1, d1);
            }
            else
            {
                displacementSamplers[i] = d0;
            }
            prevFloorIndex = floorIndex;
        }
        return displacementSamplers;
    }

    @Override
    protected float getSamplerValue(float t)
    {
        float t1 = t < 0 ? 0 : t;
        t1 = t1 > 1 ? 1 : t;
        return interpolateValue(mSamplers, t1);
    }
}
