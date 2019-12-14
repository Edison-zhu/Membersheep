package cn.membersheep.mappers;

import cn.membersheep.entity.Address;

public interface AddressMapper {
    /**
     * @Author 朱燕冰
     * @Description:  插入地址数据
     * @Date 下午10:12 2019/12/4
     * @Param [address]
     * @return java.lang.Integer受影响的行数
     **/
    Integer insert(Address address);
    /**
     * @Author 朱燕冰
     * @Description: //TODO 获取某用户的收货地址数量
     * @Date 下午10:35 2019/12/4
     * @Param [uid]
     * @return java.lang.Integer
     **/
    Integer getCountByUid(Integer uid);
}
