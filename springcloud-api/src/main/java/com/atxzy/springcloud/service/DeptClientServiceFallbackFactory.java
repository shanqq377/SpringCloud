package com.atxzy.springcloud.service;

import com.atxzy.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;


//降级
@Component("deptClientServiceFallbackFactory")
public class DeptClientServiceFallbackFactory  implements FallbackFactory {
    @Override
    public IDeptClientService create(Throwable throwable) {
        return new IDeptClientService() {
            @Override
            public Dept queryById(Long id) {
                return new Dept().setDeptno(id)
                        .setDname("id=>"+id+"没有对应的信息！客户端提供了降级的信息，这个服务现在已经被关闭")
                        .setDbSource("没有数据~");
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }

            @Override
            public boolean addDept(Dept dept) {
                return false;
            }
        };
    }
}
