package com.PelManagement.management.implementation;

import com.PelManagement.management.entity.RequestItem;
import com.PelManagement.management.repositories.RequestItemRepository;
import com.PelManagement.management.services.RequestItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestItemServiceImpl implements RequestItemService {

    @Autowired
    private RequestItemRepository requestItemRepository;

    @Override
    public RequestItem addRequestItem(RequestItem requestItem) {
        return requestItemRepository.save(requestItem);
    }

    @Override
    public Optional<RequestItem> getRequestItemById(Long id) {
        return requestItemRepository.findById(id);
    }

    @Override
    public List<RequestItem> getItemsByRequestId(Long requestId) {
        return requestItemRepository.findByRequestId(requestId);
    }

    @Override
    public RequestItem updateRequestItem(Long id, RequestItem itemDetails) {
        RequestItem requestItem = requestItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request Item not found with id: " + id));

        requestItem.setRequestId(itemDetails.getRequestId());
        requestItem.setResourceType(itemDetails.getResourceType());
        requestItem.setQuantity(itemDetails.getQuantity());
        requestItem.setDescription(itemDetails.getDescription());

        return requestItemRepository.save(requestItem);
    }

    @Override
    public void deleteRequestItem(Long id) {
        requestItemRepository.deleteById(id);
    }
}