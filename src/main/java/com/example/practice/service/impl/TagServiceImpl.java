package com.example.practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.practice.domain.Tag;
import com.example.practice.service.TagService;
import com.example.practice.mapper.TagMapper;
import org.springframework.stereotype.Service;

/**
* @author 刘德意
* @description 针对表【tag(标签)】的数据库操作Service实现
* @createDate 2023-02-28 23:27:37
*/
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag>
    implements TagService{

}




