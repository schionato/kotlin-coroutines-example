package com.example.coroutines.infra.adapter.ou.rest

import feign.Headers

@Headers(
    "Cache-Control: no-cache, no-store, must-revalidate",
    "Pragma: no-cache",
    "Expires: 0"
)
annotation class DisableCache