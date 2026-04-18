package com.cauanlagrotta.order_platform_challange.dto;

import java.util.List;
import java.util.Map;

public record ListResponse<T>(Map<String, Object> summary,
                              List<T> data,
                              PaginationResponse pagination) {}
