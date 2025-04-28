package com.ssg.my_wms2.Controller;

import com.ssg.my_wms2.Service.InboundService;
import com.ssg.my_wms2.Service.InventoryService;
import com.ssg.my_wms2.Service.OutboundService;
import com.ssg.my_wms2.Service.WarehouseService;
import com.ssg.my_wms2.dto.InboundDTO;
import com.ssg.my_wms2.dto.InventoryDTO;
import com.ssg.my_wms2.dto.OutboundDTO;
import com.ssg.my_wms2.dto.WarehouseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class OutboundController {

    private final OutboundService outboundService;
    private final InventoryService inventoryService;
    private final WarehouseService warehouseService;


    @GetMapping("/outbound/admin")
    public String outbound_selectAll_C(Model model) {// 총관리자가 전체 회원 출고 데이터를 본다
        log.info("list1.........................check");
        List<OutboundDTO> dtoList = outboundService.outbound_selectAll_S(); //리스트로 해야 한다.
        log.info(dtoList.toString());
        model.addAttribute("dtoList2", dtoList);
        return "todo/outbound_admin";
    }

    @PostMapping("/outbound/request")
    public String outbound_request_C( //회원이 출고 요청
            @RequestParam("product_id") String productId,
            @RequestParam("outbound_quantity") int outboundQuantity,
            @RequestParam("warehouse_id") String warehouseId, HttpSession session,Model model) {
        String loginId = (String) session.getAttribute("login_id");

        OutboundDTO dto = OutboundDTO.builder()
                .product_id(productId)
                .outbound_quantity(outboundQuantity)
                .warehouse_id(warehouseId)
                .req_outbound_day(LocalDate.now()) // 오늘 날짜 자동 입력
                .status("미승인") // 기본 상태
                .userid(loginId)
                .build();
        try {
            outboundService.outbound_request_S(dto);  // ⬅️ 서비스 호출

            return "redirect:/todo/outbound"; // 리스트 페이지로 이동
        } catch (IllegalArgumentException e) {
            model.addAttribute("error",e.getMessage());
            List<InventoryDTO> dtoList = inventoryService.inventory_selectByUser_S(loginId);
            model.addAttribute("dtoList3", dtoList);
            return "todo/outbound_request_form";
        }


    }

    @GetMapping("/outbound/requestForm")
    public String outbound_request_form_C(HttpSession session,Model model) {
        //출고요청 홈페이지 이동
        String loginId = (String) session.getAttribute("login_id");
        List<InventoryDTO> dtoList = inventoryService.inventory_selectByUser_S(loginId);
        model.addAttribute("dtoList3", dtoList);
        return "/todo/outbound_request_form"; // /WEB-INF/views/todo/inbound_request_form.jsp
    }

    @GetMapping("/outbound")
    public String outbound_listByUser(HttpSession session,Model model) {//회원용 출고 데이터 목록
        String loginId = (String) session.getAttribute("login_id");
        log.info("loginId:" + loginId);
        List<OutboundDTO> list = outboundService.outbound_selectByUser_S(loginId);
        model.addAttribute("dtoList2", list);
        return "todo/outbound";
    }

    @GetMapping("/outbound/approveForm")
    public String outbound_approve_form_C(Model model) {
        List<OutboundDTO> dtoList = outboundService.outbound_selectAll_S();
        model.addAttribute("dtoList2", dtoList);
        return "/todo/outbound_approve_form"; // /WEB-INF/views/todo/inbound_approve_form.jsp
    }

    @PostMapping("/outbound/update")
    public String outbound_approve_C(@RequestParam("outbound_id") int outboundId,Model model) {

        try {
            outboundService.outbound_approve_S(outboundId);
            return "redirect:/todo/outbound/approveForm";
        }catch (IllegalArgumentException e){
            model.addAttribute("error",e.getMessage());

            List<OutboundDTO> dtoList = outboundService.outbound_selectAll_S();
            model.addAttribute("dtoList2", dtoList);
            return "todo/outbound_approve_form";

        }




        //outboundService.outbound_approve_S(outboundId);

       // return "redirect:/todo/outbound";
    }

    @GetMapping("/outbound/admin/searchWarehouse")
    public String outbound_SearchByWarehouse_C(
            @RequestParam("warehouseName") String warehouseName,
            Model model) {

        log.info("출고 목록 검색 조회 - 창고명: {}", warehouseName);

        if (warehouseName == null || warehouseName.trim().isEmpty()) {
            return "redirect:/todo/outbound/admin"; // 창고명 없으면 전체 조회 페이지로 리다이렉트
        }

        List<OutboundDTO> dtoList = outboundService.outbound_SearchByWarehouse_S(warehouseName);
        model.addAttribute("dtoList2", dtoList);
        model.addAttribute("warehouseName", warehouseName); // 검색어 유지용
        return "/todo/outbound_admin"; // 관리자 입고 목록 페이지
    }



    @GetMapping("/outbound/admin/searchDate")
    public String outbound_SearchByDate_C(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            Model model) {

        log.info("출고 목록 검색 조회 - 요청일 기간: {} ~ {}", startDate, endDate);

        if (startDate == null || endDate == null || startDate.isEmpty() || endDate.isEmpty()) {
            return "redirect:/todo/outbound/admin"; // 둘 중 하나라도 없으면 전체 조회
        }

        List<OutboundDTO> dtoList = outboundService.outbound_SearchByDate_S(startDate, endDate);
        model.addAttribute("dtoList2", dtoList);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "/todo/outbound_admin"; // 관리자 입고 목록 페이지
    }

    @GetMapping("/searchWarehouse")
    public String searchWarehousePage(Model model) {
        List<WarehouseDTO> warehouseList = warehouseService.getAllWarehouse();
        model.addAttribute("warehouseList", warehouseList);
        return "/todo/outbound/searchWarehouse";

    }







}
