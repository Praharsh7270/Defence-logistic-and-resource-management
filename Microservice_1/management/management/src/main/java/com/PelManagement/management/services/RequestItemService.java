package com.PelManagement.management.services;

import com.PelManagement.management.entity.RequestItem;
import java.util.List;
import java.util.Optional;

public interface RequestItemService {
    RequestItem addRequestItem(RequestItem requestItem);
    Optional<RequestItem> getRequestItemById(Long id);
    List<RequestItem> getItemsByRequestId(Long requestId);
    RequestItem updateRequestItem(Long id, RequestItem requestItem);
    void deleteRequestItem(Long id);
}