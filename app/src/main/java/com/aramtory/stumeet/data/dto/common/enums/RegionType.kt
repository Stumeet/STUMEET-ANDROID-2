package com.aramtory.stumeet.data.dto.common.enums

enum class RegionType(private val region: String) {
    SEOUL("서울"),
    INCHEON_GYEONGGI("인천/경기"),
    JEONBUK("전북"),
    JEONNAM("전남"),
    GANGWON("강원"),
    GYEONGBUK("경북"),
    GYEONGNAM("경남"),
    CHUNGBUK("충북"),
    CHUNGNAM("충남"),
    JEJU("제주"),
    OVERSEAS("해외");

    fun getRegion(regionType: RegionType): String {
        return regionType.region
    }
}
