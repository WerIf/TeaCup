package sl.nim.netease.com.gitlijar.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class SqBean {
    @Id(autoincrement = true)
    private Long uid;
    private String name;
    private String path;

    @Generated(hash = 2081679170)
    public SqBean(Long uid, String name, String path) {
        this.uid = uid;
        this.name = name;
        this.path = path;
    }

    @Generated(hash = 761430409)
    public SqBean() {
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
