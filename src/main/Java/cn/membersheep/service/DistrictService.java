package cn.membersheep.service;

import cn.membersheep.entity.District;

import java.util.List;

public interface DistrictService {
    District getDistrictCode(String code);
    /**
     * @Author 朱燕冰
     * @Description: //TODO 获取省市区列表
     * @Date 下午3:56 2019/12/14
     * @Param [parent]
     * @return java.util.List<cn.membersheep.entity.District>
     **/
    List<District> getList(String parent);
}
