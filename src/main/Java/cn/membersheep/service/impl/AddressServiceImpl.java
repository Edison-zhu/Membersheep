package cn.membersheep.service.impl;

import cn.membersheep.entity.Address;
import cn.membersheep.entity.District;
import cn.membersheep.mappers.AddressMapper;
import cn.membersheep.service.AddressService;
import cn.membersheep.service.DistrictService;
import cn.membersheep.service.ex.InsertDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("addressService")
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    DistrictService districtService;
    @Override
    public Address addnew(Address address) {
        //完善数据设recv_dis
        String revcDistrict = getDistrictByCode(address.getRecvProvince(),address.getRecvCity(),address.getRecvArea());
        address.setRecvDistrict(revcDistrict);
        Integer count = getCountByUid(address.getUid());
        address.setIsDefault(count == 0 ? 1 : 0);
        address.setRecvDistrict(getDistrictByCode(address.getRecvProvince(), address.getRecvCity(), address.getRecvArea()));
        //address.setRecvDistrict(generateAddressDistrictinfo(address.getRecvProvince(), address.getRecvCity(), address.getRecvArea()));
        Address result = insert(address);
        return result;
    }

    @Override
    public Address insetInto(Address address) {
        Address result = insert(address);
        return result;
    }


    private Address insert(Address address){
        Integer rows = addressMapper.insert(address);
        if (rows !=1){
            throw new InsertDataException("插入的收货地址有误");
        }else {
            return address;
        }
    }
    /**
     * @Author 朱燕冰
     * @Description: //TODO 根据ID获取用户收货地址数据的数量
     * @Date 下午10:36 2019/12/11
     * @Param [uid]
     * @return java.lang.Integer
     **/
    private Integer getCountByUid(Integer uid){
        return addressMapper.getCountByUid(uid);
    }
    /**
     * @Author 朱燕冰
     * @Description: //TODO 获取收货人地址的省市区
     * @Date 下午11:01 2019/12/11
     * @Param [provinceCode, cityCode, areaCode]
     * @return java.lang.String
     **/
    private String getDistrictByCode(String Province,String City,String Area){
        String provinceName = districtService.getDistrictCode(Province).getName();
        System.out.println(provinceName);
        String cityName = districtService.getDistrictCode(City).getName();
        String areaName = districtService.getDistrictCode(Area).getName();
        StringBuffer str = new StringBuffer();
        str.append(provinceName);
        str.append(",");
        str.append(cityName);
        str.append(",");
        str.append(areaName);
        return str.toString();
    }
    public void generateAddressDistrictinfo(Address address) {
        District provinceName = districtService.getDistrictCode(address.getRecvProvince());
        District cityName = districtService.getDistrictCode(address.getRecvProvince());
        District areaName = districtService.getDistrictCode(address.getRecvProvince());
        String district =
                provinceName + "," +
                        cityName + "," +
                        areaName + ",";

        address.setRecvDistrict(district);
    }
    
}
