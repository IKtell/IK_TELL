// Generated code from Butter Knife. Do not modify!
package com.robot.tuling.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.robot.tuling.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChooseMajorAdapter$ChooseMajorViewHolder_ViewBinding implements Unbinder {
  private ChooseMajorAdapter.ChooseMajorViewHolder target;

  @UiThread
  public ChooseMajorAdapter$ChooseMajorViewHolder_ViewBinding(ChooseMajorAdapter.ChooseMajorViewHolder target,
      View source) {
    this.target = target;

    target.textSkill = Utils.findRequiredViewAsType(source, R.id.text_skill, "field 'textSkill'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChooseMajorAdapter.ChooseMajorViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.textSkill = null;
  }
}
