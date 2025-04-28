package com.ssg.my_wms2.dto;

import lombok.*;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDTO {

    private String warehouse_id;

    private String warehouse_location;
}
