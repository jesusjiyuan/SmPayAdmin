package com.zero2ipo.water.address.bizc.impl;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.water.address.bizc.IAddressService;
import com.zero2ipo.water.address.bo.AddressBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("addressService")
public class AddressServiceImpl implements IAddressService {
    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<AddressBo> findAllList(Map<String, Object> queryMap) {
        List<AddressBo> list = new ArrayList<AddressBo>();
        try {
            //设置数据库类型: 网站全局库(01)
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
            list = (List<AddressBo>)baseDao.findForList("findAddressInfoList", queryMap);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "queryAddressInfoList 查询列表失败",e);
        }
        return list;
    }

    @Override
    public AddressBo findById(String id) {
        AddressBo Address = null;
        try{
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("id",id);
            Address = (AddressBo)baseDao.findForObject("findAddressById", map);
        }catch(Exception e){
            BaseLog.e(this.getClass(), "updInatitutionsInit 修改联系人前查询", e);
            throw new BaseException("修改联系人前查询出错！",e);
        }
        return Address;
    }

    @Autowired @Qualifier("baseDao")
    private IBaseDao baseDao;
    @Override
    public List<AddressBo> findAllList(Map<String, Object> map, int skip, int max) {
        List<AddressBo> list = new ArrayList<AddressBo>();
        try {
            //设置数据库类型: 网站全局库(01)
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
            list = (List<AddressBo>)baseDao.findForList("findAddressInfoList", map,skip,max);
        } catch (Exception e) {
            BaseLog.e(this.getClass(), "queryAddressInfoList 查询列表失败",e);
        }
        return list;
    }

    @Override
    public long add(AddressBo Address) {
        SqlMapClient client =  baseDao.getSqlMapClient();
        long id=0;
        try{
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
             id=(Long)client.insert("addAddress", Address);
            client.executeBatch();//执行批处理模式
        }catch(Exception e){
            e.printStackTrace();
            BaseLog.e(this.getClass(), "addAddress 添加联系人", e);
            throw new BaseException("添加联系人出错！",e);
        }
        return id;
    }

    @SuppressWarnings("unchecked")
    public String delAddressById(String AddressIds) {
        String backInfo = "0";
        try{
            Map map = new HashMap();
            map.put("id",AddressIds.split(","));
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            baseDao.delObject("delAddress", map);
            //删除成功
            backInfo = "1";
        }catch(Exception e){
            backInfo = "0";	//删除失败
            BaseLog.e(this.getClass(), "delAddressById 删除联系人", e);
            throw new BaseException("删除联系人出错！",e);
        }
        return backInfo;
    }

    @Override
    public AddressBo updAddressInit(Map<String, Object> map) {
        AddressBo Address = null;
        try{
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            Address = (AddressBo)baseDao.findForObject("findAddressById", map);
        }catch(Exception e){
            BaseLog.e(this.getClass(), "updInatitutionsInit 修改联系人前查询", e);
            throw new BaseException("修改联系人前查询出错！",e);
        }
        return Address;
    }

    @Override
    public boolean update(AddressBo Address) {
        SqlMapClient client =  baseDao.getSqlMapClient();
        boolean flg=false;
        try{
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
            client.update("upAddress", Address);
            flg = true;
        }catch(Exception e){
            e.getStackTrace();
            BaseLog.e(this.getClass(), "updAddress 修改联系人", e);
            throw new BaseException("修改联系人出错！",e);
        }
        return flg;
    }
    @Override
    public int getTotal(Map<String, Object> map) {
        int count=0;
        try {
            baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
            count = (Integer)baseDao.findForObject("queryAddressListCount", map);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

}
