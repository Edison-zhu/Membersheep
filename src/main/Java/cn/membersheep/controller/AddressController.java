package cn.membersheep.controller;

import cn.membersheep.entity.Address;
import cn.membersheep.entity.ResponseResult;
import cn.membersheep.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/address")
public class AddressController extends BaseController{
    @Autowired
    private AddressService addressService;
    @RequestMapping(value = "/addnew.do",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<Void>handleAddNew(Address address, HttpSession session){
        Integer uid = getUidFromSession(session);
        address.setUid(uid);
        addressService.addnew(address);
        return new ResponseResult<Void>();
    }
    @RequestMapping(value = "/add.do",method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<Void>handleAddNew(Address address){
        addressService.insetInto(address);
        return new ResponseResult<Void>();
    }
}
