package com.example.practice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.practice.domain.Ticket;
import com.example.practice.service.TicketService;
import com.example.practice.mapper.TicketMapper;
import org.springframework.stereotype.Service;

/**
* @author 刘德意
* @description 针对表【ticket(不合格票表)】的数据库操作Service实现
* @createDate 2022-08-27 23:57:28
*/
@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket>
    implements TicketService{

}




