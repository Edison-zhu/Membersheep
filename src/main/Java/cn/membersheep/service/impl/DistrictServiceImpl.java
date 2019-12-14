package cn.membersheep.service.impl;

import cn.membersheep.entity.District;
import cn.membersheep.mappers.DistrictMapper;
import cn.membersheep.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    DistrictMapper districtMapper;
    @Override
    public District getDistrictCode(String code) {
        District district = findDis(code);
        return district;
    }

    @Override
    public List<District> getList(String parent) {
         return districtMapper.getList(parent);
    }

    public District findDis(String code){
        return districtMapper.findDistrictByCode(code);
    }
}
