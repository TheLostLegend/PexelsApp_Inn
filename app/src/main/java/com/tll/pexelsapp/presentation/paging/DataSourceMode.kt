package com.tll.pexelsapp.presentation.paging

sealed class DataSourceMode {
    class LIVEDATA : DataSourceMode()
    class RX : DataSourceMode()
}
