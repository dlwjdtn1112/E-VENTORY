package com.ssg.my_wms2.Controller;


import com.ssg.my_wms2.Service.InboundService;
import com.ssg.my_wms2.Service.InventoryService;
import com.ssg.my_wms2.dto.InboundDTO;
import com.ssg.my_wms2.dto.InventoryDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor

public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/inventory/admin")
    public String inventory_selectAll_C(Model model) {
        log.info("list1.........................check");
        List<InventoryDTO> dtoList = inventoryService.inventory_selectAll_S(); //리스트로 해야 한다.
        log.info(dtoList.toString());
        model.addAttribute("dtoList3", dtoList);
        return "/todo/inventory";
    }


    @GetMapping("/inventory")
    public String inventory_selectAllByUser_C(HttpSession session, Model model) {
        String loginId = (String) session.getAttribute("login_id");
        List<InventoryDTO> list = inventoryService.inventory_selectByUser_S(loginId);
        model.addAttribute("dtoList3", list);
        return "todo/inventory";
    }
}
