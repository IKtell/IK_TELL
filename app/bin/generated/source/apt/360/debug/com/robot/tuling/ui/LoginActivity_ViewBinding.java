// Generated code from Butter Knife. Do not modify!
package com.robot.tuling.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.robot.tuling.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131296301;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.mEditUserNo = Utils.findRequiredViewAsType(source, R.id.edit_user_no, "field 'mEditUserNo'", TextInputLayout.class);
    target.mEditPassword = Utils.findRequiredViewAsType(source, R.id.edit_password, "field 'mEditPassword'", TextInputLayout.class);
    view = Utils.findRequiredView(source, R.id.btn_login, "method 'onLoginClicked'");
    view2131296301 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onLoginClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mEditUserNo = null;
    target.mEditPassword = null;

    view2131296301.setOnClickListener(null);
    view2131296301 = null;
  }
}
