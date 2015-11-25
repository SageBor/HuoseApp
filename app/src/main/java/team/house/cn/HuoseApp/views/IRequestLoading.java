package team.house.cn.HuoseApp.views;

import android.view.View.OnClickListener;

public abstract class IRequestLoading {
    public abstract void statuesToError();
    public abstract void statuesToNormal();
    public abstract void statuesToInLoading();
    public abstract void statuesToInLoading(String loadingText);
    public abstract void setTag(String tag);
    public abstract String getTag();
    public abstract void statuesToError(String errorInfo);
    public abstract int getStatus();
    public abstract void setAgainListener(OnClickListener againListener);
    public void setCancelListener(OnClickListener cancelListener){};
    public void setOkListener(OnClickListener okListener){};
    
}
