package cn.membersheep.mappers;

import cn.membersheep.entity.District;

import java.util.List;

public interface DistrictMapper {
    /**
     * @Author 朱燕冰
     * @Description: //TODO 根据省市区代号获取详细数据
     * @Date 下午11:13 2019/12/4
     * @Param [code]
     * @return cn.membersheep.entity.District
     **/
    District findDistrictByCode(String code);
    /**
     * @Author 朱燕冰
     * @Description: //TODO 获取省市区列表
     * @Date 下午3:56 2019/12/14
     * @Param [parent]
     * @return java.util.List<cn.membersheep.entity.District>
     **/
    List <District> getList(String parent);
}
