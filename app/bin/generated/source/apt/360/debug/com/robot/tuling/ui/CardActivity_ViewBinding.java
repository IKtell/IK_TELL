// Generated code from Butter Knife. Do not modify!
package com.robot.tuling.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.robot.tuling.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class CardActivity_ViewBinding implements Unbinder {
  private CardActivity target;

  @UiThread
  public CardActivity_ViewBinding(CardActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public CardActivity_ViewBinding(CardActivity target, View source) {
    this.target = target;

    target.item = Utils.findRequiredView(source, R.id.item, "field 'item'");
  }

  @Override
  @CallSuper
  public void unbind() {
    CardActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.item = null;
  }
}
