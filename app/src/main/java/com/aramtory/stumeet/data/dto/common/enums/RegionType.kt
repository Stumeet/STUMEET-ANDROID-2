package com.aramtory.stumeet.data.dto.common.enums

import com.aramtory.stumeet.R

enum class RegionType(private val regionResId: Int) {
    SEOUL(R.string.enum_region_seoul),
    INCHEON_GYEONGGI(R.string.enum_region_inchen_gyeonggi),
    JEONBUK(R.string.enum_region_jeonbuk),
    JEONNAM(R.string.enum_region_jeonnam),
    GANGWON(R.string.enum_region_gangwon),
    GYEONGBUK(R.string.enum_region_gyeongbuk),
    GYEONGNAM(R.string.enum_region_gyeongnam),
    CHUNGBUK(R.string.enum_region_chugbuk),
    CHUNGNAM(R.string.enum_region_chungnam),
    JEJU(R.string.enum_region_jeju),
    OVERSEAS(R.string.enum_region_overseas);

    fun getRegion(regionType: RegionType): Int {
        return regionType.regionResId
    }
}
