package by.mnk.htp.glotovs.msr.vo;

import by.mnk.htp.glotovs.msr.entities.UserEntity;

import java.util.List;

/**
 * Created by Sefire on 20.11.2016.
 */

public class UserPaginationVO {
    private Integer totalUsersCount;
    private String page;
    private List<UserEntity> userEntityList;

    public UserPaginationVO() {
    }

    public UserPaginationVO(Integer totalUsersCount, String page, List<UserEntity> userEntityList) {
        this.totalUsersCount = totalUsersCount;
        this.page = page;
        this.userEntityList = userEntityList;
    }

    public Integer getTotalUsersCount() {
        return totalUsersCount;
    }

    public void setTotalUsersCount(Integer totalUsersCount) {
        this.totalUsersCount = totalUsersCount;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<UserEntity> getUserEntityList() {
        return userEntityList;
    }

    public void setUserEntityList(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
    }
}
