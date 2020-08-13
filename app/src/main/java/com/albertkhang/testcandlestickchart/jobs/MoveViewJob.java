
package com.albertkhang.testcandlestickchart.jobs;

import android.util.Log;
import android.view.View;

import com.albertkhang.testcandlestickchart.MainActivity;
import com.albertkhang.testcandlestickchart.utils.ObjectPool;
import com.albertkhang.testcandlestickchart.utils.Transformer;
import com.albertkhang.testcandlestickchart.utils.ViewPortHandler;

/**
 * Created by Philipp Jahoda on 19/02/16.
 */
public class MoveViewJob extends ViewPortJob {

    private static ObjectPool<MoveViewJob> pool;

    static {
        Log.d(MainActivity.FLOW_TAG, "MoveViewJob static");
        pool = ObjectPool.create(2, new MoveViewJob(null, 0, 0, null, null));
        pool.setReplenishPercentage(0.5f);
    }

    public static MoveViewJob getInstance(ViewPortHandler viewPortHandler, float xValue, float yValue, Transformer trans, View v) {
        Log.d(MainActivity.FLOW_TAG, "MoveViewJob getInstance");

        MoveViewJob result = pool.get();
        result.mViewPortHandler = viewPortHandler;
        result.xValue = xValue;
        result.yValue = yValue;
        result.mTrans = trans;
        result.view = v;
        return result;
    }

    public static void recycleInstance(MoveViewJob instance) {
        Log.d(MainActivity.FLOW_TAG, "MoveViewJob recycleInstance");
        pool.recycle(instance);
    }

    public MoveViewJob(ViewPortHandler viewPortHandler, float xValue, float yValue, Transformer trans, View v) {
        super(viewPortHandler, xValue, yValue, trans, v);
    }

    @Override
    public void run() {
        Log.d(MainActivity.FLOW_TAG, "MoveViewJob run");

        pts[0] = xValue;
        pts[1] = yValue;

        mTrans.pointValuesToPixel(pts);
        mViewPortHandler.centerViewPort(pts, view);

        this.recycleInstance(this);
    }

    @Override
    protected ObjectPool.Poolable instantiate() {
        Log.d(MainActivity.FLOW_TAG, "MoveViewJob instantiate");
        return new MoveViewJob(mViewPortHandler, xValue, yValue, mTrans, view);
    }
}
