package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.request.GetDashBoardDTO;


import com.mercadolibre.bootcampmelifrescos.dtos.response.DashboardDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.MostSoldProduct;
import com.mercadolibre.bootcampmelifrescos.model.PurchaseOrder;
import com.mercadolibre.bootcampmelifrescos.repository.PurchaseOrderRepository;
import com.mercadolibre.bootcampmelifrescos.repository.PurchaseProductsRepository;
import com.mercadolibre.bootcampmelifrescos.service.DashboardService;
import com.mercadolibre.bootcampmelifrescos.service.ProductService;
import com.mercadolibre.bootcampmelifrescos.service.PurchaseOrderService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;


@Data
@AllArgsConstructor
@Service
public class DashboardServiceImpl implements DashboardService {

    private final PurchaseOrderService purchaseOrderService;
    private final ProductService productService;
    private final PurchaseProductsRepository purchaseProductsRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;

    @Override
    public DashboardDTO getDashboardByPeriod(GetDashBoardDTO getDashBoardDTO) throws Exception {
        DashboardDTO response = new DashboardDTO();

        MostSoldProduct mostSoldProduct = productService.getMostSoldProduct(getDashBoardDTO.getStartDate(), getDashBoardDTO.getEndDate());
        List<Integer> purchaseOrdersId = purchaseOrderRepository.getAllPurchaseOrderByPeriod(getDashBoardDTO.getStartDate(), getDashBoardDTO.getEndDate());


        response.setStartDate(getDashBoardDTO.getStartDate());
        response.setEndDate(getDashBoardDTO.getEndDate());
        response.setMostSoldProduct(mostSoldProduct);
        response.setQuantitySales(purchaseOrdersId.size());

        return response;
    }
}
