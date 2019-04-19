* 获取商品类别接口：/merchantCommon/getAllProductCla
* 根据商品类别获取商品列表接口：/merchantVue/getProductByclaid/{claid}/{type}
    * claid：商品类型id
    * type：全部 -> ALL 活动 -> ACTION
    * 传参：json类型 键值为 params：{pageIndex：页码，pageSize：数据量}
* 获取商品页接口：/merchantMob/merchantProductMob/getProductByID/{id}
    * id：商品id