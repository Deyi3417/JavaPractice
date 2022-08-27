package com.example.practice.controller.ticket;

import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ErrorCode;
import com.example.practice.common.ajax.ResultUtils;
import com.example.practice.domain.Ticket;
import com.example.practice.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 票控制器
 *
 * @author 刘德意
 * @date 2022/8/28
 */
@RestController
@Slf4j
@RequestMapping("/ticket")
public class TicketController {

    @Resource
    private TicketService ticketService;

    @GetMapping("/list")
    public BasicResponse<List<Ticket>> list() {
        return ResultUtils.success(ticketService.list());
    }

    @GetMapping("/getTicketById")
    public BasicResponse<Ticket> getById(Long id) {
        Ticket ticket = ticketService.getById(id);
        if (ticket == null) {
            return ResultUtils.error(ErrorCode.NO_OBTAIN_DATA);
        }
        return ResultUtils.success(ticket);
    }

}
