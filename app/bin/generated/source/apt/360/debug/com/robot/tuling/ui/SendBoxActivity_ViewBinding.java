// Generated code from Butter Knife. Do not modify!
package com.robot.tuling.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.robot.tuling.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SendBoxActivity_ViewBinding implements Unbinder {
  private SendBoxActivity target;

  private View view2131296372;

  @UiThread
  public SendBoxActivity_ViewBinding(SendBoxActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SendBoxActivity_ViewBinding(final SendBoxActivity target, View source) {
    this.target = target;

    View view;
    target.toolbar = Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    view = Utils.findRequiredView(source, R.id.iv_send_msg, "field 'ivSendMsg' and method 'onViewClicked'");
    target.ivSendMsg = Utils.castView(view, R.id.iv_send_msg, "field 'ivSendMsg'", ImageView.class);
    view2131296372 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
    target.ivSendBox = Utils.findRequiredViewAsType(source, R.id.iv_send_box, "field 'ivSendBox'", ImageView.class);
    target.etMsg = Utils.findRequiredViewAsType(source, R.id.et_msg, "field 'etMsg'", EditText.class);
    target.llMsg = Utils.findRequiredViewAsType(source, R.id.ll_msg, "field 'llMsg'", LinearLayout.class);
    target.rlMsg = Utils.findRequiredViewAsType(source, R.id.rl_msg, "field 'rlMsg'", RelativeLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SendBoxActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.toolbar = null;
    target.ivSendMsg = null;
    target.ivSendBox = null;
    target.etMsg = null;
    target.llMsg = null;
    target.rlMsg = null;

    view2131296372.setOnClickListener(null);
    view2131296372 = null;
  }
}
