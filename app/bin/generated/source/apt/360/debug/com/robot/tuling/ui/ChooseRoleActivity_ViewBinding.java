// Generated code from Butter Knife. Do not modify!
package com.robot.tuling.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.robot.tuling.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChooseRoleActivity_ViewBinding implements Unbinder {
  private ChooseRoleActivity target;

  private View view2131296297;

  private View view2131296293;

  private View view2131296298;

  private View view2131296495;

  private View view2131296299;

  private View view2131296496;

  private View view2131296300;

  private View view2131296497;

  private View view2131296294;

  private View view2131296489;

  private View view2131296295;

  private View view2131296490;

  private View view2131296296;

  private View view2131296491;

  @UiThread
  public ChooseRoleActivity_ViewBinding(ChooseRoleActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChooseRoleActivity_ViewBinding(final ChooseRoleActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_girl, "field 'mBtnGirl' and method 'onBtnGirlClicked'");
    target.mBtnGirl = Utils.castView(view, R.id.btn_girl, "field 'mBtnGirl'", Button.class);
    view2131296297 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnGirlClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_boy, "field 'mBtnBoy' and method 'onBtnBoyClicked'");
    target.mBtnBoy = Utils.castView(view, R.id.btn_boy, "field 'mBtnBoy'", Button.class);
    view2131296293 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBtnBoyClicked();
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_girl1, "method 'onGirl1Clicked'");
    view2131296298 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onGirl1Clicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_girl1, "method 'onGirl1Clicked'");
    view2131296495 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onGirl1Clicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_girl2, "method 'onGirl2Clicked'");
    view2131296299 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onGirl2Clicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_girl2, "method 'onGirl2Clicked'");
    view2131296496 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onGirl2Clicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_girl3, "method 'onGirl3Clicked'");
    view2131296300 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onGirl3Clicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_girl3, "method 'onGirl3Clicked'");
    view2131296497 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onGirl3Clicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_boy1, "method 'onBoy1Clicked'");
    view2131296294 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBoy1Clicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_boy1, "method 'onBoy1Clicked'");
    view2131296489 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBoy1Clicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_boy2, "method 'onBoy2Clicked'");
    view2131296295 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBoy2Clicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_boy2, "method 'onBoy2Clicked'");
    view2131296490 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBoy2Clicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_boy3, "method 'onBoy3Clicked'");
    view2131296296 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBoy3Clicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.text_boy3, "method 'onBoy3Clicked'");
    view2131296491 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onBoy3Clicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    ChooseRoleActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mBtnGirl = null;
    target.mBtnBoy = null;

    view2131296297.setOnClickListener(null);
    view2131296297 = null;
    view2131296293.setOnClickListener(null);
    view2131296293 = null;
    view2131296298.setOnClickListener(null);
    view2131296298 = null;
    view2131296495.setOnClickListener(null);
    view2131296495 = null;
    view2131296299.setOnClickListener(null);
    view2131296299 = null;
    view2131296496.setOnClickListener(null);
    view2131296496 = null;
    view2131296300.setOnClickListener(null);
    view2131296300 = null;
    view2131296497.setOnClickListener(null);
    view2131296497 = null;
    view2131296294.setOnClickListener(null);
    view2131296294 = null;
    view2131296489.setOnClickListener(null);
    view2131296489 = null;
    view2131296295.setOnClickListener(null);
    view2131296295 = null;
    view2131296490.setOnClickListener(null);
    view2131296490 = null;
    view2131296296.setOnClickListener(null);
    view2131296296 = null;
    view2131296491.setOnClickListener(null);
    view2131296491 = null;
  }
}
