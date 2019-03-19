package sl.nim.netease.com.gitlijar.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity
public class SqBean {
    @Id
    private String uid;
    private String name;
    private String path;

    @Generated(hash = 48158341)
    public SqBean(String uid, String name, String path) {
        this.uid = uid;
        this.name = name;
        this.path = path;
    }

    @Generated(hash = 761430409)
    public SqBean() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
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
