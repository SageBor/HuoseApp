package team.house.cn.HuoseApp.bean;

import java.io.Serializable;

/**
 * Created by kenan on 15/12/16.
 */
public class NoticeBean implements Serializable {

    /**
     * art_title : 公告标题
     * art_source : 公告链接
     */

    private String art_title;
    private String art_source;

    public void setArt_title(String art_title) {
        this.art_title = art_title;
    }

    public void setArt_source(String art_source) {
        this.art_source = art_source;
    }

    public String getArt_title() {
        return art_title;
    }

    public String getArt_source() {
        return art_source;
    }
}
