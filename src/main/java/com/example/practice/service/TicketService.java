package com.example.practice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.practice.domain.Ticket;
import com.example.practice.domain.dto.TicketDTO;

/**
* @author 刘德意
* @description 针对表【ticket(不合格票表)】的数据库操作Service
* @createDate 2022-08-27 23:57:28
*/
public interface TicketService extends IService<Ticket> {

    /**
     * 根据ID 不合格等级获取不合格票信息
     * @param ticketDTO 传递参数信息
     * @return result
     */
    Ticket getTicket(TicketDTO ticketDTO);
}
