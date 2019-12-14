package cn.membersheep.service;

import cn.membersheep.entity.Address;

public interface AddressService {
    /**
     * @Author 朱燕冰
     * @Description: //TODO 新增的收货地址
     * @Date 下午11:27 2019/12/4
     * @Param [address]
     * @return cn.membersheep.entity.Address
     **/
    Address addnew(Address address);
    Address insetInto(Address address);
}
