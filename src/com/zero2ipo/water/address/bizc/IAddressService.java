package com.zero2ipo.water.address.bizc;
import com.zero2ipo.water.address.bo.AddressBo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
/**
 * Created by zhengyunfei on 2016/2/24.
 */
@Service
public interface IAddressService {
    public long add(AddressBo bo);
    public boolean update(AddressBo bo);
    public AddressBo updAddressInit(Map<String, Object> map) ;
    public boolean delete(String id);
    public List<AddressBo> findAllList(Map<String, Object> queryMap);
    public List<AddressBo> findAllList(Map<String, Object> queryMap, int skip, int max);
    public int getTotal(Map<String, Object> queryMap);
    public AddressBo findById(String id);
}
