package com.mercadolibre.bootcampmelifrescos.service.impl;

import com.mercadolibre.bootcampmelifrescos.dtos.WarehouseBatchDTO;
import com.mercadolibre.bootcampmelifrescos.dtos.response.WarehouseBatchResponse;
import com.mercadolibre.bootcampmelifrescos.exceptions.NotFoundApiException;
import com.mercadolibre.bootcampmelifrescos.model.Batch;
import com.mercadolibre.bootcampmelifrescos.repository.BatchRepository;
import com.mercadolibre.bootcampmelifrescos.repository.ProductRepository;
import com.mercadolibre.bootcampmelifrescos.service.WarehouseBatchService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WarehouseBatchServiceImpl implements WarehouseBatchService {
    private final BatchRepository batchRepository;
    private final ProductRepository productRepository;

    @Override
    public WarehouseBatchResponse getWarehouseBatchQuantityByProduct(Long productId) throws Exception {
        List<WarehouseBatchDTO> warehouseBatchDTOList = new ArrayList<>();

        if(productRepository.findById(productId).isEmpty())
            throw new NotFoundApiException("Product not found");
        List<Batch> batchList = batchRepository.findAllByProductOrderByInboundOrder(productRepository.findById(productId).get());

        for (Batch batch : batchList){
            WarehouseBatchDTO warehouseBatchDTO = new WarehouseBatchDTO(batch.getWarehouseId(), batch.getCurrentQuantity());
            warehouseBatchDTOList.add(warehouseBatchDTO);
            if(warehouseBatchDTOList.stream().anyMatch(code -> code.getWarehouseCode().equals(batch.getWarehouseId()))){
                verifyBatchesWithSameProductAndReturnsTotalProductQuantity(warehouseBatchDTOList);
            }
        }
        return new WarehouseBatchResponse(productId, warehouseBatchDTOList);
    }

    private void verifyBatchesWithSameProductAndReturnsTotalProductQuantity(List<WarehouseBatchDTO> warehouseBatchDTOList){
        for(int i=1;i<warehouseBatchDTOList.size(); i++){
            if(warehouseBatchDTOList.get(i).getWarehouseCode().equals(warehouseBatchDTOList.get(i - 1).getWarehouseCode())){
                warehouseBatchDTOList.get(i-1).setTotalQuantity(warehouseBatchDTOList.get(i-1).getTotalQuantity()+warehouseBatchDTOList.get(i).getTotalQuantity());
                warehouseBatchDTOList.remove(warehouseBatchDTOList.get(i));
            }
        }
    }
}
