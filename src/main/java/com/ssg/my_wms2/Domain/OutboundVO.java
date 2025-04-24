package com.ssg.my_wms2.Domain;

import java.time.LocalDate;

public class OutboundVO {

    private int outbound_id;
    private String product_id;
    private int outbound_quantity;
    private LocalDate req_outbound_day;
    private String warehouse_id;
    //private String warehouse_location; // ✅ 추가: 조인해서 보여줄 필드
    private String status;
    private String userid;

}
