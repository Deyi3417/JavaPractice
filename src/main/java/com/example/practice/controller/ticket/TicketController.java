package com.example.practice.controller.ticket;

import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ErrorCode;
import com.example.practice.common.ajax.ResultUtils;
import com.example.practice.domain.Ticket;
import com.example.practice.domain.dto.TicketDTO;
import com.example.practice.service.TicketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 票控制器
 *
 * @author 刘德意
 * @date 2022/8/28
 */
@RestController
@Slf4j
@Api(tags = "用户中心项目不合格票控制器")
@RequestMapping("/ticket")
public class TicketController {

    @Resource
    private TicketService ticketService;

    @GetMapping("/list")
    @ApiOperation("获取不合格票列表")
    public BasicResponse<List<Ticket>> list() {
        return ResultUtils.success(ticketService.list());
    }

    @GetMapping("/getTicketById")
    @ApiOperation("根据ID获取不合格票")
    public BasicResponse<Ticket> getById(Long id) {
        Ticket ticket = ticketService.getById(id);
        if (ticket == null) {
            return ResultUtils.error(ErrorCode.NO_OBTAIN_DATA);
        }
        return ResultUtils.success(ticket);
    }

    @PostMapping("/level")
    @ApiOperation("根据ID，处理方式，等级获取Ticket")
    public BasicResponse<Ticket> level(@Valid @RequestBody TicketDTO ticketDTO) {
        Ticket ticket = ticketService.getTicket(ticketDTO);
        return ResultUtils.success(ticket);
    }



}
