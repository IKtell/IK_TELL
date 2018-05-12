// Generated code from Butter Knife. Do not modify!
package com.robot.tuling.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.robot.tuling.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SkillPointsActivity_ViewBinding implements Unbinder {
  private SkillPointsActivity target;

  private View view2131296499;

  @UiThread
  public SkillPointsActivity_ViewBinding(SkillPointsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SkillPointsActivity_ViewBinding(final SkillPointsActivity target, View source) {
    this.target = target;

    View view;
    target.mRecyclerChooseMajor = Utils.findRequiredViewAsType(source, R.id.recycler_choose_major, "field 'mRecyclerChooseMajor'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.text_major, "field 'mTextMajor' and method 'onTextMajorClicked'");
    target.mTextMajor = Utils.castView(view, R.id.text_major, "field 'mTextMajor'", TextView.class);
    view2131296499 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onTextMajorClicked();
      }
    });
    target.mCardView = Utils.findRequiredViewAsType(source, R.id.card_view, "field 'mCardView'", CardView.class);
    target.mTextChooseMajor = Utils.findRequiredViewAsType(source, R.id.text_choose_major, "field 'mTextChooseMajor'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SkillPointsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerChooseMajor = null;
    target.mTextMajor = null;
    target.mCardView = null;
    target.mTextChooseMajor = null;

    view2131296499.setOnClickListener(null);
    view2131296499 = null;
  }
}
