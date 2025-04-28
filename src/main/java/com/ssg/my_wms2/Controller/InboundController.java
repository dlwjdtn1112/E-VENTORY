package com.ssg.my_wms2.Controller;
import com.ssg.my_wms2.Domain.InboundVO;
import com.ssg.my_wms2.Service.InboundService;
import com.ssg.my_wms2.Service.InventoryService;
import com.ssg.my_wms2.dto.InboundDTO;
import com.ssg.my_wms2.dto.InventoryDTO;
import com.ssg.my_wms2.mapper.InboundMapper;
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
public class InboundController {
    private final InboundService inboundService;
    private final InventoryService inventoryService;

    @GetMapping("/inbound")
    public String listByUser(HttpSession session,Model model) {
        String loginId = (String) session.getAttribute("login_id");
        log.info("loginId:" + loginId);
        List<InboundDTO> list = inboundService.inbound_selectByUser_S(loginId);
        model.addAttribute("dtoList1", list);
        return "todo/inbound";
    }

    @GetMapping("/inbound/admin")
    public String inbound_selectAll_C(Model model) {
        log.info("list1.........................check");
        List<InboundDTO> dtoList = inboundService.inbound_selectAll_S(); //리스트로 해야 한다.
        log.info(dtoList.toString());
        model.addAttribute("dtoList1", dtoList);
        return "todo/inbound_admin";
    }

    @GetMapping("/inbound/search")
    public String inbound_SearchByProduct_S(@RequestParam("keyword") String keyword, Model model) {
        log.info("입고 목록 검색 조회 - 상품명: {}", keyword);

        // ✅ 여기가 핵심! 빈 문자열 방지
        if (keyword == null || keyword.trim().isEmpty()) {
            return "redirect:/todo/inbound";
        }

        List<InboundDTO> dtoList = inboundService.inbound_SearchByProduct_S(keyword);
        model.addAttribute("dtoList1", dtoList);
        model.addAttribute("keyword", keyword);
        return "/todo/inbound";
    }

    @PostMapping("/inbound/request")
    public String inbound_request_C(
            @RequestParam("product_id") String productId,
            @RequestParam("inbound_quantity") int inboundQuantity,
            @RequestParam("warehouse_id") String warehouseId,HttpSession session,Model model) {
        String loginId = (String) session.getAttribute("login_id");

        InboundDTO dto = InboundDTO.builder()
                .product_id(productId)
                .inbound_quantity(inboundQuantity)
                .warehouse_id(warehouseId)
                .req_inbound_day(LocalDate.now()) // 오늘 날짜 자동 입력
                .status("미승인") // 기본 상태
                .userid(loginId)
                .build();

        inboundService.inbound_request_S(dto);
        // ⬅️ 서비스 호출


        return "redirect:/todo/inbound"; // 리스트 페이지로 이동

    }
//    @GetMapping("/inbound/request_form")
//    public String showInboundRequestForm(Model model) {
//        List<InventoryDTO> dtoList = inventoryService.inventory_selectAll_S(); // 재고 목록 불러오기
//        log.info("재고 목록 출력: {}", dtoList);
//        model.addAttribute("dtoList3", dtoList);  // jsp에서 forEach 돌릴 리스트
//        return "/todo/inbound_request_form";
//    }
    @PostMapping("/inbound/update")
    public String inbound_approve_C(@RequestParam("inbound_id") int inboundId,Model model) {
        try {
            inboundService.inbound_approve_S(inboundId);
            return "redirect:/todo/inbound/approveForm";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error",e.getMessage());

            List<InboundDTO> dtoList = inboundService.inbound_selectAll_S();
            model.addAttribute("dtoList1", dtoList);

            return "todo/inbound_approve_form";
        }




       // return "redirect:/todo/inbound";
//        return "/todo/inbound_approve_form";
    }

    @GetMapping("/inbound/requestForm")
    public String inbound_request_form_C(HttpSession session,Model model) {
        String loginId = (String) session.getAttribute("login_id");

//        List<InventoryDTO> dtoList = inventoryService.inventory_selectByUser_S(loginId);
//        log.info("재고 목록 출력: {}", dtoList);
        List<InboundDTO> dtoList = inboundService.inbound_selectByUser_S(loginId);
        model.addAttribute("dtoList1", dtoList);

        return "/todo/inbound_request_form"; // /WEB-INF/views/todo/inbound_request_form.jsp
    }

    // 입고 승인 입력폼 페이지로 이동
    @GetMapping("/inbound/approveForm")
    public String inbound_approve_form_C(Model model) {

        List<InboundDTO> dtoList = inboundService.inbound_selectAll_S(); //리스트로 해야 한다.
        log.info(dtoList.toString());
        model.addAttribute("dtoList1", dtoList);
        return "/todo/inbound_approve_form"; // /WEB-INF/views/todo/inbound_approve_form.jsp
    }

    @GetMapping("/inbound/admin/searchWarehouse")
    public String inbound_SearchByWarehouse_C(
            @RequestParam("warehouseName") String warehouseName,
            Model model) {

        log.info("입고 목록 검색 조회 - 창고명: {}", warehouseName);

        if (warehouseName == null || warehouseName.trim().isEmpty()) {
            return "redirect:/todo/inbound/admin"; // 창고명 없으면 전체 조회 페이지로 리다이렉트
        }

        List<InboundDTO> dtoList = inboundService.inbound_SearchByWarehouse_S(warehouseName);
        model.addAttribute("dtoList1", dtoList);
        model.addAttribute("warehouseName", warehouseName); // 검색어 유지용
        return "/todo/inbound_admin"; // 관리자 입고 목록 페이지
    }


    @GetMapping("/inbound/admin/searchDate")
    public String inbound_SearchByDate_C(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            Model model) {

        log.info("입고 목록 검색 조회 - 요청일 기간: {} ~ {}", startDate, endDate);

        if (startDate == null || endDate == null || startDate.isEmpty() || endDate.isEmpty()) {
            return "redirect:/todo/inbound/admin"; // 둘 중 하나라도 없으면 전체 조회
        }

        List<InboundDTO> dtoList = inboundService.inbound_SearchByDate_S(startDate, endDate);
        model.addAttribute("dtoList1", dtoList);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        return "/todo/inbound_admin"; // 관리자 입고 목록 페이지
    }




}
