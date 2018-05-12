// Generated code from Butter Knife. Do not modify!
package com.robot.tuling.base;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.robot.tuling.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BaseTalkActivity_ViewBinding implements Unbinder {
  private BaseTalkActivity target;

  @UiThread
  public BaseTalkActivity_ViewBinding(BaseTalkActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public BaseTalkActivity_ViewBinding(BaseTalkActivity target, View source) {
    this.target = target;

    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'mViewPager'", ViewPager.class);
    target.mLlIndicator = Utils.findRequiredViewAsType(source, R.id.ll_indicator, "field 'mLlIndicator'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BaseTalkActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mViewPager = null;
    target.mLlIndicator = null;
  }
}
