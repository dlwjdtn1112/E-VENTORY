package com.ssg.my_wms2.Domain;

import lombok.Data;

import java.time.LocalDate;
@Data
public class InboundVO {
    private int inbound_id;
    private String product_id;
    private int inbound_quantity;
    private LocalDate req_inbound_day;
    private String warehouse_id;
    //private String warehouse_location; // ✅ 추가: 조인해서 보여줄 필드
    private String status;
    private String userid;
}

