package io.clic.sashi;

import android.app.Activity;
import android.content.Context;
import com.google.appinventor.components.annotations.*;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.EventDispatcher;
import android.os.Handler;
import android.view.View;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.*;
import com.google.appinventor.components.runtime.Component;


@DesignerComponent(
        version = 1,
        description = "",
        category = ComponentCategory.EXTENSION,
        nonVisible = true,
        iconName = "")

@SimpleObject(external = true)
//Libraries
@UsesLibraries(libraries = "")
//Permissions
@UsesPermissions(permissionNames = "")

public class Clic extends AndroidNonvisibleComponent {

    //Activity and Context
    private Context context;
    private Activity activity;
     private int i=0;

    public Clic(ComponentContainer container){
        super(container.$form());
        this.activity = container.$context();
        this.context = container.$context();
    }

    @SimpleEvent
    public void Click(AndroidViewComponent component){
        EventDispatcher.dispatchEvent(this,"Click",component);
    }
    @SimpleEvent
    public void LongClick(AndroidViewComponent component){
        EventDispatcher.dispatchEvent(this,"LongClick",component);
    }
    @SimpleEvent
    public void DoubleClick(AndroidViewComponent component){
        EventDispatcher.dispatchEvent(this,"DoubleClick",component);
    }
    @SimpleFunction
    public void PerformClick(final AndroidViewComponent component){
        component.getView().performClick();
    }
    @SimpleFunction
    public void PerformLongClick(final AndroidViewComponent component){
        component.getView().performLongClick();
    }
    @SimpleFunction
    public void PerformDoubleClick(final AndroidViewComponent component){
        component.getView().performClick();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    component.getView().performClick();
                }
            },200);
    }
    @SimpleFunction
    public void SetClickable(final AndroidViewComponent component ,final boolean clickable){
        component.getView().setClickable(clickable);
        if (clickable) {
            component.getView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    i++;
                    Handler handler=new Handler();
                    Runnable runnable=new Runnable() {
                        @Override
                        public void run() {
                            i=0;
                        }
                    };
                    if (i==1){
                        Click(component);
                        handler.postDelayed(runnable,400);
                    }else if (i==2){
                        DoubleClick(component);
                    }
                }
            });
            component.getView().setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    LongClick(component);
                    return false;
                }
            });
        }
    }
}

   
