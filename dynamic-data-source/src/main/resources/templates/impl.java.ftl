package ${packageName}.impl;

import ${packageName}.service.${className}Service;
import ${packageName}.mapper.${className}Mapper;
import ${packageName}.entity.${className};
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ${className}ServiceImpl extends ServiceImpl<${className}Mapper, ${className}> implements ${className}Service{

}