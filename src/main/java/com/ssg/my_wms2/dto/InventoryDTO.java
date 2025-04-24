package com.ssg.my_wms2.dto;

import lombok.*;

import java.time.LocalDateTime;
@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDTO {
    
    private String warehouse_id;
    private String product_id;
    private int quantity;
    private LocalDateTime updated_at;
    private String userid;

}
